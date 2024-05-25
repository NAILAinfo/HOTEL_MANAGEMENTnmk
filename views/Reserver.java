package views;

import controllers.ClientController;
import models.GestionReservations;
import models.GestionChambres;
import models.Reservation;
import models.InvalidReservationDateException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Reserver extends JFrame {
    private ClientController clientController;

    private JButton reserverButton;
    private JButton annulerButton;
    private JButton afficherMontantButton;
    private JComboBox<String> typeChambreComboBox;
    private JTextField dateDebutField;
    private JTextField dateFinField;
    private JTable reservationTable;
    private DefaultTableModel tableModel;
    private Map<String, Integer> prixChambres;

    public Reserver() {
        clientController = new ClientController(new GestionReservations(), new GestionChambres());
        initPrixChambres();
        initComponents();
    }

    private void initPrixChambres() {
        prixChambres = new HashMap<>();
        prixChambres.put("STANDARD", 100);
        prixChambres.put("DE_LUXE", 150);
        prixChambres.put("CHAMBRE_FAMILIALE", 200);
        prixChambres.put("SUITE", 250);
        prixChambres.put("EXECUTIVE", 300);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Réservation Client");
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255)); // Bleu clair pour le fond

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255)); // Bleu clair pour le fond
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Réservation Client", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 123, 255)); // Bleu pour le texte
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(titleLabel, gbc);

        JLabel typeChambreLabel = new JLabel("Type chambre");
        typeChambreLabel.setForeground(new Color(0, 123, 255)); // Bleu pour le texte
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(typeChambreLabel, gbc);

        typeChambreComboBox = new JComboBox<>(new String[]{"STANDARD", "DE_LUXE", "CHAMBRE_FAMILIALE", "SUITE", "EXECUTIVE"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(typeChambreComboBox, gbc);

        JLabel dateDebutLabel = new JLabel("Date de début (dd/MM/yyyy HH:mm)");
        dateDebutLabel.setForeground(new Color(0, 123, 255)); // Bleu pour le texte
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(dateDebutLabel, gbc);

        dateDebutField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(dateDebutField, gbc);

        JLabel dateFinLabel = new JLabel("Date de fin (dd/MM/yyyy HH:mm)");
        dateFinLabel.setForeground(new Color(0, 123, 255)); // Bleu pour le texte
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(dateFinLabel, gbc);

        dateFinField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(dateFinField, gbc);

        reserverButton = createModernButton("Réserver");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(reserverButton, gbc);

        annulerButton = createModernButton("Annuler Réservation");
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(annulerButton, gbc);

        afficherMontantButton = createModernButton("Afficher Montant");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(afficherMontantButton, gbc);

        mainPanel.add(formPanel, BorderLayout.WEST);

        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Type chambre", "Date de début", "Date de fin"});
        reservationTable = new JTable(tableModel);
        reservationTable.setRowHeight(30);
        reservationTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Personnaliser l'en-tête du tableau
        JTableHeader tableHeader = reservationTable.getTableHeader();
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableHeader.setBackground(new Color(0, 123, 255));
        tableHeader.setForeground(Color.WHITE);

        // Personnaliser les cellules du tableau
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cellRenderer.setBackground(new Color(224, 255, 255)); // Bleu très clair
        cellRenderer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        reservationTable.setDefaultRenderer(Object.class, cellRenderer);

        JScrollPane scrollPane = new JScrollPane(reservationTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        reserverButton.addActionListener(evt -> {
            try {
                reserverAction(evt);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Format de date invalide. Veuillez utiliser le format dd/MM/yyyy HH:mm.", "Erreur de date", JOptionPane.ERROR_MESSAGE);
            }
        });
        annulerButton.addActionListener(this::annulerAction);
        afficherMontantButton.addActionListener(this::afficherMontantAction);

        add(mainPanel);
    }

    private void reserverAction(ActionEvent evt) throws ParseException {
        String typeChambre = (String) typeChambreComboBox.getSelectedItem();
        String dateDebutStr = dateDebutField.getText();
        String dateFinStr = dateFinField.getText();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateDebut = dateFormat.parse(dateDebutStr);
        Date dateFin = dateFormat.parse(dateFinStr);

        if (dateDebut.after(dateFin)) {
            JOptionPane.showMessageDialog(this, "La date de début ne peut pas être supérieure à la date de fin.", "Erreur de date", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Reservation reservation = new Reservation(generateUniqueId(), typeChambre, dateDebutStr, dateFinStr);
        clientController.ajouterReservation(reservation);

        tableModel.addRow(new Object[]{typeChambre, dateDebut, dateFin});
    }

    private void annulerAction(ActionEvent evt) {
        int selectedRow = reservationTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une réservation à annuler.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void afficherMontantAction(ActionEvent evt) {
        int selectedRow = reservationTable.getSelectedRow();
        if (selectedRow != -1) {
            String typeChambre = (String) tableModel.getValueAt(selectedRow, 0);
            Date dateDebut = (Date) tableModel.getValueAt(selectedRow, 1);
            Date dateFin = (Date) tableModel.getValueAt(selectedRow, 2);

            LocalDateTime debut = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime fin = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            long daysBetween = Duration.between(debut, fin).toDays();

            int prixParJour = prixChambres.get(typeChambre);
            int montantTotal = (int) (prixParJour * daysBetween);

            JOptionPane.showMessageDialog(this, "Le montant total est : " + montantTotal + "€", "Montant Total", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une réservation dans le tableau.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
        }
    }

    private int generateUniqueId() {
        return (int) (Math.random() * 10000); // Générer un ID unique de manière aléatoire
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Reserver reserver = new Reserver();
            reserver.setVisible(true);
        });
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isContentAreaFilled()) {
                    super.paintComponent(g);
                    return;
                }
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(135, 206, 235); // Bleu ciel clair
                Color color2 = new Color(0, 123, 255); // Bleu ciel foncé
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, width, height, 15, 15);
                g2d.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 123, 255)); // Bleu pour la bordure
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                g2d.dispose();
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.BLACK); // Texte en noir
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }
}
