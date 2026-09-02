package sn.projet.reservation.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import sn.projet.reservation.model.Booking;
import sn.projet.reservation.model.Room;
import sn.projet.reservation.ui.ConsoleHelper;
import sn.projet.reservation.model.User;
import java.util.Iterator;


public class BookingService {



    //Chambre disponible  libre pour les dates demandées
    public boolean estdisponible(Room chambre,LocalDate dateArrive,LocalDate dateDepart){
        for(Booking b: DataStore.bookings){

            if(b.getChambre().getNumero() == chambre.getNumero() ){

                if(dateArrive.isBefore(b.getDateDepart())
                        &&
                        dateDepart.isAfter(b.getDateArrivee())){
                    return false;
                }
            }
        }
        return true;
    }

    // Demande les dates d'arrivée / départ à l'utilisateur, avec validation du format et de l'ordre
    //retourne un tableau contenant les deux dates
    private LocalDate[] saisirDates() {
        LocalDate dateArrivee = ConsoleHelper.lireDate("Date d'arrivée (YYYY-MM-DD) : ");
        LocalDate dateDepart = null;

        while (dateDepart == null) {
            LocalDate tmp = ConsoleHelper.lireDate("Date de départ (YYYY-MM-DD) : ");
            if (!tmp.isAfter(dateArrivee)) {
                System.out.println("La date de départ doit être après la date d'arrivée.");
            } else {
                dateDepart = tmp;
            }
        }

        return new LocalDate[]{dateArrivee, dateDepart};
    }
    //Reserver une chambre
    public  boolean reserverChambre( User client , Room chambre ){

//            remplirReservation( client, chambre, dateArrive, dateDepart);
//            Booking reservation = new Booking(client,chambre,dateArrive,dateDepart);
//            DataStore.bookings.add(reservation);
//            return true;
        LocalDate[] dates = saisirDates();
        LocalDate dateArrivee = dates[0];
        LocalDate dateDepart = dates[1];

        if (!estdisponible(chambre, dateArrivee, dateDepart)) {
            System.out.println("Chambre indisponible pour ces dates.");
            return false;
        }
        //Disponibilite de la ckambre et creation d'un nouveau objet et l'ajout egalement
        Booking reservation = new Booking(client, chambre, dateArrivee, dateDepart);
        DataStore.bookings.add(reservation);
        System.out.println("Réservation effectuée avec succès.");
        return true;

    }
    // Recherche une chambre par numéro (accès direct à DataStore, partagé avec RoomService)
    private Room trouverChambreParNumero(int numero) {
        for (Room r : DataStore.rooms) {
            if (r.getNumero() == numero) {
                return r;
            }
        }
        return null;
    }

    //Annuler une reservation
    public boolean annulerReservation(int idReservation){
        Iterator<Booking> it = DataStore.bookings.iterator();
        //on verifie s'il reste des elements a parcourir
        while (it.hasNext()){

            Booking b =it.next();//on recupere l'element suivant
            if(b.getIdReservation() == idReservation){
                it.remove();
                return true;
            }
        }
        return false;
    }

    //Consulter  ses propres reservations
    public List<Booking> getReservationsClient (User client) {

        //Creation d'une liste vide
        List<Booking> reservationsClient = new ArrayList<>();
        for(Booking b: DataStore.bookings){

            if(b.getClient().equals(client)){
                reservationsClient.add(b);

            }
        }
        return reservationsClient;
    }

    //Liste des reservations enregistrer
    public List<Booking> afficherToueslesReservations(){

        return DataStore.bookings;
    }

    //Consulter l'historique des reservations par clients
    public List<Booking> historiqueClient(User client){
        List<Booking> historiqueClient = new ArrayList<>();

        for (Booking b :DataStore.bookings){
            if(b.getClient().getLogin().equals(client.getlogin())){
                historiqueClient.add(b);
            }
        }
        return historiqueClient;

    }

    //Forcer l'annulation d'une réservation
    public boolean forcerannulationReservation(int idReservation){
        Iterator<Booking> it = DataStore.bookings.iterator();
        while (it.hasNext()){

            Booking b = it.next();
            if(b.getIdReservation() == idReservation){
                it.remove();
                return true;
            }
        }
        return false;
    }
    //MENU RESERVATION ADMIN

