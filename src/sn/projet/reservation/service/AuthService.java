package sn.projet.reservation.service;

import sn.projet.reservation.model.Role;
import sn.projet.reservation.model.User;

// Gere l'authentification et l'inscription des utilisateurs
public class AuthService {
    private final DataStore store = DataStore.getInstance();
    private User currentUser = null;

    // Connexion
    public boolean login(String login, String password){
        User u = store.findUser(login);
        if (u != null && u.verifMdp(password)) {
            currentUser = u;
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    // Inscription (Client uniquement)
    public enum RegisterResult {
        SUCCESS,
        LOGIN_TAKEN,
        LOGIN_EMPTY,
        PASSWORD_TOO_SHORT
    }

    public RegisterResult register(String login, String password) {
        if (login == null || login.isBlank())     return RegisterResult.LOGIN_EMPTY;
        if (password == null || password.length() < 4) return RegisterResult.PASSWORD_TOO_SHORT;
        if (store.loginExists(login))              return RegisterResult.LOGIN_TAKEN;

        store.addUser(new User(login, Role.CLIENT, password));
        return RegisterResult.SUCCESS;
    }

    // Session
    public User getCurrentUser()    { return currentUser; }
    public boolean isLoggedIn()     { return currentUser != null; }
    public boolean isAdmin()        { return isLoggedIn() && currentUser.isAdmin(); }
    public boolean isClient()       { return isLoggedIn() && currentUser.isClient(); }

}
