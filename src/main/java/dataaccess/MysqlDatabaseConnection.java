package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDatabaseConnection {

    private Connection connection;

    public MysqlDatabaseConnection() throws SQLException, ClassNotFoundException {
        // JDBC-Treiber laden
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Verbindung zur Datenbank aufbauen
        String url = "jdbc:mysql://localhost:3306/deineDatenbank"; // Passe den URL an deine Datenbank an
        String username = "deinUsername";
        String password = "deinPasswort";

        this.connection = DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection() {
        return connection;
    }
}
