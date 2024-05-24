package views;

import javax.swing.*;
import java.awt.*;

public class ChambreFamiliale extends JFrame {
    public ChambreFamiliale() {
        setTitle("Chambre Familiale");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crée un panneau principal avec un BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Ajouter un label de titre
        JLabel titleLabel = new JLabel("Catalogue de Chambre Familiale", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Crée un panneau pour les images avec un GridLayout
        JPanel imagePanel = new JPanel(new GridLayout(2, 4, 10, 10));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Chemin des images (à remplacer par les chemins réels de vos images)
        String[] imagePaths = {
                "path/to/your/image1.jpg",
                "path/to/your/image2.jpg",
                "path/to/your/image3.jpg",
                "path/to/your/image4.jpg",
                "path/to/your/image5.jpg",
                "path/to/your/image6.jpg",
                "path/to/your/image7.jpg",
                "path/to/your/image8.jpg"
        };

        // Ajout des images au panneau
        for (String imagePath : imagePaths) {
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(new ImageIcon(imagePath));
            imagePanel.add(imageLabel);
        }

        // Ajoute le panneau d'images au centre du panneau principal
        mainPanel.add(new JScrollPane(imagePanel), BorderLayout.CENTER);

        // Ajouter le panneau principal à la fenêtre
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChambreFamiliale chambreFamiliale = new ChambreFamiliale();
            chambreFamiliale.setVisible(true);
        });
    }
}
