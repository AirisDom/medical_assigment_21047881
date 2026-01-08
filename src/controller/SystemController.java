package controller;

import java.util.List;
import model.*;

public class SystemController {
    private DataStore dataStore;
    
    public SystemController() {
        this.dataStore = new DataStore();
        dataStore.loadAllData();
        System.out.println("SystemController initialized successfully.");
    }
    
    public List<Patient> getAllPatients() {
        return dataStore.getPatients();
    }
    
    public List<Clinician> getAllClinicians() {
        return dataStore.getClinicians();
    }
    
    public List<Appointment> getAllAppointments() {
        return dataStore.getAppointments();
    }
    
    public List<Prescription> getAllPrescriptions() {
        return dataStore.getPrescriptions();
    }
    
    public List<Referral> getAllReferrals() {
        return dataStore.getReferrals();
    }
    
    public List<Staff> getAllStaff() {
        return dataStore.getStaff();
    }
    
    public List<Facility> getAllFacilities() {
        return dataStore.getFacilities();
    }
    
    /**
     * Registers a new patient.
     * Persists data both in-memory and to CSV file for durability.
     */
    public boolean registerPatient(Patient newPatient) {
        try {
            dataStore.getPatients().add(newPatient);
            
            String csvLine = CsvHandler.createCsvLine(
                newPatient.getPatientId(),
                newPatient.getFirstName(),
                newPatient.getLastName(),
                newPatient.getDateOfBirth(),
                newPatient.getNhsNumber(),
                newPatient.getGender(),
                newPatient.getPhoneNumber(),
                newPatient.getEmail(),
                newPatient.getAddress(),
                newPatient.getPostcode(),
                newPatient.getEmergencyContactName(),
                newPatient.getEmergencyContactPhone(),
                newPatient.getRegistrationDate(),
                newPatient.getGpSurgeryId()
            );
            
            CsvHandler.appendLine("patients.csv", csvLine);
            System.out.println("Patient registered successfully: " + newPatient.getPatientId());
            return true;
            
        } catch (Exception registrationException) {
            System.err.println("Error registering patient: " + registrationException.getMessage());
            return false;
        }
    }
    
    /**
     * Creates a new appointment.
     * Persists data both in-memory and to CSV file for durability.
     */
    public boolean createAppointment(Appointment newAppointment) {
        try {
            dataStore.getAppointments().add(newAppointment);
            
            String csvLine = CsvHandler.createCsvLine(
                newAppointment.getAppointmentId(),
                newAppointment.getPatientId(),
                newAppointment.getClinicianId(),
                newAppointment.getFacilityId(),
                newAppointment.getAppointmentDate(),
                newAppointment.getAppointmentTime(),
                String.valueOf(newAppointment.getDurationMinutes()),
                newAppointment.getAppointmentType(),
                newAppointment.getStatus(),
                newAppointment.getReasonForVisit(),
                newAppointment.getNotes(),
                newAppointment.getCreatedDate(),
                newAppointment.getLastModified()
            );
            
            CsvHandler.appendLine("appointments.csv", csvLine);
            System.out.println("Appointment created successfully: " + newAppointment.getAppointmentId());
            return true;
            
        } catch (Exception appointmentException) {
            System.err.println("Error creating appointment: " + appointmentException.getMessage());
            return false;
        }
    }
    
    /**
     * Creates a new prescription.
     * Persists data both in-memory and to CSV file for durability.
     */
    public boolean createPrescription(Prescription newPrescription) {
        try {
            dataStore.getPrescriptions().add(newPrescription);
            
            String csvLine = CsvHandler.createCsvLine(
                newPrescription.getPrescriptionId(),
                newPrescription.getPatientId(),
                newPrescription.getClinicianId(),
                newPrescription.getAppointmentId(),
                newPrescription.getPrescriptionDate(),
                newPrescription.getMedicationName(),
                newPrescription.getDosage(),
                newPrescription.getFrequency(),
                String.valueOf(newPrescription.getDurationDays()),
                newPrescription.getQuantity(),
                newPrescription.getInstructions(),
                newPrescription.getPharmacyName(),
                newPrescription.getStatus(),
                newPrescription.getIssueDate(),
                newPrescription.getCollectionDate()
            );
            
            CsvHandler.appendLine("prescriptions.csv", csvLine);
            System.out.println("Prescription created successfully: " + newPrescription.getPrescriptionId());
            return true;
            
        } catch (Exception prescriptionException) {
            System.err.println("Error creating prescription: " + prescriptionException.getMessage());
            return false;
        }
    }
    
    /**
     * SINGLETON PATTERN USAGE:
     * Creates a new referral via the Singleton ReferralManager.
     * This demonstrates proper delegation to the Singleton instance.
     */
    public boolean createReferral(Referral newReferral) {
        try {
            ReferralManager.getInstance().addReferral(newReferral);
            
            // Also store in DataStore for consistency with other entity access
            dataStore.getReferrals().add(newReferral);
            
            System.out.println("Referral created via Singleton: " + newReferral.getReferralId());
            return true;
            
        } catch (Exception referralException) {
            System.err.println("Error creating referral: " + referralException.getMessage());
            return false;
        }
    }
    
