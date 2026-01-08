package view;

import controller.SystemController;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import model.Appointment;
import model.Clinician;
import model.Facility;
import model.Patient;

public class AddAppointmentDialog extends JDialog {
    private SystemController controller;
    private AppointmentPanel parentPanel;

    private JComboBox<String> patientComboBox;
    private JComboBox<String> clinicianComboBox;
    private JComboBox<String> facilityComboBox;
    private JTextField dateField;
    private JTextField timeField;
    private JSpinner durationSpinner;
    private JComboBox<String> typeComboBox;
    private JComboBox<String> statusComboBox;
    private JTextField reasonField;
    private JTextArea notesArea;

    public AddAppointmentDialog(JFrame parent, SystemController controller, AppointmentPanel parentPanel) {
        super(parent, "Book New Appointment", true);
        this.controller = controller;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout(10, 10));
        setSize(500, 600);
        setLocationRelativeTo(parent);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Patient:"), gbc);

        patientComboBox = new JComboBox<>();
        loadPatients();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(patientComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Clinician:"), gbc);

        clinicianComboBox = new JComboBox<>();
        loadClinicians();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(clinicianComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Facility:"), gbc);

        facilityComboBox = new JComboBox<>();
        loadFacilities();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(facilityComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);

        dateField = new JTextField(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Time (HH:MM):"), gbc);

        timeField = new JTextField("09:00");
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(timeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Duration (minutes):"), gbc);

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(15, 5, 120, 5);
        durationSpinner = new JSpinner(spinnerModel);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(durationSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Type:"), gbc);

        String[] types = {"Routine Consultation", "Follow-up", "Urgent Consultation", 
                          "Emergency", "Vaccination", "Specialist Consultation", "Health Check"};
        typeComboBox = new JComboBox<>(types);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(typeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Status:"), gbc);

        String[] statuses = {"Scheduled", "Confirmed", "In Progress", "Completed", "Cancelled", "No Show"};
        statusComboBox = new JComboBox<>(statuses);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(statusComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Reason for Visit:"), gbc);

        reasonField = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(reasonField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Notes:"), gbc);

        notesArea = new JTextArea(4, 20);
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
        JButton saveButton = new JButton("Book Appointment");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> saveAppointment());
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

    private void loadClinicians() {
        clinicianComboBox.removeAllItems();
        clinicianComboBox.addItem("-- Select Clinician --");
        for (Clinician clinician : controller.getAllClinicians()) {
            clinicianComboBox.addItem(clinician.getClinicianId() + " - " + 
                                     clinician.getTitle() + " " + 
                                     clinician.getFirstName() + " " + 
                                     clinician.getLastName());
        }
    }

    private void loadFacilities() {
        facilityComboBox.removeAllItems();
        facilityComboBox.addItem("-- Select Facility --");
        for (Facility facility : controller.getAllFacilities()) {
            facilityComboBox.addItem(facility.getFacilityId() + " - " + facility.getFacilityName());
        }
    }

    private void saveAppointment() {
        if (patientComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a patient.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (clinicianComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a clinician.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (facilityComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a facility.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String date = dateField.getText().trim();
        String time = timeField.getText().trim();
        String reason = reasonField.getText().trim();

        if (date.isEmpty() || time.isEmpty() || reason.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid time format. Use HH:MM (24-hour).", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String patientSelection = (String) patientComboBox.getSelectedItem();
        String patientId = patientSelection.split(" - ")[0];

        String clinicianSelection = (String) clinicianComboBox.getSelectedItem();
        String clinicianId = clinicianSelection.split(" - ")[0];

        String facilitySelection = (String) facilityComboBox.getSelectedItem();
        String facilityId = facilitySelection.split(" - ")[0];

        int duration = (Integer) durationSpinner.getValue();
        String type = (String) typeComboBox.getSelectedItem();
        String status = (String) statusComboBox.getSelectedItem();
        String notes = notesArea.getText().trim();

        String appointmentId = controller.getNextAppointmentId();

        String currentDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        Appointment appointment = new Appointment(
            appointmentId,
            patientId,
            clinicianId,
            facilityId,
            date,
            time,
            duration,
            type,
            status,
            reason,
            notes,
            currentDate,
            currentDate
        );

        boolean success = controller.createAppointment(appointment);

        if (success) {
            JOptionPane.showMessageDialog(this, "Appointment booked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            parentPanel.refreshTable();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to book appointment. Please check the logs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
