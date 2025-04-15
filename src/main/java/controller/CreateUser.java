package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ConnectionDB;
import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "CreateUser", urlPatterns = "/CreateUser")
public class CreateUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("pass");
        HttpSession session = request.getSession();

        UserDAO userDAO = new UserDAO();

        if (session.getAttribute("connection") == null) {
            try {
                Connection connection = ConnectionDB.createConnection();
                session.setAttribute("connection", connection);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error de conexión con la base de datos");
                return;
            }
        }

        if ((name.isEmpty() || lastname.isEmpty() || email.isEmpty() || username.isEmpty() ||
                password.isEmpty())) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan datos obligatorios");
            return;
        }

        User newUser = new User(name, lastname, email, username, password);
        boolean isAdded = false;

        try {
            isAdded = userDAO.addUser(newUser, session);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear el usaurio.");
            throw new RuntimeException(e);
        }

        if(isAdded) {
            response.sendRedirect("/Parcial1/views/dashboard.html");
        }
        else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear el usaurio.");
        }
    }
}
