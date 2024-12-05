package at.hakimst;

import dataaccess.MySqlCourseRepository;
import dataaccess.MysqlDatabaseConnection;
import ui.Cli;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Cli myCli = null;
        try {
            myCli = new Cli(new MySqlCourseRepository());
        } catch (SQLException e) {
            System.out.println("Datenbanktreiber nicht gefunden" + e.getMessage() + " SQL STATE: " + e.getSQLState());
        } catch (ClassNotFoundException e) {
            System.out.println("Datenbanktreiber nicht gefunden" + e.getMessage());
        }
        myCli.start();
    }
}