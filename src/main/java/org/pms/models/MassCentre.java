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
@Table(name = "masscentres", indexes = {@Index(columnList = "id"), @Index(columnList = "masscentre_no"), @Index(columnList = "registered_date"), @Index(columnList = "parish_no")})
public class MassCentre implements Serializable {

    private static final long serialVersionUID = 1669408565953568157L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "masscentre_name", nullable = false)
    private String massCentreName;

    @Column(name = "masscentre_no", nullable = false, unique = true)
    private Long massCentreNo;

    @Column(name = "patron_name")
    private String patronName;

    @NotEmpty
    @Column(name = "place", nullable = false)
    private String place;

    @NotEmpty
    @Column(name = "registered_date", nullable = false)
    private String registeredDate;

    @Column(name = "landline_no")
    private String landLineNo;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "faxno")
    private String faxNo;

    @Valid
    @NotNull
    @Embedded
    private LocalAddress localAddress;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parish_no")
    private Parish mappedParish;

    @OneToMany(mappedBy = "mappedMassCentre", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<PrayerUnit> prayerUnits = new ArrayList<>();

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

        if (!massCentreName.equals(that.massCentreName)) return false;
        if (massCentreNo != null ? !massCentreNo.equals(that.massCentreNo) : that.massCentreNo != null) return false;
        if (!place.equals(that.place)) return false;
        if (!registeredDate.equals(that.registeredDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = massCentreName.hashCode();
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
                .toString();
    }
}
