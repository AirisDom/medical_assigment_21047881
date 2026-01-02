package model;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String clinicianId;
    private String facilityId;
    private String appointmentDate;
    private String appointmentTime;
    private int durationMinutes;
    private String appointmentType;
    private String status;
    private String reasonForVisit;
    private String notes;
    private String createdDate;
    private String lastModified;

    public Appointment(String appointmentId, String patientId, String clinicianId,
                       String facilityId, String appointmentDate, String appointmentTime,
                       int durationMinutes, String appointmentType, String status,
                       String reasonForVisit, String notes, String createdDate,
                       String lastModified) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.facilityId = facilityId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.durationMinutes = durationMinutes;
        this.appointmentType = appointmentType;
        this.status = status;
        this.reasonForVisit = reasonForVisit;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getClinicianId() {
        return clinicianId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public String getStatus() {
        return status;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public String getNotes() {
        return notes;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setClinicianId(String clinicianId) {
        this.clinicianId = clinicianId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", clinicianId='" + clinicianId + '\'' +
                ", facilityId='" + facilityId + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", appointmentTime='" + appointmentTime + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", appointmentType='" + appointmentType + '\'' +
                ", status='" + status + '\'' +
                ", reasonForVisit='" + reasonForVisit + '\'' +
                ", notes='" + notes + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", lastModified='" + lastModified + '\'' +
                '}';
    }
}
