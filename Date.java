public class Date {
//attributs    
    private int jour;
    private int mois;
    private int annee;
// determiner si le mois a 30 jours 
    public boolean mois30j(){
        return (mois == 4 || mois == 6 || mois == 9 || mois == 11) ;
    }
// determiner si le mois a 31 jours 
    public boolean mois31j(){
        return (mois == 1 || mois == 3 || mois == 5 || mois == 7|| mois == 8 || mois == 10|| mois == 12);
    }
// determiner si l'annee est bissextile
    public boolean anneebissextile(){
        return (annee % 4 == 0 && annee % 100 != 0) || annee % 400 == 0;
    }
// determiner le nombre de jour du mois 
    public int nbrjours(int m ){
       if (m == 2) {return anneebissextile() ? 29 : 28;} 
       else if (mois30j()) {return 30;} 
       else {return 31; } 
    }
    
    
//constructeur
    public Date(int jour, int mois, int annee) {
        if (mois < 1 || mois > 12) {throw new IllegalArgumentException("Le mois doit être compris entre 1 et 12."); }
        if (jour < 1 || jour > 31) {throw new IllegalArgumentException("Le jour doit être compris entre 1 et 31."); }
        
        if (mois30j() && jour > 30) {throw new IllegalArgumentException("Le mois " + mois + " n'a que 30 jours.");}
        
// Déterminer le nombre de jours du deuxième mois (février) en effectuant des calculs sur l'année pour déterminer si elle est bissextile
        if (mois == 2) {
            if (anneebissextile()) {
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

    public String toString() {return "Date{" +"jour=" + jour +", mois=" + mois +", annee=" + annee +'}';}
    public boolean egale(Date autre){return jour==autre.jour && mois==autre.mois &&annee==autre.annee ;}
    
 public Date hier (){
     int j=jour;
     int m=mois;
     int a=annee;
     // il y a 3 cas :
     //1/1/a resultat : 31/12/a--
     // 1/**/**** resultat : dernier jour du mois precedent/m--/a
      // la date n'est pas le premier jour du mois resultat : j--/m/a
     if (j == 1) {
            if (m == 1) {j = 31;m = 12;a--;}
            else {
                m--;j = nbrjours(m);
            }
        } else {j--;}
return new Date(j,m,a);
}
    // Creation d'une date 
        //try {
          //  Date date = new Date(30, 2, 2024);
      //  } catch (IllegalArgumentException e) {
       //     System.out.println("Erreur lors de la création de la date : " + e.getMessage());
     //   }
   // }
}
