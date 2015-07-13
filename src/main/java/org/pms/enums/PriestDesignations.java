package org.pms.enums;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum PriestDesignations {
    SUPPORTING_PRIEST, CO_ORDINATOR, CHAPLAIN;

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(super.toString().replace(' ', '_'));
    }
}
