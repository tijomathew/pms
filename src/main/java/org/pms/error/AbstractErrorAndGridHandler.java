package org.pms.error;

import org.pms.enums.StatusCode;
import org.pms.helpers.GridContainer;
import org.pms.helpers.GridGenerator;
import org.pms.helpers.GridRow;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: tijo.
 */
public abstract class AbstractErrorAndGridHandler {

    protected CustomResponse customResponse;
    protected GridGenerator gridGenerator;

    protected final CustomResponse createErrorMessage(StatusCode statusCode, String fieldName, String message) {
        customResponse = new CustomResponse(statusCode, createCustomErrorMessage(fieldName, message));
        return customResponse;
    }

    protected final CustomResponse createValidationErrorMessage(StatusCode statusCode, List<FieldError> filedErrorList) {
        customResponse = new CustomResponse(statusCode, createCustomErrorMessage(filedErrorList));
        return customResponse;
    }

    protected final CustomResponse createSuccessMessage(StatusCode statusCode, String addedObjectDisplayName, String message) {
        customResponse = new CustomResponse(statusCode, createCustomErrorMessage(addedObjectDisplayName, message));
        return customResponse;
    }

    protected final CustomResponse createStatusCodeResponse(StatusCode statusCode) {
        customResponse = new CustomResponse(statusCode, Collections.EMPTY_LIST);
        return customResponse;
    }

    protected final GridContainer createGridContent(Integer total, Integer page, Integer record, List<? extends GridRow> rows) {
        gridGenerator = new GridGenerator();
        return gridGenerator.createGridContainer(total, page, record, rows);
    }

    private List<CustomErrorMessage> createCustomErrorMessage(String fieldName, String message) {
        List<CustomErrorMessage> customErrorMessages = new ArrayList<>();
        customErrorMessages.add(new CustomErrorMessage(fieldName, message));
        return customErrorMessages;
    }

    private List<CustomErrorMessage> createCustomErrorMessage(List<FieldError> fieldErrorList) {
        List<CustomErrorMessage> customErrorMessages = new ArrayList<>();
        if (!fieldErrorList.isEmpty()) {
            for (FieldError fieldError : fieldErrorList) {
                customErrorMessages.add(new CustomErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()));
            }
        }
        return customErrorMessages;
    }
}
