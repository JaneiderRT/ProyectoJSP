package dao;

import jakarta.servlet.http.HttpSession;
import model.ConnectionDB;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    /**
     * Lista todos los usuarios que existen en la BDD.
     * @param session Objeto de tipo <code>HttpSession</code> que almacena información de la sesión de un usuario.
     * @return <code>List</code> Una lista de todos los usuarios existentes en la BDD.
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
                        resultSet.getString("lastname"),
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
     * Método para crear un usuario en el sistema.
     * @param user El usuario que se va a crear en el sistema.
     * @param session La session del usuario que ingreso al sistema.
     * @return <code>boolean</code> <ul>
     *     <li><code>true</code> si el usuario fue creado satisfactoriamente. </li>
     *     <li><code>false</code> Si el usuario no se pudo crear satisfactoriamente.</li>
     * </ul>
     */
    public boolean addUser(User user, HttpSession session) throws SQLException {
        final String SENTENCE = "insert into parcial1JSP.dbo.users (name, lastname, email, username, password)";
        String insert = SENTENCE.concat(" values (?, ?, ?, ?, ?)");
        Connection connection = (Connection) session.getAttribute("connection");

        try(PreparedStatement preparedStatement = connection.prepareStatement(insert);) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());

            int rowInsert = preparedStatement.executeUpdate();
            return rowInsert > 0;
        }
    }

    /**
     * Valida las credenciales del usuario.
     * @param username Es el nombre de usuario reconocido por el sistema para el ingreso.
     * @param password Es la contraseña del usuario para iniciar sesión en el sistema.
     * @return <code>boolean</code> <ul>
     *     <li><code>true</code> si el usuario existe</li>
     *     <li><code>false</code> si el usuario no existe.</li>
     * </ul>
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
        return userValidated;
    }
}
