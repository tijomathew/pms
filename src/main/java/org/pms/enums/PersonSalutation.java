package org.pms.enums;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum PersonSalutation {
    MR, MRS, MISS, MASTER;

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(super.toString());
    }
}
