package views;

import controllers.AuthController;
import models.Utilisateur;
import models.GestionUtilisateurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignupView extends JFrame {
    private AuthController authController;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailOrPhoneField;
    private JSpinner dateOfBirthSpinner;
    private JTextField countryField;
    private JComboBox<String> sexComboBox;
    private JButton signupButton;

    public SignupView(AuthController authController) {
        this.authController = authController;
        setTitle("Sign Up");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10)); // Added padding for better spacing
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel emailOrPhoneLabel = new JLabel("Email/Phone:");
        emailOrPhoneField = new JTextField();
        JLabel dateOfBirthLabel = new JLabel("Date of Birth:");
        dateOfBirthSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateOfBirthSpinner, "yyyy-MM-dd");
        dateOfBirthSpinner.setEditor(dateEditor);
        dateOfBirthSpinner.setValue(new Date()); // Set current date as default
        JLabel countryLabel = new JLabel("Country:");
        countryField = new JTextField();
        JLabel sexLabel = new JLabel("Sex:");
        sexComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        signupButton = new JButton("Sign Up");

        styleLabel(userLabel);
        styleLabel(passwordLabel);
        styleLabel(emailOrPhoneLabel);
        styleLabel(dateOfBirthLabel);
        styleLabel(countryLabel);
        styleLabel(sexLabel);

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailOrPhoneLabel);
        panel.add(emailOrPhoneField);
        panel.add(dateOfBirthLabel);
        panel.add(dateOfBirthSpinner);
        panel.add(countryLabel);
        panel.add(countryField);
        panel.add(sexLabel);
        panel.add(sexComboBox);
        panel.add(new JLabel()); // Empty cell to align the button
        panel.add(signupButton);

        styleButton(signupButton);

        add(panel);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String emailOrPhone = emailOrPhoneField.getText();
                Date dateOfBirth = (Date) dateOfBirthSpinner.getValue();
                String dateOfBirthStr = new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth);
                String country = countryField.getText();
                String sex = (String) sexComboBox.getSelectedItem();
                Utilisateur newUser = new Utilisateur(username, password, "Client", emailOrPhone, dateOfBirthStr, country, sex);
                authController.signup(newUser);
                JOptionPane.showMessageDialog(null, "User created successfully!");
                dispose();
            }
        });
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
        SignupView signupView = new SignupView(authController);
        signupView.setVisible(true);
    }
}
