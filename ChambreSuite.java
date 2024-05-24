package views;

import javax.swing.*;
import java.awt.*;

public class ChambreSuite extends JFrame {
    public ChambreSuite() {
        setTitle("Chambre Suite");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Catalogue de Chambre Suite", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel(new GridLayout(2, 4, 10, 10));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] imagePaths = {
                "C:\\Users\\akr\\Downloads\\SIMPLE1.jpg",
                "C:\\Users\\akr\\Downloads\\SIMPLE6.jpg",
                "C:\\Users\\akr\\Downloads\\SIMPLE6.jpg",
                "C:\\Users\\akr\\Downloads\\SIMPLE3.jpg",
                "C:\\Users\\akr\\Downloads\\SIMPLE4.jpg",
                "path/to/your/suite/image6.jpg",
                "path/to/your/suite/image7.jpg",
                "path/to/your/suite/image8.jpg"
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
            ChambreSuite chambreSuite = new ChambreSuite();
            chambreSuite.setVisible(true);
        });
    }
}
