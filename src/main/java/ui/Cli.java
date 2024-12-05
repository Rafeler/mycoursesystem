package ui;

import dataaccess.DatabaseException;
import dataaccess.MySqlCourseRepository;
import domain.Course;

import java.util.List;
import java.util.Scanner;

public class Cli {

    Scanner scanner;
    MySqlCourseRepository repo;

    public Cli(MySqlCourseRepository mySqlCourseRepository) {
        this.scanner = new Scanner(System.in);
        this.repo = repo;
    }

    public void start (){
        String input = "-";
        while(!input.equals("x"))
        {
            showMenue();
            input = scanner.nextLine();
            switch(input) {
                case "1":
                    System.out.println("Kurseingabe");
                    break;
                case "2":
                    showAllCourses();
                    break;
                case "x":
                    System.out.println("Auf Wiedersehen!");
                    break;
                default:
                    inputError();
                    break;
            }
        }
        scanner.close();
    }

    private void showAllCourses() {
        List<Course> list = null;

        try {


        list = repo.getAll();
        if (list.size() > 0) {
            for (Course course : list) {
                System.out.println(course);
            }
            } else {
                System.out.println("Kursliste leer");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei Anzeigen aller Kurse: " + databaseException.getMessage());
        } catch (Exception exception){
            System.out.println("Unbekannter Fehler bei Anzeigen aller Kurse: " + exception.getMessage());
        }
    }

    private void showMenue()
    {
        System.out.println("----------- KURSMANAGEMENT -----------");
        System.out.println("(1) Kurs ausgeben \t (2) Alle Kurse anzeigen \t");
        System.out.println("(x) ENDE");
    }
    private void inputError(){
        System.out.println("Bitte nur die Zahlen der Menüauswahl angeben");
    }
}
