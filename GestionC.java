import java.util.HashMap;
import java.util.Map;
// but : gestion des chambres
// a modifier
public class GestionC {

    private Map<String, Chambre> chambres;

    public GestionC() {
        chambres = new HashMap<>();
    }

    public Map<String, Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(Map<String, Chambre> chambres) {
        this.chambres = chambres;
    }

    public void modifierChambre(String numeroChambre, Chambre nouvelleChambre) {
        if (chambres.containsKey(numeroChambre)) {
            chambres.put(numeroChambre, nouvelleChambre);
            System.out.println("La chambre a été modifiée avec succès.");
        } else {
            System.out.println("La chambre avec le numéro " + numeroChambre + " n'existe pas.");
        }
    }

   public void supprimerChambre(String numeroChambre) {
        if (chambres.containsKey(numeroChambre)) {
            chambres.remove(numeroChambre);
            System.out.println("La chambre a été supprimée avec succès.");
        } else {
            System.out.println("La chambre avec le numéro " + numeroChambre + " n'existe pas.");
        }
    }

    public void ajouterChambre(Chambre chambre) {
        chambres.put(chambre.getNumero(), chambre);
    }

    public Chambre trouverChambreParNumero(String numeroChambre) {
        return chambres.get(numeroChambre);
    }

}
