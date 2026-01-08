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
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshTable());
        
        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void refreshTable() {
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
}
