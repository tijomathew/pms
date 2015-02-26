package org.pms.exceptions;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by tijo on 1/12/14.
 */
public class PmsException extends RuntimeException {

    private static final long serialVersionUID = -1986508802803701859L;

    private String exceptionInfo;
    private String logOwner;
    private Class<?> logOwnerType;

    public PmsException(String message, String exceptionInfo) {
        super(message);
        this.exceptionInfo = exceptionInfo;
    }

    public PmsException(String message, Throwable cause, String exceptionInfo) {
        super(message, cause);
        this.exceptionInfo = exceptionInfo;
    }

    public String getExceptionInfo() {
        if (!StringUtils.isNotBlank(exceptionInfo))
            this.exceptionInfo = "Please setup exception's custom detailed message!!...";
        return exceptionInfo;
    }

    public String getLogOwner() {
        return logOwner;
    }

    public Class<?> getLogOwnerType() {
        return logOwnerType;
    }

    public void setLogOwnerType(Class<?> logOwnerType) {
        if (logOwnerType != null) {
            this.logOwnerType = logOwnerType;
            if (StringUtils.isNotBlank(this.logOwnerType.getName()))
                this.logOwner = this.logOwnerType.getName();
        }
    }
}
