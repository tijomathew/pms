package org.pms.dtos;

/**
 * Created by tijo on 2/12/14.
 */
public class MassCenterDto {

    private Integer id;
    private Long massCenterID;
    private String massCenterName;
    private String parishName;
    private String priestNames;
    private String patronName;
    private String place;
    private String facebookPage;
    private String registeredDate;
    private String drivingRoute;
    private String map;
    private Long landLineNo;
    private Long mobileNo;
    private String email;
    private String faxNo;
    private String localAddress;

    public MassCenterDto(Integer id, Long massCenterID, String massCenterName, String patronName, String place, String facebookPage, String registeredDate, String drivingRoute, String map, Long landLineNo, Long mobileNo, String email, String faxNo) {
        this.id = id;
        this.massCenterID = massCenterID;
        this.massCenterName = massCenterName;
        this.patronName = patronName;
        this.place = place;
        this.facebookPage = facebookPage;
        this.registeredDate = registeredDate;
        this.drivingRoute = drivingRoute;
        this.map = map;
        this.landLineNo = landLineNo;
        this.mobileNo = mobileNo;
        this.email = email;
        this.faxNo = faxNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMassCenterID() {
        return massCenterID;
    }

    public void setMassCenterID(Long massCenterID) {
        this.massCenterID = massCenterID;
    }

    public String getMassCenterName() {
        return massCenterName;
    }

    public void setMassCenterName(String massCenterName) {
        this.massCenterName = massCenterName;
    }

    public String getParishName() {
        return parishName;
    }

    public void setParishName(String parishName) {
        this.parishName = parishName;
    }

    public String getPriestNames() {
        return priestNames;
    }

    public void setPriestNames(String priestNames) {
        this.priestNames = priestNames;
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

    public Long getLandLineNo() {
        return landLineNo;
    }

    public void setLandLineNo(Long landLineNo) {
        this.landLineNo = landLineNo;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }
}
