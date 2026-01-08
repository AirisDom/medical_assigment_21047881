package view;

import controller.SystemController;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import model.Clinician;
import model.Facility;

public class AddClinicianDialog extends JDialog {
    private SystemController controller;
    private ClinicianPanel parentPanel;
    
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JComboBox<String> cmbTitle;
    private JTextField txtSpeciality;
    private JTextField txtGmcNumber;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JComboBox<String> cmbWorkplace;
    private JComboBox<String> cmbWorkplaceType;
    private JComboBox<String> cmbEmploymentStatus;
    
    public AddClinicianDialog(JFrame parent, SystemController controller, ClinicianPanel parentPanel) {
        super(parent, "Add New Clinician", true);
        this.controller = controller;
        this.parentPanel = parentPanel;
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        setupFormFields(gbc);
        
        setupButtons(gbc);
        
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
        
        System.out.println("AddClinicianDialog initialized.");
    }
    
    private void setupFormFields(GridBagConstraints gbc) {
        int row = 0;
        
        // Title
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        add(new JLabel("Title:"), gbc);
        
        String[] titles = {"Dr", "Mr", "Mrs", "Ms", "Miss", "Prof"};
        cmbTitle = new JComboBox<>(titles);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        add(cmbTitle, gbc);
        
        row++;
        
        // First Name
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        add(new JLabel("First Name:"), gbc);
        
        txtFirstName = new JTextField(20);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        add(txtFirstName, gbc);
        
        row++;
        
        // Last Name
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Last Name:"), gbc);
        
        txtLastName = new JTextField(20);
        gbc.gridx = 1;
        add(txtLastName, gbc);
        
        row++;
        
        // Speciality
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Speciality:"), gbc);
        
        txtSpeciality = new JTextField(20);
        txtSpeciality.setToolTipText("e.g., Cardiology, General Practice, Orthopedics");
        gbc.gridx = 1;
        add(txtSpeciality, gbc);
        
        row++;
        
        // GMC Number
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("GMC Number:"), gbc);
        
        txtGmcNumber = new JTextField(20);
        txtGmcNumber.setToolTipText("General Medical Council Registration Number");
        gbc.gridx = 1;
        add(txtGmcNumber, gbc);
        
        row++;
        
        // Phone
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Phone Number:"), gbc);
        
        txtPhone = new JTextField(20);
        gbc.gridx = 1;
        add(txtPhone, gbc);
        
        row++;
        
        // Email
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Email:"), gbc);
        
        txtEmail = new JTextField(20);
        gbc.gridx = 1;
        add(txtEmail, gbc);
        
        row++;
        
        // Workplace
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Workplace:"), gbc);
        
        cmbWorkplace = new JComboBox<>();
        loadFacilities();
        gbc.gridx = 1;
        add(cmbWorkplace, gbc);
        
        row++;
        
        // Workplace Type
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Workplace Type:"), gbc);
        
        String[] workplaceTypes = {"Hospital", "Clinic", "GP Surgery", "Health Center"};
        cmbWorkplaceType = new JComboBox<>(workplaceTypes);
        gbc.gridx = 1;
        add(cmbWorkplaceType, gbc);
        
        row++;
        
        // Employment Status
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Employment Status:"), gbc);
        
        String[] statuses = {"Full-Time", "Part-Time", "Locum", "Consultant"};
        cmbEmploymentStatus = new JComboBox<>(statuses);
        gbc.gridx = 1;
        add(cmbEmploymentStatus, gbc);
    }
    
    private void loadFacilities() {
        cmbWorkplace.addItem("-- Select Facility --");
        for (Facility facility : controller.getAllFacilities()) {
            cmbWorkplace.addItem(facility.getFacilityId() + " - " + facility.getFacilityName());
        }
    }
    
    private void setupButtons(GridBagConstraints gbc) {
        int row = 10;
        
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> saveClinician());
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.5;
        add(btnSave, gbc);
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> dispose());
        gbc.gridx = 1;
        add(btnCancel, gbc);
    }
    
    private void saveClinician() {
        // Validation
        if (txtFirstName.getText().trim().isEmpty() ||
            txtLastName.getText().trim().isEmpty() ||
            txtSpeciality.getText().trim().isEmpty() ||
            txtGmcNumber.getText().trim().isEmpty() ||
            txtPhone.getText().trim().isEmpty() ||
            txtEmail.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this,
                "Please fill in all required fields:\n" +
                "First Name, Last Name, Speciality, GMC Number, Phone, and Email",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (cmbWorkplace.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,
                "Please select a workplace facility.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Extract workplace ID from combo box selection
        String workplaceSelection = (String) cmbWorkplace.getSelectedItem();
        String workplaceId = workplaceSelection.split(" - ")[0];
        
        // Generate new Clinician ID
        String clinicianId = controller.getNextClinicianId();
        
        // Get current date for start date
        String startDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        
        // Create new Clinician object
        Clinician newClinician = new Clinician(
            clinicianId,
            txtFirstName.getText().trim(),
            txtLastName.getText().trim(),
            (String) cmbTitle.getSelectedItem(),
            txtSpeciality.getText().trim(),
            txtGmcNumber.getText().trim(),
            txtPhone.getText().trim(),
            txtEmail.getText().trim(),
            workplaceId,
            (String) cmbWorkplaceType.getSelectedItem(),
            (String) cmbEmploymentStatus.getSelectedItem(),
            startDate
        );
        
        // Save via controller
        boolean success = controller.registerClinician(newClinician);
        
        if (success) {
            JOptionPane.showMessageDialog(this,
                "Clinician registered successfully!\nID: " + clinicianId,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            parentPanel.refreshTable();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                "Failed to register clinician. Please check the console for errors.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
