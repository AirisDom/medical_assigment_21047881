package model;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private List<Patient> patients;
    private List<Clinician> clinicians;
    private List<Appointment> appointments;
    private List<Referral> referrals;
    private List<Prescription> prescriptions;
    private List<Staff> staff;
    private List<Facility> facilities;

    public DataStore() {
        this.patients = new ArrayList<>();
        this.clinicians = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.referrals = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.facilities = new ArrayList<>();
    }

    public void loadAllData() {
        System.out.println("Starting to load all data...");
        
        loadPatients();
        loadClinicians();
        loadAppointments();
        loadReferrals();
        
        ReferralManager.getInstance().loadExistingReferrals(this.referrals);
        
        loadPrescriptions();
        loadStaff();
        loadFacilities();
        
        System.out.println("Data loading complete!");
        System.out.println("Total records loaded:");
        System.out.println("  - Patients: " + patients.size());
        System.out.println("  - Clinicians: " + clinicians.size());
        System.out.println("  - Appointments: " + appointments.size());
        System.out.println("  - Referrals: " + referrals.size());
        System.out.println("  - Prescriptions: " + prescriptions.size());
        System.out.println("  - Staff: " + staff.size());
        System.out.println("  - Facilities: " + facilities.size());
    }

    private void loadPatients() {
        List<String[]> records = CsvHandler.readCsv("patients.csv");
        
        for (int i = 1; i < records.size(); i++) {
            try {
                String[] fields = records.get(i);
                
                Patient patient = new Patient(
                    fields[0],
                    fields[1],
                    fields[2],
                    fields[3],
                    fields[4],
                    fields[5],
                    fields[6],
                    fields[7],
                    fields[8],
                    fields[9],
                    fields[10],
                    fields[11],
                    fields[12],
                    fields[13]
                );
                
                patients.add(patient);
                
            } catch (Exception e) {
                System.err.println("Error parsing patient record at line " + (i + 1) + ": " + e.getMessage());
            }
        }
    }

    private void loadClinicians() {
        List<String[]> records = CsvHandler.readCsv("clinicians.csv");
        
        for (int i = 1; i < records.size(); i++) {
            try {
                String[] fields = records.get(i);
                
                Clinician clinician = new Clinician(
                    fields[0],
                    fields[1],
                    fields[2],
                    fields[3],
                    fields[4],
                    fields[5],
                    fields[6],
                    fields[7],
                    fields[8],
                    fields[9],
                    fields[10],
                    fields[11]
                );
                
                clinicians.add(clinician);
                
            } catch (Exception e) {
                System.err.println("Error parsing clinician record at line " + (i + 1) + ": " + e.getMessage());
            }
        }
    }

    private void loadAppointments() {
        List<String[]> records = CsvHandler.readCsv("appointments.csv");
        
        for (int i = 1; i < records.size(); i++) {
            try {
                String[] fields = records.get(i);
                
                int durationMinutes = Integer.parseInt(fields[6].trim());
                
                Appointment appointment = new Appointment(
                    fields[0],
                    fields[1],
                    fields[2],
                    fields[3],
                    fields[4],
                    fields[5],
                    durationMinutes,
                    fields[7],
                    fields[8],
                    fields[9],
                    fields[10],
                    fields[11],
                    fields[12]
                );
                
                appointments.add(appointment);
                
            } catch (NumberFormatException e) {
                System.err.println("Error parsing duration at line " + (i + 1) + ": " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error parsing appointment record at line " + (i + 1) + ": " + e.getMessage());
            }
        }
    }

    private void loadReferrals() {
        List<String[]> records = CsvHandler.readCsv("referrals.csv");
        
        for (int i = 1; i < records.size(); i++) {
            try {
                String[] fields = records.get(i);
                
                Referral referral = new Referral(
                    fields[0],
                    fields[1],
                    fields[2],
                    fields[3],
                    fields[4],
                    fields[5],
                    fields[6],
                    fields[7],
                    fields[8],
                    fields[9],
                    fields[10],
                    fields[11],
                    fields[12],
                    fields[13],
                    fields[14],
                    fields[15]
                );
                
                referrals.add(referral);
                
            } catch (Exception e) {
                System.err.println("Error parsing referral record at line " + (i + 1) + ": " + e.getMessage());
            }
        }
    }

    private void loadPrescriptions() {
        List<String[]> records = CsvHandler.readCsv("prescriptions.csv");
        
        for (int i = 1; i < records.size(); i++) {
            try {
                String[] fields = records.get(i);
                
                int durationDays = Integer.parseInt(fields[8].trim());
                
                Prescription prescription = new Prescription(
                    fields[0],
                    fields[1],
                    fields[2],
                    fields[3],
                    fields[4],
                    fields[5],
                    fields[6],
                    fields[7],
                    durationDays,
                    fields[9],
                    fields[10],
                    fields[11],
                    fields[12],
                    fields[13],
                    fields[14]
                );
                
                prescriptions.add(prescription);
                
            } catch (NumberFormatException e) {
                System.err.println("Error parsing duration days at line " + (i + 1) + ": " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error parsing prescription record at line " + (i + 1) + ": " + e.getMessage());
            }
        }
    }
    private void loadStaff() {
        List<String[]> records = CsvHandler.readCsv("staff.csv");
        
        for (int i = 1; i < records.size(); i++) {
            try {
                String[] fields = records.get(i);
                
                Staff staffMember = new Staff(
                    fields[0],
                    fields[1],
                    fields[2],
                    fields[3],
                    fields[4],
                    fields[5],
                    fields[6],
                    fields[7],
                    fields[8],
                    fields[9],
                    fields[10],
                    fields[11]
                );
                
                staff.add(staffMember);
                
            } catch (Exception e) {
                System.err.println("Error parsing staff record at line " + (i + 1) + ": " + e.getMessage());
            }
        }
    }

    private void loadFacilities() {
        List<String[]> records = CsvHandler.readCsv("facilities.csv");
        
        for (int i = 1; i < records.size(); i++) {
            try {
                String[] fields = records.get(i);
                
                int capacity = Integer.parseInt(fields[9].trim());
                
                Facility facility = new Facility(
                    fields[0],
                    fields[1],
                    fields[2],
                    fields[3],
                    fields[4],
                    fields[5],
                    fields[6],
                    fields[7],
                    fields[8],
                    capacity,
                    fields[10]
                );
                
                facilities.add(facility);
                
            } catch (NumberFormatException e) {
                System.err.println("Error parsing capacity at line " + (i + 1) + ": " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error parsing facility record at line " + (i + 1) + ": " + e.getMessage());
            }
        }
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Clinician> getClinicians() {
        return clinicians;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Referral> getReferrals() {
        return referrals;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }
}
