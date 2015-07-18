package org.pms.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import org.pms.enums.PriestSalutation;
import org.pms.enums.PriestStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class describes the various attributes of the Priest.
 * It contains various getters and setters for the attributes of the priest.
 * It contains relationship with parish.
 * <p>
 * User: tijo
 */

@Entity
@Table(name = "priest_details")
public class Priest implements Serializable {

    private static final long serialVersionUID = -146489604805667795L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "priest_auto_id")
    private Long id;

    @Column(name = "priest_salutation")
    @Enumerated(EnumType.ORDINAL)
    private PriestSalutation priestSalutation;

    @Column(name = "priest_id")
    private Long priestID;

    @Column(name = "date_of_ordination")
    private String dateOfOrdination;

    @Column(name = "feast_day")
    private String feastDay;

    @Column(name = "heavenly_patron")
    private String heavenlyPatron;

    @Column(name = "native_diocese")
    private String nativeDiocese;

    @Column(name = "native_parish")
    private String nativeParish;

    @Column(name = "native_place")
    private String nativePlace;

    @Column(name = "priest_card_validity")
    private String priestCardValidity;

    @Column(name = "ordained_to_diocese")
    private String ordainedToDiocese;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "priest_status")
    @Enumerated(EnumType.ORDINAL)
    private PriestStatus priestStatus;

    @Column(name = "congregation")
    private String congregation;

    @Column(name = "family_name")
    private String familyName;

    @Embedded
    private Person priestAsPerson;

    @Embedded
    private LocalAddress localAddress;

    @Embedded
    private NativeAddress nativeAddress;

    @Embedded
    private EmergencyContact emergencyContact;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parishPriestId")
    private Parish parish;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "massCenterPriestId")
    private MassCenter massCenter;

    @Transient
    private Long parishId;

    @Transient
    private String designation;

    public Priest() {
    }

    public Long getId() {
        return id;
    }

    public Long getPriestID() {
        return priestID;
    }

    public void setPriestID(Long priestID) {
        this.priestID = priestID;
    }

    public PriestSalutation getPriestSalutation() {
        return priestSalutation;
    }

    public void setPriestSalutation(PriestSalutation priestSalutation) {
        this.priestSalutation = priestSalutation;
    }

    public String getDateOfOrdination() {
        return dateOfOrdination;
    }

    public void setDateOfOrdination(String dateOfOrdination) {
        this.dateOfOrdination = dateOfOrdination;
    }

    public String getFeastDay() {
        return feastDay;
    }

    public void setFeastDay(String feastDay) {
        this.feastDay = feastDay;
    }

    public String getHeavenlyPatron() {
        return heavenlyPatron;
    }

    public void setHeavenlyPatron(String heavenlyPatron) {
        this.heavenlyPatron = heavenlyPatron;
    }

    public String getNativeDiocese() {
        return nativeDiocese;
    }

    public void setNativeDiocese(String nativeDiocese) {
        this.nativeDiocese = nativeDiocese;
    }

    public String getNativeParish() {
        return nativeParish;
    }

    public void setNativeParish(String nativeParish) {
        this.nativeParish = nativeParish;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getPriestCardValidity() {
        return priestCardValidity;
    }

    public void setPriestCardValidity(String priestCardValidity) {
        this.priestCardValidity = priestCardValidity;
    }

    public String getOrdainedToDiocese() {
        return ordainedToDiocese;
    }

    public void setOrdainedToDiocese(String ordainedToDiocese) {
        this.ordainedToDiocese = ordainedToDiocese;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public PriestStatus getPriestStatus() {
        return priestStatus;
    }

    public void setPriestStatus(PriestStatus priestStatus) {
        this.priestStatus = priestStatus;
    }

    public String getCongregation() {
        return congregation;
    }

    public void setCongregation(String congregation) {
        this.congregation = congregation;
    }

    public Person getPriestAsPerson() {
        return priestAsPerson;
    }

    public void setPriestAsPerson(Person priestAsPerson) {
        this.priestAsPerson = priestAsPerson;
    }

    public LocalAddress getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(LocalAddress localAddress) {
        this.localAddress = localAddress;
    }

    public NativeAddress getNativeAddress() {
        return nativeAddress;
    }

    public void setNativeAddress(NativeAddress nativeAddress) {
        this.nativeAddress = nativeAddress;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public Parish getParish() {
        return parish;
    }

    public void setParish(Parish parish) {
        this.parish = parish;
    }

    public MassCenter getMassCenter() {
        return massCenter;
    }

    public void setMassCenter(MassCenter massCenter) {
        this.massCenter = massCenter;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Long getParishId() {
        return parishId;
    }

    public void setParishId(Long parishId) {
        this.parishId = parishId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
