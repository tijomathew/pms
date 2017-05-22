package org.pms.member;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum BloodGroup {
    O_POSITIVE("O+ ve"), O_NEGATIVE("O- ve"), A_POSITIVE("A+ ve"), A_NEGATIVE("A- ve"), B_POSITIVE("B+ ve"), B_NEGATIVE("B- ve"), AB_POSITIVE("AB+ ve"), AB_NEGATIVE("AB- ve");

    private final String fieldDescription;

    private BloodGroup(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public String getUIDisplayValue() {
        return this.fieldDescription;
    }

    @Override
    public String toString() {
        return super.toString().replace(' ', '_');
    }
}
