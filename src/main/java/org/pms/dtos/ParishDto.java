package org.pms.dtos;

/**
 * Created by tijo on 2/12/14.
 */
public class ParishDto {

    private Integer id;
    private Long parishID;
    private String parishName;
    private String riteName;
    private String archDioceseName;
    private String dioceseName;
    private String foraneName;
    private String parishFacebookPage;
    private String parishWebsite;
    private String parishCode;
    private String parishPlace;
    private String parishDrivingRoute;
    private String parishMap;
    private String registeredDate;
    private Long mobileNo;
    private String parishEmail;
    private Long parishLandLineNo;
    private String parishFaxNo;
    private String localAddress;

    public ParishDto(Integer id, Long parishID, String parishName, String riteName, String archDioceseName, String dioceseName, String foraneName, String parishFacebookPage, String parishWebsite, String parishCode, String parishPlace, String parishDrivingRoute, String parishMap, String registeredDate, Long mobileNo, String parishEmail, Long parishLandLineNo, String parishFaxNo) {
        this.id = id;
        this.parishID = parishID;
        this.parishName = parishName;
        this.riteName = riteName;
        this.archDioceseName = archDioceseName;
        this.dioceseName = dioceseName;
        this.foraneName = foraneName;
        this.parishFacebookPage = parishFacebookPage;
        this.parishWebsite = parishWebsite;
        this.parishCode = parishCode;
        this.parishPlace = parishPlace;
        this.registeredDate = registeredDate;
        this.mobileNo = mobileNo;
        this.parishEmail = parishEmail;
        this.parishLandLineNo = parishLandLineNo;
        this.parishFaxNo = parishFaxNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getParishID() {
        return parishID;
    }

    public void setParishID(Long parishID) {
        this.parishID = parishID;
    }

    public String getParishName() {
        return parishName;
    }

    public void setParishName(String parishName) {
        this.parishName = parishName;
    }

    public String getRiteName() {
        return riteName;
    }

    public void setRiteName(String riteName) {
        this.riteName = riteName;
    }

    public String getArchDioceseName() {
        return archDioceseName;
    }

    public void setArchDioceseName(String archDioceseName) {
        this.archDioceseName = archDioceseName;
    }

    public String getDioceseName() {
        return dioceseName;
    }

    public void setDioceseName(String dioceseName) {
        this.dioceseName = dioceseName;
    }

    public String getForaneName() {
        return foraneName;
    }

    public void setForaneName(String foraneName) {
        this.foraneName = foraneName;
    }

    public String getParishFacebookPage() {
        return parishFacebookPage;
    }

    public void setParishFacebookPage(String parishFacebookPage) {
        this.parishFacebookPage = parishFacebookPage;
    }

    public String getParishWebsite() {
        return parishWebsite;
    }

    public void setParishWebsite(String parishWebsite) {
        this.parishWebsite = parishWebsite;
    }

    public String getParishCode() {
        return parishCode;
    }

    public void setParishCode(String parishCode) {
        this.parishCode = parishCode;
    }

    public String getParishPlace() {
        return parishPlace;
    }

    public void setParishPlace(String parishPlace) {
        this.parishPlace = parishPlace;
    }

    public String getParishDrivingRoute() {
        return parishDrivingRoute;
    }

    public void setParishDrivingRoute(String parishDrivingRoute) {
        this.parishDrivingRoute = parishDrivingRoute;
    }

    public String getParishMap() {
        return parishMap;
    }

    public void setParishMap(String parishMap) {
        this.parishMap = parishMap;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getParishEmail() {
        return parishEmail;
    }

    public void setParishEmail(String parishEmail) {
        this.parishEmail = parishEmail;
    }

    public Long getParishLandLineNo() {
        return parishLandLineNo;
    }

    public void setParishLandLineNo(Long parishLandLineNo) {
        this.parishLandLineNo = parishLandLineNo;
    }

    public String  getParishFaxNo() {
        return parishFaxNo;
    }

    public void setParishFaxNo(String  parishFaxNo) {
        this.parishFaxNo = parishFaxNo;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }
}
