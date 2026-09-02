package sn.projet.reservation.service;

import sn.projet.reservation.model.Room;
import sn.projet.reservation.ui.ConsoleHelper;
import sn.projet.reservation.model.Type;
import sn.projet.reservation.model.Statut;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class RoomService {


    //Remplir infos chambre
    public Room remplirChambre(){
//        System.out.print("Numéro : ");
        int numero = ConsoleHelper.lireEntier("Numero : ");

//        System.out.print("Type (SIMPLE/DOUBLE/SUITE) : ");
        Type type = Type.valueOf(ConsoleHelper.lireLigne("Type (SIMPLE/DOUBLE/SUITE) : ").toUpperCase());

//        System.out.print("Prix : ");
        double prix = ConsoleHelper.lireDouble("Prix : ");

        Room nouvelleChambre = new Room(numero, type, prix, Statut.DISPONIBLE);
        return nouvelleChambre;
    }

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
    public boolean ajouterChambre(){
        Room nouvelleChambre = remplirChambre();

        if(chercherChambre(nouvelleChambre.getNumero()) != null){

            System.out.println("Cette chambre existe deja");
            return false;

        }
        //Ajout de la chambre
        DataStore.rooms.add(nouvelleChambre);

        System.out.println("Chambre ajoutee avec succes");
        return true;
    }

    // Modifier une chambre
    public boolean modifierChambre(int numero, Type type, double prix, Statut statut){

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

    //MENU CHAMBRE

    public void menuChambres(){
        int choix;
        do{
            System.out.println("\n ===MENU CHAMBRES ===");
            System.out.println("1. Ajouter chambre");
            System.out.println("2. Modifier chambre");
            System.out.println("3. Supprimer chambre");
            System.out.println("4. Afficher chambres");
            System.out.println("5. Retour");

//            choix = Integer.parseInt(scanner.nextLine());
            choix = ConsoleHelper.lireEntier("Choix : ");
            switch(choix){
                case 1:
                    ajouterChambre();
                    break;
                case 2:
//                    System.out.println("Le numero de la chambre a modifier ");
                    int numeroMod = ConsoleHelper.lireEntier("Numero de la chambre a modifier : ");

//                    System.out.println("Le nouveau type (SIMPLE/DOUBLE/SUITE) ");
                    Type nouveauType = Type.valueOf(ConsoleHelper.lireLigne("Type (SIMPLE/DOUBLE/SUITE) : ").toUpperCase());

//                    System.out.println("Nouveau prix : ");
                    double nouveauPrix = ConsoleHelper.lireDouble("Nouveau prix : ");

//                    System.out.println("Nouveau statut (DISPONIBLE/OCCUPEE) : ");

                    Statut nouveauStatut = Statut.valueOf(ConsoleHelper.lireLigne("Nouveau statut (DISPONIBLE/OCCUPEE) : ").toUpperCase());

                    if (modifierChambre(numeroMod, nouveauType, nouveauPrix, nouveauStatut)) {
                        System.out.println("Chambre modifiée.");
                    } else {
                        System.out.println("Chambre introuvable.");
                    }

                    break;
                case 3:
//                    System.out.print("Numéro de la chambre à supprimer : ");
                    int numeroSupp = ConsoleHelper.lireEntier("Numero de la chambre a supprime : ");
                    if (supprimerChambre(numeroSupp)) {
                        System.out.println("Chambre supprimée.");
                    } else {
                        System.out.println("Chambre introuvable.");
                    }

                    break;
                case 4:
                    for(Room r : afficherToutesChambres()){
                        System.out.println(r);
                    }
                    break;
                case 5:
                    break;

                default:
                    System.out.println("Choix invalide");

            }

        }while (choix != 5);
    }

    public void menuChambresClient(){
        int choix;
        do{
            System.out.println("\n ===MENU CHAMBRES ===");
            System.out.println("1. Consulter chambres disponible");
            System.out.println("2. Rechercher chambres");
            System.out.println("3. Retour");

//            choix = Integer.parseInt(scanner.nextLine());
            choix = ConsoleHelper.lireEntier("Choix : ");
            switch(choix){
                case 1:
                    for(Room r : getChambreDisponible()){
                        System.out.println(r);
                    }
                    break;
                case 2:
//                    System.out.println("Type recherché (SIMPLE/DOUBLE/SUITE) : ");
                    Type type = Type.valueOf(ConsoleHelper.lireLigne("Type (SIMPLE/DOUBLE/SUITE) : ").toUpperCase());
//                    System.out.println("Prix maximum : ");
                    double prixMax = ConsoleHelper.lireDouble("Le prix a recherche : ");
                    for (Room r : rechercherChambre(type, prixMax)) {
                        System.out.println(r);
                    }
                    break;
                case 3:
                    break;

                default:
                    System.out.println("Choix invalide");

            }

        }while (choix != 3);
    }

}