    /**
     * Registers a new clinician
     * Adds clinician to in-memory list AND appends to clinicians.csv
     * 
     * @param newClinician the clinician to register
     * @return true if registration successful, false otherwise
     */
    public boolean registerClinician(Clinician newClinician) {
        try {
            // Add to in-memory list
            dataStore.getClinicians().add(newClinician);
            
            // Convert clinician to CSV string and append to file
            String csvLine = CsvHandler.createCsvLine(
                newClinician.getClinicianId(),
                newClinician.getFirstName(),
                newClinician.getLastName(),
                newClinician.getTitle(),
                newClinician.getSpeciality(),
                newClinician.getGmcNumber(),
                newClinician.getPhoneNumber(),
                newClinician.getEmail(),
                newClinician.getWorkplaceId(),
                newClinician.getWorkplaceType(),
                newClinician.getEmploymentStatus(),
                newClinician.getStartDate()
            );
            
            CsvHandler.appendLine("clinicians.csv", csvLine);
            System.out.println("Clinician registered successfully: " + newClinician.getClinicianId());
            return true;
            
        } catch (Exception clinicianException) {
            System.err.println("Error registering clinician: " + clinicianException.getMessage());
            return false;
        }
    }
    
    public boolean registerStaff(Staff newStaffMember) {
        try {
            dataStore.getStaff().add(newStaffMember);
            
            String csvLine = CsvHandler.createCsvLine(
                newStaffMember.getStaffId(),
                newStaffMember.getFirstName(),
                newStaffMember.getLastName(),
                newStaffMember.getRole(),
                newStaffMember.getDepartment(),
                newStaffMember.getFacilityId(),
                newStaffMember.getPhoneNumber(),
                newStaffMember.getEmail(),
                newStaffMember.getEmploymentStatus(),
                newStaffMember.getStartDate(),
                newStaffMember.getLineManager(),
                newStaffMember.getAccessLevel()
            );
            
            CsvHandler.appendLine("staff.csv", csvLine);
            System.out.println("Staff registered successfully: " + newStaffMember.getStaffId());
            return true;
            
        } catch (Exception staffException) {
            System.err.println("Error registering staff: " + staffException.getMessage());
            return false;
        }
    }
    
    public boolean registerFacility(Facility newFacility) {
        try {
            dataStore.getFacilities().add(newFacility);
            
            String csvLine = CsvHandler.createCsvLine(
                newFacility.getFacilityId(),
                newFacility.getFacilityName(),
                newFacility.getFacilityType(),
                newFacility.getAddress(),
                newFacility.getPostcode(),
                newFacility.getPhoneNumber(),
                newFacility.getEmail(),
                newFacility.getOpeningHours(),
                newFacility.getManagerName(),
                String.valueOf(newFacility.getCapacity()),
                newFacility.getSpecialitiesOffered()
            );
            
            CsvHandler.appendLine("facilities.csv", csvLine);
            System.out.println("Facility registered successfully: " + newFacility.getFacilityId());
            return true;
            
        } catch (Exception facilityException) {
            System.err.println("Error registering facility: " + facilityException.getMessage());
            return false;
        }
    }
    
    public DataStore getDataStore() {
        return dataStore;
    }
    
    /**
     * Auto-generates sequential IDs to prevent duplicates.
     * Scans existing IDs and returns the next available number.
     */
    public String getNextPatientId() {
        List<Patient> patients = dataStore.getPatients();
        int maxId = 0;
        for (Patient currentPatient : patients) {
            try {
                int idNum = Integer.parseInt(currentPatient.getPatientId().substring(1));
                if (idNum > maxId) maxId = idNum;
            } catch (NumberFormatException numberFormatException) {
                // Skip malformed IDs - they won't affect the max calculation
            }
        }
        return String.format("P%03d", maxId + 1);
    }
    
    /**
     * Generates the next available Clinician ID (e.g., "C006")
     * @return the next clinician ID in format C### (e.g., C006)
     */
    public String getNextClinicianId() {
        List<Clinician> clinicians = dataStore.getClinicians();
        int maxId = 0;
        for (Clinician currentClinician : clinicians) {
            try {
                int idNum = Integer.parseInt(currentClinician.getClinicianId().substring(1));
                if (idNum > maxId) maxId = idNum;
            } catch (NumberFormatException numberFormatException) {
            }
        }
        return String.format("C%03d", maxId + 1);
    }
    
    public String getNextAppointmentId() {
        List<Appointment> appointments = dataStore.getAppointments();
        int maxId = 0;
        for (Appointment currentAppointment : appointments) {
            try {
                int idNum = Integer.parseInt(currentAppointment.getAppointmentId().substring(1));
                if (idNum > maxId) maxId = idNum;
            } catch (NumberFormatException numberFormatException) {
            }
        }
        return String.format("A%03d", maxId + 1);
    }
    
