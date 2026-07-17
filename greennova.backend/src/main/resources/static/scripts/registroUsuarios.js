/**
 * Green-Nova - Registro de Usuarios
 * Validaciones del formulario de registro y alta contra el backend.
 *
 * El registro se envia al backend (POST /api/usuarios/). El backend cifra la
 * contraseña con BCrypt antes de guardarla, por lo que aqui NUNCA se almacena
 * la contraseña en texto plano ni en localStorage.
 */

// URL base de la API. Vacio = mismo origen (el frontend lo sirve el propio
// Spring Boot). Si el frontend se sirve desde otro host/puerto, coloca aqui la
// URL del backend, por ejemplo: "http://localhost:8080".
const API_BASE = "";

// Función para mostrar alertas con Bootstrap
function mostrarAlerta(mensaje, tipo) {
    // Busca si ya existe un contenedor de alertas, si no lo crea arriba del formulario
    let contenedorAlertas = document.getElementById("alertas");

    if (!contenedorAlertas) { // Si no existe el div de alertas
        contenedorAlertas = document.createElement("div"); // Crea un nuevo div
        contenedorAlertas.id = "alertas"; // Le asigna el id "alertas"
        const formulario = document.querySelector(".form-box"); // Obtiene el formulario
        formulario.parentNode.insertBefore(contenedorAlertas, formulario); // Inserta el div antes del formulario
    }

    contenedorAlertas.innerHTML = `
        <div class="alert alert-${tipo} alert-dismissible fade show" role="alert">
            ${mensaje}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    `; // Inserta una alerta Bootstrap dentro del div
}

// Función para validar que el nombre completo sea correcto
function validarNombre(nombre) {
    if (nombre === "") { // Valida que no esté vacío
        mostrarAlerta("El nombre completo es obligatorio.", "danger"); // Muestra alerta de error
        return false; // Retorna falso para detener el registro
    }

    if (nombre.length < 3) { // Valida que tenga al menos 3 caracteres
        mostrarAlerta("El nombre debe tener al menos 3 caracteres.", "danger"); // Muestra alerta de error
        return false; // Retorna falso
    }

    // Valida que solo contenga letras y espacios (incluyendo acentos y ñ)
    const regexNombre = /^[a-záéíóúüñA-ZÁÉÍÓÚÜÑ\s]+$/;
    if (!regexNombre.test(nombre)) { // Si el nombre contiene caracteres no permitidos
        mostrarAlerta("El nombre solo puede contener letras y espacios.", "danger"); // Muestra alerta de error
        return false; // Retorna falso
    }

    return true; // Si pasa todas las validaciones retorna verdadero
}

// Función para validar el número de teléfono
function validarTelefono(telefono) {
    if (telefono === "") { // Valida que no esté vacío
        mostrarAlerta("El número de teléfono es obligatorio.", "danger"); // Muestra alerta de error
        return false; // Retorna falso
    }

    // Valida que solo contenga dígitos
    const regexTelefono = /^\d+$/;
    if (!regexTelefono.test(telefono)) { // Si contiene caracteres que no son números
        mostrarAlerta("El teléfono solo debe contener números.", "danger"); // Muestra alerta de error
        return false; // Retorna falso
    }

    if (telefono.length !== 10) { // Valida que tenga exactamente 10 dígitos
        mostrarAlerta("El teléfono debe tener exactamente 10 dígitos.", "danger"); // Muestra alerta de error
        return false; // Retorna falso
    }

    return true; // Si pasa todas las validaciones retorna verdadero
}

// Función para validar el email
function validarEmail(email) {
    if (email === "") { // Valida que no esté vacío
        mostrarAlerta("El email es obligatorio.", "danger"); // Muestra alerta de error
        return false; // Retorna falso
    }

    // Expresión regular para validar formato de email
    const regexEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!regexEmail.test(email)) { // Si el formato del email no es válido
        mostrarAlerta("Ingresa un email válido (ej. usuario@email.com).", "danger"); // Muestra alerta de error
        return false; // Retorna falso
    }

    // La unicidad del email la valida el backend (findByEmail); aquí solo se
    // comprueba el formato.
    return true; // Si pasa todas las validaciones retorna verdadero
}

