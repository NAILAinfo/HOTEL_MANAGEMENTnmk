package views;

import controllers.AdminController;
import models.Chambre;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddRoomView extends JFrame {
    private AdminController adminController;
    private RoomManagementView parentView;
    private JTextField roomIdField;
    private JTextField roomTypeField;
    private JTextField roomPriceField;
    private JCheckBox availableCheckBox;

    public AddRoomView(AdminController adminController, RoomManagementView parentView) {
        this.adminController = adminController;
        this.parentView = parentView;
        setTitle("Add Room");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(135, 206, 250); // Light Sky Blue
                Color color2 = new Color(0, 191, 255);   // Deep Sky Blue
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel roomIdLabel = new JLabel("Room ID:");
        roomIdLabel.setBounds(10, 20, 80, 25);
        panel.add(roomIdLabel);

        roomIdField = new JTextField(20);
        roomIdField.setBounds(100, 20, 160, 25);
        panel.add(roomIdField);

        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setBounds(10, 60, 80, 25);
        panel.add(roomTypeLabel);

        roomTypeField = new JTextField(20);
        roomTypeField.setBounds(100, 60, 160, 25);
        panel.add(roomTypeField);

        JLabel roomPriceLabel = new JLabel("Room Price:");
        roomPriceLabel.setBounds(10, 100, 80, 25);
        panel.add(roomPriceLabel);

        roomPriceField = new JTextField(20);
        roomPriceField.setBounds(100, 100, 160, 25);
        panel.add(roomPriceField);

        JLabel availableLabel = new JLabel("Available:");
        availableLabel.setBounds(10, 140, 80, 25);
        panel.add(availableLabel);

        availableCheckBox = new JCheckBox();
        availableCheckBox.setBounds(100, 140, 160, 25);
        panel.add(availableCheckBox);

        JButton addButton = new JButton("Add");
        addButton.setBounds(10, 180, 250, 25);
        panel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(roomIdField.getText());
                    if (id <= 0) {
                        throw new IllegalArgumentException("Room ID must be a positive integer.");
                    }
                    String type = roomTypeField.getText();
                    if (type.isEmpty()) {
                        throw new IllegalArgumentException("Room type cannot be empty.");
                    }
                    double price = Double.parseDouble(roomPriceField.getText());
                    if (price <= 0) {
                        throw new IllegalArgumentException("Room price must be a positive number.");
                    }
                    boolean available = availableCheckBox.isSelected();
                    // Check if room ID already exists
                    if (adminController.getGestionChambres().getChambre(id) != null) {
                        throw new IllegalArgumentException("Room ID already exists.");
                    }
                    Chambre chambre = new Chambre(id, type, price, available);
                    try {
                        adminController.ajouterChambre(chambre);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    parentView.loadChambres();
                    JOptionPane.showMessageDialog(null, "Room added successfully!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input for room ID or price. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
