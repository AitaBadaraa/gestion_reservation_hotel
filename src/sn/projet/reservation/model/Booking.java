package sn.projet.reservation.model;

import java.time.LocalDate;
import sn.projet.reservation.model.BookingStatus;
import java.time.temporal.ChronoUnit;

public class Booking {

    // "static" signifie qu'il est PARTAGÉ par toutes les instances de Booking
    // → 1ère réservation aura l'id 1, 2ème aura l'id 2, etc.
    private static int compteur = 1;
    private int id;
    private String clientLogin;
    private int numeroChambre;
    private LocalDate dateArrivee;
    private LocalDate dateDepart;
    private BookingStatus statut; // ACTIVE à la création
    private double prixTotal; // calculé automatiquement dans le constructeur

    // le paramètre "prixTotal" a été remplacé par "prixParNuit"
    // car c'est le prix PAR NUIT qui est passé depuis la chambre,
    // et c'est ICI qu'on calcule le prix TOTAL = nuits × prixParNuit
    public Booking(String clientLogin, int numeroChambre, LocalDate dateArrivee, LocalDate dateDepart, double prixParNuit) { // ← prixParNuit, pas prixTotal

        if (clientLogin == null || clientLogin.isBlank()) {
            throw new IllegalArgumentException("Le login du client ne doit pas etre vide");
        }
        if (numeroChambre <= 0) {
            throw new IllegalArgumentException("Le numero de chambre doit etre positif");
        }

        // Génération de l'ID : compteur++ retourne la valeur AVANT d'incrémenter
        // → 1ère résa : id = 1, compteur devient 2
        // → 2ème résa : id = 2, compteur devient 3, etc.
        this.id = compteur++;
        this.clientLogin = clientLogin;
        this.numeroChambre = numeroChambre;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
        this.statut = BookingStatus.ACTIVE;

        // Calcul du prix total :
        // ChronoUnit.DAYS.between(dateArrivee, dateDepart) = nombre de nuits
        // ex: arrivée 01/06, départ 05/06 → 4 nuits
        long nuits = ChronoUnit.DAYS.between(dateArrivee, dateDepart);
        this.prixTotal = nuits * prixParNuit;
    }

    // Nombre de nuits
    // between(A, B) calcule B - A
    // il faut between(dateArrivee, dateDepart) pour avoir un nombre POSITIF
    public long getNuit() {
        return ChronoUnit.DAYS.between(dateArrivee, dateDepart); 
    }

    //  Détection de chevauchement 
    // Retourne true si cette réservation ACTIVE chevauche la plage [from, to)
    // Logique : deux plages se chevauchent si :
    // - notre arrivée est AVANT la fin de la plage demandée (dateArrivee < to)
    // - notre départ est APRÈS le début de la plage demandée (dateDepart > from)
    // On vérifie EstActive() en premier : une résa annulée ne bloque aucune date
    // empêcher deux clients de réserver la même chambre sur des dates qui se croisent.
    public boolean chevauchement(LocalDate from, LocalDate to) {
        return estActive()
                && dateArrivee.isBefore(to) // notre arrivée est avant leur départ
                && dateDepart.isAfter(from); // notre départ est après leur arrivée
    }

    public void annuler() {
        this.statut = BookingStatus.ANNULEE;
    }

    public boolean estActive() {
        return statut == BookingStatus.ACTIVE;
    }

    public int getId() {
        return id;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    public int getNumeroChambre() {
        return numeroChambre;
    }

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public BookingStatus getStatut() {
        return statut;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    @Override
    public String toString() {
        return "#" + id
                + " | Client: " + clientLogin
                + " | Chambre: " + numeroChambre
                + " | Arrivée: " + dateArrivee
                + " | Départ: " + dateDepart
                + " | Nuits: " + getNuit()
                + " | Prix: " + prixTotal + " FCFA"
                + " | Statut: " + statut;
    }
}