package ui;

import dataaccess.MySqlCourseRepository;
import dataaccess.MySqlStudentRepository;
import domain.Course;
import domain.Student;

import java.util.Scanner;

public class Cli {

    Scanner scanner;
    MySqlCourseRepository courseRepo;
    MySqlStudentRepository studentRepo;

    public Cli(MySqlCourseRepository courseRepo, MySqlStudentRepository studentRepo) {
        this.scanner = new Scanner(System.in);
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    public void start() {
        String input = "-";
        while (!input.equals("x")) {
            showMenue();
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Kurseingabe");
                    addCourse();
                    break;
                case "2":
                    showAllCourses();
                    break;
                case "3":
                    showCourseDetails();
                    break;
                case "4":
                    updateCourseDetails();
                    break;
                case "5":
                    deleteCourse();
                    break;
                case "6":
                    courseSearch();
                    break;
                case "7":
                    runningCourses();
                    break;
                case "8":
                    System.out.println("Studenteneingabe");
                    addStudent();
                    break;
                case "9":
                    showAllStudents();
                    break;
                case "10":
                    showStudentDetails();
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

    // Studentenmethoden
    private void addStudent() {
        System.out.println("Geben Sie die Studentendaten ein:");
        System.out.println("Vorname:");
        String firstName = scanner.nextLine();
        System.out.println("Nachname:");
        String lastName = scanner.nextLine();
        System.out.println("Geburtsdatum (YYYY-MM-DD):");
        String birthDateString = scanner.nextLine();

        try {
            // Geburtsdatum von String in java.sql.Date umwandeln
            java.sql.Date birthDate = java.sql.Date.valueOf(birthDateString);

            // Student in die Datenbank einfügen
            Student student = new Student(firstName, lastName, birthDate);
            studentRepo.insert(student);
            System.out.println("Student hinzugefügt: " + student);
        } catch (IllegalArgumentException e) {
            System.out.println("Fehler beim Umwandeln des Geburtsdatums: Das Format muss YYYY-MM-DD sein.");
        } catch (Exception e) {
            System.out.println("Fehler beim Hinzufügen des Studenten: " + e.getMessage());
        }
    }


    private void showAllStudents() {
        System.out.println("Alle Studenten:");
        try {
            for (Student student : studentRepo.getAll()) {
                System.out.println(student);
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Abrufen der Studenten: " + e.getMessage());
        }
    }

    private void showStudentDetails() {
        System.out.println("Geben Sie die ID des Studenten ein, den Sie anzeigen möchten:");
        Long studentId = Long.parseLong(scanner.nextLine());
        try {
            Student student = studentRepo.getById(studentId).orElse(null);
            if (student != null) {
                System.out.println("Studenten Details: " + student);
            } else {
                System.out.println("Student mit der ID " + studentId + " nicht gefunden.");
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Abrufen der Studentendetails: " + e.getMessage());
        }
    }

    // Kursmethoden (bereits vorhanden)
    private void addCourse() {
        // ... bestehende Logik für das Hinzufügen von Kursen
    }

    private void showAllCourses() {
        // ... bestehende Logik für das Anzeigen aller Kurse
    }

    private void showCourseDetails() {
        // ... bestehende Logik für das Anzeigen der Kursdetails
    }

    private void updateCourseDetails() {
        // ... bestehende Logik für das Aktualisieren von Kursdetails
    }

    private void deleteCourse() {
        // ... bestehende Logik für das Löschen eines Kurses
    }

    private void courseSearch() {
        // ... bestehende Logik für die Kurssuche
    }

    private void runningCourses() {
        // ... bestehende Logik für laufende Kurse
    }

    private void showMenue() {
        System.out.println("----------- KURS- UND STUDENTENMANAGEMENT -----------");
        System.out.println("(1) Kurs eingeben \t (2) Alle Kurse anzeigen \t (3) Kursdetails anzeigen");
        System.out.println("(4) Kursdetails ändern \t (5) Kurs löschen \t (6) Kurssuche");
        System.out.println("(7) Laufende Kurse \t (8) Student eingeben \t (9) Alle Studenten anzeigen");
        System.out.println("(10) Studenten Details anzeigen \t (x) ENDE");
    }

    private void inputError() {
        System.out.println("Bitte nur die Zahlen der Menüauswahl angeben");
    }
}
