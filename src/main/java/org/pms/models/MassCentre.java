package org.pms.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
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
    @ManyToOne
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

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MassCentre that = (MassCentre) o;

        if (faxNo != null ? !faxNo.equals(that.faxNo) : that.faxNo != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (landLineNo != null ? !landLineNo.equals(that.landLineNo) : that.landLineNo != null) return false;
        if (localAddress != null ? !localAddress.equals(that.localAddress) : that.localAddress != null) return false;
        if (mappedFamilies != null ? !mappedFamilies.equals(that.mappedFamilies) : that.mappedFamilies != null)
            return false;
        if (mappedParish != null ? !mappedParish.equals(that.mappedParish) : that.mappedParish != null) return false;
        if (mappedPriest != null ? !mappedPriest.equals(that.mappedPriest) : that.mappedPriest != null) return false;
        if (!massCentreName.equals(that.massCentreName)) return false;
        if (massCentreNo != null ? !massCentreNo.equals(that.massCentreNo) : that.massCentreNo != null) return false;
        if (mobileNo != null ? !mobileNo.equals(that.mobileNo) : that.mobileNo != null) return false;
        if (patronName != null ? !patronName.equals(that.patronName) : that.patronName != null) return false;
        if (!place.equals(that.place)) return false;
        if (prayerUnits != null ? !prayerUnits.equals(that.prayerUnits) : that.prayerUnits != null) return false;
        if (!registeredDate.equals(that.registeredDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = massCentreName.hashCode();
        result = 31 * result + (patronName != null ? patronName.hashCode() : 0);
        result = 31 * result + place.hashCode();
        result = 31 * result + registeredDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("massCentreName", massCentreName)
                .append("massCentreNo", massCentreNo)
                .append("patronName", patronName)
                .append("place", place)
                .append("registeredDate", registeredDate)
                .append("landLineNo", landLineNo)
                .append("mobileNo", mobileNo)
                .append("faxNo", faxNo)
                .append("localAddress", localAddress)
                .append("mappedParish", mappedParish)
                .append("prayerUnits", prayerUnits)
                .append("mappedFamilies", mappedFamilies)
                .append("mappedPriest", mappedPriest)
                .toString();
    }
}
