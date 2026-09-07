package sn.projet.reservation;

import sn.projet.reservation.model.Role;
import sn.projet.reservation.model.Room;
import sn.projet.reservation.model.User;
import sn.projet.reservation.model.Type;

import java.time.LocalDate;

import sn.projet.reservation.model.Booking;

public class Main {
    public static void main(String[] args) {
        Booking b1 = new Booking("aaytaah123", 101, LocalDate.of(2025, 6, 1), LocalDate.of(2025,6,5), 50000);
        Booking b2 = new Booking("kineeee123", 101, LocalDate.of(2025, 6, 3), LocalDate.of(2025,6,8), 50000);
        System.out.println(b1);
        System.out.println(b2);

        Boolean conflit = b1.chevauchement(LocalDate.of(2025, 6, 3), LocalDate.of(2025,6,8));
        System.out.println("Conflit : " + conflit);
    }
}
