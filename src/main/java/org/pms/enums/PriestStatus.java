package org.pms.enums;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum PriestStatus {
    ACTIVE, NOT_ACTIVE, TRANSFERRED;

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(super.toString().replace(' ', '_'));
    }
}
