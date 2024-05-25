package views;

import javax.swing.*;
import java.awt.*;

public class VoirChambre extends JFrame {

    private JButton standardButton;
    private JButton deLuxeButton;
    private JButton familialeButton;
    private JButton suiteButton;
    private JButton executiveButton;
    private JLabel welcomeLabel;
    private JLabel receptionImageLabel;

    public VoirChambre() {
        setTitle("Sélectionner le type de chambre");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        sidePanel.setBackground(new Color(0, 225, 255));
        sidePanel.setLayout(new GridLayout(5, 1, 0, 20));
        sidePanel.setPreferredSize(new Dimension(150, getHeight()));

        standardButton = createModernButton("Standard 600§");
        deLuxeButton = createModernButton("DE_LUXE 1200§");
        familialeButton = createModernButton("Familiale 1500§");
        suiteButton = createModernButton("Suite 2000§");
        executiveButton = createModernButton("Executive 2500§");

        sidePanel.add(standardButton);
        sidePanel.add(deLuxeButton);
        sidePanel.add(familialeButton);
        sidePanel.add(suiteButton);
        sidePanel.add(executiveButton);

        mainPanel.add(sidePanel, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        welcomeLabel = new JLabel("Sélectionner le type de chambre", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(0, 123, 255)); // Texte en bleu

        receptionImageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\akr\\Downloads\\213bebf06d1670c419b880f57aaf71ba (1).jpg").getImage());
        receptionImageLabel.setIcon(imageIcon);
        receptionImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        contentPanel.add(welcomeLabel, BorderLayout.NORTH);
        contentPanel.add(receptionImageLabel, BorderLayout.CENTER);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Ajout d'action aux boutons
        standardButton.addActionListener(e -> new ChambreStandard().setVisible(true));
        deLuxeButton.addActionListener(e -> new ChambreDeLuxe().setVisible(true));
        familialeButton.addActionListener(e -> new ChambreFamiliale().setVisible(true));
        suiteButton.addActionListener(e -> new ChambreSuite().setVisible(true));
        executiveButton.addActionListener(e -> new ChambreExecutive().setVisible(true));
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(0, 123, 255)); // Texte en bleu
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setPreferredSize(new Dimension(200, 40));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255), 2), // Bordure bleu
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 123, 255)); // Fond bleu
                button.setForeground(Color.WHITE); // Texte blanc
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE); // Fond blanc
                button.setForeground(new Color(0, 123, 255)); // Texte bleu
            }
        });

        return button;
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color color1 = new Color(135, 206, 235); // Bleu ciel
            Color color2 = new Color(176, 224, 230); // Bleu plus clair
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VoirChambre().setVisible(true));
    }
}
