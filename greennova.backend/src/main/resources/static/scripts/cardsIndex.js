import {
  plantasExterior,
  plantasInterior,
  macetas,
  accesorios,
  suculentas,
  cactus,
} from "./productos.js";

const productosNuevos =
  JSON.parse(localStorage.getItem("productoNuevo")) || [];

const contenedor = document.getElementById("prodRow");

const categorias = {
  todos: [
    ...plantasInterior,
    ...plantasExterior,
    ...macetas,
    ...suculentas,
    ...cactus,
    ...accesorios,
    ...productosNuevos,
  ],
  interior: plantasInterior,
  exterior: plantasExterior,
  macetas,
  suculentas,
  cactus,
  accesorios,
  nuevos: productosNuevos,
};

/*=======================
 Renderizar tarjetas Index
=======================*/
function renderizarTarjetas(productos, mensaje = "No hay productos.") {
  contenedor.innerHTML = "";

  if (!productos || !productos.length) {
    contenedor.innerHTML = `<p class="text-center">${mensaje}</p>`;
    return;
  }

  // 1. Tomar únicamente los primeros 4 productos
  const productosAMostrar = productos.slice(0, 4);

  productosAMostrar.forEach((p) => {
    const urlImagen = p.imagen || "";
    const imagen = urlImagen.startsWith("./") || urlImagen.startsWith("http")
      ? urlImagen
      : `./img/${urlImagen}`;

    const nombre = p.nombreProducto || p.nombre || "Producto sin nombre";
    const precio = p.precioProducto || p.precio || 0;

    // 2. Tarjetas más compactas (col-6 para móviles, col-md-3 para PC)
    contenedor.insertAdjacentHTML(
      "beforeend",
      `
      <div class="col-6 col-md-3 mb-3"> 
        <div class="card h-100 shadow-sm">
          <img src="${imagen}" class="card-img-top p-2"
               style="height:150px; object-fit:cover; border-radius: 12px;" alt="${nombre}">

          <div class="card-body d-flex flex-column p-2"> 

            <h6 class="card-title fw-bold mb-1 text-truncate" title="${nombre}">${nombre}</h6>
            
            <p class="text-muted mb-2" style="font-size: 0.75rem; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden;">
              ${p.descripcion || "Sin descripción"}
            </p>

            <button class="btn btn-sm btn-success mt-auto  w-100 btnComprar">
              Comprar
            </button>

          </div>
        </div>
      </div>
      `
    );
  });
}

renderizarTarjetas(categorias.todos);

/** Metodo para mandar mensaje de alerta del boton añadir carrito */
    document.addEventListener('DOMContentLoaded', () => {
    // Selecciona todos los botones que tienen la clase "btn-add-cart"
    const botonesCarrito = document.querySelectorAll('.btnComprar');

    // Escucha el click en cada uno de ellos y lanza la alerta única
    botonesCarrito.forEach(boton => {
        boton.addEventListener('click', () => {
            alert('Producto agregado al carrito');
        });
    });
});