// Función para validar la contraseña
function validarPassword(password) {
    if (password === "") { // Valida que no esté vacía
        mostrarAlerta("La contraseña es obligatoria.", "danger"); // Muestra alerta de error
        return false; // Retorna falso
    }

    if (password.length < 8) { // Valida que tenga al menos 8 caracteres
        mostrarAlerta("La contraseña debe tener al menos 8 caracteres.", "danger"); // Muestra alerta de error
        return false; // Retorna falso
    }

    // Valida que contenga al menos una letra mayúscula
    if (!/[A-Z]/.test(password)) {
        mostrarAlerta("La contraseña debe contener al menos una letra mayúscula.", "danger"); // Muestra alerta de error
        return false; // Retorna falso
    }

    // Valida que contenga al menos una letra minúscula
    if (!/[a-z]/.test(password)) {
        mostrarAlerta("La contraseña debe contener al menos una letra minúscula.", "danger"); // Muestra alerta de error
        return false; // Retorna falso
    }

    // Valida que contenga al menos un número
    if (!/[0-9]/.test(password)) {
        mostrarAlerta("La contraseña debe contener al menos un número.", "danger"); // Muestra alerta de error
        return false; // Retorna falso
    }

    return true; // Si pasa todas las validaciones retorna verdadero
}

// Función principal que registra al usuario contra el backend
async function registrarUsuario(event) {
    event.preventDefault(); // Evita que el formulario se envíe y recargue la página

    // Obtiene los valores de cada campo del formulario
    const nombre = document.getElementById("nombre").value.trim(); // Obtiene el nombre y elimina espacios
    const telefono = document.getElementById("telefono").value.trim(); // Obtiene el teléfono y elimina espacios
    const email = document.getElementById("email").value.trim(); // Obtiene el email y elimina espacios
    const password = document.getElementById("password").value; // Obtiene la contraseña (sin trim para respetar espacios intencionales)

    // Ejecuta todas las validaciones en orden
    if (!validarNombre(nombre)) return; // Si el nombre no es válido, detiene la función
    if (!validarTelefono(telefono)) return; // Si el teléfono no es válido, detiene la función
    if (!validarEmail(email)) return; // Si el email no es válido, detiene la función
    if (!validarPassword(password)) return; // Si la contraseña no es válida, detiene la función

    // Objeto que espera el backend. El campo se llama "pass" para coincidir con
    // el modelo Usuario del backend.
    const usuario = {
        nombre: nombre,
        telefono: telefono,
        email: email,
        pass: password
    };

    try {
        const respuesta = await fetch(`${API_BASE}/api/usuarios/`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(usuario)
        });

        if (!respuesta.ok) { // Error del servidor (500, etc.)
            mostrarAlerta("Ocurrió un error en el servidor. Intenta más tarde.", "danger");
            return;
        }

        // El backend responde con el usuario creado (JSON) o con cuerpo vacío
        // cuando el email ya está registrado.
        const texto = await respuesta.text();
        if (!texto) {
            mostrarAlerta("Este email ya está registrado.", "warning");
            return;
        }

        mostrarAlerta("¡Usuario registrado correctamente!", "success"); // Muestra alerta de éxito
        document.querySelector(".form-box").reset(); // Limpia todos los campos del formulario
    } catch (error) {
        mostrarAlerta("No se pudo conectar con el servidor. Verifica que el backend esté en ejecución.", "danger");
    }
}

// Espera a que el DOM esté cargado para agregar el evento al formulario
document.addEventListener("DOMContentLoaded", function () {
    const formulario = document.querySelector(".form-box"); // Obtiene el formulario
    formulario.addEventListener("submit", registrarUsuario); // Agrega el evento submit al formulario
});
