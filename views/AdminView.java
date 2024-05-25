package views;

import controllers.AdminController;
import models.GestionChambres;
import models.GestionReservations;
import models.GestionUtilisateurs;

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
        this.adminController = new AdminController(new GestionChambres(), new GestionReservations(), new GestionUtilisateurs ());
        setTitle("Admin Panel");
        setSize(1000, 700);
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

        roomsButton = createButton("C:\\Users\\akr\\Downloads\\ea2518422b6568aa0feafab103e76eae.jpg", 100, 150, 250, 250);
        panel.add(roomsButton);

        roomsLabel = createLabel("Rooms", 160, 420, 120, 30);
        panel.add(roomsLabel);

        reservationsButton = createButton("C:\\Users\\akr\\Downloads\\reservation.jpg", 400, 150, 250, 250);
        panel.add(reservationsButton);

        reservationsLabel = createLabel("Reservations", 460, 420, 120, 30);
        panel.add(reservationsLabel);

        usersButton = createButton("C:\\Users\\akr\\Downloads\\users.jpg", 700, 150, 250, 250);
        panel.add(usersButton);

        usersLabel = createLabel("Users", 760, 420, 120, 30);
        panel.add(usersLabel);
    }

    private JButton createButton(String imagePath, int x, int y, int width, int height) {
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
        button.setBackground(new Color(70, 130, 180));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return button;
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }
    private void manageUsers() {
        UserManagementView userManagementView = new UserManagementView(adminController);
        userManagementView.setVisible(true);
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
        AdminController adminController = new AdminController(new GestionChambres(), new GestionReservations(),new GestionUtilisateurs ());
        AdminView adminView = new AdminView(adminController);
        adminView.setVisible(true);
    }
}
