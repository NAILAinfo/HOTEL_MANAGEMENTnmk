public class Chambre {
    // enumeration pour le type de chambre
    public enum Type { STANDARD, DE_LUXE, CHAMBRE_FAMILIALE, SUITE, EXECUTIVE}
// enumeration pour le type de chambre
    public enum Disponibilite {RESERVEE,DISPONIBLE,OCCUPEE}

    // les attributs 
    private String numero;
    private Type type;
    private double tarif;
    private Disponibilite etat;
    
//constructeur 
    public Chambre(int numero, Type type, double tarif) {
        if (numero < 1 || numero > 999) {
            throw new IllegalArgumentException("Le numéro de chambre doit être compris entre 1 et 999.");
        }
        this.numero = String.format("%03d", numero);// le numero de la chambre doit contenir 3 chiffres 
        this.type = type;
        this.tarif = tarif;        this.etat = etat;

    }
    
//getters    
    public String getNumero() {return numero;}
    public Type getType() {return type;}
    public double getTarif() {return tarif;}
    public Disponibilite getEtat() {return etat;}

//setters
    public void setNumero(int numero) {
        if (numero < 1 || numero > 999) {
            throw new IllegalArgumentException("Le numéro de chambre doit être compris entre 1 et 999.");
        }
        this.numero = String.format("%03d", numero);
    //on utilise String.format pour formater le numéro sur trois chiffres avec des zéros non significatifs (par exemple, 001 au lieu de 1).
    }
    public void setType(Type type) {this.type = type;}
    public void setTarif(double tarif) {this.tarif = tarif;}
    public void setEtat(Disponibilite etat) {this.etat = etat;}

    public String toString() {return "Chambre:" +"numero='" + numero + '\'' +", type=" + type +", tarif=" + tarif +'}';}
    
}
