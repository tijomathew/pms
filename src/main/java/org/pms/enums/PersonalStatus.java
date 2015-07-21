package org.pms.enums;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum PersonalStatus {
    SINGLE("Single"), MARRIED("Married"), STUDENT("Student"), DIVORSED("Divorsed"), OTHER("Other");

    private final String fieldDescription;

    private PersonalStatus(String fieldDescription) {
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
