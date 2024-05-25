package models;

public class MyDate {
    // Attributs
    private int jour;
    private int mois;
    private int annee;

    // Constructeur
    public MyDate(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    // Getters et setters
    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    // Méthode pour déterminer si le mois a 30 jours
    public boolean mois30j() {
        return (mois == 4 || mois == 6 || mois == 9 || mois == 11);
    }

    // Méthode pour déterminer si le mois a 31 jours
    public boolean mois31j() {
        return (mois == 1 || mois == 3 || mois == 5 || mois == 7 || mois == 8 || mois == 10 || mois == 12);
    }

    // Méthode pour déterminer si l'année est bissextile
    public boolean anneebissextile() {
        return (annee % 4 == 0 && annee % 100 != 0) || annee % 400 == 0;
    }

    // Méthode pour déterminer le nombre de jours du mois
    public int nbrjours() {
        if (mois == 2) {
            return anneebissextile() ? 29 : 28;
        } else if (mois30j()) {
            return 30;
        } else {
            return 31;
        }
    }

    // Méthode pour obtenir la date du jour précédent
    public MyDate hier() {
        int j = jour;
        int m = mois;
        int a = annee;

        if (j == 1) {
            if (m == 1) {
                j = 31;
                m = 12;
                a--;
            } else {
                m--;
                j = nbrjours();
            }
        } else {
            j--;
        }

        return new MyDate(j, m, a);
    }

    // Méthode pour déterminer si une date est après une autre date
    public boolean estApres(MyDate autre) {
        if (annee > autre.annee) {
            return true;
        } else if (annee < autre.annee) {
            return false;
        } else {
            if (mois > autre.mois) {
                return true;
            } else if (mois < autre.mois) {
                return false;
            } else {
                return jour > autre.jour;
            }
        }
    }

    // Méthode pour calculer la différence en jours entre deux dates
    public int difference(MyDate autre) {
        int différence = 0;
        MyDate dateTemp = new MyDate(jour, mois, annee);

        while (!dateTemp.egale(autre)) {
            dateTemp = dateTemp.hier();
            différence++;
        }

        return différence;
    }

    // Méthode pour vérifier si une date est égale à une autre date
    public boolean egale(MyDate autre) {
        return jour == autre.jour && mois == autre.mois && annee == autre.annee;
    }

    // Méthode pour obtenir une représentation sous forme de chaîne de caractères de l'objet MyDate
    @Override
    public String toString() {
        return "MyDate{" +
                "jour=" + jour +
                ", mois=" + mois +
                ", annee=" + annee +
                '}';
    }public boolean apres(MyDate autreDate) {
        if (annee > autreDate.annee) {
            return true;
        } else if (annee < autreDate.annee) {
            return false;
        } else {
            if (mois > autreDate.mois) {
                return true;
            } else if (mois < autreDate.mois) {
                return false;
            } else {
                return jour > autreDate.jour;
            }
        }
    }

    public boolean estAvant(MyDate autreDate) {
        if (annee < autreDate.annee) {
            return true;
        } else if (annee > autreDate.annee) {
            return false;
        } else {
            if (mois < autreDate.mois) {
                return true;
            } else if (mois > autreDate.mois) {
                return false;
            } else {
                return jour < autreDate.jour;
            }
        }
    }}