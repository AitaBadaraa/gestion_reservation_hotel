package sn.projet.reservation;
import java.util.ArrayList;
import sn.projet.reservation.model.Room;
import sn.projet.reservation.model.Type;
import sn.projet.reservation.model.Statut;

import sn.projet.reservation.service.RoomService;


import sn.projet.reservation.model.Role;
import sn.projet.reservation.model.Room;
import sn.projet.reservation.model.User;
import sn.projet.reservation.model.Type;

public class Main {
    public static void main(String[] args) {
        RoomService roomService = new RoomService();
        Room chambre = new Room(101, Type.SIMPLE, 25000, Statut.DISPONIBLE);

        roomService.ajouterChambre(chambre);
        roomService.afficherToutesChambres();
        
    }
}