    public String getNextReferralId() {
        List<Referral> referrals = ReferralManager.getInstance().getPendingReferrals();
        int maxId = 0;
        for (Referral currentReferral : referrals) {
            try {
                int idNum = Integer.parseInt(currentReferral.getReferralId().substring(1));
                if (idNum > maxId) maxId = idNum;
            } catch (NumberFormatException numberFormatException) {
            }
        }
        return String.format("R%03d", maxId + 1);
    }
    
    public String getNextPrescriptionId() {
        List<Prescription> prescriptions = dataStore.getPrescriptions();
        int maxId = 0;
        for (Prescription currentPrescription : prescriptions) {
            try {
                int idNum = Integer.parseInt(currentPrescription.getPrescriptionId().substring(2));
                if (idNum > maxId) maxId = idNum;
            } catch (NumberFormatException numberFormatException) {
            }
        }
        return String.format("RX%03d", maxId + 1);
    }
    
    public String getNextStaffId() {
        List<Staff> staffMembers = dataStore.getStaff();
        int maxId = 0;
        for (Staff currentStaffMember : staffMembers) {
            try {
                int idNum = Integer.parseInt(currentStaffMember.getStaffId().substring(1));
                if (idNum > maxId) maxId = idNum;
            } catch (NumberFormatException numberFormatException) {
                // Skip malformed IDs - they won't affect the max calculation
            }
        }
        return String.format("S%03d", maxId + 1);
    }
    
    public String getNextFacilityId() {
        List<Facility> facilities = dataStore.getFacilities();
        int maxId = 0;
        for (Facility currentFacility : facilities) {
            try {
                int idNum = Integer.parseInt(currentFacility.getFacilityId().substring(1));
                if (idNum > maxId) maxId = idNum;
            } catch (NumberFormatException numberFormatException) {

            }
        }
        return String.format("F%03d", maxId + 1);
    }
    
    /**
     * Updates an existing patient.
     * Updates both in-memory list and CSV file.
     */
    public boolean updatePatient(Patient updatedPatient) {
        try {
            // Find and update in-memory list
            List<Patient> patients = dataStore.getPatients();
            boolean found = false;
            
            for (int i = 0; i < patients.size(); i++) {
                if (patients.get(i).getPatientId().equals(updatedPatient.getPatientId())) {
                    patients.set(i, updatedPatient);
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                System.err.println("Patient not found in memory: " + updatedPatient.getPatientId());
                return false;
            }
            
            // Update CSV file
            String csvLine = CsvHandler.createCsvLine(
                updatedPatient.getPatientId(),
                updatedPatient.getFirstName(),
                updatedPatient.getLastName(),
                updatedPatient.getDateOfBirth(),
                updatedPatient.getNhsNumber(),
                updatedPatient.getGender(),
                updatedPatient.getPhoneNumber(),
                updatedPatient.getEmail(),
                updatedPatient.getAddress(),
                updatedPatient.getPostcode(),
                updatedPatient.getEmergencyContactName(),
                updatedPatient.getEmergencyContactPhone(),
                updatedPatient.getRegistrationDate(),
                updatedPatient.getGpSurgeryId()
            );
            
            boolean csvUpdated = CsvHandler.updateLineById("patients.csv", updatedPatient.getPatientId(), csvLine);
            
            if (csvUpdated) {
                System.out.println("Patient updated successfully: " + updatedPatient.getPatientId());
                return true;
            } else {
                System.err.println("Failed to update patient in CSV: " + updatedPatient.getPatientId());
                return false;
            }
            
        } catch (Exception updateException) {
            System.err.println("Error updating patient: " + updateException.getMessage());
            return false;
        }
    }
    
    /**
     * Deletes a patient by ID.
     * Removes from both in-memory list and CSV file.
     */
    public boolean deletePatient(String patientId) {
        try {
            // Remove from in-memory list
            List<Patient> patients = dataStore.getPatients();
            boolean removed = patients.removeIf(p -> p.getPatientId().equals(patientId));
            
            if (!removed) {
                System.err.println("Patient not found in memory: " + patientId);
                return false;
            }
            
            // Delete from CSV file
            boolean csvDeleted = CsvHandler.deleteLineById("patients.csv", patientId);
            
            if (csvDeleted) {
                System.out.println("Patient deleted successfully: " + patientId);
                return true;
            } else {
                System.err.println("Failed to delete patient from CSV: " + patientId);
                // Re-add to memory since CSV deletion failed
                // Note: We've lost the original patient object, so this is a limitation
                return false;
            }
            
        } catch (Exception deleteException) {
            System.err.println("Error deleting patient: " + deleteException.getMessage());
            return false;
        }
    }
    
    /**
     * Gets a patient by ID
     */
    public Patient getPatientById(String patientId) {
        List<Patient> patients = dataStore.getPatients();
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }
}
