package org.pms.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class describes the various attributes of the Parish.
 * It contains various getters and setters of attributes of the parish.
 * It contains various relationships with mass centre and families.
 * It contains various methods for adding mass centre and families.
 * <p/>
 * User: tijo
 */

@Entity
@Table(name = "parishes")
public class Parish implements Serializable {

    private static final long serialVersionUID = 4089680743003228381L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "parish_no")
    private Long parishNo;

    @NotEmpty
    @Column(name = "parish_name")
    private String parishName;

    @NotEmpty
    @Column(name = "place")
    private String place;

    @Column(name = "website")
    private String webSite;

    @Column(name = "facebook_page")
    private String facebookPage;

    @NotEmpty
    @Column(name = "registered_date")
    private String registeredDate;

    @Pattern(regexp = "(^[0-9]{10,15}$)")
    @Column(name = "mobile_no")
    private String mobileNo;

    @Pattern(regexp = "(^[0-9]{10,15}$)")
    @Column(name = "landline_no")
    private String landLineNo;

    @Pattern(regexp = "(^[0-9]{10,15}$)")
    @Column(name = "fax_no")
    private String faxNo;

    @Column(name = "patron")
    private String patron;

    @Valid
    @NotNull
    @Embedded
    private LocalAddress localAddress;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "mappedParish", cascade = CascadeType.ALL)
    private List<MassCentre> massCentreList = new ArrayList<MassCentre>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "familyParish", cascade = CascadeType.ALL)
    private List<Family> mappedFamilies = new ArrayList<Family>();

    public Parish() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParishNo() {
        return parishNo;
    }

    public void setParishNo(Long parishNo) {
        this.parishNo = parishNo;
    }

    public String getParishName() {
        return parishName;
    }

    public void setParishName(String parishName) {
        this.parishName = parishName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getFacebookPage() {
        return facebookPage;
    }

    public void setFacebookPage(String facebookPage) {
        this.facebookPage = facebookPage;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getLandLineNo() {
        return landLineNo;
    }

    public void setLandLineNo(String landLineNo) {
        this.landLineNo = landLineNo;
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

    public List<MassCentre> getMassCentreList() {
        return massCentreList;
    }

    public void setMassCentreList(List<MassCentre> massCentreList) {
        this.massCentreList = massCentreList;
    }

    public List<Family> getMappedFamilies() {
        return mappedFamilies;
    }

    public void setMappedFamilies(List<Family> mappedFamilies) {
        this.mappedFamilies = mappedFamilies;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }


    /**
     * This method is for adding mass centres for parish.
     *
     * @param massCentre The mass centre to be added.
     */
    public void addMassCentresForParish(MassCentre massCentre) {
        if (!this.massCentreList.contains(massCentre)) {
            this.massCentreList.add(massCentre);
        }
    }

    /**
     * This method is for adding family for parish.
     *
     * @param family The family to be added.
     */
    public void addFamilyForParish(Family family) {
        if (!this.mappedFamilies.contains(family)) {
            this.mappedFamilies.add(family);
        }
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("parishNo", parishNo)
                .append("parishName", parishName)
                .append("place", place)
                .append("webSite", webSite)
                .append("facebookPage", facebookPage)
                .append("registeredDate", registeredDate)
                .append("mobileNo", mobileNo)
                .append("landLineNo", landLineNo)
                .append("faxNo", faxNo)
                .append("patron", patron)
                .append("localAddress", localAddress)
                .append("massCentreList", massCentreList)
                .append("mappedFamilies", mappedFamilies)
                .toString();
    }
}
