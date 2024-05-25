package views;

import controllers.AdminController;
import models.Chambre;
import models.GestionChambres;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class RoomManagementView extends JFrame {
    private AdminController adminController;
    private JButton addRoomButton;
    private JButton deleteRoomButton;
    private JButton updateStatusButton;
    private JButton updateRoomButton;
    private JList<String> roomList;
    private DefaultListModel<String> listModel;
    private JTextField roomIdField;
    private JTextField roomTypeField;
    private JTextField roomPriceField;
    private JComboBox<String> statusComboBox;

    public RoomManagementView(AdminController adminController) {
        this.adminController = adminController;
        setTitle("Room Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);
        add(panel);
        placeComponents(panel);

        loadChambres();

        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddRoomView(adminController, RoomManagementView.this).setVisible(true);
            }
        });

        deleteRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(roomIdField.getText());
                    adminController.supprimerChambre(numero);
                    loadChambres();
                    JOptionPane.showMessageDialog(null, "Room deleted successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid room ID!");
                }
            }
        });

        updateStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(roomIdField.getText());
                    String status = (String) statusComboBox.getSelectedItem();
                    adminController.updateChambreStatus(numero, status);
                    loadChambres();
                    JOptionPane.showMessageDialog(null, "Room status updated successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid room ID!");
                }
            }
        });

        updateRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(roomIdField.getText());
                    String type = roomTypeField.getText();
                    double price = Double.parseDouble(roomPriceField.getText());
                    Chambre chambre = adminController.getGestionChambres().getChambre(id);
                    if (chambre != null) {
                        chambre.setType(type);
                        chambre.setPrice(price);
                        chambre.setStatus((String) statusComboBox.getSelectedItem());
                        adminController.getGestionChambres().modifierChambre(id, chambre);
                        loadChambres();
                        JOptionPane.showMessageDialog(null, "Room details updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Room not found!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        });

        roomList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String selectedValue = roomList.getSelectedValue();
                    if (selectedValue != null) {
                        int roomId = Integer.parseInt(selectedValue.split(" ")[0]);
                        Chambre chambre = adminController.getGestionChambres().getChambre(roomId);
                        if (chambre != null) {
                            roomIdField.setText(String.valueOf(chambre.getNumero()));
                            roomTypeField.setText(chambre.getType());
                            roomPriceField.setText(String.valueOf(chambre.getPrice()));
                            statusComboBox.setSelectedItem(chambre.getStatus());
                        }
                    }
                }
            }
        });
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel roomIdLabel = new JLabel("Room ID:");
        roomIdLabel.setBounds(340, 20, 80, 25);
        roomIdLabel.setForeground(Color.WHITE);
        panel.add(roomIdLabel);

        roomIdField = new JTextField(20);
        roomIdField.setBounds(440, 20, 200, 25);
        panel.add(roomIdField);

        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setBounds(340, 60, 80, 25);
        roomTypeLabel.setForeground(Color.WHITE);
        panel.add(roomTypeLabel);

        roomTypeField = new JTextField(20);
        roomTypeField.setBounds(440, 60, 200, 25);
        panel.add(roomTypeField);

        JLabel roomPriceLabel = new JLabel("Room Price:");
        roomPriceLabel.setBounds(340, 100, 80, 25);
        roomPriceLabel.setForeground(Color.WHITE);
        panel.add(roomPriceLabel);

        roomPriceField = new JTextField(20);
        roomPriceField.setBounds(440, 100, 200, 25);
        panel.add(roomPriceField);

        addRoomButton = new JButton("Add Room");
        addRoomButton.setBounds(340, 140, 140, 30);
        addRoomButton.setBackground(new Color(70, 130, 180));
        addRoomButton.setForeground(Color.WHITE);
        panel.add(addRoomButton);

        deleteRoomButton = new JButton("Delete Room");
        deleteRoomButton.setBounds(500, 140, 140, 30);
        deleteRoomButton.setBackground(new Color(70, 130, 180));
        deleteRoomButton.setForeground(Color.WHITE);
        panel.add(deleteRoomButton);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(340, 180, 80, 25);
        statusLabel.setForeground(Color.WHITE);
        panel.add(statusLabel);

        statusComboBox = new JComboBox<>(new String[]{"Free", "Occupied"});
        statusComboBox.setBounds(440, 180, 200, 25);
        panel.add(statusComboBox);

        updateStatusButton = new JButton("Update Status");
        updateStatusButton.setBounds(340, 220, 140, 30);
        updateStatusButton.setBackground(new Color(70, 130, 180));
        updateStatusButton.setForeground(Color.WHITE);
        panel.add(updateStatusButton);

        updateRoomButton = new JButton("Update Room");
        updateRoomButton.setBounds(500, 220, 140, 30);
        updateRoomButton.setBackground(new Color(70, 130, 180));
        updateRoomButton.setForeground(Color.WHITE);
        panel.add(updateRoomButton);

        listModel = new DefaultListModel<>();
        roomList = new JList<>(listModel);
        roomList.setBackground(new Color(230, 230, 250));
        JScrollPane scrollPane = new JScrollPane(roomList);
        scrollPane.setBounds(10, 20, 320, 520);
        panel.add(scrollPane);
    }

    public void loadChambres() {
        listModel.clear();
        HashMap<Integer, Chambre> chambres = adminController.getGestionChambres().getChambres();
        for (Chambre chambre : chambres.values()) {
            listModel.addElement(chambre.getNumero() + " - " + chambre.getType() + " - " + chambre.getPrice() + " - " + chambre.getStatus());
        }
    }

    private static class GradientPanel extends JPanel {
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
        AdminController adminController = new AdminController(new GestionChambres(), new models.GestionReservations(),new models.GestionUtilisateurs ());
        new RoomManagementView(adminController).setVisible(true);
    }
}
