package org.pms.models;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.pms.enums.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * This class describes the common attributes of a person.
 * It contains various getters and setters for the attributes of the person.
 * User: tijo
 */

@Embeddable
public class Person implements Serializable {

    private static final long serialVersionUID = 966228303336553974L;

    @NotNull
    @Column(name = "salutation", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonSalutation salutation;

    @NotEmpty
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @NotEmpty
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty
    @Column(name = "dob", nullable = false)
    private String dateOfBirth;

    @NotEmpty
    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @NotNull
    @Column(name = "gender", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @NotEmpty
    @Column(name = "nationality", nullable = false)
    private String nationality;

    @NotNull
    @Column(name = "personal_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonalStatus personalStatus;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "land_no")
    private String landLine;

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

    @NotNull
    @Column(name = "life_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LifeStatus lifeStatus;

    @Column(name = "personal_remarks")
    private String personalRemarks;

    @Lob
    @Column(name = "image_content")
    private byte[] imageBytes;

    @Transient
    private MultipartFile file;

    @Transient
    private String imageBytesAsString;


    public Person() {
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        if (!nationality.contentEquals(",")) {
            String splitedArgs[] = new String[2];
            if (!nationality.isEmpty()) {
                splitedArgs = nationality.split(",");
            }
            if (splitedArgs.length > 1 && !splitedArgs[1].isEmpty()) {
                this.nationality = splitedArgs[1];
            } else {
                this.nationality = splitedArgs[0];
            }
        }
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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getLandLine() {
        return landLine;
    }

    public void setLandLine(String landLine) {
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

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public void setImageBytesAsString(String imageBytesAsString) {
        this.imageBytesAsString = imageBytesAsString;
    }

    public String getImageBytesAsString() {
        if (imageBytes != null) {
            this.imageBytesAsString = new String(Base64.encodeBase64(imageBytes));
        }
        return this.imageBytesAsString;
    }

    public String getFullName() {
        return new StringBuilder(String.valueOf(this.getSalutation().getUIDisplayValue())).append(" ").append(this.getFirstName()).append(" ").append(this.getMiddleName()).append(" ").append(this.getLastName()).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!dateOfBirth.equals(person.dateOfBirth)) return false;
        if (!educationQualifications.equals(person.educationQualifications)) return false;
        if (!firstName.equals(person.firstName)) return false;
        if (!lastName.equals(person.lastName)) return false;
        if (lifeStatus != person.lifeStatus) return false;
        if (!nationality.equals(person.nationality)) return false;
        if (personalStatus != person.personalStatus) return false;
        if (!placeOfBirth.equals(person.placeOfBirth)) return false;
        if (salutation != person.salutation) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + placeOfBirth.hashCode();
        result = 31 * result + educationQualifications.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("salutation", salutation)
                .append("firstName", firstName)
                .append("middleName", middleName)
                .append("lastName", lastName)
                .append("dateOfBirth", dateOfBirth)
                .append("placeOfBirth", placeOfBirth)
                .append("gender", gender)
                .append("nationality", nationality)
                .append("personalStatus", personalStatus)
                .append("email", email)
                .append("mobileNo", mobileNo)
                .append("landLine", landLine)
                .append("faxNo", faxNo)
                .append("educationQualifications", educationQualifications)
                .append("jobDetails", jobDetails)
                .append("bloodGroup", bloodGroup)
                .append("carNumber", carNumber)
                .append("lifeStatus", lifeStatus)
                .append("personalRemarks", personalRemarks)
                .append("imageBytes", imageBytes)
                .append("file", file)
                .append("imageBytesAsString", imageBytesAsString)
                .toString();
    }
}
