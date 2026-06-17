package sn.projet.reservation.service;

import sn.projet.reservation.model.Room;
import sn.projet.reservation.model.Type;
import sn.projet.reservation.model.Statut;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class RoomService {

    // Chercher une chambre
    public Room chercherChambre(int numero){

        for(Room r : DataStore.rooms){

            if(r.getNumero() == numero){
                return r;
            }

        }

        return null;
    }

    // Ajouter une chambre
    public boolean ajouterChambre(Room chambre){

        if(chercherChambre(chambre.getNumero()) != null){

            System.out.println("Cette chambre existe deja");
            return false;

        }

        DataStore.rooms.add(chambre);

        System.out.println("Chambre ajoutee avec succes");
        return true;
    }

    // Modifier une chambre
    public boolean modifierChambre(
            int numero,
            Type type,
            double prix,
            Statut statut){

        Room chambre = chercherChambre(numero);

        if(chambre == null){
            return false;
        }

        chambre.setType(type);
        chambre.setPrix(prix);
        chambre.setStatut(statut);

        return true;
    }

    // Supprimer une chambre
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

    // Afficher toutes les chambres
    public List<Room> afficherToutesChambres(){

        return DataStore.rooms;

    }

    // Chambres disponibles
    public List<Room> getChambreDisponible(){

        List<Room> disponibles = new ArrayList<>();

        for(Room r : DataStore.rooms){

            if(r.getStatut().toString().equals("DISPONIBLE")){

                disponibles.add(r);

            }

        }

        return disponibles;
    }
    //Rechercher une chambre

    public List<Room> rechercherChambre(Type type, double prixMax){

        List<Room> resultat = new ArrayList<>();

        for(Room r : DataStore.rooms){

            if(r.getType() == type && r.getPrix() <= prixMax){

                resultat.add(r);

            }

        }

        return resultat;
    }

}