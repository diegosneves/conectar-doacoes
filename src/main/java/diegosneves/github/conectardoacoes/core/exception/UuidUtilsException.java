package diegosneves.github.conectardoacoes.core.exception;

import diegosneves.github.conectardoacoes.core.enums.ExceptionDetails;

public class UuidUtilsException extends RuntimeException {

    public static final ExceptionDetails ERROR = ExceptionDetails.INVALID_UUID_FORMAT_MESSAGE;

    public UuidUtilsException(String message) {
        super(ERROR.buildMessage(message));
    }

}
