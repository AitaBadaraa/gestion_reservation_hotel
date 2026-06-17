package sn.projet.reservation.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import sn.projet.reservation.model.Booking;
import sn.projet.reservation.model.Room;
import sn.projet.reservation.model.User;


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
    //Reserver une chambre
    public  boolean reserverChambre( User client , Room chambre,LocalDate dateArrive,LocalDate dateDepart ){

            if(!estdisponible(chambre,dateArrive,dateDepart)){
                return false;

            }
            Booking reservation = new Booking(client,chambre,dateArrive,dateDepart);
            DataStore.bookings.add(reservation);
            return true;

    }

    //Annuler une reservation
    public boolean annulerReservation(int idReservation){
        Iterator<Booking> it = DataStore.bookings.iterator();
        while (it.hasNext()){

            Booking b =it.next();
            if(b.getIdReservation() == idReservation){
                it.remove();
                return true;
            }
        }
        return false;
    }

    //Consulter  ses propres reservations
    public List<Booking> getReservationsClient (User client) {

        List<Booking> reservationsClient = new ArrayList<>();
        for(Booking b: DataStore.bookings){

            if(b.getClient().equals(client)){
                reservationsClient.add(b);

            }
        }
        return reservationsClient;
    }

    //Liste ded reservations enregistrer
    public List<Booking> afficherToueslesReservations(){

        return DataStore.bookings;
    }

    //Consulter l'historique des reservations par clients
    public List<Booking> historiqueClient(User client){
        List<Booking> historiqueClient = new ArrayList<>();

        for (Booking b :DataStore.bookigs){
            if(b.getClient().getLogin().equals(login)){
                historiqueClient.add(b);
            }
        }
        return historiqueClient;

    }

    //Forcer l'annulation d'une réservation
    public boolean forcerannulationReservation(int idReservation){
        Iterator<Booking> it = DataStore.bookings.iterator();
        while (it.hasNext()){

            Booking b =it.next();
            if(b.getIdReservation() == idReservation){
                it.remove();
                return true;
            }
        }
        return false;
    }


}
