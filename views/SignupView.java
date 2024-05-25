package views;

import controllers.AuthController;
import models.InvalidDateException;
import models.InvalidEmailFormatException;
import models.Utilisateur;
import models.GestionUtilisateurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignupView extends JFrame {
    private AuthController authController;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField dateOfBirthField; // Utilisation de JTextField pour la date de naissance
    private JTextField countryField;
    private JComboBox<String> sexComboBox;
    private JButton signupButton;
    private UserManagementView userManagementView;

    public SignupView(AuthController authController, UserManagementView userManagementView) {
        this.authController = authController;
        this.userManagementView = userManagementView;
        setTitle("Sign Up");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel dateOfBirthLabel = new JLabel("Date of Birth (yyyy-MM-dd):");
        dateOfBirthField = new JTextField(); // Utilisation de JTextField pour la date de naissance
        JLabel countryLabel = new JLabel("Country:");
        countryField = new JTextField();
        JLabel sexLabel = new JLabel("Sex:");
        sexComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        signupButton = new JButton("Sign Up");

        styleLabel(userLabel);
        styleLabel(passwordLabel);
        styleLabel(emailLabel);
        styleLabel(dateOfBirthLabel);
        styleLabel(countryLabel);
        styleLabel(sexLabel);

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(dateOfBirthLabel);
        panel.add(dateOfBirthField); // Ajout du champ de texte pour la date de naissance
        panel.add(countryLabel);
        panel.add(countryField);
        panel.add(sexLabel);
        panel.add(sexComboBox);
        panel.add(new JLabel());
        panel.add(signupButton);

        styleButton(signupButton);

        add(panel);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    String email = emailField.getText();
                    String dateOfBirthStr = dateOfBirthField.getText();
                    Date dateOfBirth = parseAndValidateDate(dateOfBirthStr);
                    String country = countryField.getText();
                    String sex = (String) sexComboBox.getSelectedItem();
                    Utilisateur newUser = new Utilisateur(username, password, "Client", email, dateOfBirthStr, country, sex);
                    authController.signup(newUser);
                    if (userManagementView != null) {
                        userManagementView.addUserToTable(newUser);
                    }
                    JOptionPane.showMessageDialog(null, "User created successfully!");
                    dispose();
                } catch (InvalidEmailFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid email format: " + ex.getMessage());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (InvalidDateException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
                }
            }
        });
    }

    private Date parseAndValidateDate(String dateStr) throws ParseException, InvalidDateException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Mode strict pour la validation des dates
        Date date = dateFormat.parse(dateStr);
        validateDate(date);
        return date;
    }

    private void validateDate(Date date) throws InvalidDateException {
        Date today = new Date();
        if (date.after(today)) {
            throw new InvalidDateException("Date of birth cannot be in the future.");
        }
    }

    private void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color color1 = new Color(70, 130, 180);
            Color color2 = new Color(230, 230, 250);
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    public static void main(String[] args) {
        // Example usage
        AuthController authController = new AuthController(new GestionUtilisateurs());
        SignupView signupView = new SignupView(authController, null);
        signupView.setVisible(true);
    }
}
