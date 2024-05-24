package views;

import controllers.AdminController;
import models.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Date;

public class ReservationManagementView extends JFrame {
    private AdminController adminController;
    private JButton acceptReservationButton;
    private JButton declineReservationButton;
    private JButton addReservationButton;
    private JList<String> reservationList;
    private DefaultListModel<String> listModel;
    private JTextField reservationIdField;
    private JTextField roomIdField;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JTextField clientUsernameField;

    public ReservationManagementView(AdminController adminController) {
        this.adminController = adminController;

        setTitle("Reservation Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        GradientPanel panel = new GradientPanel();
        add(panel);
        placeComponents(panel);

        loadReservationList();

        addReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomId = Integer.parseInt(roomIdField.getText());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startDate = dateFormat.format(startDateSpinner.getValue());
                String endDate = dateFormat.format(endDateSpinner.getValue());
                String clientUsername = clientUsernameField.getText();
                Reservation reservation = new Reservation(roomId, startDate, endDate, clientUsername);
                adminController.addReservation(reservation);
                loadReservationList();
                JOptionPane.showMessageDialog(null, "Reservation added successfully!");
            }
        });

        acceptReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = reservationList.getSelectedValue();
                if (selectedValue != null) {
                    int id = Integer.parseInt(selectedValue.split(" - ")[0]);
                    adminController.acceptReservation(id);
                    adminController.removeReservation(id);
                    loadReservationList();
                    JOptionPane.showMessageDialog(null, "Reservation accepted!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a reservation to accept.");
                }
            }
        });

        declineReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = reservationList.getSelectedValue();
                if (selectedValue != null) {
                    int id = Integer.parseInt(selectedValue.split(" - ")[0]);
                    adminController.declineReservation(id);
                    adminController.removeReservation(id);
                    loadReservationList();
                    JOptionPane.showMessageDialog(null, "Reservation declined!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a reservation to decline.");
                }
            }
        });
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel reservationIdLabel = new JLabel("Reservation ID:");
        reservationIdLabel.setBounds(340, 20, 160, 25);
        reservationIdLabel.setForeground(Color.WHITE);
        panel.add(reservationIdLabel);

        reservationIdField = new JTextField(20);
        reservationIdField.setBounds(510, 20, 200, 25);
        panel.add(reservationIdField);

        JLabel roomIdLabel = new JLabel("Room ID:");
        roomIdLabel.setBounds(340, 60, 160, 25);
        roomIdLabel.setForeground(Color.WHITE);
        panel.add(roomIdLabel);

        roomIdField = new JTextField(20);
        roomIdField.setBounds(510, 60, 200, 25);
        panel.add(roomIdField);

        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setBounds(340, 100, 160, 25);
        startDateLabel.setForeground(Color.WHITE);
        panel.add(startDateLabel);

        startDateSpinner = new JSpinner(new SpinnerDateModel());
        startDateSpinner.setBounds(510, 100, 200, 25);
        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd");
        startDateSpinner.setEditor(startEditor);
        panel.add(startDateSpinner);

        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setBounds(340, 140, 160, 25);
        endDateLabel.setForeground(Color.WHITE);
        panel.add(endDateLabel);

        endDateSpinner = new JSpinner(new SpinnerDateModel());
        endDateSpinner.setBounds(510, 140, 200, 25);
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd");
        endDateSpinner.setEditor(endEditor);
        panel.add(endDateSpinner);

        JLabel clientUsernameLabel = new JLabel("Client Username:");
        clientUsernameLabel.setBounds(340, 180, 160, 25);
        clientUsernameLabel.setForeground(Color.WHITE);
        panel.add(clientUsernameLabel);

        clientUsernameField = new JTextField(20);
        clientUsernameField.setBounds(510, 180, 200, 25);
        panel.add(clientUsernameField);

        addReservationButton = new JButton("Add Reservation");
        addReservationButton.setBounds(340, 220, 160, 30);
        addReservationButton.setBackground(new Color(70, 130, 180));
        addReservationButton.setForeground(Color.WHITE);
        panel.add(addReservationButton);

        acceptReservationButton = new JButton("Accept Reservation");
        acceptReservationButton.setBounds(510, 220, 200, 30);
        acceptReservationButton.setBackground(new Color(70, 130, 180));
        acceptReservationButton.setForeground(Color.WHITE);
        panel.add(acceptReservationButton);

        declineReservationButton = new JButton("Decline Reservation");
        declineReservationButton.setBounds(340, 260, 370, 30);
        declineReservationButton.setBackground(new Color(70, 130, 180));
        declineReservationButton.setForeground(Color.WHITE);
        panel.add(declineReservationButton);

        listModel = new DefaultListModel<>();
        reservationList = new JList<>(listModel);
        reservationList.setBackground(new Color(230, 230, 250));
        JScrollPane scrollPane = new JScrollPane(reservationList);
        scrollPane.setBounds(10, 20, 320, 520);
        panel.add(scrollPane);
    }

    public void loadReservationList() {
        listModel.clear();
        HashMap<Integer, Reservation> reservations = adminController.getGestionReservations().getAllReservations();
        for (Reservation reservation : reservations.values()) {
            listModel.addElement(reservation.getId() + " - " + reservation.getNumeroChambre() + " - " + reservation.getDateDebut() + " - " + reservation.getDateFin() + " - " + reservation.getClientUsername() + " - " + reservation.getStatus());
        }
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
        AdminController adminController = new AdminController(new models.GestionChambres(), new models.GestionReservations());
        new ReservationManagementView(adminController).setVisible(true);
    }
}
