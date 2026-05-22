package sn.projet.reservation.model;

public class Room {
    private final int numero;
    private double prix;
    private Statut statut;
    private Type type;

    public Room(int numero, double prix, Type type) {
        if (numero <= 0) {
            throw new IllegalArgumentException("Le numero de chambre doit etre positif");
        }
        this.type = type;
        this.numero = numero;
        this.prix = prix;
        this.statut = Statut.DISPONIBLE;
    }

    public int getNumero() {
        return numero;
    }

    public Type getType() {
        return type;
    }

    public double getPrixt() {
        return prix;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public boolean estDisponible() {
        return statut == Statut.DISPONIBLE;
    }

    @Override
    public String toString() {
        return "Chambre: " + numero + " Type: " + type + " Prix: "+ prix + " Statut: " + statut;
    }

}
