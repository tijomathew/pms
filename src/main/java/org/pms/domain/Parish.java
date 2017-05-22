package org.pms.domain;

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
 * This class describes the various attributes of the mass center in the Ireland.
 * It contains various getters and setters of the attributes of the mass center.
 * It contains various relationships with parish, prayerUnit and family.
 * It contains methods for adding prayerUnit and family to the mass center.
 * <p>
 * User: tijo
 */
@Entity
@Table(name = "parish", indexes = {@Index(columnList = "id"), @Index(columnList = "parish_no"), @Index(columnList = "registered_date")})
public class Parish implements Serializable {

    private static final long serialVersionUID = 1669408565953568157L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "parish_name", nullable = false)
    private String parsihName;

    @Column(name = "parish_no", nullable = false)
    private Long parishNo;

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
    @JoinColumn(name = "zonal_no")
    private Zonal mappedZonal;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "mappedParish")
    private List<PrayerUnit> prayerUnits = new ArrayList<>();

    public Parish() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParsihName() {
        return parsihName;
    }

    public void setParsihName(String parishName) {
        this.parsihName = parishName;
    }

    public Long getParishNo() {
        return parishNo;
    }

    public void setParishNo(Long parishNo) {
        this.parishNo = parishNo;
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

    public Zonal getMappedZonal() {
        return mappedZonal;
    }

    public void setMappedZonal(Zonal mappedZonal) {
        this.mappedZonal = mappedZonal;
    }

    public List<PrayerUnit> getPrayerUnits() {
        return prayerUnits;
    }

    public void setPrayerUnits(List<PrayerUnit> prayerUnits) {
        this.prayerUnits = prayerUnits;
    }

    /**
     * This method for adding prayerUnit to the mass center.
     *
     * @param prayerUnit prayerUnit to be added to the mass center.
     */
    public void addPrayerUnitsForParish(PrayerUnit prayerUnit) {
        if (!this.prayerUnits.contains(prayerUnit)) {
            this.prayerUnits.add(prayerUnit);
        }
    }

}