package org.pms.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
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

    @Column(name = "diocese_name")
    private String dioceseName;

    @Column(name = "diocese_no")
    private Long dioceseNo;

    @NotEmpty
    @Column(name = "registered_date", nullable = false)
    private String registeredDate;

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
