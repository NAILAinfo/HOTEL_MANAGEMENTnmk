package views;

import controllers.ClientController;
import models.GestionReservations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    private ClientController clientController;

    private JButton reserverButton;
    private JButton voirChambreButton;
    private JLabel welcomeLabel;
    private JLabel receptionImageLabel;

    public ClientView(ClientController clientController) {
        this.clientController = clientController;
        setTitle("Client Panel");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(0, 196, 255, 255));
        sidePanel.setLayout(new GridLayout(3, 1, 0, 20));
        sidePanel.setPreferredSize(new Dimension(150, getHeight()));

        reserverButton = createModernButton("Reserver");
        voirChambreButton = createModernButton("Voir chambre");

        sidePanel.add(reserverButton);
        sidePanel.add(voirChambreButton);

        mainPanel.add(sidePanel, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        welcomeLabel = new JLabel("Welcome client!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        receptionImageLabel = new JLabel(new ImageIcon("C:\\Users\\akr\\Downloads\\69728626a026547be9b34810da0aa224.jpg"));

        contentPanel.add(welcomeLabel, BorderLayout.NORTH);
        contentPanel.add(receptionImageLabel, BorderLayout.CENTER);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        reserverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reserver reserverPage = new Reserver();
                reserverPage.setVisible(true);
            }
        });

        voirChambreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VoirChambre voirChambrePage = new VoirChambre();
                voirChambrePage.setVisible(true);
            }
        });
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(new Color(0, 119, 255)); // Bleu ciel
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setPreferredSize(new Dimension(100, 40));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 4, 255), 2), // Bleu ciel
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 119, 255)); // Bleu ciel
                button.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
                button.setForeground(new Color(0, 119, 255)); // Bleu ciel
            }
        });

        return button;
    }

    public static void main(String[] args) {
        ClientController clientController = new ClientController(new GestionReservations());
        ClientView clientView = new ClientView(clientController);
        clientView.setVisible(true);
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color color1 = new Color(135, 206, 235); // Bleu ciel clair
            Color color2 = new Color(0, 123, 200); // Bleu ciel foncé
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }
}
