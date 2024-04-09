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
        if (!fin.apres(debut)) {
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
}
