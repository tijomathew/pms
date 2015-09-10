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
 * This class describes the various attributes of PrayerUnit.
 * User: tijo
 */

@Entity
@Table(name = "prayerunits", indexes = {@Index(columnList = "id"), @Index(columnList = "prayerunit_no"), @Index(columnList = "registered_date"), @Index(columnList = "masscentre_no")})
public class PrayerUnit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String prayerUnitName;

    @Column(name = "prayerunit_no", nullable = false)
    private Long prayerUnitNo;

    @NotEmpty
    @Column(name = "place", nullable = false)
    private String prayerUnitPlace;

    @Column(name = "patron")
    private String patron;

    @NotEmpty
    @Column(name = "registered_date", nullable = false)
    private String registeredDate;

    //This field is added to resolve the selection issue of mass center if two or more parish has same mass center name. This field has no role in logic of adding a mass center.
    @Transient
    private Parish mappedParish;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "masscentre_no")
    private MassCentre mappedMassCentre;

    @NotNull
    @Valid
    @Embedded
    private LocalAddress localAddress;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "familyPrayerUnit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Family> mappedFamilies = new ArrayList<>();

    public PrayerUnit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrayerUnitName() {
        return prayerUnitName;
    }

    public void setPrayerUnitName(String prayerUnitName) {
        this.prayerUnitName = prayerUnitName;
    }

    public Long getPrayerUnitNo() {
        return prayerUnitNo;
    }

    public void setPrayerUnitNo(Long prayerUnitNo) {
        this.prayerUnitNo = prayerUnitNo;
    }

    public String getPrayerUnitPlace() {
        return prayerUnitPlace;
    }

    public void setPrayerUnitPlace(String prayerUnitPlace) {
        this.prayerUnitPlace = prayerUnitPlace;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public Parish getMappedParish() {
        return mappedParish;
    }

    public void setMappedParish(Parish mappedParish) {
        this.mappedParish = mappedParish;
    }

    public MassCentre getMappedMassCentre() {
        return mappedMassCentre;
    }

    public void setMappedMassCentre(MassCentre mappedMassCentre) {
        this.mappedMassCentre = mappedMassCentre;
    }

    public LocalAddress getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(LocalAddress localAddress) {
        this.localAddress = localAddress;
    }

    public List<Family> getMappedFamilies() {
        return mappedFamilies;
    }

    public void setMappedFamilies(List<Family> mappedFamilies) {
        this.mappedFamilies = mappedFamilies;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public void addFamilyForWard(Family family) {
        if (!this.mappedFamilies.contains(family)) {
            this.mappedFamilies.add(family);
        }
    }

    public Long getParishNumber() {
        return this.getMappedMassCentre().getMappedParish().getParishNo();
    }

    public Long getMassCentreNumber() {
        return this.getMappedMassCentre().getMassCentreNo();
    }

    public String getParishName() {
        return this.getMappedMassCentre().getMappedParish().getParishName();
    }

    public String getMassCentreName() {
        return this.getMappedMassCentre().getMassCentreName();
    }

    public Long getParishId() {
        return this.getMappedMassCentre().getMappedParish().getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrayerUnit that = (PrayerUnit) o;

        if (!prayerUnitName.equals(that.prayerUnitName)) return false;
        if (prayerUnitNo != null ? !prayerUnitNo.equals(that.prayerUnitNo) : that.prayerUnitNo != null) return false;
        if (!prayerUnitPlace.equals(that.prayerUnitPlace)) return false;
        if (!registeredDate.equals(that.registeredDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prayerUnitName.hashCode();
        result = 31 * result + prayerUnitPlace.hashCode();
        result = 31 * result + registeredDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("prayerUnitName", prayerUnitName)
                .append("prayerUnitNo", prayerUnitNo)
                .append("prayerUnitPlace", prayerUnitPlace)
                .append("patron", patron)
                .append("registeredDate", registeredDate)
                .append("mappedParish", mappedParish)
                .append("mappedMassCentre", mappedMassCentre)
                .append("localAddress", localAddress)
                .append("mappedFamilies", mappedFamilies)
                .toString();
    }
}
