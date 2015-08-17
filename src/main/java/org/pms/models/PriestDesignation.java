package org.pms.models;

import org.pms.enums.PriestDesignations;

import javax.persistence.*;

/**
 * User: tijo.
 */
@Entity
@Table(name = "priest_designations")
public class PriestDesignation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "designation")
    private PriestDesignations designation;

    @Column(name = "priest_id")
    private Long priestId;

    @Column(name = "parish_id")
    private Long parishId;

    @Column(name = "masscentre_id")
    private Long massCentreId;

    @Column(name = "prayerunit_id")
    private Long prayerUnitId;

    public PriestDesignation() {
    }

    public Long getId() {
        return id;
    }

    public PriestDesignations getDesignation() {
        return designation;
    }

    public void setDesignation(PriestDesignations designation) {
        this.designation = designation;
    }

    public Long getPriestId() {
        return priestId;
    }

    public void setPriestId(Long priestId) {
        this.priestId = priestId;
    }

    public Long getParishId() {
        return parishId;
    }

    public void setParishId(Long parishId) {
        this.parishId = parishId;
    }

    public Long getMassCentreId() {
        return massCentreId;
    }

    public void setMassCentreId(Long massCentreId) {
        this.massCentreId = massCentreId;
    }

    public Long getPrayerUnitId() {
        return prayerUnitId;
    }

    public void setPrayerUnitId(Long prayerUnitId) {
        this.prayerUnitId = prayerUnitId;
    }
}
