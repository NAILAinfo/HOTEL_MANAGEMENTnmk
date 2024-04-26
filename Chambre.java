public class Chambre {
    // Définition des tarifs de base pour chaque type de chambre
    public static  double TARIF_STANDARD_BASE = 100.0;
    public static  double TARIF_DE_LUXE_BASE = 150.0;
    public static  double TARIF_CHAMBRE_FAMILIALE_BASE = 200.0;
    public static  double TARIF_SUITE_BASE = 250.0;
    public static  double TARIF_EXECUTIVE_BASE = 300.0;

    // Ajout du coût supplémentaire pour le dîner
    public static final double SUPPLEMENT_DINER = 50.0;
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
