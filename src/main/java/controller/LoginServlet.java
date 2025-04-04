package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ConnectionDB;
import dao.UserDAO;

import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    /**
     * En caso de recibir una solicitud por método <code>POST</code>, se realiza la validación del usuario y se
     * devuelve una respuesta.
     * @param request  La solicitud que se realiza, en este caso para el inicio de sesión.
     * @param response La respuesta que se devuelve, dependiendo si el usuario ingresa correctamente
     *                 las credenciales o no.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO userDao = new UserDAO();

        if(userDao.validateUser(username, password)) {
            HttpSession session = request.getSession();

            try {
                Connection connection = ConnectionDB.createConnection();
                session.setAttribute("connection", connection);
            } catch (Exception e) {
                e.printStackTrace();
            }

            session.setAttribute("username", username);
            response.sendRedirect("/Parcial1/views/Dashboard.jsp");
        }
        else {
            request.getSession().setAttribute("error", "Credenciales Incorrectas");
            response.sendRedirect("/Parcial1/index.jsp");
        }
    }
}
