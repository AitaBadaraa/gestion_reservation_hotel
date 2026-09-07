package sn.projet.reservation.service;

import sn.projet.reservation.model.*;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    // Listes Principales
    private final List<User> users = new ArrayList<>();
    private final List<Room> rooms = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    private static DataStore instance;
    private DataStore(){
        seedData();
    }

    public static DataStore getInstance(){
        if(instance == null){
            instance = new DataStore();
        }
        return instance;
    }

    // Données initiales
    private void seedData(){
        // Admin par defaut
        users.add(new User("admin", Role.ADMIN, "admin"));
        // Quelques clients de démonstration
        users.add(new User("alice", Role.CLIENT, "client"));
        users.add(new User("bob",   Role.CLIENT,   "client"));

        // Chambres initiales
        rooms.add(new Room(101,25000, Type.SIMPLE));
        rooms.add(new Room(102, 25000, Type.SIMPLE));
        rooms.add(new Room(201, 45000, Type.DOUBLE));
        rooms.add(new Room(202, 45000, Type.DOUBLE));
        rooms.add(new Room(301,  90000, Type.SUITE));
        rooms.add(new Room(302,  90000, Type.SUITE));
    }

    // Acces aux utilisateurs
    public  List<User> getUsers(){
        return users;
    }

    public User findUser(String login) {
        return users.stream()
                .filter(u -> u.getLogin().equalsIgnoreCase(login))
                .findFirst().orElse(null);
    }

    public boolean loginExists(String login) {
        return findUser(login) != null;
    }

    public void addUser(User u) { users.add(u); }

    // Acces aux chambres
    public List<Room> getRooms() { return rooms; }

    public Room findRoom(int number) {
        return rooms.stream()
                .filter(r -> r.getNumero() == number)
                .findFirst().orElse(null);
    }

    public boolean roomExists(int number) {
        return findRoom(number) != null;
    }

    public void addRoom(Room r) { rooms.add(r); }

    public boolean removeRoom(int number) {
        return rooms.removeIf(r -> r.getNumero() == number);
    }

    // Acces aux reservations
    public List<Booking> getBookings() { return bookings; }

    public Booking findBooking(int id) {
        return bookings.stream()
                .filter(b -> b.getId() == id)
                .findFirst().orElse(null);
    }

    public void addBooking(Booking b) { bookings.add(b); }

}
