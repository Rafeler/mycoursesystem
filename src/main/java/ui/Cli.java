package ui;

import java.util.Scanner;

public class Cli {
    Scanner scanner;

    public Cli() {
        this.scanner = new Scanner(System.in);
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
                    System.out.println("Alle Kurse anzeigen");
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
