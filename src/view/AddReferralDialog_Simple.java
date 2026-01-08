package view;

import controller.SystemController;
import model.Patient;
import model.Clinician;
import model.Referral;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddReferralDialog extends JDialog {
    private SystemController controller;
    private ReferralsPanel parentPanel;

    private JComboBox<String> patientComboBox;
    private JComboBox<String> doctorComboBox;
    private JComboBox<String> urgencyComboBox;
    private JTextArea notesArea;

    public AddReferralDialog(JFrame parent, SystemController controller, ReferralsPanel parentPanel) {
        super(parent, "Create New Referral", true);
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
        formPanel.add(new JLabel("Doctor ID:"), gbc);

        doctorComboBox = new JComboBox<>();
        loadDoctors();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(doctorComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Urgency:"), gbc);

        String[] urgencyLevels = {"Routine", "Urgent"};
        urgencyComboBox = new JComboBox<>(urgencyLevels);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(urgencyComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Notes:"), gbc);

        notesArea = new JTextArea(8, 30);
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        JScrollPane notesScrollPane = new JScrollPane(notesArea);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(notesScrollPane, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save Referral");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> saveReferral());
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

    private void loadDoctors() {
        doctorComboBox.removeAllItems();
        doctorComboBox.addItem("-- Select Doctor --");
        for (Clinician clinician : controller.getAllClinicians()) {
            doctorComboBox.addItem(clinician.getClinicianId() + " - " + 
                                  clinician.getTitle() + " " + 
                                  clinician.getFirstName() + " " + 
                                  clinician.getLastName());
        }
    }

    private void saveReferral() {
        if (patientComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a patient.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (doctorComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a doctor.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String notes = notesArea.getText().trim();

        String patientSelection = (String) patientComboBox.getSelectedItem();
        String patientId = patientSelection.split(" - ")[0];

        String doctorSelection = (String) doctorComboBox.getSelectedItem();
        String doctorId = doctorSelection.split(" - ")[0];

        String urgency = (String) urgencyComboBox.getSelectedItem();

        String referralId = controller.getNextReferralId();

        String currentDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        Referral ref = new Referral(
            referralId,
            patientId,
            doctorId,
            doctorId,
            "S001",
            "H001",
            currentDate,
            urgency,
            "Referral for specialist",
            notes,
            "",
            "Pending",
            "",
            notes,
            currentDate,
            currentDate
        );

        boolean success = controller.createReferral(ref);

        if (success) {
            JOptionPane.showMessageDialog(this, "Referral created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            parentPanel.refreshTable();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to create referral. Please check the logs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
