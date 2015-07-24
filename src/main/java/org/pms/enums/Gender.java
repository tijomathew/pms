package org.pms.enums;

/**
 * User: Cufa User.
 */
public enum Gender {
    MALE("Male"), FEMALE("Female");

    private final String fieldDescription;

    private Gender(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public String getUIDisplayValue() {
        return this.fieldDescription;
    }
}
