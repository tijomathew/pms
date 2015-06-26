package org.pms.constants;

import org.apache.commons.lang3.text.WordUtils;

/**
 * This enum class is representing the different Roles present in the system.
 * User: tijo
 */
public enum Roles {
    ADMIN, PARISH_ADMIN, MASS_CENTER_ADMIN, PRAYER_UNIT_ADMIN, FAMILY_USER;

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(super.toString().replace(' ', '-'));
    }

}
