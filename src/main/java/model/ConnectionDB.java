package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.ConfigurationDB;

public class ConnectionDB {

    private ConnectionDB() {} // Constructor privado

    public static Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(ConfigurationDB.URL, ConfigurationDB.USER, ConfigurationDB.PASSWORD);
            System.out.println("✅ Conexión exitosa a SQL Server");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("✅ Conexión cerrada correctamente");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
