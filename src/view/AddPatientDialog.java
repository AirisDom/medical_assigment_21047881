package view;

import controller.SystemController;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import model.Patient;

public class AddPatientDialog extends JDialog {
    private SystemController controller;
    
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtDOB;
    private JTextField txtNhsNumber;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtAddress;
    private JTextField txtPostcode;
    
    public AddPatientDialog(JFrame parent, SystemController controller) {
        super(parent, "Add New Patient", true);
        this.controller = controller;
        
        setLayout(new GridLayout(9, 2, 10, 10));
        
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        setupFormFields();
        
        setupButtons();
        
        setModal(true);
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
        
        System.out.println("AddPatientDialog initialized.");
    }
    
    private void setupFormFields() {
        add(new JLabel("First Name:"));
        txtFirstName = new JTextField(20);
        add(txtFirstName);
        
        add(new JLabel("Last Name:"));
        txtLastName = new JTextField(20);
        add(txtLastName);
        
        add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        txtDOB = new JTextField(20);
        txtDOB.setToolTipText("Format: YYYY-MM-DD (e.g., 1990-05-15)");
        add(txtDOB);
        
        add(new JLabel("NHS Number:"));
        txtNhsNumber = new JTextField(20);
        add(txtNhsNumber);
        
        add(new JLabel("Phone Number:"));
        txtPhone = new JTextField(20);
        add(txtPhone);
        
        add(new JLabel("Email:"));
        txtEmail = new JTextField(20);
        add(txtEmail);
        
        add(new JLabel("Address:"));
        txtAddress = new JTextField(20);
        add(txtAddress);
        
        add(new JLabel("Postcode:"));
        txtPostcode = new JTextField(20);
        add(txtPostcode);
    }
    
    private void setupButtons() {
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> savePatient());
        add(btnSave);
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> dispose());
        add(btnCancel);
    }
    
    private void savePatient() {
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
        
        try {
            String patientId = controller.getNextPatientId();
            
            String registrationDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
            
            Patient newPatient = new Patient(
                patientId,
                txtFirstName.getText().trim(),
                txtLastName.getText().trim(),
                txtDOB.getText().trim(),
                txtNhsNumber.getText().trim(),
                "Not Specified",
                txtPhone.getText().trim(),
                txtEmail.getText().trim(),
                txtAddress.getText().trim(),
                txtPostcode.getText().trim(),
                "",
                "",
                registrationDate,
                "GP001"
            );
            
            boolean success = controller.registerPatient(newPatient);
            
            if (success) {
                JOptionPane.showMessageDialog(this,
                    "Patient registered successfully!\nPatient ID: " + patientId,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Failed to register patient. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error saving patient: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
