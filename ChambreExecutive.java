package views;

import javax.swing.*;
import java.awt.*;

public class ChambreExecutive extends JFrame {
    public ChambreExecutive() {
        setTitle("Chambre Executive");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Catalogue de Chambre Executive", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel(new GridLayout(2, 4, 10, 10));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] imagePaths = {
                "path/to/your/executive/image1.jpg",
                "path/to/your/executive/image2.jpg",
                "path/to/your/executive/image3.jpg",
                "path/to/your/executive/image4.jpg",
                "path/to/your/executive/image5.jpg",
                "path/to/your/executive/image6.jpg",
                "path/to/your/executive/image7.jpg",
                "path/to/your/executive/image8.jpg"
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
            ChambreExecutive chambreExecutive = new ChambreExecutive();
            chambreExecutive.setVisible(true);
        });
    }
}
