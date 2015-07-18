package org.pms.enums;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum RelationShipInFamily {
    HUSBAND, WIFE, SON, DAUGHTER, FATHER, MOTHER, GRANDFATHER, GRANDMOTHER;

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(super.toString());
    }
}
