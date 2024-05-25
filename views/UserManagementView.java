package views;

import controllers.AdminController;
import models.Utilisateur;
import models.GestionReservations;
import models.GestionChambres;
import models.GestionUtilisateurs;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserManagementView extends JFrame {
    private AdminController adminController;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    public UserManagementView(AdminController adminController) {
        this.adminController = adminController;
        setTitle("User Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Create table model with columns
        tableModel = new DefaultTableModel(new Object[]{"Username", "Role", "Email/Phone", "Date of Birth", "Country", "Sex"}, 0);
        userTable = new JTable(tableModel);
        mainPanel.add(new JScrollPane(userTable), BorderLayout.CENTER);

        // Create buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        addButton = createButton("Add User");
        editButton = createButton("Edit User");
        deleteButton = createButton("Delete User");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Load users into the table
        loadUsers();

        addButton.addActionListener(e -> addUser());
        editButton.addActionListener(e -> editUser());
        deleteButton.addActionListener(e -> deleteUser());
    }

    private void loadUsers() {
        tableModel.setRowCount(0); // Clear existing rows
        List<Utilisateur> users = adminController.getGestionUtilisateurs().getAllUtilisateurs(); // Use getAllUtilisateurs
        for (Utilisateur user : users) {
            tableModel.addRow(new Object[]{
                    user.getUsername(),
                    user.getRole(),
                    user.getEmail(),
                    user.getDateOfBirth(),
                    user.getCountry(),
                    user.getSex()
            });
        }
    }

    public void addUserToTable(Utilisateur user) {
        tableModel.addRow(new Object[]{
                user.getUsername(),
                user.getRole(),
                user.getEmail (),
                user.getDateOfBirth(),
                user.getCountry(),
                user.getSex()
        });
    }

    private void addUser() {
        SignupView signupView = new SignupView(adminController.getAuthController(), this);
        signupView.setVisible(true);
    }

    private void editUser() {
        // Edit user logic (to be implemented)
        JOptionPane.showMessageDialog(this, "Edit User functionality to be implemented.");
    }

    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            String username = (String) tableModel.getValueAt(selectedRow, 0);
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete user: " + username + "?", "Delete User", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                adminController.getGestionUtilisateurs().supprimerUtilisateur(username);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "User deleted successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
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
        AdminController adminController = new AdminController(new GestionChambres(), new GestionReservations(), new GestionUtilisateurs());
        UserManagementView userManagementView = new UserManagementView(adminController);
        userManagementView.setVisible(true);
    }
}
