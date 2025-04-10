let prefijo = "option-"
let opAnterior = "";

document.querySelectorAll('.li-options a').forEach(link => {
    link.addEventListener('click', function(e) {
        e.preventDefault();
        const url = this.getAttribute('data-url');
        if (url) {
            fetch(url)
                .then(response => response.text())
                .then(html => {
                    document.getElementById('main-content').innerHTML = html;
                })
                .catch(error => {
                    console.error('Error cargando el contenido:', error);
                    document.getElementById('main-content').innerHTML = "<p>Error al cargar la página.</p>";
                });

            if (opAnterior) {
                document.getElementById(opAnterior).classList.remove("active");
            }

            let nameOption = prefijo.concat(url.replace(".jsp", ""));
            document.getElementById(nameOption).classList.add("active");
            opAnterior = nameOption;
        }
    });
});