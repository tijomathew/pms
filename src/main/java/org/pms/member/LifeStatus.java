package org.pms.member;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum LifeStatus {
    LIVE("Live"), LATE("Late");

    private final String fieldDescription;

    private LifeStatus(String fieldDescription) {
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
