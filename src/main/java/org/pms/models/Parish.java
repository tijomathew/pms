package org.pms.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class describes the various attributes of the Parish.
 * It contains various getters and setters of attributes of the parish.
 * It contains various relationships with priest, mass center and families.
 * It contains various methods for adding priest, mass center and families.
 * <p>
 * User: tijo
 */
@Entity
@Table(name = "parish_details")
public class Parish implements Serializable {

    private static final long serialVersionUID = 4089680743003228381L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auto_id", unique = true, nullable = false)
    private long id;

    @NotEmpty(message = "{NotEmpty.parish.churchName}")
    @Column(name = "church_name")
    private String churchName;

    @Column(name = "rite_name")
    private String riteName;

    @Column(name = "diocese_name")
    private String dioceseName;

    @Column(name = "parish_id")
    private Long parishID;

    @Column(name = "forane_name")
    private String foraneName;

    @Column(name = "name")
    private String name;

    @Column(name = "place")
    private String place;

    @Column(name = "code")
    private String code;

    @Column(name = "website")
    private String webSite;

    @Column(name = "facebook_page")
    private String facebookPage;

    @Column(name = "driving_route")
    private String drivingRoute;

    @Column(name = "map")
    private String map;

    @Column(name = "registered_date")
    private String registeredDate;

    @Column(name = "mobile_no")
    private long mobileNo;

    @Column(name = "email")
    private String email;

    @Column(name = "landline_no")
    private long landLineNo;

    @Column(name = "fax_no")
    private String faxNo;

    @Embedded
    private LocalAddress localAddress;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "parish", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Priest> priestList = new ArrayList<Priest>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "mappedParish", cascade = CascadeType.ALL)
    private List<MassCenter> massCenterList = new ArrayList<MassCenter>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "familyParish", cascade = CascadeType.ALL)
    private List<Family> mappedFamilies = new ArrayList<Family>();

    /*@OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User adminToParish;*/

    public Parish() {
    }

    public long getId() {
        return id;
    }

    public String getChurchName() {
        return churchName;
    }

    public void setChurchName(String churchName) {
        this.churchName = churchName;
    }

    public String getRiteName() {
        return riteName;
    }

    public void setRiteName(String riteName) {
        this.riteName = riteName;
    }

    public String getDioceseName() {
        return dioceseName;
    }

    public void setDioceseName(String dioceseName) {
        this.dioceseName = dioceseName;
    }

    public Long getParishID() {
        return parishID;
    }

    public void setParishID(Long parishID) {
        this.parishID = parishID;
    }

    public String getForaneName() {
        return foraneName;
    }

    public void setForaneName(String foraneName) {
        this.foraneName = foraneName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getDrivingRoute() {
        return drivingRoute;
    }

    public void setDrivingRoute(String drivingRoute) {
        this.drivingRoute = drivingRoute;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getLandLineNo() {
        return landLineNo;
    }

    public void setLandLineNo(long landLineNo) {
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

    public List<Priest> getPriestList() {
        return priestList;
    }

    public void setPriestList(List<Priest> priestList) {
        this.priestList = priestList;
    }

    public List<MassCenter> getMassCenterList() {
        return massCenterList;
    }

    public void setMassCenterList(List<MassCenter> massCenterList) {
        this.massCenterList = massCenterList;
    }

    public List<Family> getMappedFamilies() {
        return mappedFamilies;
    }

    public void setMappedFamilies(List<Family> mappedFamilies) {
        this.mappedFamilies = mappedFamilies;
    }

    /*public User getAdminToParish() {
        return adminToParish;
    }

    public void setAdminToParish(User adminToParish) {
        this.adminToParish = adminToParish;
    }*/

    /**
     * This method is for adding priests for the parish.
     *
     * @param priest The priest to be added.
     */
    public void addPriestsForParish(Priest priest) {
        if (!this.priestList.contains(priest)) {
            this.priestList.add(priest);
        }
    }

    /**
     * This method is for adding mass centers for parish.
     *
     * @param massCenter The mass center to be added.
     */
    public void addMassCentersForParish(MassCenter massCenter) {
        if (!this.massCenterList.contains(massCenter)) {
            this.massCenterList.add(massCenter);
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

}
