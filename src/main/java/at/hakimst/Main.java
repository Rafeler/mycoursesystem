package at.hakimst;

import dataaccess.MySqlCourseRepository;
import dataaccess.MySqlStudentRepository;
import dataaccess.MysqlDatabaseConnection;
import ui.Cli;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Cli myCli = null;
        try {
            // Verbindung zur Datenbank herstellen
            MysqlDatabaseConnection mysqlDatabaseConnection = new MysqlDatabaseConnection();

            // Erstellen der Repository-Instanzen mit der Datenbankverbindung
            MySqlCourseRepository courseRepo = new MySqlCourseRepository(mysqlDatabaseConnection);
            MySqlStudentRepository studentRepo = new MySqlStudentRepository(mysqlDatabaseConnection);

            // Übergabe beider Repositories an die CLI
            myCli = new Cli(courseRepo, studentRepo);

            // Starten der CLI
            myCli.start();
        } catch (SQLException e) {
            System.out.println("Datenbankfehler aufgetreten: " + e.getMessage() + " SQL STATE: " + e.getSQLState());
        } catch (ClassNotFoundException e) {
            System.out.println("Datenbanktreiber nicht gefunden: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler: " + e.getMessage());
        }
    }
}
