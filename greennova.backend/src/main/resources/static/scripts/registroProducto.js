/**
 * Green-Nova - Registro de Productos

 */
 //const productos = []; // Crea un arreglo vacío donde se guardarán los productos
 const productos=JSON.parse(localStorage.getItem("productoNuevo"))||[];

let idActual = 1; // Crea una variable para manejar el id autoincrementable

function mostrarAlerta(mensaje, tipo) { // Función para mostrar alertas con Bootstrap
    const contenedorAlertas = document.getElementById("alertas"); // Obtiene el div donde se mostrarán las alertas

    contenedorAlertas.innerHTML = ` 
        <div class="alert alert-${tipo} alert-dismissible fade show" role="alert">
            ${mensaje}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    `; // Inserta una alerta Bootstrap dentro del div
}

function guardarProducto() { // Función que valida y guarda el producto
    const idProducto = document.getElementById("idProducto").value; // Obtiene el valor del id de producto
    const nombreProducto = document.getElementById("nombreProducto").value.trim(); // Obtiene el nombre y elimina espacios al inicio y final
    const tipoProducto = document.getElementById("tipoProducto").value; // Obtiene el valor del tipo de producto  
    const precioProducto = document.getElementById("precioProducto").value; // Obtiene el precio  
    const imagen = document.getElementById("imagenProducto");//obtiene la imagen
    const luz = document.getElementById("luzProducto").value.trim(); //Obtiene el campo requerimiento de luz y elimina espacios
    const riego = document.getElementById("riegoProducto").value.trim();//Obtiene el campo frecuencia de riego y elimina espacios
    const funcion = document.getElementById("funcionProducto").value.trim();//obtiene el campo funcion y beneficio y elimina espacios
    const descripcion = document.getElementById("descripcionCorta").value.trim(); // Obtiene la descripción y elimina espacios
    

     if (idProducto === "") { // Valida que el tipo de producto no esté vacío
        mostrarAlerta("Debes ingresar un id de producto.", "danger"); // Muestra alerta de error
        return; // Detiene la función
    }

     if (nombreProducto === "") { // Valida que el nombre no esté vacío
        mostrarAlerta("El nombre del producto es obligatorio.", "danger"); // Muestra alerta de error
        return; // Detiene la función
    }

    if (tipoProducto === "") { // Valida que el tipo de producto no esté vacío
        mostrarAlerta("Debes seleccionar un tipo de producto.", "danger"); // Muestra alerta de error
        return; // Detiene la función
    }

      if (precioProducto === "" || precioProducto <= 0) { // Valida que el precio exista y sea mayor a cero
        mostrarAlerta("El precio es obligatorio y debe ser mayor a 0.", "danger"); // Muestra alerta de error
        return; // Detiene la función
    }
  

    if (imagen.files.length === 0) { // Valida que la imagen no esté vacía
        mostrarAlerta("La imagen del producto es obligatoria.", "danger"); // Muestra alerta de error
        return; // Detiene la función
    }


    
     if (luz === "") { // Valida que el nombre no esté vacío
        mostrarAlerta("El campo Requerimiento de Luz es obligatorio.", "danger"); // Muestra alerta de error
        return; // Detiene la función
    }

     if (riego === "") { // Valida que el nombre no esté vacío
        mostrarAlerta("El campo Frecuencia de Riego es obligatorio.", "danger"); // Muestra alerta de error
        return; // Detiene la función
    }

     if (funcion === "") { // Valida que el nombre no esté vacío
        mostrarAlerta("El campo Función/Beneficio es obligatorio.", "danger"); // Muestra alerta de error
        return; // Detiene la función
    }

    if (descripcion === "") { // Valida que la descripción no esté vacía
        mostrarAlerta("La descripción del producto es obligatoria.", "danger"); // Muestra alerta de error
        return; // Detiene la función
    }

  

    const producto = { // Crea un objeto producto
        idProducto: idProducto, // Asigna el id actual al producto
        nombreProducto: nombreProducto,// Guarda el nombre
        tipoProducto: tipoProducto, // Guarda el tipo de producto
        precioProducto: Number(precioProducto),// Convierte el precio a número y lo guarda
        //imagen: imagen.files[0].name, // Guarda la imagen
        imagen: imagen.files[0]?.name||"sin-imagen.jpg",//se pone validacion si no sube imagen
        luz: luz,
        riego: riego,
        funcion: funcion,
        descripcion: descripcion // Guarda la descripción
    }; // Fin del objeto producto

    productos.push(producto); // Agrega el producto al arreglo de productos
    localStorage.setItem("productoNuevo",JSON.stringify(productos))
    idActual++; // Incrementa el id para el siguiente producto

   // document.getElementById("resultadoJSON").textContent = JSON.stringify(productos, null, 4); // Muestra el arreglo como JSON ordenado

    mostrarAlerta("Producto guardado correctamente.", "success"); // Muestra alerta de éxito

    document.getElementById("formRegistroProducto").reset(); // Limpia todos los campos del formulario
}