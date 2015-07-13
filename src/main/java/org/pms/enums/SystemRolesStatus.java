package org.pms.enums;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: tijo.
 */
public enum SystemRolesStatus {
    ACTIVE, BLOCKED, DEACTIVE;

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(super.toString());
    }
}
