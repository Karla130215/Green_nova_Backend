const API_URL='/api/productos/'
document.addEventListener("DOMContentLoaded", () => {


    /* ==========================================================
       CONFIGURACIÓN DE RUTAS
       ========================================================== */

    // Detecta si la página actual está dentro de /templates/
    const enTemplates = window.location.pathname
        .toLowerCase()
        .includes("/templates/");

    // Si estamos en templates subimos un nivel, si no, permanecemos en la raíz
    const ruta = enTemplates ? "../" : "";

    /* ==========================================================
       FONT AWESOME
       ========================================================== */

    if (!document.querySelector('link[href*="font-awesome"]')) {
        const fontAwesome = document.createElement("link");
        fontAwesome.rel = "stylesheet";
        fontAwesome.href =
            "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css";
        document.head.appendChild(fontAwesome);
    }

    /* ==========================================================
       HEADER
       ========================================================== */

    const header = document.createElement("header");
    header.className = "sticky-top shadow-sm";

    header.innerHTML = `
        <nav class="navbar navbar-expand-md navbar-dark custom-navbar py-3">
            <div class="container">

                <a class="navbar-brand d-flex align-items-center gap-3" href="${ruta}index.html">
                    <div class="logo-container">
                        <img src="${ruta}img/icon_logo.svg" alt="Green-Nova Logo">
                    </div>
                    <span class="fw-bold fs-4 tracking-wide text-white">
                        Green-Nova
                    </span>
                </a>

                <button
                    class="navbar-toggler border-0 shadow-none"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                    id="custom-navbar-toggler">

                    <svg class="bi bi-list" width="30" height="30" fill="white" viewBox="0 0 16 16">
                        <path fill-rule="evenodd"
                            d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                    </svg>

                </button>

                <div class="collapse navbar-collapse" id="navbarNav">

                    <ul class="navbar-nav mx-auto mb-3 mb-md-0 gap-md-2 text-center">

                        <li class="nav-item">
                            <a class="nav-link" href="${ruta}index.html">
                                Inicio
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="${ruta}templates/productos.html">
                                Productos
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="${ruta}templates/contacto.html">
                                Contáctanos
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="${ruta}templates/nosotros.html">
                                Nosotros
                            </a>
                        </li>

                    </ul>

                    <div class="d-flex flex-column flex-md-row gap-2 justify-content-center align-items-center">

                        <a
                            href="${ruta}templates/ingresarUsuario.html"
                            class="btn btn-outline-brand-header w-100 w-md-auto text-decoration-none text-center">
                            Iniciar Sesión
                        </a>

                        <a
                            href="${ruta}templates/registroUsuarios.html"
                            class="btn btn-brand-header w-100 w-md-auto text-decoration-none text-center shadow-sm">
                            Registrarse
                        </a>

                    </div>

                </div>

            </div>
        </nav>
    `;

    document.body.insertBefore(header, document.body.firstChild);

    /* ==========================================================
       MENÚ HAMBURGUESA
       ========================================================== */

    const menuEl = document.getElementById("navbarNav");
    const togglerBtn = document.getElementById("custom-navbar-toggler");

    if (menuEl && togglerBtn) {

        menuEl.addEventListener("show.bs.collapse", () => {

            togglerBtn.innerHTML = `
                <svg class="bi bi-x" width="30" height="30" fill="white" viewBox="0 0 16 16">
                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                </svg>
            `;

        });

        menuEl.addEventListener("hide.bs.collapse", () => {

            togglerBtn.innerHTML = `
                <svg class="bi bi-list" width="30" height="30" fill="white" viewBox="0 0 16 16">
                    <path fill-rule="evenodd"
                        d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                </svg>
            `;

        });

    }

    /* ==========================================================
       FOOTER
       ========================================================== */

    const footer = document.createElement("footer");
    footer.className = "custom-footer py-5 text-white mt-auto";

    footer.innerHTML = `
        <div class="container">

            <div class="row align-items-center gy-4 text-center text-md-start">

                <div class="col-12 col-md-4 d-flex flex-column align-items-center align-items-md-start gap-3">

                    <span class="text-uppercase fw-bold text-brand-light small tracking-wider">
                        Síguenos
                    </span>

                    <div class="d-flex gap-4 fs-5">

                        <a href="#" class="text-white opacity-75 text-decoration-none hover-accent">
                            <i class="fa-brands fa-x-twitter"></i>
                        </a>

                        <a href="#" class="text-white opacity-75 text-decoration-none hover-accent">
                            <i class="fa-brands fa-instagram"></i>
                        </a>

                        <a href="#" class="text-white opacity-75 text-decoration-none hover-accent">
                            <i class="fa-brands fa-youtube"></i>
                        </a>

                        <a href="#" class="text-white opacity-75 text-decoration-none hover-accent">
                            <i class="fa-brands fa-linkedin-in"></i>
                        </a>

                    </div>

                </div>

                <div class="col-12 col-md-4 text-center d-flex align-items-end justify-content-center h-100 mt-md-4">

                    <p class="mb-0 small fw-medium opacity-75">
                        Green-Nova &copy; 2026
                    </p>

                </div>

                <div class="col-12 col-md-4 text-center text-md-end d-flex flex-column gap-1">

                    <a
                        href="${ruta}templates/contacto.html"
                        class="text-uppercase fw-bold text-brand-light small tracking-wider text-decoration-none">

                        Contáctanos

                    </a>

                </div>

            </div>

        </div>
    `;

    document.body.appendChild(footer);

    /* ==========================================================
       CARRUSEL EQUIPO
       ========================================================== */

    document.querySelectorAll(".equipo-detalle").forEach((detalle) => {

        const boton = document.querySelector(
            `[data-bs-target="#${detalle.id}"]`
        );

        if (!boton) return;

        detalle.addEventListener("show.bs.collapse", () => {
            boton.textContent = "Ver menos";
        });

        detalle.addEventListener("hide.bs.collapse", () => {
            boton.textContent = "Ver más";
        });

    });

    const equipoCarousel = document.getElementById("equipoCarousel");

    if (equipoCarousel) {

        equipoCarousel.addEventListener("slide.bs.carousel", () => {

            equipoCarousel
                .querySelectorAll(".equipo-detalle.show")
                .forEach((detalle) => {

                    bootstrap.Collapse
                        .getOrCreateInstance(detalle)
                        .hide();

                });

        });

    }

});