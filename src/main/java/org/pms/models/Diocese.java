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
 * Created by tijo on 25/02/17.
 */
@Entity
@Table(name = "diocese", indexes = {@Index(columnList = "id"), @Index(columnList = "diocese_no"), @Index(columnList = "registered_date")})
public class Diocese implements Serializable {

    private static final long serialVersionUID = -8110584730705608173L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "diocese_name", nullable = false)
    private String dioceseName;

    @Column(name = "diocese_no", nullable = false)
    private Long dioceseNo;

    @Column(name = "patron_name")
    private String patronName;

    @NotEmpty
    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "landline_no")
    private String landLineNo;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "faxno")
    private String faxNo;

    @NotEmpty
    @Column(name = "registered_date", nullable = false)
    private String registeredDate;

    @Valid
    @NotNull
    @Embedded
    private LocalAddress localAddress;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "mappedDiocese", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Zonal> mappedZonal = new ArrayList<>();

    public Diocese() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDioceseName() {
        return dioceseName;
    }

    public void setDioceseName(String dioceseName) {
        this.dioceseName = dioceseName;
    }

    public Long getDioceseNo() {
        return dioceseNo;
    }

    public void setDioceseNo(Long dioceseNo) {
        this.dioceseNo = dioceseNo;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public List<Zonal> getMappedZonal() {
        return mappedZonal;
    }

    public void setMappedZonal(List<Zonal> mappedZonal) {
        this.mappedZonal = mappedZonal;
    }

    public LocalAddress getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(LocalAddress localAddress) {
        this.localAddress = localAddress;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Diocese diocese = (Diocese) o;

        if (id != null ? !id.equals(diocese.id) : diocese.id != null) return false;
        if (dioceseName != null ? !dioceseName.equals(diocese.dioceseName) : diocese.dioceseName != null) return false;
        if (dioceseNo != null ? !dioceseNo.equals(diocese.dioceseNo) : diocese.dioceseNo != null) return false;
        if (registeredDate != null ? !registeredDate.equals(diocese.registeredDate) : diocese.registeredDate != null)
            return false;
        return !(mappedZonal != null ? !mappedZonal.equals(diocese.mappedZonal) : diocese.mappedZonal != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dioceseName != null ? dioceseName.hashCode() : 0);
        result = 31 * result + (dioceseNo != null ? dioceseNo.hashCode() : 0);
        result = 31 * result + (registeredDate != null ? registeredDate.hashCode() : 0);
        result = 31 * result + (mappedZonal != null ? mappedZonal.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Diocese{");
        sb.append("id=").append(id);
        sb.append(", dioceseName='").append(dioceseName).append('\'');
        sb.append(", dioceseNo=").append(dioceseNo);
        sb.append(", registeredDate='").append(registeredDate).append('\'');
        sb.append(", mappedZonal=").append(mappedZonal);
        sb.append('}');
        return sb.toString();
    }
}
