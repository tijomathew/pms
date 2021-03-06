package org.pms.enums;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum PriestStatus {
    ACTIVE("Active"), NOT_ACTIVE("Not Active"), TRANSFERRED("Transferred");

    private final String fieldDescription;

    private PriestStatus(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public String getUIDisplayValue() {
        return this.fieldDescription;
    }

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(super.toString().replace(' ', '_'));
    }
}
