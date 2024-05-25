package views;

import controllers.AuthController;
import models.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPasswordView extends JFrame {
    private AuthController authController;
    private JTextField usernameField;
    private JTextField emailField;
    private JButton resetPasswordButton;

    public ForgotPasswordView(AuthController authController) {
        this.authController = authController;
        setTitle("Forgot Password");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);
        add(panel);

        placeComponents(panel);

        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = usernameField.getText();
                    String email = emailField.getText();
                    Utilisateur user = authController.getUserByUsername(username);
                    if (user != null && user.getEmail().equals(email)) {
                        // Implement the password reset functionality here
                        JOptionPane.showMessageDialog(null, "Password reset instructions sent to your email.");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or email.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
    }

    private void placeComponents(JPanel panel) {
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50, 50, 80, 25);
        usernameLabel.setForeground(Color.WHITE);
        panel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(150, 50, 160, 25);
        panel.add(usernameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 100, 80, 25);
        emailLabel.setForeground(Color.WHITE);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(150, 100, 160, 25);
        panel.add(emailField);

        resetPasswordButton = new JButton("Reset Password");
        resetPasswordButton.setBounds(100, 150, 160, 30);
        resetPasswordButton.setBackground(new Color(70, 130, 180));
        resetPasswordButton.setForeground(Color.WHITE);
        panel.add(resetPasswordButton);
    }
}
