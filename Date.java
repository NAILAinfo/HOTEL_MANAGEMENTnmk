public class Date {
//attributs    
    private int jour;
    private int mois;
    private int annee;
//constructeur
    public Date(int jour, int mois, int annee) {
        if (mois < 1 || mois > 12) {throw new IllegalArgumentException("Le mois doit être compris entre 1 et 12."); }
        if (jour < 1 || jour > 31) {throw new IllegalArgumentException("Le jour doit être compris entre 1 et 31."); }
        if ((mois == 4 || mois == 6 || mois == 9 || mois == 11) && jour > 30) {throw new IllegalArgumentException("Le mois " + mois + " n'a que 30 jours.");}
// Déterminer le nombre de jours du deuxième mois (février) en effectuant des calculs sur l'année pour déterminer si elle est bissextile
        if (mois == 2) {
            if ((annee % 4 == 0 && annee % 100 != 0) || annee % 400 == 0) {
                if (jour > 29) {
                    throw new IllegalArgumentException("Février en année bissextile a 29 jours.");
                }
            } else {
                if (jour > 28) {
                    throw new IllegalArgumentException("Février a 28 jours, sauf en année bissextile.");
                }
            }
        }
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }
//Getters
    public int getJour() {return jour;}
    public int getMois() { return mois;}
    public int getAnnee() {return annee;}
//Setters
    public void setJour(int jour) {this.jour = jour;}
    public void setMois(int mois) {this.mois = mois;}
    public void setAnnee(int annee) {this.annee = annee;}

    public String toString() {
        return "Date{" +"jour=" + jour +", mois=" + mois +", annee=" + annee +'}';
    }

        // Creation d'une date 
        //try {
          //  Date date = new Date(30, 2, 2024);
      //  } catch (IllegalArgumentException e) {
       //     System.out.println("Erreur lors de la création de la date : " + e.getMessage());
     //   }
   // }
}
