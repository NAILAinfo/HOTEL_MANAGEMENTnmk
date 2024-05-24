package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        sidePanel.setBackground(new Color(0, 102, 102));
        sidePanel.setLayout(new GridLayout(5, 1, 0, 20));
        sidePanel.setPreferredSize(new Dimension(150, getHeight()));

        standardButton = createModernButton("Standard");
        deLuxeButton = createModernButton("DE_LUXE");
        familialeButton = createModernButton("Familiale");
        suiteButton = createModernButton("Suite");
        executiveButton = createModernButton("Executive");

        sidePanel.add(standardButton);
        sidePanel.add(deLuxeButton);
        sidePanel.add(familialeButton);
        sidePanel.add(suiteButton);
        sidePanel.add(executiveButton);

        mainPanel.add(sidePanel, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        welcomeLabel = new JLabel("Sélectionner le type de chambre", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE);

        receptionImageLabel = new JLabel(new ImageIcon("C:\\Users\\akr\\Downloads\\213bebf06d1670c419b880f57aaf71ba (1).jpg"));

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
        button.setForeground(Color.BLACK);
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
