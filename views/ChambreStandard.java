package views;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;

public class ChambreStandard extends JFrame {
    public ChambreStandard() {
        setTitle("Chambre Standard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Catalogue de Chambre Standard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel(new GridLayout(2, 4, 10, 10));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        imagePanel.setOpaque(false); // Rendre le panneau d'images transparent pour laisser voir l'arrière-plan

        String[] imagePaths = {
                "C:\\Users\\akr\\Downloads\\SIMPLE1.jpg",
                "C:\\Users\\akr\\Downloads\\SIMPLE6.jpg",
                "C:\\Users\\akr\\Downloads\\ce2f96736ac895b1e9cc61a0f19ab65d.jpg",
                "C:\\Users\\akr\\Downloads\\SIMPLE3.jpg",
                "C:\\Users\\akr\\Downloads\\SIMPLE4.jpg",
                "C:\\Users\\akr\\Downloads\\SIMPLE7.jpg",
                "C:\\Users\\akr\\Downloads\\SIMPLE8.jpg",
                "C:\\Users\\akr\\Downloads\\SIMPLE9.jpg"
        };

        for (String imagePath : imagePaths) {
            JLabel imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            imageLabel.setIcon(resizeImage(imagePath, 200, 150));
            imagePanel.add(imageLabel);
        }

        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private ImageIcon resizeImage(String imagePath, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChambreStandard chambreStandard = new ChambreStandard();
            chambreStandard.setVisible(true);
        });
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int width = getWidth();
            int height = getHeight();
            Color color1 = new Color(0, 102, 204); // Bleu foncé
            Color color2 = new Color(102, 178, 255); // Bleu clair
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
            g2d.dispose();
        }
    }
}
