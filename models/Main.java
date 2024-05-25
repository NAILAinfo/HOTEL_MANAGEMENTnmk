package models;

import controllers.AdminController;
import views.RoomManagementView;
import views.ReservationManagementView;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        GestionChambres gestionChambres = new GestionChambres();
        try {
            gestionChambres.loadChambres(); // Load existing rooms if any
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Define the types and price ranges
        String[] types = {"DE_LUXE", "STANDARD", "SUITE","FAMILAILE","EXECUTIVE"};
        double[] prices = {23000, 15000, 30000};

        // Generate 30 rooms
        for (int i = 1; i <= 30; i++) {
            String type = types[i % 3]; // Rotate through the types
            double price = prices[i % 3]; // Rotate through the prices
            Chambre chambre = new Chambre(i, type, price, true); // Set available to true
            gestionChambres.ajouterChambre(chambre);
        }

        // Save the rooms
        try {
            gestionChambres.saveChambres();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize other necessary components
        GestionReservations gestionReservations = new GestionReservations();
        GestionUtilisateurs gestionUtilisateur = new GestionUtilisateurs();

        // Generate 30 random reservations
        Random random = new Random();
        for (int i = 1; i <= 30; i++) {
            int chambreId = i;
            LocalDate dateDebut = LocalDate.now().plusDays(random.nextInt(30));
            LocalDate dateFin = dateDebut.plusDays(random.nextInt(10) + 1);
            Reservation reservation = new Reservation(chambreId, dateDebut.toString(), dateFin.toString(), "Pending");
            gestionReservations.ajouterReservation(reservation);
        }
        try {
            gestionReservations.saveReservations();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdminController adminController = new AdminController(gestionChambres, gestionReservations, gestionUtilisateur);

        new ReservationManagementView(adminController).setVisible(true);
        // Show the Room Management View
        RoomManagementView roomManagementView = new RoomManagementView(adminController);
        roomManagementView.setVisible(true);
    }
}
