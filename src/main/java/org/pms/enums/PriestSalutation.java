package org.pms.enums;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum PriestSalutation {
    REV, REV_DR;

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(super.toString().replace(' ', '_'));
    }
}
