package views;

import controllers.AuthController;
import models.GestionUtilisateurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HotelWelcomeView extends JFrame {
    private JButton enterButton;
    private JLabel imageLabel;
    private JLabel titleLabel;

    public HotelWelcomeView() {
        setTitle("Hotel Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Load the image
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\akr\\Downloads\\&&&.jpg");
        imageLabel = new JLabel(imageIcon);

        // Create the title label
        titleLabel = new JLabel("WELCOME TO YOUR HOTEL MANAGEMENT SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28)); // Change the font to "Segoe UI" with size 28
        titleLabel.setForeground(new Color(0, 51, 102)); // Darker blue color

        // Create the enter button
        enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        enterButton.setBackground(Color.WHITE); // White background
        enterButton.setForeground(new Color(0, 51, 102)); // Darker blue text
        enterButton.setFocusPainted(false);
        enterButton.setBorderPainted(false);
        enterButton.setOpaque(true);
        enterButton.setPreferredSize(new Dimension(200, 50)); // Increase button size

        // Center the button
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(240, 248, 255)); // Light blue background
        buttonPanel.add(enterButton);

        // Add components to the frame
        add(titleLabel, BorderLayout.NORTH);
        add(imageLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add ActionListener to the enter button
        addEnterButtonListener(e -> {
            dispose(); // Close the current window
            AuthController authController = new AuthController(new GestionUtilisateurs ()); // Create AuthController instance
            new LoginView(authController).setVisible(true); // Open the LoginView window
        });
    }

    public void addEnterButtonListener(ActionListener listener) {
        enterButton.addActionListener(listener);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelWelcomeView welcomeView = new HotelWelcomeView();
            welcomeView.setVisible(true);
        });
    }
}
