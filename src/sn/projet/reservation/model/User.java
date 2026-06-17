package sn.projet.reservation.model;

public class User {
    private String login;
    private Role role;
    private String mdp;

    // Hashage du mot de passe
    public static String hashMdp(String rawMdp) {
        return Integer.toHexString(rawMdp.hashCode());
    }

    public User(String login, Role role, String mdp) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Le login ne doit pas etre vide");
        }
        this.login = login;
        this.role = role;
        this.mdp = hashMdp(mdp);
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }

    public String getMdp() {
        return mdp;
    }

    // Hache le mot de passe saisi et compare avec celui stocké.
    public boolean verifMdp(String rawMdp) {
        return this.mdp.equals(hashMdp(rawMdp));
    }

    public boolean estAdmin() {
        return role == Role.ADMIN;
    }

    public boolean estClient() {
        return role == Role.CLIENT;
    }

    @Override
    public String toString() {
        return "Login: " + login + " Role: " + role;
    }
}
