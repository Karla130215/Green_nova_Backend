const prodRow = document.getElementById('prodRow');
const botonesFiltro = document.querySelectorAll('.btn-filtro');
let productosGlobal = []; // Arreglo maestro que guardará los productos del backend

// 1. Cargar los datos desde el Backend
async function cargarProductosDesdeBackend() {
  try {
    const respuesta = await fetch('http://localhost:8080/api/productos');

    if (respuesta.ok) {
      productosGlobal = await respuesta.json();
      mostrarProductos(productosGlobal);
    } else {
      prodRow.innerHTML = '<p class="text-center text-danger">Error al cargar productos del servidor.</p>';
    }
  } catch (error) {
    console.error("Error al conectar con la base de datos:", error);
    prodRow.innerHTML = '<p class="text-center text-danger">No se pudo conectar. Verifica que Spring Boot esté encendido.</p>';
  }
}

// 2. Función para pintar las tarjetas en el HTML
function mostrarProductos(listaProductos) {
  prodRow.innerHTML = '';

  // Si la categoría seleccionada no tiene productos registrados
  if(listaProductos.length === 0) {
    prodRow.innerHTML = '<p class="text-center w-100 text-muted mt-5">No hay productos registrados en esta categoría aún.</p>';
    return;
  }

  listaProductos.forEach(producto => {
    // En caso de que la imagen guardada no sea URL, usamos un fallback
    const imagenSegura = producto.imagen.includes('http') ? producto.imagen : 'https://images.pexels.com/photos/1084199/pexels-photo-1084199.jpeg';

    const cardHTML = `
            <div class="col-12 col-md-6 col-lg-4 mb-4">
                <div class="card h-100 shadow-sm border-0">
                    <img src="${imagenSegura}" class="card-img-top" alt="${producto.nombre}" style="height: 250px; object-fit: cover;">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title fw-bold" style="font-family: var(--font-work-sans);">${producto.nombre}</h5>
                        <h6 class="text-success mb-3 fs-5">$${producto.precio} MXN</h6>
                        <p class="card-text small text-muted mb-4" style="font-family: var(--font-inter);">${producto.descripcion ? producto.descripcion.substring(0, 80) : 'Sin descripción'}...</p>
                        
                        <button class="btn btn-success mt-auto w-100 rounded-pill">Añadir al carrito</button>
                    </div>
                </div>
            </div>
        `;
    prodRow.innerHTML += cardHTML;
  });
}

// 3. Activar los botones del menú para filtrar
botonesFiltro.forEach(boton => {
  boton.addEventListener('click', (e) => {
    // Quitamos la clase 'active' de todos los botones y se la ponemos al que recibió el clic
    botonesFiltro.forEach(b => b.classList.remove('active'));
    e.target.classList.add('active');

    // Extraemos el texto del botón (Ej. "Interior", "Macetas")
    const textoBoton = e.target.textContent.trim().toLowerCase();

    // Filtramos la información
    if (textoBoton === 'todos' || textoBoton === 'nuevo') {
      mostrarProductos(productosGlobal); // Muestra el arreglo completo
    } else {
      const productosFiltrados = productosGlobal.filter(p => {
        // Comparamos el tipo de producto en Java con el texto del botón
        return p.tipoProducto && p.tipoProducto.toLowerCase() === textoBoton;
      });
      mostrarProductos(productosFiltrados); // Muestra solo los coincidentes
    }
  });
});

// 4. Iniciar todo cuando cargue la pantalla
document.addEventListener('DOMContentLoaded', cargarProductosDesdeBackend);