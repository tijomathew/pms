package org.pms.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@Table(name = "masscentre_details")
public class MassCentre implements Serializable {

    private static final long serialVersionUID = 1669408565953568157L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "masscentre_name")
    private String massCentreName;

    @Column(name = "masscentre_no")
    private Long massCentreNo;

    @Column(name = "patron_name")
    private String patronName;

    @NotEmpty
    @Column(name = "place")
    private String place;

    @NotEmpty
    @Column(name = "registered_date")
    private String registeredDate;

    @Pattern(regexp = "(^[0-9]{10,15}$)")
    @Column(name = "landline_no")
    private String landLineNo;

    @Pattern(regexp = "(^[0-9]{10,15}$)")
    @Column(name = "mobile_no")
    private String mobileNo;

    @Pattern(regexp = "(^[0-9]{10,15}$)")
    @Column(name = "faxno")
    private String faxNo;

    @Valid
    @NotNull
    @Embedded
    private LocalAddress localAddress;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parish_no")
    private Parish mappedParish;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "mappedMassCentre", cascade = CascadeType.ALL)
    private List<PrayerUnit> prayerUnits = new ArrayList<PrayerUnit>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "familyMassCentre", cascade = CascadeType.ALL)
    private List<Family> mappedFamilies = new ArrayList<Family>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "massCentre", cascade = CascadeType.ALL)
    private List<Priest> mappedPriest = new ArrayList<Priest>();

    public MassCentre() {
    }

    public Long getId() {
        return id;
    }

    public String getMassCentreName() {
        return massCentreName;
    }

    public void setMassCentreName(String massCentreName) {
        this.massCentreName = massCentreName;
    }

    public Long getMassCentreNo() {
        return massCentreNo;
    }

    public void setMassCentreNo(Long massCentreNo) {
        this.massCentreNo = massCentreNo;
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

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getLandLineNo() {
        return landLineNo;
    }

    public void setLandLineNo(String landLineNo) {
        this.landLineNo = landLineNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
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
    public void addPrayerUnitsForMassCentre(PrayerUnit ward) {
        if (!this.prayerUnits.contains(ward)) {
            this.prayerUnits.add(ward);
        }
    }

    /**
     * This method for adding family to the mass center.
     *
     * @param family family to be added to the mass center.
     */
    public void addFamilyForMassCentre(Family family) {
        if (!this.mappedFamilies.contains(family)) {
            this.mappedFamilies.add(family);
        }
    }

    public Long getParishNumber() {
        return this.getMappedParish().getParishNo();
    }

    public String getParishName() {
        return this.getMappedParish().getParishName();
    }

}
