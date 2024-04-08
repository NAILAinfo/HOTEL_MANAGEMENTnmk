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
    private Date naissance;
//constructeur dans le cas d'inscription avec l'adresse email 
    public Client(String nom, String prenom, String num, String email, String motDePasse, String tel, Date naissance) throws EmailFormatException {
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
    }
//constructeur dans le cas d'inscription avec le numéro de téléphone 
    public Client(String nom, String prenom, String num, String tel, Date naissance)  throws EmailFormatException {
        if (tel.length() < 10 ) {throw new IllegalArgumentException("Le numéro de téléphone est incomplet.");}
        if (tel.charAt(0) != '0' ) {throw new IllegalArgumentException("Le numéro de téléphone doit commencer par zéro !");}
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
        this.email = null;
        this.motDePasse = null;
        this.tel = tel;
        this.naissance = naissance;
    }
//getters
    public String getNom() {return nom;}
    public String getPrenom() {return prenom;}
    public String getNum() { return num;}
    public String getEmail() {return email;}
    public String getMotDePasse() {return motDePasse;}
    public String getTel() {return tel;}
    public Date getNaissance() {return naissance;}    
 //setters   
    public void setNom(String nom) { this.nom = nom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public void setNum(String num) {this.num = num;}
    public void setEmail(String email) {this.email = email;}
    public void setMotDePasse(String motDePasse) {this.motDePasse = motDePasse;}
    public void setTel(String tel) {this.tel = tel;}
    public void setNaissance(Date naissance) {this.naissance = naissance;}
}

