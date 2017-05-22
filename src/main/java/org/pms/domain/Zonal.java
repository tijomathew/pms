package org.pms.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 25/02/17.
 */
@Entity
@Table(name = "zonalareas", indexes = {@Index(columnList = "id"), @Index(columnList = "zonal_no"), @Index(columnList = "registered_date")})
public class Zonal implements Serializable {

    private static final long serialVersionUID = -4823336204421910407L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "zonal_name")
    private String zonalName;

    @Column(name = "zonal_no")
    private Long zonalNo;

    @NotEmpty
    @Column(name = "registered_date", nullable = false)
    private String registeredDate;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "mappedZonal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parish> mappedParishes = new ArrayList<>();

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diocese_no")
    private Diocese mappedDiocese;

    public Zonal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZonalName() {
        return zonalName;
    }

    public void setZonalName(String zonalName) {
        this.zonalName = zonalName;
    }

    public Long getZonalNo() {
        return zonalNo;
    }

    public void setZonalNo(Long zonalId) {
        this.zonalNo = zonalId;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public List<Parish> getMappedParishes() {
        return mappedParishes;
    }

    public void setMappedParishes(List<Parish> mappedParishes) {
        this.mappedParishes = mappedParishes;
    }

    public Diocese getMappedDiocese() {
        return mappedDiocese;
    }

    public void setMappedDiocese(Diocese mappedDiocese) {
        this.mappedDiocese = mappedDiocese;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zonal zonal = (Zonal) o;

        if (id != null ? !id.equals(zonal.id) : zonal.id != null) return false;
        if (zonalName != null ? !zonalName.equals(zonal.zonalName) : zonal.zonalName != null) return false;
        if (zonalNo != null ? !zonalNo.equals(zonal.zonalNo) : zonal.zonalNo != null) return false;
        if (registeredDate != null ? !registeredDate.equals(zonal.registeredDate) : zonal.registeredDate != null)
            return false;
        if (mappedParishes != null ? !mappedParishes.equals(zonal.mappedParishes) : zonal.mappedParishes != null)
            return false;
        return !(mappedDiocese != null ? !mappedDiocese.equals(zonal.mappedDiocese) : zonal.mappedDiocese != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (zonalName != null ? zonalName.hashCode() : 0);
        result = 31 * result + (zonalNo != null ? zonalNo.hashCode() : 0);
        result = 31 * result + (registeredDate != null ? registeredDate.hashCode() : 0);
        result = 31 * result + (mappedParishes != null ? mappedParishes.hashCode() : 0);
        result = 31 * result + (mappedDiocese != null ? mappedDiocese.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Zonal{");
        sb.append("id=").append(id);
        sb.append(", zonalName='").append(zonalName).append('\'');
        sb.append(", zonalNo=").append(zonalNo);
        sb.append(", registeredDate='").append(registeredDate).append('\'');
        sb.append(", mappedParishes=").append(mappedParishes);
        sb.append(", mappedDiocese=").append(mappedDiocese);
        sb.append('}');
        return sb.toString();
    }
}
