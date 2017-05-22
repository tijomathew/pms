package org.pms.family;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum RelationShipInFamily {
    HUSBAND("Husband"), WIFE("Wife"), SON("Son"), DAUGHTER("Daughter"), FATHER("Father"), MOTHER("Mother"), GRANDFATHER("Grand Father"), GRANDMOTHER("Grand Mother");

    private final String fieldDescription;

    private RelationShipInFamily(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public String getUIDisplayValue() {
        return this.fieldDescription;
    }

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(super.toString());
    }
}
