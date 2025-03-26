package model;

import jakarta.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    /**
     *
     * @return <code>List</code> Una lista de todos los usuarios existentes en la BDD.
     * @exception SQLException
     * @description Lista todos los usuarios que existen en la BDD.
     */
    public List<User> userList(HttpSession session) {
        List<User> userList = new ArrayList<User>();
        String query = "select * from parcial1JSP.dbo.users";
        Connection connection = (Connection) session.getAttribute("connection");

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while(resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );

                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    /**
     *
     * @param username Es el nombre de usuario reconocido por el sistema para el ingreso.
     * @param password Es la contraseña del usuario para iniciar sesión en el sistema.
     * @return <code>boolean</code> Indica <code>true</code> si el usuario existe y <code>false</code>
     *         si el usuario no existe.
     * @description Valida las credenciales del usuario.
     */
    public boolean validateUser(String username, String password) {
        String query = "select * from parcial1JSP.dbo.users where username = ? and password = ?";
        boolean userValidated = false;

        try (Connection connection = ConnectionDB.createConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {

            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);

            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    userValidated = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(userValidated);
        return userValidated;
    }
}
