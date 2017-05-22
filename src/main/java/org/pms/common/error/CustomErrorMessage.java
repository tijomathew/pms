package org.pms.common.error;

/**
 * User: tijo.
 */
public final class CustomErrorMessage {

    private final String fieldName;
    private final String message;

    public CustomErrorMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
