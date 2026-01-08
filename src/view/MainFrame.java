package view;

import controller.SystemController;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private SystemController controller;
    
    public MainFrame(SystemController controller) {
        super("Healthcare Management System");
        this.controller = controller;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        PatientPanel patientsPanel = new PatientPanel(controller);
        AppointmentPanel appointmentsPanel = new AppointmentPanel(controller);
        ReferralsPanel referralsPanel = new ReferralsPanel(controller);
        PrescriptionPanel prescriptionsPanel = new PrescriptionPanel(controller);
        
        tabbedPane.addTab("Patients", patientsPanel);
        tabbedPane.addTab("Appointments", appointmentsPanel);
        tabbedPane.addTab("Referrals", referralsPanel);
        tabbedPane.addTab("Prescriptions", prescriptionsPanel);
        
        add(tabbedPane, BorderLayout.CENTER);
        
        JLabel statusLabel = new JLabel("Logged in as: Admin");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(statusLabel, BorderLayout.SOUTH);
        
        System.out.println("MainFrame initialized successfully.");
    }
    
    private JPanel createPlaceholderPanel(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.GRAY);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
    
    public SystemController getController() {
        return controller;
    }
}
