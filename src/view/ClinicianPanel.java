package view;

import controller.SystemController;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Clinician;

public class ClinicianPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private SystemController controller;
    
    public ClinicianPanel(SystemController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        
        setupTable();
        
        setupButtons();
        
        refreshTable();
        
        System.out.println("ClinicianPanel initialized successfully.");
    }
    
    private void setupTable() {
        String[] columns = {"ID", "Title", "First Name", "Last Name", "Speciality", "GMC Number", "Phone", "Email"};
        
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
        
        JButton addButton = new JButton("Add Clinician");
        addButton.addActionListener(e -> addClinician());
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshTable());
        
        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void refreshTable() {
        tableModel.setRowCount(0);
        
        List<Clinician> clinicians = controller.getAllClinicians();
        
        for (Clinician c : clinicians) {
            tableModel.addRow(new Object[]{
                c.getClinicianId(),
                c.getTitle(),
                c.getFirstName(),
                c.getLastName(),
                c.getSpeciality(),
                c.getGmcNumber(),
                c.getPhoneNumber(),
                c.getEmail()
            });
        }
        
        System.out.println("Clinician table refreshed. Total clinicians: " + clinicians.size());
    }
    
    private void addClinician() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        
        AddClinicianDialog dialog = new AddClinicianDialog(parentFrame, controller, this);
        dialog.setVisible(true);
    }
}
