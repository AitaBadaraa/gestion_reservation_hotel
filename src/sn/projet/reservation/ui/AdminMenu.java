package sn.projet.reservation.ui;
import sn.projet.reservation.service.RoomService;
import sn.projet.reservation.service.BookingService;
import sn.projet.reservation.service.AuthService;
import java.util.Scanner;
import sn.projet.reservation.ui.ConsoleHelper;


public class AdminMenu {
    private Scanner scanner;
    private BookingService bookingService;
    private RoomService roomService;
    private AuthService authService;

    //Constructeur
    public AdminMenu(Scanner scanner, BookingService bookingService,RoomService roomService,AuthService authService ) {
        this.scanner = scanner;
        this.bookingService = bookingService;
        this.roomService = roomService;
        this.authService = authService;
    }
    //Affichage de la MenuAdmin
    public void afficherMenuAdmin(){
        int choix;
       do{
           System.out.println("\n==== MENU ADMINISTRATEUR ==== ");
           System.out.println(" 1. Gerer les chambres");
           System.out.println(" 2. Gerer les reservations");
           System.out.println(" 3. Voir tous les utilisateurs");
           System.out.println(" 4. Deconnexion");

//           choix = Integer.parseInt(scanner.nextLine());
           choix = ConsoleHelper.lireEntier("Choix : ");
           switch(choix){
               case 1:
                   roomService.menuChambres();
                   break;
               case 2:
                   bookingService.menuReservations();
                   break;
               case 3:
                   authService.afficherTousLesUtilisateurs();
                   break;
               case 4:
                   System.out.println("Déconnexion...");
                   break;
                   default:
                       System.out.println("Le choix n'existe pas");

           }
       }while(choix!=4);
    }

}