    public void menuReservations(){
        int choix;
        do{
            System.out.println("\n ===MENU RESERVATION===");
            System.out.println("1. Afficher toutes les reservations enregistres");
            System.out.println("2. Consulter l'historique des client");
            System.out.println("3. Forcer l'annulation d'une reservation");
            System.out.println("4. Retour");

//            choix = Integer.parseInt(scanner.nextLine());
            choix = ConsoleHelper.lireEntier("Choix : ");
            switch(choix){
                case 1:
                    List<Booking> Toutes = afficherToueslesReservations();
                    for(Booking b : Toutes){
                        System.out.println(b);
                    }
                    break;
                case 2:
//                    System.out.println("Login du client");
//                    String login = scanner.nextLine();
                    String login = ConsoleHelper.lireLigne("Login du client : ");
                    User client = DataStore.trouverUserParLogin(login);
                    if (client != null){
                        for(Booking b : historiqueClient(client)){
                            System.out.println(b);
                        }
                    }else {
                        System.out.println("Client introuvable");
                    }

                    break;
                case 3:
//                    System.out.println("ID de la reservation a annuler");
//                    int idAnnuler = Integer.parseInt(scanner.nextLine());
                    int idAnnuler = ConsoleHelper.lireEntier("ID de la réservation à annuler :");
                    if(forcerannulationReservation(idAnnuler)){
                        System.out.println("Reservation annulee");
                    }else {
                        System.out.println("Reservation introuvable");
                    }
                    break;
                case 4:
                    break;

                default:
                    System.out.println("Choix invalide");

            }

        }while (choix != 4);
     }

     //MENU RESERVATION CLIENT
    public void menuReservationsClient(User client){
        int choix;
        do{
            System.out.println("\n ===MENU RESERVATION===");
            System.out.println("1. Reserver une chambres");
            System.out.println("2. Verifier disponibilite des chambres ");
            System.out.println("3. Annuler une reservation existante");
            System.out.println("4. Consulter ses propores reservations");
            System.out.println("5. Retour");


//            choix = Integer.parseInt(scanner.nextLine());
            choix = ConsoleHelper.lireEntier("Choix : ");
            switch(choix){
                case 1:
//                    System.out.println("Numero de la chambre");
//                    int numero = Integer.parseInt(scanner.nextLine());
                    int numero = ConsoleHelper.lireEntier("Numero : ");
                    Room chambre = trouverChambreParNumero(numero);
                    if(chambre == null){
                        System.out.println("La chambre est introuvable");
                    }else{
                        reserverChambre(client,chambre);
                    }

                    break;
                case 2:
//                    System.out.print("Numéro de la chambre : ");
//                    int numerorechercher = Integer.parseInt(scanner.nextLine());
                    int numerorechercher = ConsoleHelper.lireEntier("Numero : ");
                    Room chambrerechercher = trouverChambreParNumero(numerorechercher);
                    if (chambrerechercher == null) {
                        System.out.println("Chambre introuvable.");
                    } else {
                        LocalDate[] dates = saisirDates();
                        boolean dispo = estdisponible(chambrerechercher, dates[0], dates[1]);
                        System.out.println(dispo ? "Disponible." : "Non disponible.");
                    }
                    break;
                case 3:
//                    System.out.print("ID de la réservation à annuler : ");
//                    int idAnnuler = Integer.parseInt(scanner.nextLine());
                    int idAnnuler = ConsoleHelper.lireEntier("ID de la réservation à annuler :");

                    if (annulerReservation(idAnnuler)) {
                        System.out.println("Réservation annulée.");
                    } else {
                        System.out.println("Réservation introuvable.");
                    }
                    break;
                case 4:
                    for (Booking b : getReservationsClient(client)) {
                        System.out.println(b);
                    }
                    break;
                case 5:
                    break;

                default:
                    System.out.println("Choix invalide");

            }

        }while (choix != 5);
    }


}
