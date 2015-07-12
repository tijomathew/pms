package org.pms.error;

import java.util.List;

/**
 * User: tijo.
 */
public final class CustomResponse {

    private final String statusMessage;
    private final List<CustomErrorMessage> customErrorMessages;

    public CustomResponse(String statusMessage, List<CustomErrorMessage> customErrorMessages) {
        this.statusMessage = statusMessage;
        this.customErrorMessages = customErrorMessages;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public List<CustomErrorMessage> getCustomErrorMessages() {
        return customErrorMessages;
    }
}
