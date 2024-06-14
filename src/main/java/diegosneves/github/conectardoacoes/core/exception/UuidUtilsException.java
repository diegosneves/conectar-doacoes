package diegosneves.github.conectardoacoes.core.exception;

import diegosneves.github.conectardoacoes.core.enums.ExceptionDetails;

/**
 * Esta é uma classe de exceção personalizada que estende a classe {@link RuntimeException}.
 * É lançado quando um erro relacionado a {@link java.util.UUID UUIDs} inválidos ocorre na aplicação.
 * <p>
 * A classe inclui um único campo estático, {@code ERROR}, que especifica o tipo de erro.
 *
 * <p>
 * <b>Note:</b> O construtor desta classe constrói a mensagem de exceção através do método {@code buildMessage}
 * disponível no objeto ERROR de tipo {@link ExceptionDetails}.
 * </p>
 *
 * @author diegoneves
 * @see      RuntimeException
 * @version 1.0.0
 */
public class UuidUtilsException extends RuntimeException {

    public static final ExceptionDetails ERROR = ExceptionDetails.INVALID_UUID_FORMAT_MESSAGE;

    /**
     * Construtor da classe. Cria uma instância da exceção {@link UuidUtilsException}.
     *
     * @param message A string que representa a mensagem detalhada da exceção. A mensagem é usada mais tarde para criar uma mensagem de erro mais detalhada por meio do método buildMessage do objeto ERROR.
     */
    public UuidUtilsException(String message) {
        super(ERROR.buildMessage(message));
    }

}
