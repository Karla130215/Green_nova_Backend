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

//=======================
// Renderizar tarjetas
//=======================
function renderizarTarjetas(productos, mensaje = "No hay productos.") {
  contenedor.innerHTML = "";

  if (!productos || !productos.length) {
    contenedor.innerHTML = `<p class="text-center">${mensaje}</p>`;
    return;
  }

  productos.forEach((p) => {
    // Validación por si la imagen no viene definida
    const urlImagen = p.imagen || "";
    const imagen = urlImagen.startsWith("./") || urlImagen.startsWith("http")
      ? urlImagen
      : `./img/${urlImagen}`;

    // Soporte para propiedades largas o cortas (nombreProducto vs nombre)
    const nombre = p.nombreProducto || p.nombre || "Producto sin nombre";
    const precio = p.precioProducto || p.precio || 0;
    const funcion = p.Funcion || p.funcion || "No especificado";

    contenedor.insertAdjacentHTML(
      "beforeend",
      `
      <div class="col-md-3 mb-4">
        <div class="card h-100 shadow-sm">
          <img src="${imagen}" class="card-img-top p-3"
               style="height:250px;object-fit:cover" alt="${nombre}">

          <div class="card-body d-flex flex-column">

            <h5 class="card-title">${nombre}</h5>

            <h6 class="text-success fw-bold">
              $${precio}
            </h6>

            <p class="mb-1"><strong>Luz:</strong> ${p.luz || "No especificado"}</p>

            <p class="mb-1"><strong>Riego:</strong> ${p.riego || "No especificado"}</p>

            <p class="mb-2"><strong>Función:</strong> ${funcion}</p>

            <p class="small text-muted flex-grow-1">
              ${p.descripcion || "Sin descripción"}
            </p>

            <button class="btn btn-success mt-auto btnComprar">
              Comprar
            </button>

          </div>
        </div>
      </div>
      `
    );
  });
}

//=======================
// Configurar botones
//=======================
const botones = [
  { id: "todosBtn", categoria: "todos", mensaje: "No hay productos." },
  { id: "interiorBtn", categoria: "interior", mensaje: "No hay plantas de interior." },
  { id: "exteriorBtn", categoria: "exterior", mensaje: "No hay plantas de exterior." },
  { id: "macetasBtn", categoria: "macetas", mensaje: "No hay macetas." },
  { id: "suculentasBtn", categoria: "suculentas", mensaje: "No hay suculentas." },
  { id: "cactusBtn", categoria: "cactus", mensaje: "No hay cactus." },
  { id: "accesoriosBtn", categoria: "accesorios", mensaje: "No hay accesorios." },
  { id: "nuevoArticulo", categoria: "nuevos", mensaje: "No hay productos nuevos." },
];

botones.forEach(({ id, categoria, mensaje }) => {
  const boton = document.getElementById(id);

  if (!boton) return;

  boton.addEventListener("click", () => {
    document
      .querySelectorAll(".btn-filtro")
      .forEach((b) => b.classList.remove("active"));

    boton.classList.add("active");

    renderizarTarjetas(categorias[categoria], mensaje);
  });
});

//=======================
// Carga inicial
//=======================
// Muestra por defecto todos los productos al abrir la página
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