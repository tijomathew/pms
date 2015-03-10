package org.pms.dtos;

/**
 * Created by tijo on 2/12/14.
 */
public class PriestDto {

    private Integer id;
    private Long priestID;
    private String name;
    private String designation;
    private String heavenlyPatron;
    private String nativeDiocese;
    private String nativeParish;
    private String nativePlace;
    private String priestCardValidity;
    private String ordainedToDiocese;
    private String fatherName;
    private String motherName;
    private String priestStatus;
    private String congregation;
    private String irelandAddress;
    private String indiaAddress;
    private String emergencyContact;


    public PriestDto(Integer id, Long priestID, String name, String designation, String heavenlyPatron, String nativeDiocese, String nativeParish, String nativePlace, String priestCardValidity, String ordainedToDiocese, String fatherName, String motherName, String priestStatus, String congregation) {
        this.id = id;
        this.priestID = priestID;
        this.name = name;
        this.designation = designation;
        this.heavenlyPatron = heavenlyPatron;
        this.nativeDiocese = nativeDiocese;
        this.nativeParish = nativeParish;
        this.nativePlace = nativePlace;
        this.priestCardValidity = priestCardValidity;
        this.ordainedToDiocese = ordainedToDiocese;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.priestStatus = priestStatus;
        this.congregation = congregation;
    }

    public PriestDto() {

    }

    public Integer getId() {
        return id;
    }

    public Long getPriestID() {
        return priestID;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getHeavenlyPatron() {
        return heavenlyPatron;
    }

    public String getNativeDiocese() {
        return nativeDiocese;
    }

    public String getNativeParish() {
        return nativeParish;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public String getPriestCardValidity() {
        return priestCardValidity;
    }

    public String getOrdainedToDiocese() {
        return ordainedToDiocese;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getPriestStatus() {
        return priestStatus;
    }

    public String getCongregation() {
        return congregation;
    }

    public void setPriestID(Long priestID) {
        this.priestID = priestID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setHeavenlyPatron(String heavenlyPatron) {
        this.heavenlyPatron = heavenlyPatron;
    }

    public void setNativeDiocese(String nativeDiocese) {
        this.nativeDiocese = nativeDiocese;
    }

    public void setNativeParish(String nativeParish) {
        this.nativeParish = nativeParish;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public void setPriestCardValidity(String priestCardValidity) {
        this.priestCardValidity = priestCardValidity;
    }

    public void setOrdainedToDiocese(String ordainedToDiocese) {
        this.ordainedToDiocese = ordainedToDiocese;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setPriestStatus(String priestStatus) {
        this.priestStatus = priestStatus;
    }

    public void setCongregation(String congregation) {
        this.congregation = congregation;
    }

    public String getIrelandAddress() {
        return irelandAddress;
    }

    public void setIrelandAddress(String irelandAddress) {
        this.irelandAddress = irelandAddress;
    }

    public String getIndiaAddress() {
        return indiaAddress;
    }

    public void setIndiaAddress(String indiaAddress) {
        this.indiaAddress = indiaAddress;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
}
