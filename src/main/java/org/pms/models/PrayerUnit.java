package org.pms.models;

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
@Table(name = "prayer_unit_details")
public class PrayerUnit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String prayerUnitName;

    @Column(name = "prayerunit_no")
    private Long prayerUnitNo;

    @NotEmpty
    @Column(name = "place")
    private String prayerUnitPlace;

    @Column(name = "patron")
    private String patron;

    //This field is added to resolve the selection issue of mass center if two or more parish has same mass center name. This field has no role in logic of adding a mass center.
    @Transient
    private Parish mappedParish;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "masscentre_no")
    private MassCentre mappedMassCentre;

    @NotNull
    @Valid
    @Embedded
    private LocalAddress localAddress;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "familyPrayerUnit", cascade = CascadeType.ALL)
    private List<Family> mappedFamilies = new ArrayList<Family>();

    public PrayerUnit() {
    }

    public Long getId() {
        return id;
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


}
