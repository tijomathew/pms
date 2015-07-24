package org.pms.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class describes the various attributes of the Family.
 * It contains various relationship with parish,mass center, ward and famil members.
 * It contains various getters and setters for the different attributes of the ward.
 * It contains methods for adding members to the family.
 * <p>
 * User: tijo
 */

@Entity
@Table(name = "family_details")
public class Family implements Serializable {

    private static final long serialVersionUID = 79358401630884627L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "family_auto_id")
    private long id;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "family_id")
    private Long familyID;

    @NotEmpty
    @Column(name = "parish_in_native")
    private String parishInNative;

    @Column(name = "diocese_in_native")
    private String dioceseInNative;

    @Column(name = "date_of_registration")
    private String dateOfRegistration;

    @Transient
    private Long parishId;

    @Transient
    private Long massCenterId;

    @Transient
    private Long prayerUnitId;

    @Embedded
    private LocalAddress localAddress;

    @Embedded
    private NativeAddress nativeAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_parish_id")
    private Parish familyParish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_masscenter_id")
    private MassCenter familyMassCenter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_prayer_unit_id")
    private PrayerUnit familyPrayerUnit;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "familyMember", cascade = CascadeType.ALL)
    private List<Member> memberList = new ArrayList<Member>();

    public Family() {
    }

    public long getId() {
        return id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Long getFamilyID() {
        return familyID;
    }

    public void setFamilyID(Long familyID) {
        this.familyID = familyID;
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

    public Long getParishId() {
        return parishId;
    }

    public void setParishId(Long parishId) {
        this.parishId = parishId;
    }

    public Long getMassCenterId() {
        return massCenterId;
    }

    public void setMassCenterId(Long massCenterId) {
        this.massCenterId = massCenterId;
    }

    public Long getPrayerUnitId() {
        return prayerUnitId;
    }

    public void setPrayerUnitId(Long prayerUnitId) {
        this.prayerUnitId = prayerUnitId;
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

    public Parish getFamilyParish() {
        return familyParish;
    }

    public void setFamilyParish(Parish familyParish) {
        this.familyParish = familyParish;
    }

    public MassCenter getFamilyMassCenter() {
        return familyMassCenter;
    }

    public void setFamilyMassCenter(MassCenter familyMassCenter) {
        this.familyMassCenter = familyMassCenter;
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
        return this.getFamilyParish().getParishID();
    }

    public Long getMassCenterNumber() {
        return this.getFamilyMassCenter().getMassCenterID();
    }

    public Long getPrayerUnitNumber() {
        return this.getFamilyPrayerUnit().getPrayerUnitCode();
    }

    public String getMassCenterPlace() {
        return this.getFamilyMassCenter().getPlace();
    }

    public String getPrayerUnitPlace() {
        return this.getFamilyPrayerUnit().getPrayerUnitPlace();
    }


}
