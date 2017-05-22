package org.pms.user;

/**
 * User: Cufa User.
 */
public enum SystemRole {
    ADMIN("Admin"), PARISH_ADMIN("Parish Admin"), PRAYER_UNIT_ADMIN("Prayer Unit Admin"), FAMILY_USER("Family User"), PMS_CURRENT_USER("pms current user"), FINANCE_USER("Finance_Admin"), DIOCESE_ADMIN("Diocese Admin");

    private final String fieldDescription;

    private SystemRole(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public String getUIDisplayValue() {
        return this.fieldDescription;
    }

}
