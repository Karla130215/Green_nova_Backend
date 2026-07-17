/**
 * Green-Nova - Inicio de Sesión
 * Autenticación contra el backend (POST /api/login).
 *
 * El backend compara la contraseña con el hash BCrypt almacenado y, si es
 * correcta, devuelve un token JWT que se guarda en sessionStorage.
 */

// URL base de la API. Vacio = mismo origen (el frontend lo sirve el propio
// Spring Boot). Si el frontend se sirve desde otro host/puerto, coloca aqui la
// URL del backend, por ejemplo: "http://localhost:8080".
const API_BASE = "";

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

    contenedorAlertas.innerHTML = `
        <div class="alert alert-${tipo} alert-dismissible fade show" role="alert">
            ${mensaje}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    `;
}

// Función para procesar y validar el acceso de un usuario contra el backend
async function autenticarUsuario(event) {
    event.preventDefault(); // Evita la recarga automática de la página

    // Capturar selectores del DOM
    const emailInput = document.getElementById("login-email");
    const passwordInput = document.getElementById("login-password");

    if (!emailInput || !passwordInput) return;

    const emailValue = emailInput.value.trim();
    const passwordValue = passwordInput.value;

    // Validación de campos obligatorios vacíos
    if (emailValue === "" || passwordValue === "") {
        mostrarAlerta("Todos los campos marcados con asterisco (*) son obligatorios.", "danger");
        return;
    }

    // El cuerpo usa "email" y "pass" para coincidir con el DTO LoginRequest del backend.
    const credenciales = {
        email: emailValue,
        pass: passwordValue
    };

    try {
        const respuesta = await fetch(`${API_BASE}/api/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(credenciales)
        });

        // El backend responde 200 con { token } si las credenciales son correctas.
        // Si son incorrectas responde con un error (no-2xx).
        if (!respuesta.ok) {
            mostrarAlerta("El correo electrónico o la contraseña son incorrectos.", "danger");
            return;
        }

        const datos = await respuesta.json(); // { token: "..." }

        // Guarda el token JWT y los datos de sesión para usarlos en el resto del sitio.
        sessionStorage.setItem("token", datos.token);
        sessionStorage.setItem("sesionActiva", JSON.stringify({ email: emailValue }));

        mostrarAlerta("¡Inicio de sesión exitoso! Redirigiendo...", "success");

        // Redirección automática tras 1.5 segundos
        setTimeout(() => {
            window.location.href = "../index.html";
        }, 1500);
    } catch (error) {
        mostrarAlerta("No se pudo conectar con el servidor. Verifica que el backend esté en ejecución.", "danger");
    }
}

// Asegurar el montaje de los escuchas de eventos una vez que el DOM esté completamente listo
document.addEventListener("DOMContentLoaded", () => {
    const formularioLogin = document.getElementById("login-form");

    if (formularioLogin) {
        formularioLogin.addEventListener("submit", autenticarUsuario);
    } else {
        console.error("Error: No se pudo encontrar el elemento con ID 'login-form'.");
    }
});
