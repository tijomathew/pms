package org.pms.enums;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum SystemRole {
    ADMIN, PARISH_ADMIN, MASS_CENTER_ADMIN, PRAYER_UNIT_ADMIN, FAMILY_USER, PMS_CURRENT_USER;

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(super.toString().replace(' ', '_'));
    }
}
