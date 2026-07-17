/**
 * Validaciones y autenticación del lado del cliente 
 */

// Función para mostrar alertas dinámicas usando Bootstrap 
function mostrarAlerta(mensaje, tipo) {
    let contenedorAlertas = document.getElementById("alertas");

    if (!contenedorAlertas) { 
        contenedorAlertas = document.createElement("div"); 
        contenedorAlertas.id = "alertas"; 
        
        const formulario = document.querySelector(".form-box"); 
        if (formulario) {
            formulario.parentNode.insertBefore(contenedorAlertas, formulario); 
        } else {
            document.body.appendChild(contenedorAlertas);
        }
    }

    // Corregido: Se eliminaron las barras inversas innecesarias en data-bs-dismiss
    contenedorAlertas.innerHTML = `
        <div class="alert alert-${tipo} alert-dismissible fade show" role="alert">
            ${mensaje}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    `; 
}

// Función para procesar y validar el acceso de un usuario
function autenticarUsuario(event) {
    event.preventDefault(); // Evita la recarga automática de la página

    // Capturar selectores del DOM
    const emailInput = document.getElementById("login-email");
    const passwordInput = document.getElementById("login-password");

    if (!emailInput || !passwordInput) return;

    const emailValue = emailInput.value.trim();
    const passwordValue = passwordInput.value;

    // 1. Validación de campos obligatorios vacíos
    if (emailValue === "" || passwordValue === "") {
        mostrarAlerta("Todos los campos marcados con asterisco (*) son obligatorios.", "danger");
        return;
    }

    // 2. Recuperar la colección de usuarios registrados desde localStorage
    const usuarios = JSON.parse(localStorage.getItem("usuariosRegistrados")) || [];

    // 3. Buscar correspondencia de credenciales
    const usuarioValido = usuarios.find(user => user.email === emailValue && user.password === passwordValue);

    if (usuarioValido) {
        sessionStorage.setItem("sesionActiva", JSON.stringify({
            nombre: usuarioValido.nombre,
            email: usuarioValido.email
        }));

        mostrarAlerta("¡Inicio de sesión exitoso! Redirigiendo...", "success");

        // 4. Redirección automática tras 1.5 segundos
        setTimeout(() => {
            window.location.href = "../index.html";
        }, 1500);

    } else {
        // Ahora esta alerta se ejecutará sin romperse por errores previos
        mostrarAlerta("El correo electrónico o la contraseña son incorrectos.", "danger");
    }
}

// Asegurar el montaje de los escuchas de eventos una vez que el DOM esté completamente listo
document.addEventListener("DOMContentLoaded", () => {
    const formularioLogin = document.getElementById("login-form");
    
    if (formularioLogin) {
        formularioLogin.addEventListener("submit", autenticarUsuario);
        console.log("Formulario de login detectado y vinculado correctamente.");
    } else {
        console.error("Error: No se pudo encontrar el elemento con ID 'login-form'.");
    }
});