package diegosneves.github.conectardoacoes.core.exception;

import diegosneves.github.conectardoacoes.core.enums.ExceptionDetails;

/**
 * Esta é uma classe de exceção personalizada que estende a {@link RuntimeException}.
 * É usado especialmente para lidar com erros que ocorrem durante a criação de um usuário.
 * <p>
 * A classe contém uma constante de ERROR, que define o detalhe da exceção. Esta constante é do tipo ExceptionDetails e
 * é inicializada com o valor {@link ExceptionDetails#USER_MANIPULATION_ERROR USER_MANIPULATION_ERROR}.
 * <p>
 * Possui dois construtores:
 * <p>
 * 1. Que aceita apenas uma String como argumento, que serve como mensagem para a exceção.
 * <p>
 * 2. Que aceita uma String e um {@link Throwable} como argumentos. A String serve como mensagem para a exceção e o {@link Throwable} é a causa que levou à exceção.
 *
 * <p>
 * Exemplo de uso:
 * <p>
 * <pre>
 *     {@code
 * try {
 *    // código de criação de usuário
 * } catch (AlgumaExcecao e) {
 *    throw new UserServiceFailureException("Detalhe da falha", e);
 * }
 * }
 * </pre>
 * <p>
 * @author diegosneves
 * @version 1.0
 * @see RuntimeException
 */
public class UserServiceFailureException extends RuntimeException {

    public static final ExceptionDetails ERROR = ExceptionDetails.USER_MANIPULATION_ERROR;

    /**
     * Construtor que aceita uma mensagem como argumento e chama o construtor da superclasse com a
     * mensagem de erro construída a partir de ERROR e a mensagem dada.
     *
     * @param message Detalhe adicional específico desta instância de exceção.
     */
    public UserServiceFailureException(String message) {
        super(ERROR.buildMessage(message));
    }

    /**
     * Construtor que aceita uma mensagem e uma causa como argumentos e chama o construtor da superclasse com a
     * mensagem de erro construída a partir de ERROR, a mensagem dada e a causa da exceção.
     *
     * @param message Detalhe adicional específico desta instância de exceção.
     * @param cause A causa raiz que levou a esta exceção.
     */
    public UserServiceFailureException(String message, Throwable cause) {
        super(ERROR.buildMessage(message), cause);
    }

}
