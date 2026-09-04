package sn.projet.reservation;
import java.util.Scanner;
import sn.projet.reservation.service.RoomService;
import sn.projet.reservation.service.BookingService;
import sn.projet.reservation.ui.AdminMenu;
import sn.projet.reservation.ui.ClientMenu;
import sn.projet.reservation.ui.ConsoleHelper;
import sn.projet.reservation.service.AuthService;
import sn.projet.reservation.model.Role;
import sn.projet.reservation.model.User;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RoomService roomService = new RoomService();
        BookingService bookingService = new BookingService();
        AuthService authService =  new AuthService();
        AdminMenu adminmenu = new AdminMenu(scanner, bookingService, roomService,authService);
        ClientMenu clientmenu = new ClientMenu(scanner,bookingService,roomService,authService);

        int choix;
       do{
           System.out.println("\n ===== Système de Réservation d'Hôtel === ");
           System.out.println(" 1. Connexion");
           System.out.println(" 2. Inscription");
           System.out.println(" 3. Quitter");

           choix = ConsoleHelper.lireEntier("Choix :");
           switch (choix){
               case 1:
                   String login = ConsoleHelper.lireLigne("Login : ");
                   String motDePasse = ConsoleHelper.lireLigne("Mot de passe : ");
                   User utilisateur = authService.connecter(login, MotDePasse);
                   if (utilisateur == null){
                       System.out.println("L'utilisateur n'existe pas.");
                   } else if (utilisateur.getRole() == Role.ADMIN) {
                       adminmenu.afficherMenuAdmin();
                   }else {
                       clientmenu.afficherMenuClient(utilisateur);
                   }
                   break;
                case 2:
                    String nouveauLogin = ConsoleHelper.lireLigne("Choisissez le login : ");
                    String nouveauMotDePasse = ConsoleHelper.lireLigne("Choisissez un mot de passe : ");
                    boolean success = authService.inscrire(nouveauLogin, nouveauMotDePasse);
                    if (success){
                        System.out.println("Inscription reussi !");
                    }else {
                        System.out.println("Ce login existe deja  ! ");
                    }
                    break;
                 case 3:
                     System.out.println("Au revoir!");
                     break;
                     default:
                         System.out.println("Choix invalide");
           }

       }while(choix!= 3);


        
    }
}
