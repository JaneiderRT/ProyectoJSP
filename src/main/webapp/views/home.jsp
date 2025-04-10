<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<h1 id="main-title">
    Bienvenido <span id="user"><%=session.getAttribute("username")%></span>
</h1>

<article id="main-article">
    <p id="main-text">
        Como administrador, tendrás acceso a las herramientas necesarias para gestionar usuarios y
        supervisar las funcionalidades principales de la plataforma en el panel lateral izquierdo.
    </p>
</article>