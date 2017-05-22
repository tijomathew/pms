package org.pms.member;

import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum PersonSalutation {
    MR("Mr."), MRS("Mrs."), MISS("Miss."), MASTER("Master."),REV("Rev."),REV_DR("Rev. Dr.");

    private final String fieldDescription;

    private PersonSalutation(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public String getUIDisplayValue() {
        return this.fieldDescription;
    }

}
