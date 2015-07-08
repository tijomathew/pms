package org.pms.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: tijo.
 */
@Entity
@Table(name = "priest_designations")
public class PriestDesignation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "designation_id")
    private Long id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "priest_id")
    private Long priestId;

    @Column(name = "parish_id")
    private Long parishId;

    @Column(name = "masscenter_id")
    private Long massCenterId;

    @Column(name = "prayerunit_id")
    private Long prayerUnitId;

    public PriestDesignation() {
    }

    public Long getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
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
}
