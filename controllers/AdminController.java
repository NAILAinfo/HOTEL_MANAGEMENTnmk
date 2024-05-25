package controllers;

import models.Chambre;
import models.GestionChambres;
import models.GestionReservations;
import models.Reservation;
import models.GestionUtilisateurs;
import java.io.IOException;
import java.util.HashMap;

public class AdminController {
    private final AuthController authController;
    private GestionChambres gestionChambres;
    private GestionReservations gestionReservations;
    private GestionUtilisateurs gestionUtilisateur;

    public AdminController(GestionChambres gestionChambres, GestionReservations gestionReservations, GestionUtilisateurs gestionUtilisateur) {
        this.gestionChambres = gestionChambres;
        this.gestionReservations = gestionReservations;
        try {
            this.gestionChambres.loadChambres();
            this.gestionReservations.loadReservations();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } this.authController = new AuthController(new GestionUtilisateurs());
    this.gestionUtilisateur = gestionUtilisateur;}

    public GestionChambres getGestionChambres() {
        return gestionChambres;
    }

    public GestionReservations getGestionReservations() {
        return gestionReservations;
    }

    public void addReservation(Reservation reservation) {
        gestionReservations.ajouterReservation(reservation);
        try {
            gestionReservations.saveReservations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterChambre(Chambre chambre) throws IOException {
        if (chambre == null) {
            throw new IllegalArgumentException("La chambre ne peut pas être null.");
        }

        gestionChambres.ajouterChambre(chambre);
        gestionChambres.saveChambres();
    }
    public AuthController getAuthController() {
        return authController;
    }

    public GestionUtilisateurs getGestionUtilisateurs() {
        return authController.getGestionUtilisateurs();
    }
    public void acceptReservation(int reservationId) {
        gestionReservations.acceptReservation(reservationId);
        try {
            gestionReservations.saveReservations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void declineReservation(int reservationId) {
        gestionReservations.declineReservation(reservationId);
        try {
            gestionReservations.saveReservations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeReservation(int reservationId) {
        gestionReservations.getAllReservations().remove(reservationId);
        try {
            gestionReservations.saveReservations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Reservation> getReservations() {
        return gestionReservations.getAllReservations();
    }



    public void saveChambres() throws IOException {
        gestionChambres.saveChambres();
    }

    public void updateChambreStatus(int numero, String status) {
        Chambre chambre = getGestionChambres().getChambre(numero);
        if (chambre != null) {
            chambre.setStatus(status);
            try {
                saveChambres();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean supprimerChambre(int numero) {
        for (Reservation reservation : gestionReservations.getAllReservations().values()) {
            if (reservation.getNumeroChambre() == numero) {
                return false;
            }
        }
        gestionChambres.supprimerChambre(numero);
        return true;
    }

    public boolean modifierChambre(int numero, Chambre chambre) {
        for (Reservation reservation : gestionReservations.getAllReservations().values()) {
            if (reservation.getNumeroChambre() == numero) {
                return false;
            }
        }
        gestionChambres.modifierChambre(numero, chambre);
        return true;
    }

    public void loadChambres() throws IOException, ClassNotFoundException {
        gestionChambres.loadChambres();
    }
}
