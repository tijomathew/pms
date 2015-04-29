package org.pms.dtos;

/**
 * Created by febin on 26/4/15.
 */
public class UserDto {
    private Integer id;
    private String userName;
    private String systemRole;
    private String isActive;
    private String email;
    private Long phoneNo;
    private String parishId;
    private String massCenterId;
    private String prayerUnitId;
    private String familyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(String systemRole) {
        this.systemRole = systemRole;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getParishId() {
        return parishId;
    }

    public void setParishId(String parishId) {
        this.parishId = parishId;
    }

    public String getMassCenterId() {
        return massCenterId;
    }

    public void setMassCenterId(String massCenterId) {
        this.massCenterId = massCenterId;
    }

    public String getPrayerUnitId() {
        return prayerUnitId;
    }

    public void setPrayerUnitId(String prayerUnitId) {
        this.prayerUnitId = prayerUnitId;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }
}
