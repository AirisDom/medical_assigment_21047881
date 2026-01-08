package view;

import controller.SystemController;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import model.Patient;
import model.Prescription;

public class AddPrescriptionDialog extends JDialog {
    private SystemController controller;
    private PrescriptionPanel parentPanel;

    private JComboBox<String> patientComboBox;
    private JTextField drugNameField;
    private JTextField dosageField;
    private JTextField quantityField;
    private JTextArea instructionsArea;

    public AddPrescriptionDialog(JFrame parent, SystemController controller, PrescriptionPanel parentPanel) {
        super(parent, "Issue New Prescription", true);
        this.controller = controller;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout(10, 10));
        setSize(450, 400);
        setLocationRelativeTo(parent);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Patient ID:"), gbc);

        patientComboBox = new JComboBox<>();
        loadPatients();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(patientComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Drug Name:"), gbc);

        drugNameField = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(drugNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Dosage:"), gbc);

        dosageField = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(dosageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Quantity:"), gbc);

        quantityField = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Instructions:"), gbc);

        instructionsArea = new JTextArea(6, 30);
        instructionsArea.setLineWrap(true);
        instructionsArea.setWrapStyleWord(true);
        JScrollPane instructionsScrollPane = new JScrollPane(instructionsArea);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(instructionsScrollPane, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Issue Prescription");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> savePrescription());
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadPatients() {
        patientComboBox.removeAllItems();
        patientComboBox.addItem("-- Select Patient --");
        for (Patient patient : controller.getAllPatients()) {
            patientComboBox.addItem(patient.getPatientId() + " - " + 
                                   patient.getFirstName() + " " + patient.getLastName());
        }
    }

    private void savePrescription() {
        if (patientComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a patient.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String drugName = drugNameField.getText().trim();
        String dosage = dosageField.getText().trim();
        String quantity = quantityField.getText().trim();
        String instructions = instructionsArea.getText().trim();

        if (drugName.isEmpty() || dosage.isEmpty() || quantity.isEmpty() || instructions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String patientSelection = (String) patientComboBox.getSelectedItem();
        String patientId = patientSelection.split(" - ")[0];

        String prescriptionId = controller.getNextPrescriptionId();

        String currentDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        Prescription rx = new Prescription(
            prescriptionId,
            patientId,
            "C001",
            "",
            currentDate,
            drugName,
            dosage,
            "As directed",
            30,
            quantity,
            instructions,
            "Default Pharmacy",
            "Issued",
            currentDate,
            ""
        );

        boolean success = controller.createPrescription(rx);

        if (success) {
            JOptionPane.showMessageDialog(this, "Prescription issued successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            parentPanel.refreshTable();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to issue prescription. Please check the logs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
