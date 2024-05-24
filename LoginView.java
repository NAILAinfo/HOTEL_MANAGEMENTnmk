package views;

import controllers.AuthController;
import controllers.AdminController;
import controllers.ClientController;
import models.Utilisateur;
import models.GestionUtilisateurs;
import models.GestionChambres;
import models.GestionReservations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginView extends JFrame {
    private AuthController authController;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginView(AuthController authController) {
        this.authController = authController;
        setTitle("Login");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(null);
        add(mainPanel);

        placeComponents(mainPanel);
        addSignUpLabel(mainPanel);
        addForgotPasswordLabel(mainPanel);
        addImageLabel(mainPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    Utilisateur user = authController.login(username, password);
                    if (user.getRole().equals("Admin")) {
                        GestionChambres gestionChambres = new GestionChambres();
                        try {
                            gestionChambres.loadChambres(); // Load rooms state
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        GestionReservations gestionReservations = new GestionReservations();
                        AdminController adminController = new AdminController(gestionChambres, gestionReservations);
                        AdminView adminView = new AdminView(adminController);
                        adminView.setVisible(true);
                        adminView.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                                try {
                                    adminController.saveChambres(); // Save rooms state
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        });
                    } else if (user.getRole().equals("Client")) {
                        GestionReservations gestionReservations = new GestionReservations();
                        ClientController clientController = new ClientController(gestionReservations);
                        new ClientView(clientController).setVisible(true);
                    }
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Login failed: " + ex.getMessage());
                }
            }
        });
    }

    private void placeComponents(JPanel panel) {
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(400, 50, 80, 25);
        userLabel.setForeground(Color.WHITE);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(500, 50, 160, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(400, 100, 80, 25);
        passwordLabel.setForeground(Color.WHITE);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(500, 100, 160, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(450, 150, 150, 30);
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(loginButton);
    }

    private void addSignUpLabel(JPanel panel) {
        JLabel signupLabel = new JLabel("Sign Up");
        signupLabel.setBounds(500, 200, 150, 30);
        signupLabel.setForeground(new Color(70, 130, 180));
        signupLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signupLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(signupLabel);

        signupLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                SignupView signupView = new SignupView(authController);
                signupView.setVisible(true);
            }
        });
    }

    private void addForgotPasswordLabel(JPanel panel) {
        JLabel forgotPasswordLabel = new JLabel(" Forgot Password");
        forgotPasswordLabel.setBounds(500, 225, 150, 30);
        forgotPasswordLabel.setForeground(new Color(70, 130, 180));
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(forgotPasswordLabel);

        forgotPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                ForgotPasswordView forgotPasswordView = new ForgotPasswordView(authController);
                forgotPasswordView.setVisible(true);
            }
        });
    }

    private void addImageLabel(JPanel panel) {
        JLabel imageLabel = new JLabel(new ImageIcon("path_to_your_image.jpg"));
        imageLabel.setBounds(50, 50, 300, 300);
        panel.add(imageLabel);
    }

    public static void main(String[] args) {
        GestionUtilisateurs gestionUtilisateurs = new GestionUtilisateurs();
        try {
            gestionUtilisateurs.loadUsers(); // Load users from file
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Add default users if they don't exist
        if (gestionUtilisateurs.getUtilisateur("admin") == null) {
            System.out.println("Ajout de l'utilisateur admin");
            gestionUtilisateurs.ajouterUtilisateur(new Utilisateur("admin", "admin", "Admin"));
        }
        if (gestionUtilisateurs.getUtilisateur("client") == null) {
            System.out.println("Ajout de l'utilisateur client");
            gestionUtilisateurs.ajouterUtilisateur(new Utilisateur("client", "client", "Client"));
        }

        try {
            gestionUtilisateurs.saveUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AuthController authController = new AuthController(gestionUtilisateurs);
        LoginView loginView = new LoginView(authController);
        loginView.setVisible(true);
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g; // Corrected casting
            int width = getWidth();
            int height = getHeight();
            Color color1 = new Color(70, 130, 180);
            Color color2 = new Color(230, 230, 250);
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }
}
