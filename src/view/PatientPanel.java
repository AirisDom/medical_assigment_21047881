package view;

import controller.SystemController;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Patient;

public class PatientPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private SystemController controller;
    
    public PatientPanel(SystemController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        
        setupTable();
        
        setupButtons();
        
        refreshTable();
        
        System.out.println("PatientPanel initialized successfully.");
    }
    
    private void setupTable() {
        String[] columns = {"ID", "First Name", "Last Name", "DOB", "NHS Number", "Phone"};
        
        tableModel = new DefaultTableModel(columns, 0);
        
        table = new JTable(tableModel);
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void setupButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton addButton = new JButton("Add Patient");
        addButton.addActionListener(e -> addPatient());
        
        JButton editButton = new JButton("Edit Patient");
        editButton.addActionListener(e -> editPatient());
        
        JButton deleteButton = new JButton("Delete Patient");
        deleteButton.addActionListener(e -> deletePatient());
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshTable());
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void refreshTable() {
        tableModel.setRowCount(0);
        
        List<Patient> patients = controller.getAllPatients();
        
        for (Patient p : patients) {
            tableModel.addRow(new Object[]{
                p.getPatientId(),
                p.getFirstName(),
                p.getLastName(),
                p.getDateOfBirth(),
                p.getNhsNumber(),
                p.getPhoneNumber()
            });
        }
        
        System.out.println("Patient table refreshed. Total patients: " + patients.size());
    }
    
    private void addPatient() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        
        AddPatientDialog dialog = new AddPatientDialog(parentFrame, controller);
        dialog.setVisible(true);
        
        refreshTable();
    }
    
    private void editPatient() {
        int selectedRow = table.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select a patient to edit.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String patientId = (String) tableModel.getValueAt(selectedRow, 0);
        Patient patient = controller.getPatientById(patientId);
        
        if (patient == null) {
            JOptionPane.showMessageDialog(this,
                "Patient not found!",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        EditPatientDialog dialog = new EditPatientDialog(parentFrame, controller, patient, this);
        dialog.setVisible(true);
    }
    
    private void deletePatient() {
        int selectedRow = table.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select a patient to delete.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String patientId = (String) tableModel.getValueAt(selectedRow, 0);
        String firstName = (String) tableModel.getValueAt(selectedRow, 1);
        String lastName = (String) tableModel.getValueAt(selectedRow, 2);
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete patient:\n" +
            patientId + " - " + firstName + " " + lastName + "?\n\n" +
            "This action cannot be undone!",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = controller.deletePatient(patientId);
            
            if (success) {
                JOptionPane.showMessageDialog(this,
                    "Patient deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Failed to delete patient. Please check the console for errors.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
