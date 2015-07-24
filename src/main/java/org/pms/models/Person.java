package org.pms.models;

import org.pms.enums.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * This class describes the common attributes of a person.
 * It contains various getters and setters for the attributes of the person.
 * User: tijo
 */

@Embeddable
public class Person implements Serializable {

    private static final long serialVersionUID = 966228303336553974L;

    @Column(name = "salutation")
    @Enumerated(EnumType.ORDINAL)
    private PersonSalutation salutation;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private String dateOfBirth;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "gender")
    @Enumerated(value = EnumType.ORDINAL)
    private Gender gender;

    @Column(name = "photo_location")
    private String photoPathLocation;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "personal_status")
    @Enumerated(EnumType.ORDINAL)
    private PersonalStatus personalStatus;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_no")
    private long mobileNo;

    @Column(name = "land_no")
    private long landLine;

    @Column(name = "fax")
    private String faxNo;

    @Column(name = "education_qualifications")
    private String educationQualifications;

    @Column(name = "job_details")
    private String jobDetails;

    @Column(name = "blood_group")
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "life_status")
    @Enumerated(EnumType.ORDINAL)
    private LifeStatus lifeStatus;

    @Column(name = "personal_remarks")
    private String personalRemarks;

    public Person() {
    }

    public PersonSalutation getSalutation() {
        return salutation;
    }

    public void setSalutation(PersonSalutation salutation) {
        this.salutation = salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhotoPathLocation() {
        return photoPathLocation;
    }

    public void setPhotoPathLocation(String photoPathLocation) {
        this.photoPathLocation = photoPathLocation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public PersonalStatus getPersonalStatus() {
        return personalStatus;
    }

    public void setPersonalStatus(PersonalStatus personalStatus) {
        this.personalStatus = personalStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public long getLandLine() {
        return landLine;
    }

    public void setLandLine(long landLine) {
        this.landLine = landLine;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getEducationQualifications() {
        return educationQualifications;
    }

    public void setEducationQualifications(String educationQualifications) {
        this.educationQualifications = educationQualifications;
    }

    public String getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(String jobDetails) {
        this.jobDetails = jobDetails;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public LifeStatus getLifeStatus() {
        return lifeStatus;
    }

    public void setLifeStatus(LifeStatus lifeStatus) {
        this.lifeStatus = lifeStatus;
    }

    public String getPersonalRemarks() {
        return personalRemarks;
    }

    public void setPersonalRemarks(String personalRemarks) {
        this.personalRemarks = personalRemarks;
    }

    public String getFullName(){
        return new StringBuilder(String.valueOf(this.getSalutation())).append(" ").append(" ").append(this.getMiddleName()).append(" ").append(this.getLastName()).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (landLine != person.landLine) return false;
        if (mobileNo != person.mobileNo) return false;
        if (bloodGroup != null ? !bloodGroup.equals(person.bloodGroup) : person.bloodGroup != null) return false;
        if (carNumber != null ? !carNumber.equals(person.carNumber) : person.carNumber != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(person.dateOfBirth) : person.dateOfBirth != null) return false;
        if (educationQualifications != null ? !educationQualifications.equals(person.educationQualifications) : person.educationQualifications != null)
            return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (faxNo != null ? !faxNo.equals(person.faxNo) : person.faxNo != null) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (gender != null ? !gender.equals(person.gender) : person.gender != null) return false;
        if (jobDetails != null ? !jobDetails.equals(person.jobDetails) : person.jobDetails != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (lifeStatus != null ? !lifeStatus.equals(person.lifeStatus) : person.lifeStatus != null) return false;
        if (middleName != null ? !middleName.equals(person.middleName) : person.middleName != null) return false;
        if (nationality != null ? !nationality.equals(person.nationality) : person.nationality != null) return false;
        if (personalRemarks != null ? !personalRemarks.equals(person.personalRemarks) : person.personalRemarks != null)
            return false;
        if (personalStatus != null ? !personalStatus.equals(person.personalStatus) : person.personalStatus != null)
            return false;
        if (photoPathLocation != null ? !photoPathLocation.equals(person.photoPathLocation) : person.photoPathLocation != null)
            return false;
        if (placeOfBirth != null ? !placeOfBirth.equals(person.placeOfBirth) : person.placeOfBirth != null)
            return false;
        if (salutation != null ? !salutation.equals(person.salutation) : person.salutation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = salutation != null ? salutation.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (placeOfBirth != null ? placeOfBirth.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (photoPathLocation != null ? photoPathLocation.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (personalStatus != null ? personalStatus.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) (mobileNo ^ (mobileNo >>> 32));
        result = 31 * result + (int) (landLine ^ (landLine >>> 32));
        result = 31 * result + (faxNo != null ? faxNo.hashCode() : 0);
        result = 31 * result + (educationQualifications != null ? educationQualifications.hashCode() : 0);
        result = 31 * result + (jobDetails != null ? jobDetails.hashCode() : 0);
        result = 31 * result + (bloodGroup != null ? bloodGroup.hashCode() : 0);
        result = 31 * result + (carNumber != null ? carNumber.hashCode() : 0);
        result = 31 * result + (lifeStatus != null ? lifeStatus.hashCode() : 0);
        result = 31 * result + (personalRemarks != null ? personalRemarks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "salutation='" + salutation + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", photoPathLocation='" + photoPathLocation + '\'' +
                ", nationality='" + nationality + '\'' +
                ", personalStatus='" + personalStatus + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo=" + mobileNo +
                ", landLine=" + landLine +
                ", faxNo='" + faxNo + '\'' +
                ", educationQualifications='" + educationQualifications + '\'' +
                ", jobDetails='" + jobDetails + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", lifeStatus='" + lifeStatus + '\'' +
                ", personalRemarks='" + personalRemarks + '\'' +
                '}';
    }
}
