package models;

import java.io.*;
import java.util.HashMap;

public class GestionChambres {
    private HashMap<Integer, Chambre> chambres;
    private static final String ROOMS_FILENAME = "rooms.dat";

    public GestionChambres() {
        this.chambres = new HashMap<>();
    }

    public void ajouterChambre(Chambre chambre) {
        chambres.put(chambre.getNumero(), chambre);
    }

    public void modifierChambre(int numero, Chambre chambre) {
        chambres.put(numero, chambre);
    }

    public void supprimerChambre(int numero) {
        chambres.remove(numero);
    }

    public Chambre getChambre(int numero) {
        return chambres.get(numero);
    }

    public HashMap<Integer, Chambre> getChambres() {
        return chambres;
    }

    public void loadChambres() throws IOException, ClassNotFoundException {
        File file = new File(ROOMS_FILENAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                chambres = (HashMap<Integer, Chambre>) ois.readObject();
            }
        } else {
            chambres = new HashMap<>();
            saveChambres(); // Create an empty file if it does not exist
        }
    }

    public void saveChambres() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ROOMS_FILENAME))) {
            oos.writeObject(chambres);
        }
    }
    public boolean isRoomAvailable(String type) {
        for (Chambre chambre : chambres.values()) {
            if (chambre.getType().equalsIgnoreCase(type) && chambre.isAvailable()) {
                return true; // At least one room of this type is available
            }
        }
        return false; // No rooms of this type are available
    }
    public boolean checkRoomAvailability(String type) {
        for (Chambre chambre : chambres.values()) { // Utiliser chambres.values() pour obtenir une vue des valeurs de la HashMap
            if (chambre.getType().equalsIgnoreCase(type) && chambre.isAvailable()) {
                return true; // Au moins une chambre de ce type est disponible
            }
        }
        return false; // Aucune chambre de ce type n'est disponible
    }
}
