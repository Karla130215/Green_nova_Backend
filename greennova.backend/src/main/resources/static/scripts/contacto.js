(function () {
    "use strict";

    const EMAILJS_SERVICE_ID = "service_0a27wrd";
    const EMAILJS_TEMPLATE_ID = "template_p0zvahe";
    const EMAILJS_PUBLIC_KEY = "_6gsEKA7tILIt971C";

    emailjs.init(EMAILJS_PUBLIC_KEY);

    const form = document.getElementById("contactForm");
    const btnEnviar = document.getElementById("btn-enviar");
    const btnText = btnEnviar?.querySelector(".contacto-btn-text");
    const btnSpinner = btnEnviar?.querySelector(".contacto-btn-spinner");
    const toastEl = document.getElementById("emailToast");
    const toastBody = document.getElementById("toastBody");

    if (!form) return;

    function showToast(message, isSuccess) {
        if (!toastEl || !toastBody) return;
        toastBody.textContent = message;
        toastEl.classList.remove("text-bg-success", "text-bg-danger");
        toastEl.classList.add(isSuccess ? "text-bg-success" : "text-bg-danger");
        const toast = bootstrap.Toast.getOrCreateInstance(toastEl, { delay: 5000 });
        toast.show();
    }

    function setLoading(loading) {
        if (!btnText || !btnSpinner) return;
        if (loading) {
            btnText.classList.add("d-none");
            btnSpinner.classList.remove("d-none");
            btnEnviar.disabled = true;
        } else {
            btnText.classList.remove("d-none");
            btnSpinner.classList.add("d-none");
            btnEnviar.disabled = false;
        }
    }

    function validarCampos() {
        const reglas = [
            { id: "nombre", regex: /^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s]{3,50}$/, error: "Solo letras, entre 3 y 50 caracteres." },
            {
                id: "apellido",
                regex: /^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s]{3,50}$/,
                error: "Solo letras, entre 3 y 50 caracteres."
            },
            { id: "email", regex: /^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$/, error: "Correo no válido." },
            { id: "mensaje", regex: /^[\s\S]{10,1000}$/, error: "Mínimo 10 caracteres." }
        ];

        let valido = true;
        reglas.forEach(function (r) {
            const campo = document.getElementById(r.id);
            const feedback = campo.parentElement.querySelector(".invalid-feedback");
            if (!r.regex.test(campo.value.trim())) {
                campo.classList.add("is-invalid");
                if (feedback) feedback.textContent = r.error;
                valido = false;
            } else {
                campo.classList.remove("is-invalid");
            }
        });
        return valido;
    }

    form.addEventListener("submit", function (e) {
        e.preventDefault();

        if (!validarCampos()) {
            form.classList.add("was-validated");
            showToast("Por favor completa todos los campos correctamente.", false);
            return;
        }

        setLoading(true);

        const templateParams = {
            nombre: document.getElementById("nombre").value.trim(),
            apellido: document.getElementById("apellido").value.trim(),
            from_name:
                document.getElementById("nombre").value.trim() + " " + document.getElementById("apellido").value.trim(),
            from_email: document.getElementById("email").value.trim(),
            message: document.getElementById("mensaje").value.trim()
        };

        emailjs
            .send(EMAILJS_SERVICE_ID, EMAILJS_TEMPLATE_ID, templateParams)
            .then(function () {
                showToast("¡Mensaje enviado correctamente! ✅", true);
                form.reset();
                form.classList.remove("was-validated");
            })
            .catch(function (error) {
                console.error("EmailJS error:", error);
                showToast("Error al enviar el mensaje. Intenta de nuevo. ❌", false);
            })
            .finally(function () {
                setLoading(false);
            });
    });
})();
