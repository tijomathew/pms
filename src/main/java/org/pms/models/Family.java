package org.pms.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class describes the various attributes of the Family.
 * It contains various relationship with parish,mass center, ward and famil members.
 * It contains various getters and setters for the different attributes of the ward.
 * It contains methods for adding members to the family.
 * <p/>
 * User: tijo
 */

@Entity
@Table(name = "family")
public class Family implements Serializable {

    private static final long serialVersionUID = 79358401630884627L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "family_name")
    private String familyName;

    @Column(name = "family_no")
    private Long familyNo;

    @NotEmpty
    @Column(name = "parish_in_native")
    private String parishInNative;

    @NotEmpty
    @Column(name = "diocese_in_native")
    private String dioceseInNative;

    @NotEmpty
    @Column(name = "date_of_registration")
    private String dateOfRegistration;

    @Valid
    @NotNull
    @Embedded
    private LocalAddress localAddress;

    @Valid
    @NotNull
    @Embedded
    private NativeAddress nativeAddress;

    @Valid
    @NotNull
    @Embedded
    private EmergencyContact emergencyContact;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "parish_no")
    private Parish familyParish;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "masscentre_no")
    private MassCentre familyMassCentre;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "prayerunit_no")
    private PrayerUnit familyPrayerUnit;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "familyMember")
    private List<Member> memberList = new ArrayList<Member>();

    public Family() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Long getFamilyNo() {
        return familyNo;
    }

    public void setFamilyNo(Long familyNo) {
        this.familyNo = familyNo;
    }

    public String getParishInNative() {
        return parishInNative;
    }

    public void setParishInNative(String parishInNative) {
        this.parishInNative = parishInNative;
    }

    public String getDioceseInNative() {
        return dioceseInNative;
    }

    public void setDioceseInNative(String dioceseInNative) {
        this.dioceseInNative = dioceseInNative;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
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

    public Parish getFamilyParish() {
        return familyParish;
    }

    public void setFamilyParish(Parish familyParish) {
        this.familyParish = familyParish;
    }

    public MassCentre getFamilyMassCentre() {
        return familyMassCentre;
    }

    public void setFamilyMassCentre(MassCentre familyMassCentre) {
        this.familyMassCentre = familyMassCentre;
    }

    public PrayerUnit getFamilyPrayerUnit() {
        return familyPrayerUnit;
    }

    public void setFamilyPrayerUnit(PrayerUnit familyWard) {
        this.familyPrayerUnit = familyWard;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    /**
     * This method add different members of the family in the list.
     *
     * @param member member to be added to the family.
     */
    public void addMemberForFamily(Member member) {
        if (!this.memberList.contains(member)) {
            this.memberList.add(member);
        }
    }

    public Long getParishNumber() {
        return this.getFamilyParish().getParishNo();
    }

    public String getParishName() {
        return this.getFamilyParish().getParishName();
    }

    public Long getMassCentreNumber() {
        return this.getFamilyMassCentre().getMassCentreNo();
    }

    public Long getPrayerUnitNumber() {
        return this.getFamilyPrayerUnit().getPrayerUnitNo();
    }

    public String getMassCentreName() {
        return this.getFamilyMassCentre().getMassCentreName();
    }

    public String getPrayerUnitName() {
        return this.getFamilyPrayerUnit().getPrayerUnitName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Family family = (Family) o;

        if (!dateOfRegistration.equals(family.dateOfRegistration)) return false;
        if (!dioceseInNative.equals(family.dioceseInNative)) return false;
        if (!familyName.equals(family.familyName)) return false;
        if (familyNo != null ? !familyNo.equals(family.familyNo) : family.familyNo != null) return false;
        if (!parishInNative.equals(family.parishInNative)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = familyName.hashCode();
        result = 31 * result + parishInNative.hashCode();
        result = 31 * result + dioceseInNative.hashCode();
        result = 31 * result + dateOfRegistration.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("familyName", familyName)
                .append("familyNo", familyNo)
                .append("parishInNative", parishInNative)
                .append("dioceseInNative", dioceseInNative)
                .append("dateOfRegistration", dateOfRegistration)
                .append("localAddress", localAddress)
                .append("nativeAddress", nativeAddress)
                .append("emergencyContact", emergencyContact)
                .append("familyParish", familyParish)
                .append("familyMassCentre", familyMassCentre)
                .append("familyPrayerUnit", familyPrayerUnit)
                .append("memberList", memberList)
                .toString();
    }
}
