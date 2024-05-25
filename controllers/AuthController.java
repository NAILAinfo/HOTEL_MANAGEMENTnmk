package controllers;

import models.GestionUtilisateurs;
import models.Utilisateur;
import models.InvalidEmailFormatException;

import java.io.IOException;

public class AuthController {
    private GestionUtilisateurs gestionUtilisateurs;

    public AuthController(GestionUtilisateurs gestionUtilisateurs) {
        this.gestionUtilisateurs = gestionUtilisateurs;
        try {
            this.gestionUtilisateurs.loadUsers();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public GestionUtilisateurs getGestionUtilisateurs() {
        return gestionUtilisateurs;
    }

    public void signup(Utilisateur utilisateur) throws InvalidEmailFormatException {
        validateEmail(utilisateur.getEmail());

        gestionUtilisateurs.ajouterUtilisateur(utilisateur);
        try {
            gestionUtilisateurs.saveUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateEmail(String email) throws InvalidEmailFormatException {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (!email.matches(emailRegex)) {
            throw new InvalidEmailFormatException("Invalid email format. Email must be in the form of username@gmail.com");
        }
    }



    public Utilisateur getUserByUsername(String username) {
        return gestionUtilisateurs.getUtilisateur(username);
    }

    public Utilisateur login(String username, String password) throws Exception {
        Utilisateur user = gestionUtilisateurs.getUtilisateur(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new Exception("Invalid username or password");
        }
    }
}
