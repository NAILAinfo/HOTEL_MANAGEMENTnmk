package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chambre implements Serializable {
    private int numero;
    private String type;
    private double price;
    private boolean available; // Changed to instance variable

    private String status;

    public Chambre(int numero, String type, double price, boolean available) {
        this.numero = numero;
        this.type = type;
        this.price = price;
        this.available = available;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public static void main(String[] args) {
        List<Chambre> chambres = new ArrayList<>();

        // Define the types and price ranges
        String[] types = {"LUXE", "STANDARD, SUITE,FAMILIALE ,EXECUTIVE"};
        double[] prices = {23000, 15000, 30000};

        // Generate 30 rooms
        for (int i = 1; i <= 30; i++) {
            String type = types[i % 3]; // Rotate through the types
            double price = prices[i % 3]; // Rotate through the prices
            Chambre chambre = new Chambre(i, type, price, true); // All rooms available by default
            chambres.add(chambre);
        }

        // Print the generated rooms
        for (Chambre chambre : chambres) {
            System.out.println("Chambre numéro : " + chambre.getNumero() + ", Type : " + chambre.getType() + ", Prix : " + chambre.getPrice());
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }}
