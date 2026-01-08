package view;

import controller.SystemController;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Prescription;

public class PrescriptionPanel extends JPanel {
    private SystemController controller;
    private JTable prescriptionTable;
    private DefaultTableModel tableModel;

    public PrescriptionPanel(SystemController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Patient ID", "Drug Name", "Dosage", "Start Date"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        prescriptionTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(prescriptionTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton issuePrescriptionButton = new JButton("Issue Prescription");
        
        issuePrescriptionButton.addActionListener(e -> openAddPrescriptionDialog());
        
        buttonPanel.add(issuePrescriptionButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadPrescriptions();
        
        System.out.println("PrescriptionPanel initialized successfully.");
    }

    private void loadPrescriptions() {
        tableModel.setRowCount(0);

        List<Prescription> prescriptions = controller.getAllPrescriptions();
        for (Prescription prescription : prescriptions) {
            Object[] row = {
                prescription.getPrescriptionId(),
                prescription.getPatientId(),
                prescription.getMedicationName(),
                prescription.getDosage(),
                prescription.getPrescriptionDate()
            };
            tableModel.addRow(row);
        }
        
        System.out.println("Prescription table loaded. Total prescriptions: " + prescriptions.size());
    }

    private void openAddPrescriptionDialog() {
        AddPrescriptionDialog dialog = new AddPrescriptionDialog(
            (JFrame) SwingUtilities.getWindowAncestor(this),
            controller,
            this
        );
        dialog.setVisible(true);
    }

    public void refreshTable() {
        loadPrescriptions();
    }
}
