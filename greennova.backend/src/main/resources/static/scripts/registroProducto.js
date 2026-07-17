/**
 * Green-Nova - Registro de Productos hacia Spring Boot
 */

// Función para mostrar alertas con Bootstrap
function mostrarAlerta(mensaje, tipo) {
    const contenedorAlertas = document.getElementById("alertas");

    contenedorAlertas.innerHTML = ` 
        <div class="alert alert-${tipo} alert-dismissible fade show" role="alert">
            ${mensaje}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    `;

    // Hace que la alerta desaparezca automáticamente después de 5 segundos
    setTimeout(() => {
        contenedorAlertas.innerHTML = '';
    }, 5000);
}

// Función principal que se ejecuta al darle clic a "Guardar producto"
async function guardarProducto() {

    // 1. Obtenemos los valores de los inputs del HTML
    const nombreProducto = document.getElementById("nombreProducto").value.trim();
    const tipoProducto = document.getElementById("tipoProducto").value;
    const precioProducto = document.getElementById("precioProducto").value;
    const imagen = document.getElementById("imagenProducto");
    const luz = document.getElementById("luzProducto").value.trim();
    const riego = document.getElementById("riegoProducto").value.trim();
    const funcion = document.getElementById("funcionProducto").value.trim();
    const descripcion = document.getElementById("descripcionCorta").value.trim();

    // 2. Validaciones básicas para que no envíen el formulario vacío
    if (nombreProducto === "") {
        mostrarAlerta("El nombre del producto es obligatorio.", "danger");
        return;
    }
    if (tipoProducto === "") {
        mostrarAlerta("Debes seleccionar una categoría o tipo de producto.", "danger");
        return;
    }
    if (precioProducto === "" || precioProducto <= 0) {
        mostrarAlerta("El precio es obligatorio y debe ser mayor a 0.", "danger");
        return;
    }
    if (descripcion === "") {
        mostrarAlerta("La descripción del producto es obligatoria.", "danger");
        return;
    }

    // 3. Armamos el objeto exactamente con los nombres que espera Producto.java
    const nuevoProducto = {
        nombre: nombreProducto,
        tipoProducto: tipoProducto,
        precio: Number(precioProducto),
        // Extraemos el nombre del archivo si hay uno, o usamos una imagen por defecto
        imagen: imagen.files.length > 0 ? imagen.files[0].name : "https://images.pexels.com/photos/1084199/pexels-photo-1084199.jpeg",
        rLuz: luz || "No especificado",
        fRiego: riego || "No especificado",
        funcion: funcion || "Adorno",
        descripcion: descripcion
    };

    // 4. Enviamos los datos al backend usando Fetch (POST)
    try {
        const respuesta = await fetch('http://localhost:8080/api/productos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoProducto)
        });

        if (respuesta.ok) {
            mostrarAlerta("¡Producto guardado exitosamente en la Base de Datos!", "success");
            document.getElementById("formRegistroProducto").reset(); // Limpiamos el formulario
        } else {
            mostrarAlerta("Hubo un problema al guardar el producto. Revisa los datos.", "danger");
        }
    } catch (error) {
        console.error("Error de conexión:", error);
        mostrarAlerta("No se pudo conectar con el servidor. Verifica que Spring Boot esté encendido.", "danger");
    }
}