import java.util.ArrayList;
import java.util.List;

public class Reservation {
    // Attributs
    private Chambre chambre;
    private List<MyDate> datesSejour;
    private Client client;
    private MyDate Debut;
    private MyDate Fin;
    private String statut;
    private int numero;
    private double SUPPLEMENT_DINER;


    // Constructeur
    public Reservation(Chambre chambre, MyDate debut, MyDate fin) {
        this.chambre = chambre;
        this.datesSejour = new ArrayList<>();
        this.client = client;
        this.statut = statut;
        this.Debut = debut;
        this.Fin = fin;

        // Vérifie si la date de fin est après la date de début
        if (!fin.estApres(debut)) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début.");
        }
    }public MyDate getDebut() {
        return Debut;
    }

    public void setDebut(MyDate debut) {
        this.Debut = debut;
    }

    public MyDate getFin() {
        return Fin;
    }

    public void setFin(MyDate fin) {
        this.Fin = fin;
    }
    //methode
    // Getters et Setters

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String status() {return status();}
    public void setStatut(String statut) { this.statut =statut; }



    public Chambre getChambre() {
        return chambre;
    }



    public int peridoe() {
        return Debut.difference(Fin); }






    public void reserver(MyDate dateDebut, MyDate dateFin) {
        if (chambre.estDisponible(dateDebut, dateFin)) {
            // Vérification pour ne pas permettre l'annulation la veille de l'arrivée
            if (!peutAnnuler(dateDebut)) {
                System.out.println("Impossible d'annuler la réservation la veille de l'arrivée.");
                return;
            }

            // Mettre à jour l'état de la chambre
            chambre.setEtat(Chambre.Disponibilite.RESERVEE);

            // Enregistrer les dates du séjour
            MyDate date = dateDebut;
            while (!date.equals(dateFin.hier())) {
                datesSejour.add(date);
                date = date.hier();
            }

            System.out.println("Réservation effectuée avec succès.");
        } else {
            System.out.println("La chambre n'est pas disponible pour la période spécifiée.");
        }
    }

    public void annuler(MyDate dateAnnulation) {
        // Vérification pour ne pas permettre l'annulation la veille de l'arrivée
        if (!peutAnnuler(dateAnnulation)) {
            System.out.println("Impossible d'annuler la réservation la veille de l'arrivée.");
            return;
        }

        // Mettre à jour l'état de la chambre
        chambre.setEtat(Chambre.Disponibilite.DISPONIBLE);

        // Supprimer les dates de séjour enregistrées
        datesSejour.clear();

        System.out.println("Réservation annulée avec succès.");
    }

    private boolean peutAnnuler(MyDate dateAnnulation) {
        // Empêcher l'annulation la veille de l'arrivée
        MyDate dateArrivee = datesSejour.get(datesSejour.size() - 1);
        return !dateAnnulation.equals(dateArrivee.hier());
    }

    public double calculerDureeSejourMoyenne() {
        // Logique pour calculer la durée moyenne des séjours passés dans la chambre
        int totalJours = 0;
        for (MyDate date : datesSejour) {
            totalJours += date.difference(date.hier());
        }
        return (double) totalJours / datesSejour.size();
    }

    // Méthode pour modifier les détails d'une réservation existante
    public void modifierReservation(MyDate nouvelleDateDebut, MyDate nouvelleDateFin, Chambre nouvelleChambre) {
        // Vérifie si la nouvelle date de fin est après la nouvel date de debut
        if (!nouvelleDateFin.apres(nouvelleDateDebut)) {
            throw new IllegalArgumentException("La nouvelle date de fin doit être après la nouvelle date de début.");
        }
        // Mettre à jour les détails de la réservation avec les nouvelles valeurs
        this.Debut = nouvelleDateDebut;
        this.Fin = nouvelleDateFin;
        this.chambre = nouvelleChambre;

    }

    //calculer le cout d'une reservation
    public double calculerCoutReservation() {
        // Calcul de la durée du séjour en jours en utilisant la méthode existante
        double dureeSejour = this.calculerDureeSejourMoyenne();

        // Vérification de la durée minimale de séjour
        if (dureeSejour < 1) {
            throw new IllegalArgumentException("La durée du séjour doit être d'au moins un jour.");
        }

        // Calcul du tarif de base de la chambre en fonction du type
        double tarifBase = 0.0;
        switch (chambre.type) {
            case STANDARD:
                tarifBase = chambre.TARIF_STANDARD_BASE;
                break;
            case DE_LUXE:
                tarifBase = chambre.TARIF_DE_LUXE_BASE;
                break;
            case CHAMBRE_FAMILIALE:
                tarifBase =chambre. TARIF_CHAMBRE_FAMILIALE_BASE;
                break;
            case SUITE:
                tarifBase = chambre.TARIF_SUITE_BASE;
                break;
            case EXECUTIVE:
                tarifBase = chambre.TARIF_EXECUTIVE_BASE;
                break;
            default:
                throw new IllegalArgumentException("Type de chambre invalide.");
        }

        // Calcul du coût total de la chambre pour la durée du séjour
        double coutTotalChambre = tarifBase * dureeSejour;

        // Calcul du coût des suppléments (par exemple, dîner)
        double coutSupplement = 0.0;
        if (besoinSupplementDiner()) {
            coutSupplement += SUPPLEMENT_DINER;
        }

        // Calcul du coût total de la réservation (chambre + suppléments)
        double coutTotalReservation = coutTotalChambre + coutSupplement;

        return coutTotalReservation;
    }

    // Méthode pour vérifier si un supplément de dîner est nécessaire
    private boolean besoinSupplementDiner() {
        // Ajoutez ici la logique pour déterminer si un supplément de dîner est nécessaire
        // Par exemple, vous pouvez vérifier si le client a demandé un dîner lors de la réservation
        // ou si la réservation comprend une certaine période où le dîner est disponible, etc.
        return false; // Par défaut, aucun supplément de dîner n'est nécessaire
    }


    public boolean chevauche(MyDate Debut, MyDate Fin) { 
        return (Debut.estApres(Debut) && Debut.estAvant(Fin)) || (Fin.estApres(Debut) && Fin.estAvant(Fin));
    }
        
    }

  
