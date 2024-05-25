package controllers;

import models.GestionChambres;
import models.GestionReservations;
import models.Reservation;

import java.io.IOException;
import java.util.Date;
import models.RoomUnavailableException;
import models.GestionChambres;

public class ClientController {
    private GestionReservations gestionReservations;
    private GestionChambres gestionChambres;
    private GestionChambres getType;


    public ClientController(GestionReservations gestionReservations, GestionChambres gestionChambres) {
        this.gestionReservations = gestionReservations;
        this.gestionChambres = gestionChambres;
    }

    public void ajouterReservation(Reservation reservation) {
        gestionReservations.ajouterReservation(reservation);
        // Ensure that reservations are saved
        try {
            gestionReservations.saveReservations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void declineReservation(Reservation reservation) {
        gestionReservations.declineReservation(reservation.getId());
        // Ensure that reservations are saved
        try {
            gestionReservations.saveReservations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public boolean annulerReservation(int reservationId) {
        Reservation reservation = gestionReservations.getReservation (reservationId);
        if (reservation != null) {
            Date now = new Date ();
            Date dateDebut = new Date (reservation.getDateDebut ()); // Assurez-vous que getDateDebut() retourne un long ou une date compatible
            if (now.after (dateDebut)) {
                return false;
            }
            gestionReservations.supprimeReservation (reservationId);
            return true;
        }
        return false;
    }

    public void modifierReservation(int id, Reservation reservation) throws Exception {
        Reservation existante = gestionReservations.getReservation (id);
        if (existante == null) {
            throw new Exception ("La réservation n'existe pas.");
        }
        gestionReservations.modifierReservation (id, reservation);
    }
}
