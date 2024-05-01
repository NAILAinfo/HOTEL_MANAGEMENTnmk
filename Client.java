
import java.util.ArrayList;
import java.util.List;

//exception pour ne pas donner une adresse email fausse
class EmailFormatException extends Exception {
    public EmailFormatException(String message) {
        super(message);
    }}

public class Client {
    private String nom;
    private String prenom;
    private String num;
    private String email;
    private String motDePasse;
    private String tel;
    private MyDate naissance;
    private List<Reservation> demandesReservation;
    private List<Reservation> reservations;

    //constructeur dans le cas d'inscription avec l'adresse email
    public Client(String nom, String prenom, String num, String email, String motDePasse, String tel, MyDate naissance) throws EmailFormatException {
        if (tel.length() < 10 ) {throw new IllegalArgumentException("Le numéro de téléphone est incomplet.");}
        if (tel.charAt(0) != '0' ) {throw new IllegalArgumentException("Le numéro de téléphone doit commencer par zéro !");}
        String standard = "^[a-zA-Z0-9]+@gmail\\.com$";
        if (!email.matches(standard)) {throw new EmailFormatException("L'adresse email doit être de la forme 'chaine@gmail.com'."); }
        if (motDePasse.length() < 8 || !motDePasse.matches(".*[a-zA-Z].*") || !motDePasse.matches(".*\\d.*")) {throw new IllegalArgumentException("Le mot de passe doit contenir au moins 8 caractères et inclure à la fois des chiffres et des lettres.");}
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
        this.email = email;
        this.motDePasse = motDePasse;
        this.tel = tel;
        this.naissance = naissance;
        this.demandesReservation=new ArrayList <>();
        this.reservations = new ArrayList<>();
    }
    //constructeur dans le cas d'inscription avec le numéro de téléphone
    public Client(String nom, String prenom, String num, String tel, MyDate naissance)  throws EmailFormatException {
        if (tel.length() < 10 ) {throw new IllegalArgumentException("Le numéro de téléphone est incomplet.");}
        if (tel.charAt(0) != '0' ) {throw new IllegalArgumentException("Le numéro de téléphone doit commencer par zéro !");}
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
        this.email = null;
        this.motDePasse = null;
        this.tel = tel;
        this.naissance = naissance;
        this.demandesReservation=new ArrayList <> ();
        this.reservations = new ArrayList<>();
    }
    //getters
    public String getNom() {return nom;}
    public String getPrenom() {return prenom;}
    public String getNum() { return num;}
    public String getEmail() {return email;}
    public String getMotDePasse() {return motDePasse;}
    public String getTel() {return tel;}
    public MyDate getNaissance() {return naissance;}
    //setters
    public void setNom(String nom) { this.nom = nom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public void setNum(String num) {this.num = num;}
    public void setEmail(String email) {this.email = email;}
    public void setMotDePasse(String motDePasse) {this.motDePasse = motDePasse;}
    public void setTel(String tel) {this.tel = tel;}
    public void setNaissance(MyDate naissance) {this.naissance = naissance;}

    // Méthode pour faire une demande de réservation
    public void demanderReservation(Chambre chambre, MyDate Debut, MyDate Fin) {
        // Vérifier la disponibilité de la chambre pour les dates spécifiées
        if (chambre.estDisponible(Debut, Fin)) {
            // Créer une nouvelle demande de réservation
            Reservation nouvelleDemande = new Reservation(chambre, Debut, Fin);
            // Ajouter la demande à la liste des demandes de réservation

            reservations.add(nouvelleDemande);
            // Afficher un message de confirmation
            System.out.println("Demande de réservation effectuée avec succès pour la chambre " + chambre.getNumero() + ".");
        } else {
            // Afficher un message d'erreur si la chambre n'est pas disponible
            System.out.println("La chambre " + chambre.getNumero() + " n'est pas disponible pour les dates spécifiées.");
        }
    }

    //creer Reservation
    public void creerReservation(Chambre chambre, Client client, MyDate dateDebut, MyDate dateFin, String statut) {
        // Créer une nouvelle réservation avec les informations spécifiées
        Reservation nouvelleReservation = new Reservation(chambre , dateDebut,dateFin);
        // Appeler la méthode reserver de la réservation pour effectuer la réservation
        nouvelleReservation.reserver(dateDebut, dateFin);
        // Ajouter la réservation à la liste des réservations du client
        reservations.add(nouvelleReservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    //afficher la liste des reservation d'un client specifique
    public void afficherReservations(Client client) {
        System.out.println("Liste des réservations pour le client " + client.getNom() + " " + client.getPrenom() + ":");
        for (Reservation reservation : client.getReservations()) {
            System.out.println("Chambre: " + reservation.getChambre().getNumero() +
                    ", MyDate de début: " + reservation.getDebut() +
                    ", MyDate de fin: " + reservation.getFin());
        }
    }



}

