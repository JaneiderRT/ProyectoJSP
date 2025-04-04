package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ConnectionDB;
import dao.UserDAO;
import model.User;
import util.PasswordExclusionStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="APIUsers", urlPatterns="/api/users")
public class APIUsers extends HttpServlet {

    /**
     * Método que maneja la petición de tipo GET en la API para devolver los usuarios de la base de datos.
     * @param request Objeto de tipo <code>HttpServletRequest</code> que maneja la solicitud http.
     * @param response Objeto de tipo <code>HttpServletResponse</code> que maneja la respuesta http.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new GsonBuilder().setExclusionStrategies(new PasswordExclusionStrategy()).create();
        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();

        try {
            Connection connection = ConnectionDB.createConnection();
            session.setAttribute("connection", connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List users = userDAO.userList(session);

        PrintWriter printWriter = response.getWriter();
        printWriter.print(gson.toJson(users));
        printWriter.flush();
    }

    /**
     * Método para crear un usuario en el sistema recibiendo un json.
     * @param request Objeto de tipo <code>HttpServletRequest</code> que maneja la solicitud http.
     * @param response Objeto de tipo <code>HttpServletResponse</code> que maneja la respuesta http.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();

        if (session.getAttribute("connection") == null) {
            try {
                Connection connection = ConnectionDB.createConnection();
                session.setAttribute("connection", connection);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error de conexión con la base de datos");
                return;
            }
        }

        StringBuilder jsonReceived = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonReceived.append(line);
            }
        }

        User newUser = gson.fromJson(jsonReceived.toString(), User.class);

        if ((newUser.getName() == null || newUser.getLastname() == null || newUser.getUsername() == null ||
             newUser.getPassword() == null || newUser.getEmail() == null)) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan datos obligatorios");
            return;
        }

        // Validar si puede ser
        if ((newUser.getName().equals("") || newUser.getLastname().equals("") || newUser.getUsername().equals("") ||
             newUser.getPassword().equals("") || newUser.getEmail().equals(""))) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan datos obligatorios");
            return;
        }

        boolean isAdded = false;
        try {
            isAdded = userDAO.addUser(newUser, session);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JsonObject jsonResponse = new JsonObject();

        if(isAdded) {
            jsonResponse.addProperty("message", "El usuario se ha creado exitosamente.");
            response.setStatus(HttpServletResponse.SC_CREATED);
        }
        else {
            jsonResponse.addProperty("message", "No se pudo crear el usuario.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.print(gson.toJson(jsonResponse));
        printWriter.flush();
    }
}
