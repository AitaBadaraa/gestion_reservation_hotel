package sn.projet.reservation.service;
import sn.projet.reservation.model.Room;
import java.util.List;
import java.util.Iterator;

public class RoomService {
    // Ajouter une chambre

    //Chercher  une chambre

    public Room chercherChambre(int numero){
        for(Room r : DataStore.rooms){
            if(r.getNumero() == numero){
                return r;
            }
        }
        return null;
    }
    public boolean ajouterchambre(Room chambre){

            if(chercherChambre(chambre.getNumero()) != null){
                System.out.println("Cette chambre existe deja ");
                return false;
            }
            DataStore.rooms.add(chambre);
            System.out.println("Chambre ajouter avec succes ");
             return true;
    }

    //Modifier une chambre

    public  boolean modifierChambre(int numero, String type, double  prix, String statut){
        Room chambre = chercherChambre(numero);
                if(chambre == null){
                    return false;
                }
                chambre.setType(type);
                chambre.setPrix(prix);
                chambre.setStatut(statut);

                return true;
    }

    //Supprimer une chambre

    public boolean supprimerChambre(int numero){

        Iterator<Room> it = DataStore.rooms.iterator();
        while(it.hasNext()){
            Room r = it.next();

            if(r.getNumero() == numero){
                it.remove();
                return true;
            }
        }
        return false;

    }

    //Afficher liste des tout les chambre

    public  List<Room> afficherToutesChambres(){
        return DataStore.rooms;
    }

}