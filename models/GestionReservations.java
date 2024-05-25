package models;

import java.io.*;
import java.util.HashMap;

public class GestionReservations {
    private HashMap<Integer, Reservation> reservations;
    private static final String RESERVATIONS_FILENAME = "reservations.dat";

    public GestionReservations() {
        this.reservations = new HashMap<>();
    }

    public void ajouterReservation(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }

    public Reservation getReservation(int id) {
        return reservations.get(id);
    }

    public HashMap<Integer, Reservation> getAllReservations() {
        return reservations;
    }

    public void modifierReservation(int id, Reservation reservation) {
        reservations.put(id, reservation);
    }

    public void acceptReservation(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.setStatus("Accepted");
        }
    }

    public void declineReservation(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.setStatus("Declined");
        }
    }

    public void loadReservations() throws IOException, ClassNotFoundException {
        File file = new File(RESERVATIONS_FILENAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                reservations = (HashMap<Integer, Reservation>) ois.readObject();
            }
        } else {
            reservations = new HashMap<>();
            saveReservations(); // Create an empty file if it does not exist
        }
    }


    public void saveReservations() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RESERVATIONS_FILENAME))) {
            oos.writeObject(reservations);
        }
    }

    public void supprimeReservation(int reservationId) {
    }
    public boolean isRoomAvailable(String type) {
        // Parcourir les réservations et vérifier si une chambre du type donné est disponible
        for (Reservation reservation : reservations.values()) {
            if (reservation.getType().equalsIgnoreCase(type) && reservation.getStatus().equalsIgnoreCase("Available")) {
                return true; // Au moins une chambre de ce type est disponible
            }
        }
        return false; // Aucune chambre de ce type n'est disponible
    }


}
