package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import model.ConnectionDB;

public class LogoutServlet extends HttpServlet {
    /**
     *
     * @param request La solicitud que se realiza, en este caso para el inicio de sesión.
     * @param response La respuesta que se devuelve, dependiendo si el usuario ingresa correctamente
     * @throws ServletException
     * @throws IOException
     * @description Se cierra la sesión del usuario.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Connection connection = (Connection) session.getAttribute("connection");

            if (connection != null) {
                ConnectionDB.closeConnection(connection);
            }

            session.invalidate();
        }

        response.sendRedirect("/Parcial1/index.jsp");
    }
}
