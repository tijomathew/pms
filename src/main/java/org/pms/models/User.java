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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "adminToParish", cascade = CascadeType.ALL)
    private Parish mappedParish;


    public User() {
    }

    public long getId() {
        return id;
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

    public Parish getMappedParish() {
        return mappedParish;
    }

    public void setMappedParish(Parish mappedParish) {
        this.mappedParish = mappedParish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (createdBy != null ? !createdBy.equals(user.createdBy) : user.createdBy != null) return false;
        if (createdOn != null ? !createdOn.equals(user.createdOn) : user.createdOn != null) return false;
        if (!email.equals(user.email)) return false;
        if (familyId != null ? !familyId.equals(user.familyId) : user.familyId != null) return false;
        if (!isActive.equals(user.isActive)) return false;
        if (isLoggedInForFirstTime != null ? !isLoggedInForFirstTime.equals(user.isLoggedInForFirstTime) : user.isLoggedInForFirstTime != null)
            return false;
        if (massCenterId != null ? !massCenterId.equals(user.massCenterId) : user.massCenterId != null) return false;
        if (parishId != null ? !parishId.equals(user.parishId) : user.parishId != null) return false;
        if (!password.equals(user.password)) return false;
        if (prayerUnitId != null ? !prayerUnitId.equals(user.prayerUnitId) : user.prayerUnitId != null) return false;
        if (!systemRole.equals(user.systemRole)) return false;
        if (updatedBy != null ? !updatedBy.equals(user.updatedBy) : user.updatedBy != null) return false;
        if (updatedOn != null ? !updatedOn.equals(user.updatedOn) : user.updatedOn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + password.hashCode();
        result = 31 * result + systemRole.hashCode();
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (updatedBy != null ? updatedBy.hashCode() : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        result = 31 * result + (updatedOn != null ? updatedOn.hashCode() : 0);
        result = 31 * result + isActive.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (isLoggedInForFirstTime != null ? isLoggedInForFirstTime.hashCode() : 0);
        result = 31 * result + (parishId != null ? parishId.hashCode() : 0);
        result = 31 * result + (massCenterId != null ? massCenterId.hashCode() : 0);
        result = 31 * result + (prayerUnitId != null ? prayerUnitId.hashCode() : 0);
        result = 31 * result + (familyId != null ? familyId.hashCode() : 0);
        return result;
    }


}
