package models;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class GestionUtilisateurs implements Serializable {
    private Map<String, Utilisateur> utilisateurs;

    public GestionUtilisateurs() {
        utilisateurs = new HashMap<>();
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.put(utilisateur.getUsername(), utilisateur);
    }

    public Utilisateur getUtilisateur(String username) {
        return utilisateurs.get(username);
    }

    public void supprimerUtilisateur(String username) {
        utilisateurs.remove(username);
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return new ArrayList<>(utilisateurs.values());
    }

    public void loadUsers() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
            utilisateurs = (HashMap<String, Utilisateur>) ois.readObject();
        }
    }

    public void saveUsers() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            oos.writeObject(utilisateurs);
        }
    }
}
