package org.pms.user;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: tijo.
 */
public enum SystemRolesStatus {
    ACTIVE("Active"), BLOCKED("Blocked"), DEACTIVE("De-Active");

    private final String fieldDescription;

    private SystemRolesStatus(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public String getUIDisplayValue() {
        return this.fieldDescription;
    }

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(super.toString());
    }
}
