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

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "associatedParish")
    private List<Receipt> receiptList = new ArrayList<>();

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

    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parish parish = (Parish) o;

        if (id != null ? !id.equals(parish.id) : parish.id != null) return false;
        if (parsihName != null ? !parsihName.equals(parish.parsihName) : parish.parsihName != null) return false;
        if (parishNo != null ? !parishNo.equals(parish.parishNo) : parish.parishNo != null) return false;
        if (patronName != null ? !patronName.equals(parish.patronName) : parish.patronName != null) return false;
        if (place != null ? !place.equals(parish.place) : parish.place != null) return false;
        if (registeredDate != null ? !registeredDate.equals(parish.registeredDate) : parish.registeredDate != null)
            return false;
        if (landLineNo != null ? !landLineNo.equals(parish.landLineNo) : parish.landLineNo != null) return false;
        if (mobileNo != null ? !mobileNo.equals(parish.mobileNo) : parish.mobileNo != null) return false;
        if (faxNo != null ? !faxNo.equals(parish.faxNo) : parish.faxNo != null) return false;
        if (localAddress != null ? !localAddress.equals(parish.localAddress) : parish.localAddress != null)
            return false;
        if (mappedZonal != null ? !mappedZonal.equals(parish.mappedZonal) : parish.mappedZonal != null) return false;
        if (prayerUnits != null ? !prayerUnits.equals(parish.prayerUnits) : parish.prayerUnits != null) return false;
        return !(receiptList != null ? !receiptList.equals(parish.receiptList) : parish.receiptList != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parsihName != null ? parsihName.hashCode() : 0);
        result = 31 * result + (parishNo != null ? parishNo.hashCode() : 0);
        result = 31 * result + (patronName != null ? patronName.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (registeredDate != null ? registeredDate.hashCode() : 0);
        result = 31 * result + (landLineNo != null ? landLineNo.hashCode() : 0);
        result = 31 * result + (mobileNo != null ? mobileNo.hashCode() : 0);
        result = 31 * result + (faxNo != null ? faxNo.hashCode() : 0);
        result = 31 * result + (localAddress != null ? localAddress.hashCode() : 0);
        result = 31 * result + (mappedZonal != null ? mappedZonal.hashCode() : 0);
        result = 31 * result + (prayerUnits != null ? prayerUnits.hashCode() : 0);
        result = 31 * result + (receiptList != null ? receiptList.hashCode() : 0);
        return result;
    }

    /*@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Parish{");
        sb.append("id=").append(id);
        sb.append(", parsihName='").append(parsihName).append('\'');
        sb.append(", parishNo=").append(parishNo);
        sb.append(", patronName='").append(patronName).append('\'');
        sb.append(", place='").append(place).append('\'');
        sb.append(", registeredDate='").append(registeredDate).append('\'');
        sb.append(", landLineNo='").append(landLineNo).append('\'');
        sb.append(", mobileNo='").append(mobileNo).append('\'');
        sb.append(", faxNo='").append(faxNo).append('\'');
        sb.append(", localAddress=").append(localAddress);
        sb.append(", mappedZonal=").append(mappedZonal);
        sb.append(", prayerUnits=").append(prayerUnits);
        sb.append(", receiptList=").append(receiptList);
        sb.append('}');
        return sb.toString();
    }*/
}
