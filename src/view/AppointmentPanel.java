package view;

import controller.SystemController;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Appointment;

public class AppointmentPanel extends JPanel {
    private SystemController controller;
    private JTable appointmentTable;
    private DefaultTableModel tableModel;
    private JButton bookAppointmentButton;

    public AppointmentPanel(SystemController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Patient ID", "Clinician ID", "Date", "Time", "Type", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        appointmentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(appointmentTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bookAppointmentButton = new JButton("Book Appointment");
        bookAppointmentButton.addActionListener(e -> openBookAppointmentDialog());
        buttonPanel.add(bookAppointmentButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadAppointments();
    }

    private void loadAppointments() {
        tableModel.setRowCount(0);

        List<Appointment> appointments = controller.getAllAppointments();
        for (Appointment appointment : appointments) {
            Object[] row = {
                appointment.getAppointmentId(),
                appointment.getPatientId(),
                appointment.getClinicianId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getAppointmentType(),
                appointment.getStatus()
            };
            tableModel.addRow(row);
        }
    }

    private void openBookAppointmentDialog() {
        AddAppointmentDialog dialog = new AddAppointmentDialog(
            (JFrame) SwingUtilities.getWindowAncestor(this),
            controller,
            this
        );
        dialog.setVisible(true);
        loadAppointments();
    }

    public void refreshTable() {
        loadAppointments();
    }
}
