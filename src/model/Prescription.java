package model;

public class Prescription {
    private String prescriptionId;
    private String patientId;
    private String clinicianId;
    private String appointmentId;
    private String prescriptionDate;
    private String medicationName;
    private String dosage;
    private String frequency;
    private int durationDays;
    private String quantity;
    private String instructions;
    private String pharmacyName;
    private String status;
    private String issueDate;
    private String collectionDate;

    public Prescription(String prescriptionId, String patientId, String clinicianId,
                        String appointmentId, String prescriptionDate, String medicationName,
                        String dosage, String frequency, int durationDays, String quantity,
                        String instructions, String pharmacyName, String status,
                        String issueDate, String collectionDate) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.appointmentId = appointmentId;
        this.prescriptionDate = prescriptionDate;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.durationDays = durationDays;
        this.quantity = quantity;
        this.instructions = instructions;
        this.pharmacyName = pharmacyName;
        this.status = status;
        this.issueDate = issueDate;
        this.collectionDate = collectionDate;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getClinicianId() {
        return clinicianId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPrescriptionDate() {
        return prescriptionDate;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public String getStatus() {
        return status;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getCollectionDate() {
        return collectionDate;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setClinicianId(String clinicianId) {
        this.clinicianId = clinicianId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setPrescriptionDate(String prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId='" + prescriptionId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", clinicianId='" + clinicianId + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", prescriptionDate='" + prescriptionDate + '\'' +
                ", medicationName='" + medicationName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", frequency='" + frequency + '\'' +
                ", durationDays=" + durationDays +
                ", quantity='" + quantity + '\'' +
                ", instructions='" + instructions + '\'' +
                ", pharmacyName='" + pharmacyName + '\'' +
                ", status='" + status + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", collectionDate='" + collectionDate + '\'' +
                '}';
    }
}
