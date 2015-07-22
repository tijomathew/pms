package org.pms.models;

import org.joda.time.DateTime;
import org.pms.enums.SystemRole;
import org.pms.enums.SystemRolesStatus;

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
    @Enumerated(EnumType.ORDINAL)
    private SystemRole systemRole;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_on")
    private Long createdOn = new DateTime().getMillis();

    @Column(name = "updated_on")
    private Long updatedOn = new DateTime().getMillis();

    @Column(name = "is_active")
    private SystemRolesStatus isActive;

    @Column(name = "email")
    private String email;

    @Column(name = "already_loggedIn")
    private Boolean alreadyLoggedIn = Boolean.FALSE;

    @Column(name = "mapped_parish")
    private Long parishId;

    @Column(name = "mapped_masscenter")
    private Long massCenterId;

    @Column(name = "mapped_prayer_unit")
    private Long prayerUnitId;

    @Column(name = "family_id")
    private Long familyId;

    @Column(name = "is_validated")
    private boolean isValidated = Boolean.FALSE;

    @Transient
    private String sendMailFlag;

    @Transient
    private String newPassword;

    @Transient
    private String confirmPassword;

    /*@OneToOne(fetch = FetchType.LAZY, mappedBy = "adminToParish", cascade = CascadeType.ALL)
    private Parish mappedParish;*/


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

    public SystemRole getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(SystemRole systemRole) {
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

    public SystemRolesStatus getIsActive() {
        return isActive;
    }

    public void setIsActive(SystemRolesStatus isActive) {
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

    public Boolean getAlreadyLoggedIn() {
        return alreadyLoggedIn;
    }

    public void setAlreadyLoggedIn(Boolean alreadyLoggedIn) {
        this.alreadyLoggedIn = alreadyLoggedIn;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean isValidated) {
        this.isValidated = isValidated;
    }

    public String getSendMailFlag() {
        return sendMailFlag;
    }

    public void setSendMailFlag(String sendMailFlag) {
        this.sendMailFlag = sendMailFlag;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (isValidated != user.isValidated) return false;
        if (!alreadyLoggedIn.equals(user.alreadyLoggedIn)) return false;
        if (!createdBy.equals(user.createdBy)) return false;
        if (!createdOn.equals(user.createdOn)) return false;
        if (!email.equals(user.email)) return false;
        if (isActive != user.isActive) return false;
        if (!password.equals(user.password)) return false;
        if (systemRole != user.systemRole) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + password.hashCode();
        result = 31 * result + systemRole.hashCode();
        result = 31 * result + createdBy.hashCode();
        result = 31 * result + createdOn.hashCode();
        result = 31 * result + isActive.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + alreadyLoggedIn.hashCode();
        result = 31 * result + (isValidated ? 1 : 0);
        return result;
    }
}
