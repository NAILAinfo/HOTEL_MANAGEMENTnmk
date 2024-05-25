package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterUtilisateur extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField roleField;
    private JTextField emailOrPhoneField;
    private JTextField dateOfBirthField;
    private JTextField countryField;
    private JTextField sexField;
    private JButton addButton;

    public AjouterUtilisateur() {
        setTitle("Add User");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        // Initialize components
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        roleField = new JTextField();
        emailOrPhoneField = new JTextField();
        dateOfBirthField = new JTextField();
        countryField = new JTextField();
        sexField = new JTextField();
        addButton = new JButton("Add User");

        // Add components to frame
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Role:"));
        add(roleField);
        add(new JLabel("Email or Phone:"));
        add(emailOrPhoneField);
        add(new JLabel("Date of Birth:"));
        add(dateOfBirthField);
        add(new JLabel("Country:"));
        add(countryField);
        add(new JLabel("Sex:"));
        add(sexField);
        add(new JLabel());
        add(addButton);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getRole() {
        return roleField.getText();
    }

    public String getEmailOrPhone() {
        return emailOrPhoneField.getText();
    }

    public String getDateOfBirth() {
        return dateOfBirthField.getText();
    }

    public String getCountry() {
        return countryField.getText();
    }

    public String getSex() {
        return sexField.getText();
    }

    public void AjouterUtilisateurListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }public static void main(String[] args) {
        // Create an instance of AjouterUtilisateur
        AjouterUtilisateur ajouterUtilisateur = new AjouterUtilisateur();

        // Set the frame to be visible
        ajouterUtilisateur.setVisible(true);

    }}


