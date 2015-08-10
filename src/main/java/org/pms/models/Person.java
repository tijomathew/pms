package org.pms.models;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
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

    @Column(name = "salutation")
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private PersonSalutation salutation;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "dob")
    @NotEmpty
    private String dateOfBirth;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "gender")
    @Enumerated(value = EnumType.ORDINAL)
    @NotNull
    private Gender gender;

    @Column(name = "nationality")
    @NotEmpty
    private String nationality;

    @Column(name = "personal_status")
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private PersonalStatus personalStatus;

    @Email
    @NotEmpty
    @Column(name = "email")
    private String email;

    @Column(name = "mobile_no")
    @NotEmpty
    @Pattern(regexp = "(^[0-9]{10,15}$)")
    private String mobileNo;

    @Column(name = "land_no")
    private String landLine;

    @Column(name = "fax")
    private String faxNo;

    @Column(name = "education_qualifications")
    @NotEmpty
    private String educationQualifications;

    @Column(name = "job_details")
    private String jobDetails;

    @Column(name = "blood_group")
    @Enumerated(EnumType.STRING)
    @NotNull
    private BloodGroup bloodGroup;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "life_status")
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private LifeStatus lifeStatus;

    @Column(name = "personal_remarks")
    private String personalRemarks;

    @Column(name = "image_content")
    @Lob
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


}
