<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<form id="form-container" action="../CreateUser" method="post">
    <div class="group-fields">
        <label for="">Nombres</label>
        <input type="text" name="name" id="name" required>
    </div>

    <div class="group-fields">
        <label for="">Apellidos</label>
        <input type="text" name="lastname" id="lastname" required>
    </div>

    <div class="group-fields">
        <label for="">Correo Electrónico</label>
        <input type="email" name="email" id="email" required>
    </div>

    <div class="group-fields">
        <label for="">Usuario</label>
        <input type="text" name="username" id="username" required>
    </div>

    <div class="group-fields">
        <label for="">Contraseña</label>
        <input type="password" name="pass" id="pass" required>
    </div>

    <div>
        <button id="btn-submit" type="submit">Crear Usuario</button>
        <button id="btn-reset" type="reset">Limpiar Campos</button>
    </div>
</form>