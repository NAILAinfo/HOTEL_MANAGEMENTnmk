package views;

import controllers.AdminController;
import models.InvalidDateException;
import models.GestionUtilisateurs;
import models.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
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
    private JTextField startDateField; // Utilisation de JTextField pour la date de début
    private JTextField endDateField;   // Utilisation de JTextField pour la date de fin
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
                try {
                    int roomId = Integer.parseInt(roomIdField.getText());
                    String startDate = startDateField.getText();
                    String endDate = endDateField.getText();
                    String clientUsername = clientUsernameField.getText();

                    validateDate(startDate);
                    validateDate(endDate);

                    Reservation reservation = new Reservation(roomId, startDate, endDate, clientUsername);
                    adminController.addReservation(reservation);
                    loadReservationList();
                    JOptionPane.showMessageDialog(null, "Reservation added successfully!");
                } catch (ParseException | InvalidDateException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
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

        JLabel startDateLabel = new JLabel("Start Date (yyyy-MM-dd):");
        startDateLabel.setBounds(340, 100, 160, 25);
        startDateLabel.setForeground(Color.WHITE);
        panel.add(startDateLabel);

        startDateField = new JTextField(20);
        startDateField.setBounds(510, 100, 200, 25);
        panel.add(startDateField);

        JLabel endDateLabel = new JLabel("End Date (yyyy-MM-dd):");
        endDateLabel.setBounds(340, 140, 160, 25);
        endDateLabel.setForeground(Color.WHITE);
        panel.add(endDateLabel);

        endDateField = new JTextField(20);
        endDateField.setBounds(510, 140, 200, 25);
        panel.add(endDateField);

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

    private void validateDate(String dateStr) throws ParseException, InvalidDateException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Mode strict pour la validation des dates
        Date date = dateFormat.parse(dateStr);
        if (dateStr.length() != 10) {
            throw new InvalidDateException("Date format is incorrect.");
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
        AdminController adminController = new AdminController(new models.GestionChambres(), new models.GestionReservations(), new models.GestionUtilisateurs());
        new ReservationManagementView(adminController).setVisible(true);
    }
}
