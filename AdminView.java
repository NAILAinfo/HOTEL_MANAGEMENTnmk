package views;

import controllers.AdminController;
import models.GestionChambres;
import models.GestionReservations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {
    private AdminController adminController;
    private JButton roomsButton;
    private JButton reservationsButton;
    private JButton usersButton;
    private JLabel roomsLabel;
    private JLabel reservationsLabel;
    private JLabel usersLabel;

    public AdminView(AdminController adminController) {
        this.adminController = new AdminController(new GestionChambres(), new GestionReservations());
        setTitle("Admin Panel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(null);
        add(mainPanel);

        placeComponents(mainPanel);
        addEventListeners();
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        roomsButton = createButton("C:\\Users\\akr\\Downloads\\213bebf06d1670c419b880f57aaf71ba.jpg", 100, 150, 200, 200);
        panel.add(roomsButton);

        roomsLabel = createLabel("Rooms", 160, 360, 80, 25);
        panel.add(roomsLabel);

        reservationsButton = createButton("path/to/reservations_image.jpg", 300, 150, 200, 200);
        panel.add(reservationsButton);

        reservationsLabel = createLabel("Reservations", 360, 360, 120, 25);
        panel.add(reservationsLabel);

        usersButton = createButton("path/to/users_image.jpg", 500, 150, 200, 200);
        panel.add(usersButton);

        usersLabel = createLabel("Users", 560, 360, 80, 25);
        panel.add(usersLabel);
    }

    private JButton createButton(String imagePath, int x, int y, int width, int height) {
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        button.setIcon(new ImageIcon(imagePath));
        button.setBackground(new Color(70, 130, 180));
        return button;
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private void addEventListeners() {
        roomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RoomManagementView(adminController).setVisible(true);
            }
        });

        reservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReservationManagementView(adminController).setVisible(true);
            }
        });

        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserManagementView(adminController).setVisible(true);
            }
        });
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color color1 = new Color(70, 130, 180);
            Color color2 = new Color(230, 230, 250);
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    public static void main(String[] args) {
        AdminController adminController = new AdminController(new GestionChambres(), new GestionReservations());
        AdminView adminView = new AdminView(adminController);
        adminView.setVisible(true);
    }
}
