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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "priest_designation_id")
    private Priest priest;

    @Column(name = "mapped_organization")
    private Long mappedOrganizationId;

    public PriestDesignation() {
    }

    public PriestDesignation(String designation, Priest priest, Long mappedOrganizationId) {
        this.designation = designation;
        this.priest = priest;
        this.mappedOrganizationId = mappedOrganizationId;
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

    public Priest getPriest() {
        return priest;
    }

    public void setPriest(Priest priest) {
        this.priest = priest;
    }

    public Long getMappedOrganizationId() {
        return mappedOrganizationId;
    }

    public void setMappedOrganizationId(Long mappedOrganizationId) {
        this.mappedOrganizationId = mappedOrganizationId;
    }
}
