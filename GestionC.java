import java.util.HashMap;
import java.util.Map;

import java.util.List;

// but : gestion des chambres
// a modifier
public class GestionC {
    private Map<String,Chambre> chambres;
    private Map<Client, List<Reservation>> reservationsClients;
    private Map<MyDate, List<Reservation>> reservationsParDate;
    public GestionC() {
        chambres = new HashMap<>();
        reservationsClients = new HashMap<>();
        reservationsParDate = new HashMap<>();

    }

    public Map<String, Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(Map<String, Chambre> chambres) {
        this.chambres = chambres;
    }

    public void modifierChambre(String numeroChambre, Chambre nouvelleChambre) {
        if (chambres.containsKey(numeroChambre)) {
            chambres.put(numeroChambre, nouvelleChambre);
            System.out.println("La chambre a été modifiée avec succès.");
        } else {
            System.out.println("La chambre avec le numéro " + numeroChambre + " n'existe pas.");
        }
    }

    public void supprimerChambre(String numeroChambre) {
        if (chambres.containsKey(numeroChambre)) {
            chambres.remove(numeroChambre);
            System.out.println("La chambre a été supprimée avec succès.");
        } else {
            System.out.println("La chambre avec le numéro " + numeroChambre + " n'existe pas.");
        }
    }

    public void ajouterChambre(Chambre chambre) {
        chambres.put(chambre.getNumero(), chambre);
    }

    public Chambre trouverChambreParNumero(String numeroChambre) {
        return chambres.get(numeroChambre);

    }
    public  void afficherReservationsClients() {
        for (Map.Entry<Client, List<Reservation>> entry : reservationsClients.entrySet()) {
            Client client = entry.getKey();
            List<Reservation> reservations = entry.getValue();

            System.out.println("Reservations de " + client.getNom() + " " + client.getPrenom() + ":");
            if (reservations.isEmpty()) {
                System.out.println("Aucune réservation.");
            } else {
                for (Reservation reservation : reservations) {
                    System.out.println(reservation.toString());
                }
            }
            System.out.println("---------------------------------");
        }
    }public void afficherReservationsParDate() {
        for (Map.Entry<MyDate, List<Reservation>> entry : reservationsParDate.entrySet()) {
            MyDate date = entry.getKey();
            List<Reservation> reservations = entry.getValue();

            System.out.println("Réservations pour la date " + date.toString() + ":");
            if (reservations.isEmpty()) {
                System.out.println("Aucune réservation.");
            } else {
                for (Reservation reservation : reservations) {
                    System.out.println(reservation.toString());
                }
            }
            System.out.println("---------------------------------");
        }
    }
}
