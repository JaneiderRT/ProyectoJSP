<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    // Verifica si hay un mensaje de error en la sesión
    String error = (String) session.getAttribute("error");
    if (error != null) {
        session.removeAttribute("error"); // Elimina el mensaje para que no se muestre siempre
    }

    // Verifica si ya hay una sesión activa
    HttpSession userSession = request.getSession(false);
    if (userSession != null && userSession.getAttribute("usuario") != null) {
        response.sendRedirect("dashboard.html");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio De Sesión</title>
    <link rel="stylesheet" href="./assets/css/main.css">
</head>
<body>

<div class="login-container">
    <h2>Iniciar Sesión</h2>

    <% if (error != null) { %>
        <p class="error"><%= error %></p>
    <% } %>

    <form action="LoginServlet" method="post">
        <input type="text" name="username" placeholder="Usuario" required>
        <input type="password" name="password" placeholder="Contraseña" required>
        <button type="submit">Ingresar</button>
    </form>
</div>

</body>
</html>
