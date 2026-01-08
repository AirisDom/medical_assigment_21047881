package view;

import controller.SystemController;
import java.awt.*;
import javax.swing.*;
import model.Patient;

public class EditPatientDialog extends JDialog {
    private SystemController controller;
    private Patient patient;
    private PatientPanel parentPanel;
    
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtDOB;
    private JTextField txtNhsNumber;
    private JTextField txtGender;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtAddress;
    private JTextField txtPostcode;
    private JTextField txtEmergencyName;
    private JTextField txtEmergencyPhone;
    
    public EditPatientDialog(JFrame parent, SystemController controller, Patient patient, PatientPanel parentPanel) {
        super(parent, "Edit Patient - " + patient.getPatientId(), true);
        this.controller = controller;
        this.patient = patient;
        this.parentPanel = parentPanel;
        
        setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        setupFormFields(formPanel);
        
        add(formPanel, BorderLayout.CENTER);
        
        setupButtons();
        
        setModal(true);
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
        
        System.out.println("EditPatientDialog initialized for: " + patient.getPatientId());
    }
    
    private void setupFormFields(JPanel formPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        int row = 0;
        
        // Patient ID (read-only)
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Patient ID:"), gbc);
        
        JLabel lblPatientId = new JLabel(patient.getPatientId());
        lblPatientId.setFont(new Font("Arial", Font.BOLD, 12));
        lblPatientId.setForeground(new Color(0, 100, 200));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(lblPatientId, gbc);
        
        row++;
        
        // First Name
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("First Name:"), gbc);
        
        txtFirstName = new JTextField(patient.getFirstName(), 25);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtFirstName, gbc);
        
        row++;
        
        // Last Name
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Last Name:"), gbc);
        
        txtLastName = new JTextField(patient.getLastName(), 25);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtLastName, gbc);
        
        row++;
        
        // Date of Birth
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Date of Birth:"), gbc);
        
        txtDOB = new JTextField(patient.getDateOfBirth(), 25);
        txtDOB.setToolTipText("Format: YYYY-MM-DD (e.g., 1990-05-15)");
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtDOB, gbc);
        
        row++;
        
        // NHS Number
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("NHS Number:"), gbc);
        
        txtNhsNumber = new JTextField(patient.getNhsNumber(), 25);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtNhsNumber, gbc);
        
        row++;
        
        // Gender
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Gender:"), gbc);
        
        txtGender = new JTextField(patient.getGender(), 25);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtGender, gbc);
        
        row++;
        
        // Phone Number
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Phone Number:"), gbc);
        
        txtPhone = new JTextField(patient.getPhoneNumber(), 25);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtPhone, gbc);
        
        row++;
        
        // Email
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Email:"), gbc);
        
        txtEmail = new JTextField(patient.getEmail(), 25);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtEmail, gbc);
        
        row++;
        
        // Address
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Address:"), gbc);
        
        txtAddress = new JTextField(patient.getAddress(), 25);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtAddress, gbc);
        
        row++;
        
        // Postcode
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Postcode:"), gbc);
        
        txtPostcode = new JTextField(patient.getPostcode(), 25);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtPostcode, gbc);
        
        row++;
        
        // Emergency Contact Name
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Emergency Contact Name:"), gbc);
        
        txtEmergencyName = new JTextField(patient.getEmergencyContactName(), 25);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtEmergencyName, gbc);
        
        row++;
        
        // Emergency Contact Phone
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Emergency Contact Phone:"), gbc);
        
        txtEmergencyPhone = new JTextField(patient.getEmergencyContactPhone(), 25);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtEmergencyPhone, gbc);
    }
    
    private void setupButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        
        JButton btnSave = new JButton("Save Changes");
        btnSave.setPreferredSize(new Dimension(130, 30));
        btnSave.addActionListener(e -> updatePatient());
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(100, 30));
        btnCancel.addActionListener(e -> dispose());
        
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void updatePatient() {
        if (txtFirstName.getText().trim().isEmpty() ||
            txtLastName.getText().trim().isEmpty() ||
            txtDOB.getText().trim().isEmpty() ||
            txtNhsNumber.getText().trim().isEmpty() ||
            txtPhone.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this,
                "Please fill in all required fields:\n" +
                "First Name, Last Name, DOB, NHS Number, and Phone",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Update the patient object
        patient.setFirstName(txtFirstName.getText().trim());
        patient.setLastName(txtLastName.getText().trim());
        patient.setDateOfBirth(txtDOB.getText().trim());
        patient.setNhsNumber(txtNhsNumber.getText().trim());
        patient.setGender(txtGender.getText().trim());
        patient.setPhoneNumber(txtPhone.getText().trim());
        patient.setEmail(txtEmail.getText().trim());
        patient.setAddress(txtAddress.getText().trim());
        patient.setPostcode(txtPostcode.getText().trim());
        patient.setEmergencyContactName(txtEmergencyName.getText().trim());
        patient.setEmergencyContactPhone(txtEmergencyPhone.getText().trim());
        
        boolean success = controller.updatePatient(patient);
        
        if (success) {
            JOptionPane.showMessageDialog(this,
                "Patient updated successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            parentPanel.refreshTable();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                "Failed to update patient. Please check the console for errors.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
