package diegosneves.github.conectardoacoes.adapters.rest.exception;

/**
 * Exceção personalizada que é lançada quando não é definido um construtor padrão.
 *
 * @see RuntimeException
 * @author diegoneves
 * @since 1.0.0
 */
public class ConstructorDefaultUndefinedException extends CustomException {

    /**
     * Construtor da exceção {@link ConstructorDefaultUndefinedException}.
     *
     * @param message a mensagem de detalhe da exceção.
     */
    public ConstructorDefaultUndefinedException(Integer term, String message) {
        super(message, obtainExceptionDetails(term));
    }

    /**
     * Construtor da exceção {@link ConstructorDefaultUndefinedException}.
     *
     * @param message a mensagem de detalhe da exceção.
     * @param e a causa da exceção.
     */
    public ConstructorDefaultUndefinedException(Integer term, String message, Throwable e) {
        super(message, e, obtainExceptionDetails(term));
    }

}
