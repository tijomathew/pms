package org.pms.common.error;

import org.pms.user.StatusCode;

import java.util.List;

/**
 * User: tijo.
 */
public final class CustomResponse {

    private final StatusCode statusCode;
    private final List<CustomErrorMessage> customErrorMessages;

    public CustomResponse(StatusCode statusCode, List<CustomErrorMessage> customErrorMessages) {
        this.statusCode = statusCode;
        this.customErrorMessages = customErrorMessages;
    }

}
