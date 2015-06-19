package org.pms.exceptions;

import java.io.Serializable;

/**
 * User: Cufa User.
 */
public final class MissingFilterDataException extends PmsException {

    private static final long serialVersionUID = 8757163846085006637L;

    public MissingFilterDataException(String message, String exceptionInfo) {
        super(message, exceptionInfo);
    }

    public MissingFilterDataException(String message, Throwable cause, String exceptionInfo) {
        super(message, cause, exceptionInfo);
    }


}
