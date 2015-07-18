package org.pms.dtos;

import org.pms.enums.SystemRole;
import org.pms.enums.SystemRolesStatus;

/**
 * Created by febin on 26/4/15.
 */
public class UserDto {
    private Integer id;
    private SystemRole systemRole;
    private SystemRolesStatus isActive;
    private String email;
    private Long parishId;
    private Long massCenterId;
    private Long prayerUnitId;
    private Long familyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SystemRole getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(SystemRole systemRole) {
        this.systemRole = systemRole;
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
}
