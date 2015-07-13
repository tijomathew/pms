package org.pms.enums;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum BloodGroup {
    O_POSITIVE, O_NEGATIVE, A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE;

    @Override
    public String toString() {
        return super.toString().replace(' ', '_');
    }
}
