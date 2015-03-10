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
    private String priestId;

    @Column(name = "parish_id")
    private String parishId;

    @Column(name = "masscenter_id")
    private String massCenterId;

    @Column(name = "prayerunit_id")
    private String prayerUnitId;

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

    public String getPriestId() {
        return priestId;
    }

    public void setPriestId(String priestId) {
        this.priestId = priestId;
    }

    public String getParishId() {
        return parishId;
    }

    public void setParishId(String parishId) {
        this.parishId = parishId;
    }

    public String getMassCenterId() {
        return massCenterId;
    }

    public void setMassCenterId(String massCenterId) {
        this.massCenterId = massCenterId;
    }

    public String getPrayerUnitId() {
        return prayerUnitId;
    }

    public void setPrayerUnitId(String prayerUnitId) {
        this.prayerUnitId = prayerUnitId;
    }
}
