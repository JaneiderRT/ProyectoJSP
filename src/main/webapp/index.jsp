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
        response.sendRedirect("UserList.jsp"); // Si ya está autenticado, lo redirige
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio De Sesión</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }
        .login-container {
            background: white;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            width: 300px;
            text-align: center;
        }
        input {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            width: 100%;
            padding: 10px;
            background: #007bff;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }
        button:hover {
            background: #0056b3;
        }
        .error {
            color: red;
            font-size: 14px;
        }
    </style>
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
