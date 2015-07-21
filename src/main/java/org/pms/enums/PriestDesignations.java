package org.pms.enums;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum PriestDesignations {
    SUPPORTING_PRIEST("Supporting Priest"), CO_ORDINATOR("Co-Ordinator"), CHAPLAIN("Chaplain");

    private final String fieldDescription;

    private PriestDesignations(String fieldDescription) {
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
