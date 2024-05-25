package diegosneves.github.conectardoacoes.core.exception;

import diegosneves.github.conectardoacoes.core.enums.ExceptionDetails;

public class UserCreationFailureException extends RuntimeException {

    public static final ExceptionDetails ERROR = ExceptionDetails.USER_NAME_INVALID;

    public UserCreationFailureException(String message) {
        super(ERROR.buildMessage(message));
    }

    public UserCreationFailureException(String message, Throwable cause) {
        super(ERROR.buildMessage(message), cause);
    }

}
