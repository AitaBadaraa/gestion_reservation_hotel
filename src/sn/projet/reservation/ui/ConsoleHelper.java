package sn.projet.reservation.ui;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ConsoleHelper {
    private static Scanner scanner = new Scanner(System.in);
    //On demande un entier à l'utilisateur, on redemande tant que la saisie n'est pas valide
    public  static int lireEntier(String message){
        int valeur = 0;
        boolean valide = true;
        while(valide){
            System.out.println(message);
            try {
                valeur = Integer.parseInt(scanner.nextLine());
                valide = false;
            }catch(NumberFormatException e){
                System.out.println("Merci de saisir un nombre valide !!!");
            }
        }
        return valeur;
    }

    public static double lireDouble(String message){
        double valeur = 0;
        boolean valide = true;
        while(valide){
            System.out.println(message);
            try {
                valeur = Double.parseDouble(scanner.nextLine());
                valide = false;
            }catch(NumberFormatException e){
                System.out.println("Merci de saisir un nombre valide !!!");
            }
        }
        return valeur;
    }

    public static LocalDate lireDate(String message){
        LocalDate valeur = null;

        while (valeur == null){
            System.out.println(message);
            try {
                valeur = LocalDate.parse(scanner.nextLine());
            }catch(DateTimeParseException e){
                System.out.println("Merci de saisir une date valide !!!");
            }
        }
        return valeur;
    }

    public static String lireLigne(String message){
        String valeur = null;
        System.out.println(message);

        valeur = scanner.nextLine();
        return valeur;
    }

}
