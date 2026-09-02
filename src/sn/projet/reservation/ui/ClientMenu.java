package sn.projet.reservation.ui;
import sn.projet.reservation.service.RoomService;
import sn.projet.reservation.service.BookingService;
import sn.projet.reservation.service.AuthService;
import sn.projet.reservation.model.Room;
import sn.projet.reservation.model.Booking;
import sn.projet.reservation.model.Type;
import sn.projet.reservation.model.User;
import java.util.Scanner;
import sn.projet.reservation.ui.ConsoleHelper;

public class ClientMenu {
    private Scanner scanner;
    private BookingService bookingService;
    private RoomService roomService;
    private AuthService authService;

    public ClientMenu(Scanner scanner,BookingService bookingService,RoomService roomService, AuthService authService){
        this.scanner = scanner;
        this.bookingService = bookingService;
        this.roomService = roomService;
        this.authService = authService;
    }
    public void afficherMenuClient(User client){
        int choix;
        do{
            System.out.println("\n==== MENU CLIENT ==== ");
            System.out.println(" 1. Consulter les chambres disponibles");
            System.out.println(" 2. Rechercher une chambre");
            System.out.println(" 3. Reserver une chambre");
            System.out.println(" 4. Voir mes reservations");
            System.out.println(" 5. Annuler une reservation");
            System.out.println(" 6. Deconnexion");

            choix = ConsoleHelper.lireEntier("Choix : ");
            switch (choix){
                case 1:
                    for (Room r : roomService.getChambreDisponible()) {
                        System.out.println(r);
                    }
                    break;
                case 2:
                    Type type = Type.valueOf(ConsoleHelper.lireLigne("Type (SIMPLE/DOUBLE/SUITE) : ").toUpperCase());
                    double prixMax = ConsoleHelper.lireDouble("Prix maximum : ");
                    for(Room r : roomService.rechercherChambre(type,prixMax)){
                        System.out.println(r);
                    }
                    break;
                case 3:
//                    System.out.println("Numero de la chambre");
                    int numChambre = ConsoleHelper.lireEntier("Numero de la chambre : ");
                    Room chambre = roomService.chercherChambre(numChambre);
                    if(chambre == null){
                        System.out.println("La chambre introuvable");
                    }else {
                        bookingService.reserverChambre(client, chambre);
                    }

                    break;
                case 4:
                    for(Booking b : bookingService.getReservationsClient(client)){
                        System.out.println(b);
                    }
                    break;
                case 5:
//                    System.out.print("ID de la réservation à annuler : ");
                    int idAnnuler = ConsoleHelper.lireEntier("ID de la réservation à annuler :");
                    if (bookingService.annulerReservation(idAnnuler)) {
                        System.out.println("Réservation annulée.");
                    } else {
                        System.out.println("Réservation introuvable.");
                    }
                    break;
                case 6:
                    System.out.println("Déconnexion...");
                    break;
                    default:
                        System.out.println("Le choix n'existe pas");
            }

        }while (choix != 6);
    }
    
}
