import java.util.ArrayList;
import java.util.List;

public class Chambre {
    // Définition des tarifs de base pour chaque type de chambre
    public static final double TARIF_STANDARD_BASE = 100.0;
    public static final double TARIF_DE_LUXE_BASE = 150.0;
    public static final double TARIF_CHAMBRE_FAMILIALE_BASE = 200.0;
    public static final double TARIF_SUITE_BASE = 250.0;
    public static final double TARIF_EXECUTIVE_BASE = 300.0;

    // Ajout du coût supplémentaire pour le dîner
    public static final double SUPPLEMENT_DINER = 50.0;

    public void setType(Type nouveauType) {
    }

    // Enumeration pour le type de chambre
    public enum Type { STANDARD, DE_LUXE, CHAMBRE_FAMILIALE, SUITE, EXECUTIVE }

    // Enumeration pour l'état de disponibilité de la chambre
    public enum Disponibilite { RESERVEE, DISPONIBLE, OCCUPEE }

    // Attributs
    private String numero;
    public Type type;
    private double tarif;
    private Disponibilite etat;
    private List<Reservation> reservations;
    public List<MyDate> dates;

    // Constructeur
    public Chambre(int numero, Type type, double tarif) {
        if (numero < 1 || numero > 999) {
            throw new IllegalArgumentException("Le numéro de chambre doit être compris entre 1 et 999.");
        }
        this.numero = String.format("%03d", numero); // le numero de la chambre doit contenir 3 chiffres
        this.type = type;
        this.tarif = tarif;
        this.reservations = new ArrayList<>();
        this.etat = Disponibilite.DISPONIBLE; // La chambre est initialement disponible
    }

    // Getters
    public String getNumero() { return numero; }
    public Type getType() { return type; }
    public double getTarif() { return tarif; }
    public Disponibilite getEtat() { return etat; }
    public List<Reservation> getReservations() { return reservations; }

    // Setters
    public void setNumero(int numero) {
        if (numero < 1 || numero > 999) {
            throw new IllegalArgumentException("Le numéro de chambre doit être compris entre 1 et 999.");
        }
        this.numero = String.format("%03d", numero);
    }
    public void setTarif(double tarif) { this.tarif = tarif; }
    public void setEtat(Disponibilite etat) { this.etat = etat; }

    // Méthode pour vérifier la disponibilité de la chambre pour une période donnée
    public boolean estDisponible(MyDate debut, MyDate fin) {
        for (Reservation reservation : reservations) {
            if (reservation.chevauche(debut, fin)) {
                return false; // La chambre est déjà réservée pour cette période
            }
        }
        return true; // La chambre est disponible pour la période spécifiée
    }

    // Méthode pour réserver la chambre
    public void reserver(Reservation reservation) {
        reservations.add(reservation); // Ajout de la réservation à la liste des réservations
        etat = Disponibilite.RESERVEE; // Mise à jour de l'état de disponibilité de la chambre
    }

    // Méthode pour annuler une réservation
    public void annulerReservation(Reservation reservation) {
        reservations.remove(reservation); // Suppression de la réservation de la liste des réservations
        if (reservations.isEmpty()) {
            etat = Disponibilite.DISPONIBLE; // Si aucune réservation n'est présente, la chambre redevient disponible
        }
    }

    // Méthode pour assigner le tarif en fonction du type de chambre
    private double assignerTarif(Type type) {
        switch (type) {
            case STANDARD:
                return TARIF_STANDARD_BASE;
            case DE_LUXE:
                return TARIF_DE_LUXE_BASE;
            case CHAMBRE_FAMILIALE:
                return TARIF_CHAMBRE_FAMILIALE_BASE;
            case SUITE:
                return TARIF_SUITE_BASE;
            case EXECUTIVE:
                return TARIF_EXECUTIVE_BASE;
            default:
                throw new IllegalArgumentException("Type de chambre non pris en charge.");
        }
    }
}
