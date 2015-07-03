package org.pms.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
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
    @Column(name = "auto_id", unique = true, nullable = false)
    private long id;

    @NotEmpty
    @Column(name = "name")
    private String prayerUnitName;

    @Column(name = "code")
    private String prayerUnitCode;

    @Column(name = "place")
    private String prayerUnitPlace;

    @Transient
    private Long massCenterId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prayer_unit_masscenter_id")
    private MassCenter mappedMassCenter;

    @Embedded
    private LocalAddress localAddress;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "familyPrayerUnit", cascade = CascadeType.ALL)
    private List<Family> mappedFamilies = new ArrayList<Family>();

    public PrayerUnit() {
    }

    public long getId() {
        return id;
    }

    public String getPrayerUnitName() {
        return prayerUnitName;
    }

    public void setPrayerUnitName(String prayerUnitName) {
        this.prayerUnitName = prayerUnitName;
    }

    public String getPrayerUnitCode() {
        return prayerUnitCode;
    }

    public void setPrayerUnitCode(String prayerUnitCode) {
        this.prayerUnitCode = prayerUnitCode;
    }

    public String getPrayerUnitPlace() {
        return prayerUnitPlace;
    }

    public void setPrayerUnitPlace(String prayerUnitPlace) {
        this.prayerUnitPlace = prayerUnitPlace;
    }

    public Long getMassCenterId() {
        return massCenterId;
    }

    public void setMassCenterId(Long massCenterId) {
        this.massCenterId = massCenterId;
    }

    public MassCenter getMappedMassCenter() {
        return mappedMassCenter;
    }

    public void setMappedMassCenter(MassCenter mappedMassCenter) {
        this.mappedMassCenter = mappedMassCenter;
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


}
