package ui;

import dataaccess.DatabaseException;
import dataaccess.MySqlCourseRepository;
import domain.Course;
import domain.CourseType;
import domain.InvalidValueException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.sql.Date;

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
                    addCourse();
                    break;
                case "2":
                    showAllCourses();
                    break;
                case "3":
                    showCoursDetails();
                    break;
                case "4":
                    updateCoursDetails();
                    break;
                case "5":
                    deleteCourse();
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

    private void deleteCourse() {
        System.out.println("Welchen Kurs möchten Sie löschen? Bitte ID eingeben:");
        Long courseIDtoDelete = Long.parseLong(scanner.nextLine());

        try{
            repo.deletebyId(courseIDtoDelete);

        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler beim Löschen: " + databaseException.getMessage());
        }
        catch (Exception e){
            System.out.println("Unbekannter Fehler beim Löschen: " + e.getMessage());
        }
    }

    private void updateCoursDetails() {
        System.out.println("Für welche Kurs-ID möchten Sie die Kursdetails ändern?");
        Long courseId = Long.parseLong(scanner.nextLine());

        try{
            Optional<Course> courseOptional = repo.getById(courseId);
            if(courseOptional.isEmpty()){
                System.out.println("Kurs mit der gegebenen ID nicht in der Datenbank!");
            } else {
                Course course = courseOptional.get();

                System.out.println("Änderungen für folgenden Kurs: ");
                System.out.println(course);

                String name, description, hours, dateFrom, dateTo, courseType;

                System.out.println("Bitte neue Kursdaten angeben (Enter falls keine Änderung gewünscht ist): ");
                System.out.println("Name");
                name = scanner.nextLine();
                System.out.println("Beschreibung");
                description = scanner.nextLine();
                System.out.println("Stundenanzahl");
                hours = scanner.nextLine();
                System.out.println("Startdatum  (YYYY-MM-DD): ");
                dateFrom = scanner.nextLine();
                System.out.println("Enddatum  (YYYY-MM-DD): ");
                dateTo = scanner.nextLine();
                System.out.println("Kurstyp (ZA/BF/FF/OE): ");
                courseType = scanner.nextLine();

                Optional<Course> optionalCourseUpdated = repo.update(
                        new Course(
                                course.getId(),
                                name.equals("") ? course.getName(): name,
                                description.equals("") ? course.getDescription() : description,
                                hours.equals("") ? course.getHours():Integer.parseInt(hours),
                                dateFrom.equals("") ? course.getBegindate():Date.valueOf(dateFrom),
                                dateTo.equals("") ? course.getEnddate():Date.valueOf(dateTo),
                                courseType.equals("") ? course.getCourseType():CourseType.valueOf(courseType)
                        )
                );

                optionalCourseUpdated.ifPresentOrElse(
                        (c)-> System.out.println("Kurs aktualisiert: " + c),
                        ()-> System.out.println("Kurskonnte nicht aktualisiert werden!")
                );

            }
        } catch (IllegalArgumentException illegalArgumentException)
        {
            System.out.println("Eingabefehler: " + illegalArgumentException.getMessage());
        } catch (InvalidValueException invalidValueException) {
            System.out.println("Kursdaten nicht korrekt angegeben: " + invalidValueException.getMessage());
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler beim Einfügen: " + databaseException.getMessage());
        }catch (Exception exception) {
            System.out.println("Unbekannter Fehler beim Einfügen: " + exception.getMessage());

        }
    }

    private void addCourse() {

        String name, description;
        int hours;
        Date datefrom, dateto;
        CourseType courseType;

        try{
            System.out.println("Bitte alle Kursdaten angeben: ");
            System.out.println("Name");
            name = scanner.nextLine();
            if(name.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein!");
            System.out.println("Beschreibung");
            description = scanner.nextLine();
            if(description.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein!");
            System.out.println("Stundenanzahl");
            hours = Integer.parseInt(scanner.nextLine());
            System.out.println("Startdatum (YYYY-MM-DD): ");
            datefrom = Date.valueOf(scanner.nextLine());
            System.out.println("Enddatum (YYYY-MM-DD): ");
            dateto = Date.valueOf(scanner.nextLine());
            System.out.println("Kurstyp: (ZA/BF/FF/OE): ");
            courseType = CourseType.valueOf(scanner.nextLine());

            Optional<Course> optionalCourse = repo.insert(
                    new Course(name, description, hours, datefrom, dateto, courseType)
            );

            if(optionalCourse.isPresent()){
                System.out.println("Kursangelegt: " + optionalCourse.get());
            } else {
                System.out.println("Kurs konnte nicht angelegt werden!");
            }
        } catch (IllegalArgumentException illegalArgumentException)
        {
            System.out.println("Eingabefehler: " + illegalArgumentException.getMessage());
        } catch (InvalidValueException invalidValueException) {
            System.out.println("Kursdaten nicht korrekt angegeben: " + invalidValueException.getMessage());
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler beim Einfügen: " + databaseException.getMessage());
        }catch (Exception exception) {
            System.out.println("Unbekannter Fehler beim Einfügen: " + exception.getMessage());
        }
    }

    private void showCoursDetails() {
        System.out.println("Für welchen Kurs möchten Sie die Kursdetails anzeigen?");
        Long courseId = Long.parseLong(scanner.nextLine());
        try{
            Optional<Course> courseOptional = repo.getById(courseId);
            if (courseOptional.isPresent()) {
                System.out.println(courseOptional.get());
            }else{
                System.out.println("Kurs mit der ID " + courseId + " nicht gefunden!");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei Kurs-Detailanzeige: " + databaseException.getMessage());
        }catch (Exception exception) {
            System.out.println("Unbekannter Fehler bei Kurs-Detailanzeige: " + exception.getMessage());
        }
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
        System.out.println("(1) Kurs ausgeben \t (2) Alle Kurse anzeigen \t" + "(3) Kursdetails anzeigen \t");
        System.out.println("(4) Kursdetails ändern \t (5) Kurs löschen \t (-) xxxx");
        System.out.println("(x) ENDE");
    }
    private void inputError(){
        System.out.println("Bitte nur die Zahlen der Menüauswahl angeben");
    }
}
