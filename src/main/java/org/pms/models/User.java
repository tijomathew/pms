package org.pms.models;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class describes the user's attributes who has login privileges in the system.
 * When we add family head, we create them as User for adding his family members details in the system other than mass center Admin.
 * It contains various getters and setters for the attributes of the user.
 * User: tijo
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auto_id", unique = true, nullable = false)
    private long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "system_role")
    private String systemRole;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_on")
    private Long createdOn = new DateTime().getMillis();

    @Column(name = "updated_on")
    private Long updatedOn = new DateTime().getMillis();

    @Column(name = "is_active")
    private String isActive;

    @Column(name = "email")
    private String email;

    @Column(name = "already_loggedIn")
    private Boolean isLoggedInForFirstTime = Boolean.FALSE;

    @Column(name = "mapped_parish")
    private Long parishId;

    @Column(name = "mapped_masscenter")
    private Long massCenterId;

    @Column(name = "mapped_prayer_unit")
    private Long prayerUnitId;

    @Column(name = "family_id")
    private Long familyId;


    public User() {
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(String systemRole) {
        this.systemRole = systemRole;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
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

    public Long getParishId() {
        return parishId;
    }

    public void setParishId(Long parishId) {
        this.parishId = parishId;
    }

    public Long getMassCenterId() {
        return massCenterId;
    }

    public void setMassCenterId(Long massCenterId) {
        this.massCenterId = massCenterId;
    }

    public Long getPrayerUnitId() {
        return prayerUnitId;
    }

    public void setPrayerUnitId(Long prayerUnitId) {
        this.prayerUnitId = prayerUnitId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public Boolean getIsLoggedInForFirstTime() {
        return isLoggedInForFirstTime;
    }

    public void setIsLoggedInForFirstTime(Boolean isLoggedInForFirstTime) {
        this.isLoggedInForFirstTime = isLoggedInForFirstTime;
    }
}
