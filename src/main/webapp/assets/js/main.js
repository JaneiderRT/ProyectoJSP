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

function logoutUser(event) {
    event.preventDefault();
    if(confirm("¿Estás seguro de que deseas cerrar sesión?")) {
        window.location.href = "/Parcial1/LogoutServlet"
    }
}

const btn = document.getElementById("btn-theme");

btn.addEventListener('click', () => {
    document.documentElement.classList.toggle('dark-mode');

    const isDark = document.documentElement.classList.contains('dark-mode');
    localStorage.setItem('theme', isDark ? 'dark' : 'light');
})

window.addEventListener('DOMContentLoaded', () => {
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme === 'dark') {
        document.documentElement.classList.add('dark-mode');
    }
});