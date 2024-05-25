package models;

import java.io.Serializable;

public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L; // Ajout d'un identifiant de version pour la classe

    private String username;
    private String password;
    private String role;
    private String emailOrPhone;
    private String dateOfBirth;
    private String country;
    private String sex;

    // Constructor for all fields
    public Utilisateur(String username, String password, String role, String emailOrPhone, String dateOfBirth, String country, String sex) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.emailOrPhone = emailOrPhone;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.sex = sex;
    }

    // Constructor for minimal fields (e.g., Admin)
    public Utilisateur(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return emailOrPhone; // Or return a dedicated email field if exists.
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public String getSex() {
        return sex;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public class InvalidEmailFormatException extends Exception {
        public InvalidEmailFormatException(String message) {
            super(message);
        }
    }



    public void setPassword(String password) {
        this.password = password;
    }
}
