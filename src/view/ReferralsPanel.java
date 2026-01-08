package view;

import controller.SystemController;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Referral;

public class ReferralsPanel extends JPanel {
    private final SystemController controller;
    private final JTable referralTable;
    private final DefaultTableModel tableModel;

    public ReferralsPanel(SystemController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Patient ID", "Referring Clinician", "Referred To", "Urgency", "Status", "Date"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        referralTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(referralTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Create Referral");
        JButton refreshButton = new JButton("Refresh");
        
        addButton.addActionListener(e -> openAddReferralDialog());
        refreshButton.addActionListener(e -> refreshTable());
        
        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshTable();
        
        System.out.println("ReferralsPanel initialized successfully.");
    }

    public void refreshTable() {
        tableModel.setRowCount(0);

        List<Referral> referrals = controller.getAllReferrals();
        for (Referral referral : referrals) {
            Object[] row = {
                referral.getReferralId(),
                referral.getPatientId(),
                referral.getReferringClinicianId(),
                referral.getReferredToClinicianId(),
                referral.getUrgencyLevel(),
                referral.getStatus(),
                referral.getReferralDate()
            };
            tableModel.addRow(row);
        }
        
        System.out.println("Referral table refreshed. Total referrals: " + referrals.size());
    }

    private void openAddReferralDialog() {
        AddReferralDialog dialog = new AddReferralDialog(
            (JFrame) SwingUtilities.getWindowAncestor(this),
            controller,
            this
        );
        dialog.setVisible(true);
    }
}
