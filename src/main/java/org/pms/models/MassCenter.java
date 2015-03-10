package org.pms.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class describes the various attributes of the mass center in the Ireland.
 * It contains various getters and setters of the attributes of the mass center.
 * It contains various relationships with parish, ward and family.
 * It contains methods for adding ward and family to the mass center.
 * <p/>
 * User: tijo
 */
@Entity
@Table(name = "masscenter_details")
public class MassCenter implements Serializable {

    private static final long serialVersionUID = 1669408565953568157L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auto_id", unique = true, nullable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String centerCode;

    @Column(name = "massCenterID")
    private String massCenterID;

    @Column(name = "patron_name")
    private String patronName;

    @Column(name = "place")
    private String place;

    @Column(name = "facebook_page")
    private String facebookPage;

    @Column(name = "registered_date")
    private String registeredDate;

    @Column(name = "driving_route")
    private String drivingRoute;

    @Column(name = "map")
    private String map;

    @Column(name = "landline_no")
    private long landLineNo;

    @Column(name = "mobile_no")
    private long mobileNo;

    @Column(name = "email")
    private String email;

    @Column(name = "faxno")
    private String faxNo;

    @Transient
    private Long parish;

    @Embedded
    private LocalAddress localAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parish_masscenter_id")
    private Parish mappedParish;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "mappedMassCenter", cascade = CascadeType.ALL)
    private List<PrayerUnit> prayerUnits = new ArrayList<PrayerUnit>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "familyMassCenter", cascade = CascadeType.ALL)
    private List<Family> mappedFamilies = new ArrayList<Family>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "massCenter", cascade = CascadeType.ALL)
    private List<Priest> mappedPriest = new ArrayList<Priest>();

    public MassCenter() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public String getMassCenterID() {
        return massCenterID;
    }

    public void setMassCenterID(String massCenterID) {
        this.massCenterID = massCenterID;
    }

    public String getPatronName() {
        return patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFacebookPage() {
        return facebookPage;
    }

    public void setFacebookPage(String facebookPage) {
        this.facebookPage = facebookPage;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getDrivingRoute() {
        return drivingRoute;
    }

    public void setDrivingRoute(String drivingRoute) {
        this.drivingRoute = drivingRoute;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public long getLandLineNo() {
        return landLineNo;
    }

    public void setLandLineNo(long landLineNo) {
        this.landLineNo = landLineNo;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public Long getParish() {
        return parish;
    }

    public void setParish(Long parish) {
        this.parish = parish;
    }

    public LocalAddress getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(LocalAddress localAddress) {
        this.localAddress = localAddress;
    }

    public Parish getMappedParish() {
        return mappedParish;
    }

    public void setMappedParish(Parish mappedParish) {
        this.mappedParish = mappedParish;
    }

    public List<PrayerUnit> getPrayerUnits() {
        return prayerUnits;
    }

    public void setPrayerUnits(List<PrayerUnit> prayerUnits) {
        this.prayerUnits = prayerUnits;
    }

    public List<Family> getMappedFamilies() {
        return mappedFamilies;
    }

    public void setMappedFamilies(List<Family> mappedFamilies) {
        this.mappedFamilies = mappedFamilies;
    }

    public List<Priest> getMappedPriest() {
        return mappedPriest;
    }

    public void setMappedPriest(List<Priest> mappedPriest) {
        this.mappedPriest = mappedPriest;
    }

    /**
     * This method for adding ward to the mass center.
     *
     * @param ward ward to be added to the mass center.
     */
    public void addWardsForMassCenter(PrayerUnit ward) {
        if (!this.prayerUnits.contains(ward)) {
            this.prayerUnits.add(ward);
        }
    }

    /**
     * This method for adding family to the mass center.
     *
     * @param family family to be added to the mass center.
     */
    public void addFamilyForMassCenter(Family family) {
        if (!this.mappedFamilies.contains(family)) {
            this.mappedFamilies.add(family);
        }
    }

}
