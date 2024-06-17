package diegosneves.github.conectardoacoes.core.exception;

import diegosneves.github.conectardoacoes.core.enums.ExceptionDetails;

/**
 * Esta é uma classe de exceção personalizada que estende a {@link RuntimeException}.
 * É usado especialmente para lidar com erros que ocorrem durante a criação de um Endereço.
 * <p>
 * A classe contém uma constante de ERROR, que define o detalhe da exceção. Esta constante é do tipo {@link ExceptionDetails} e
 * é inicializada com o valor {@link ExceptionDetails#ADDRESS_CREATION_ERROR}.
 * <p>
 * Exemplo de uso:
 * <p>
 * <pre>
 *     {@code
 * try {
 *    // código de criação de um Endereço.
 * } catch (AlgumaExcecao e) {
 *    throw new AdressCreationFailureException("Detalhe da falha");
 * }
 * }
 * </pre>
 * <p>
 * @author diegosneves
 * @see RuntimeException
 * @since 1.0.0
 */
public class AddressCreationFailureException extends RuntimeException {

    public static final ExceptionDetails ERROR = ExceptionDetails.ADDRESS_CREATION_ERROR;

    /**
     * Construtor que aceita uma mensagem como argumento e chama o construtor da superclasse com a
     * mensagem de erro construída a partir de {@code ERROR} e a mensagem dada.
     *
     * @param message Detalhe adicional específico desta instância de exceção.
     */
    public AddressCreationFailureException(String message) {
        super(ERROR.buildMessage(message));
    }

}
