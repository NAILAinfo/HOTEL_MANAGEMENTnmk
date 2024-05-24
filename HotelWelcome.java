package views;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HotelWelcome extends JFrame {
    private BufferedImage backgroundImage;

    public HotelWelcome() {
        setTitle("Hotel Welcome");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\akr\\Downloads\\60c80702eedfe6fa07b27515a5784c87.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a panel to display the image and text
        ImagePanel imagePanel = new ImagePanel(backgroundImage);
        imagePanel.setLayout(null); // Use null layout to place button at specific coordinates

        // Create the button with an icon
        JButton entryButton = new JButton(new ImageIcon("C:\\Users\\akr\\Downloads\\e9023b2df785e84f558e20484caa8a05.jpg"));
        entryButton.setBounds(880, 680, 64, 64); // Set the button's position and size

        // Customize the button appearance
        entryButton.setBackground(Color.BLUE);
        entryButton.setOpaque(true);
        entryButton.setBorderPainted(false);

        imagePanel.add(entryButton);
        add(imagePanel);
    }

    class ImagePanel extends JPanel {
        private BufferedImage image;

        public ImagePanel(BufferedImage image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("SansSerif", Font.BOLD, 64));
                g2d.drawString("WELCOME", 350, 400); // Adjust the position as needed
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelWelcome frame = new HotelWelcome();
            frame.setVisible(true);
        });
    }
}
