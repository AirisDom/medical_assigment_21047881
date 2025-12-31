package model;

/**
 * Clinician POJO class representing a healthcare clinician
 */
public class Clinician {
    private String clinicianId;
    private String firstName;
    private String lastName;
    private String title;
    private String speciality;
    private String gmcNumber;
    private String phoneNumber;
    private String email;
    private String workplaceId;
    private String workplaceType;
    private String employmentStatus;
    private String startDate;

    /**
     * Constructor with all fields
     */
    public Clinician(String clinicianId, String firstName, String lastName, String title,
                     String speciality, String gmcNumber, String phoneNumber, String email,
                     String workplaceId, String workplaceType, String employmentStatus,
                     String startDate) {
        this.clinicianId = clinicianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.speciality = speciality;
        this.gmcNumber = gmcNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workplaceId = workplaceId;
        this.workplaceType = workplaceType;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
    }

    // Getters
    public String getClinicianId() {
        return clinicianId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getGmcNumber() {
        return gmcNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getWorkplaceId() {
        return workplaceId;
    }

    public String getWorkplaceType() {
        return workplaceType;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    // Setters
    public void setClinicianId(String clinicianId) {
        this.clinicianId = clinicianId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setGmcNumber(String gmcNumber) {
        this.gmcNumber = gmcNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWorkplaceId(String workplaceId) {
        this.workplaceId = workplaceId;
    }

    public void setWorkplaceType(String workplaceType) {
        this.workplaceType = workplaceType;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Clinician{" +
                "clinicianId='" + clinicianId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", speciality='" + speciality + '\'' +
                ", gmcNumber='" + gmcNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", workplaceId='" + workplaceId + '\'' +
                ", workplaceType='" + workplaceType + '\'' +
                ", employmentStatus='" + employmentStatus + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
