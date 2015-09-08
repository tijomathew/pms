package org.pms.models;

import org.apache.commons.lang3.StringUtils;
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
@Table(name = "users", indexes = {@Index(columnList = "user_prayerunit"),@Index(columnList = "user_of_family")})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "system_role")
    @Enumerated(EnumType.STRING)
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
    @Enumerated(EnumType.STRING)
    private SystemRolesStatus isActive;

    @Column(name = "email")
    private String email;

    @Column(name = "already_loggedIn")
    private Boolean alreadyLoggedIn = Boolean.FALSE;

    @Column(name = "is_validated")
    private boolean isValidated = Boolean.FALSE;

    @Column(name = "sent_mail")
    private boolean sendMailFlag = Boolean.TRUE;

    @Transient
    private String newPassword;

    @Transient
    private String confirmPassword;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_of_parish")
    private Parish usersOfParishes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_of_masscentre")
    private MassCentre usersOfMassCentres;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_prayerunit")
    private PrayerUnit usersOfPrayerUnits;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_of_family")
    private Family userOfFamily;

    public User() {
    }

    public Long getId() {
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

    public String getSystemRoleAsDisplayValue() {
        return systemRole.getUIDisplayValue();
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

    public Boolean getAlreadyLoggedIn() {
        return alreadyLoggedIn;
    }

    public void setAlreadyLoggedIn(Boolean alreadyLoggedIn) {
        this.alreadyLoggedIn = alreadyLoggedIn;
    }

    public boolean getIsValidated() {
        return isValidated;
    }

    public void setValidated(boolean isValidated) {
        this.isValidated = isValidated;
    }

    public boolean getSendMailFlag() {
        return sendMailFlag;
    }

    public void setSendMailFlag(boolean sendMailFlag) {
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

    public String getParish() {
        String returnedObject = StringUtils.EMPTY;
        if (this.getUsersOfParishes() != null)
            returnedObject = this.getUsersOfParishes().getParishName();
        return returnedObject;
    }

    public String getMassCentre() {
        String returnedObject = StringUtils.EMPTY;
        if (this.getUsersOfMassCentres() != null)
            returnedObject = this.getUsersOfMassCentres().getMassCentreName();
        return returnedObject;
    }

    public String getPrayerUnit() {
        String returnedObject = StringUtils.EMPTY;
        if (this.getUsersOfPrayerUnits() != null)
            returnedObject = this.getUsersOfPrayerUnits().getPrayerUnitName();
        return returnedObject;
    }

    public String getFamily() {
        String returnedObject = StringUtils.EMPTY;
        if (this.getUserOfFamily() != null)
            returnedObject = this.getUserOfFamily().getFamilyName();
        return returnedObject;
    }

    public Long getParishId() {
        Long returnedId = 0l;
        if (this.getUsersOfPrayerUnits() != null)
            returnedId = this.getUsersOfPrayerUnits().getId();
        return returnedId;
    }

    public Long getMassCentreId() {
        Long returnedId = 0l;
        if (this.getUsersOfPrayerUnits() != null)
            returnedId = this.getUsersOfPrayerUnits().getId();
        return returnedId;
    }

    public Long getPrayerUnitId() {
        Long returnedId = 0l;
        if (this.getUsersOfPrayerUnits() != null)
            returnedId = this.getUsersOfPrayerUnits().getId();
        return returnedId;
    }

    public Long getFamilyId() {
        Long returnedId = 0l;
        if (this.getUsersOfPrayerUnits() != null)
            returnedId = this.getUsersOfPrayerUnits().getId();
        return returnedId;
    }

    public Parish getUsersOfParishes() {
        return usersOfParishes;
    }

    public void setUsersOfParishes(Parish usersOfParishes) {
        this.usersOfParishes = usersOfParishes;
    }

    public MassCentre getUsersOfMassCentres() {
        return usersOfMassCentres;
    }

    public void setUsersOfMassCentres(MassCentre usersOfMassCentres) {
        this.usersOfMassCentres = usersOfMassCentres;
    }

    public PrayerUnit getUsersOfPrayerUnits() {
        return usersOfPrayerUnits;
    }

    public void setUsersOfPrayerUnits(PrayerUnit usersOfPrayerUnits) {
        this.usersOfPrayerUnits = usersOfPrayerUnits;
    }

    public Family getUserOfFamily() {
        return userOfFamily;
    }

    public void setUserOfFamily(Family userOfFamily) {
        this.userOfFamily = userOfFamily;
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
