package org.pms.enums;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum PersonalStatus {
    SINGLE, MARRIED, STUDENT, DIVORSED, OTHER;

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(super.toString());
    }
}
