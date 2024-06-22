package diegosneves.github.conectardoacoes.adapters.rest.exception;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;

/**
 * Exceção personalizada que é lançada quando não é definido um construtor padrão.
 *
 * @see RuntimeException
 * @author diegoneves
 * @since 1.0.0
 */
public class ConstructorDefaultUndefinedException extends RuntimeException {

    public static final ExceptionDetails ERROR = ExceptionDetails.CONSTRUCTOR_DEFAULT_UNDEFINED;

    /**
     * Construtor da exceção {@link ConstructorDefaultUndefinedException}.
     *
     * @param message a mensagem de detalhe da exceção.
     */
    public ConstructorDefaultUndefinedException(String message) {
        super(ERROR.formatErrorMessage(message));
    }

    /**
     * Construtor da exceção {@link ConstructorDefaultUndefinedException}.
     *
     * @param message a mensagem de detalhe da exceção.
     * @param e a causa da exceção.
     */
    public ConstructorDefaultUndefinedException(String message, Throwable e) {
        super(ERROR.formatErrorMessage(message), e);
    }

}
