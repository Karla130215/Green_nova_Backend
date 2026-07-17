/**
 * Green-Nova - Registro de Usuarios
 * Validaciones del formulario de registro
 */

// Arreglo donde se guardarán los usuarios registrados, cargando los que ya existan en localStorage
const usuarios = JSON.parse(localStorage.getItem("usuariosRegistrados")) || [];

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
    // valida que no se repitan numeros 
    if (/^(\d)\1{9}$/.test(telefono)) {
        mostrarAlerta("El número de telefono es inválido (dígitos repetidos).", "danger");
        return false;
    }

    // valida que no se escriban secuencias acendentes o descendentes
    for (let i = 0; i < telefono.length - 2; i++) { // Recorre el arreglo y de se detiene dos digitos antes
        const a = parseInt(telefono[i]);      //analizamos por grupos de tres (por eso el -2)
        const b = parseInt(telefono[i + 1]);  
        const c = parseInt(telefono[i + 2]);

        // Validamos si es Ascendente
        if (b === a + 1 && c === b + 1){
             mostrarAlerta("El numero que ingreso es invalido.", "danger");
            return false;
        } 
        
        // Validamos si es Descendente
        if (b === a - 1 && c === b - 1){
            mostrarAlerta("El teléfono que ingreso es invalido.", "danger");
           return false; 
        } 
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

    // Valida que el email no esté ya registrado
    const emailExistente = usuarios.find(u => u.email === email); // Busca si ya existe un usuario con ese email
    if (emailExistente) { // Si encuentra un email repetido
        mostrarAlerta("Este email ya está registrado.", "warning"); // Muestra alerta de advertencia
        return false; // Retorna falso
    }

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

// Función principal que registra al usuario
function registrarUsuario(event) {
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

    // Si todas las validaciones pasan, crea el objeto usuario
    const usuario = {
        nombre: nombre, // Guarda el nombre completo
        telefono: telefono, // Guarda el teléfono
        email: email, // Guarda el email
        password: password, // Guarda la contraseña
        fechaRegistro: new Date().toLocaleString() // Guarda la fecha y hora del registro
    };

    usuarios.push(usuario); // Agrega el usuario al arreglo de usuarios
    localStorage.setItem("usuariosRegistrados", JSON.stringify(usuarios)); // Guarda el arreglo actualizado en localStorage

    mostrarAlerta("¡Usuario registrado correctamente!", "success"); // Muestra alerta de éxito

    document.querySelector(".form-box").reset(); // Limpia todos los campos del formulario
}

// Espera a que el DOM esté cargado para agregar el evento al formulario
document.addEventListener("DOMContentLoaded", function () {
    const formulario = document.querySelector(".form-box"); // Obtiene el formulario
    formulario.addEventListener("submit", registrarUsuario); // Agrega el evento submit al formulario
});
