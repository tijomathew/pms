package org.pms.enums;

/**
 * User: Cufa User.
 */
public enum SystemRole {
    ADMIN("Admin"), MASS_CENTER_ADMIN("Mass Center Admin"), PRAYER_UNIT_ADMIN("Prayer Unit Admin"), FAMILY_USER("Family User"), PMS_CURRENT_USER("pms current user");

    private final String fieldDescription;

    private SystemRole(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public String getUIDisplayValue() {
        return this.fieldDescription;
    }

}
