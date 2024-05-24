package views;

import javax.swing.*;
import java.awt.*;

public class ChambreDeLuxe extends JFrame {
    public ChambreDeLuxe() {
        setTitle("Chambre De Luxe");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Catalogue de Chambre De Luxe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel(new GridLayout(2, 4, 10, 10));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] imagePaths = {
                "path/to/your/deluxe/image1.jpg",
                "path/to/your/deluxe/image2.jpg",
                "path/to/your/deluxe/image3.jpg",
                "path/to/your/deluxe/image4.jpg",
                "path/to/your/deluxe/image5.jpg",
                "path/to/your/deluxe/image6.jpg",
                "path/to/your/deluxe/image7.jpg",
                "path/to/your/deluxe/image8.jpg"
        };

        for (String imagePath : imagePaths) {
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(new ImageIcon(imagePath));
            imagePanel.add(imageLabel);
        }

        mainPanel.add(new JScrollPane(imagePanel), BorderLayout.CENTER);
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChambreDeLuxe chambreDeLuxe = new ChambreDeLuxe();
            chambreDeLuxe.setVisible(true);
        });
    }
}
