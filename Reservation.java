public class Reservation {
    //attributs
    private int id;
    private String num;
    private int numero;
    private Date debut;
    private Date fin;
// constructeur 
    public Reservation(int id, Client client, int numero, Date debut, Date fin) {
        this.id = id;
        this.num = client.getNum();
        this.numero = numero;
        // Vérifie si la date de fin est après la date de début
        if (!fin.estApres(debut)) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début.");
        }
        this.debut = debut;
        this.fin = fin;
    }
// Getters et Setters    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNum() {return num;}
    public void setNum(String num) {this.num = num;}
    public int getNumero() {return numero;}
    public void setNumero(int numero) {this.numero = numero;}
    public Date getDebut() {return debut; }
    public void setDebut(Date debut) {this.debut = debut;}
    public Date getFin() {return fin;}
    public void setFin(Date fin) {this.fin = fin;}
    
      public int peridoe() {
         return debut.differenceJ(fin);
   
    }
    private boolean peutAnnuler(Date annulation) {
        Date d=debut.hier();
        return d.estApres(annulation);
    }
    public void annuler(Date dateAnnulation) {
        // Vérification pour ne pas permettre l'annulation la veille de l'arrivée
        if (!peutAnnuler(dateAnnulation)) {
            System.out.println("Impossible d'annuler la réservation la veille de l'arrivée.");
            return;
        }

        // Mettre à jour l'état de la chambre
        chambre.setEtat(Chambre.Disponibilite.DISPONIBLE);

        // Supprimer les dates de séjour enregistrées
     //   debut.clear();
       //  fin.clear();
        System.out.println("Réservation annulée avec succès.");
    }

}
