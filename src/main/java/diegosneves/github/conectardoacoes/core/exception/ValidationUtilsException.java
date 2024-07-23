package diegosneves.github.conectardoacoes.core.exception;

import diegosneves.github.conectardoacoes.core.enums.ExceptionDetails;

/**
 * Esta é uma classe de exceção personalizada que estende a classe {@link RuntimeException}.
 * É lançado quando um erro relacionado a uma exceção personalizada não pode ser lançada.
 * <p>
 * A classe inclui um único campo estático, {@code ERROR}, que especifica o tipo de erro.
 *
 * <p>
 * <b>Note:</b> O construtor desta classe constrói a mensagem de exceção através do método {@code buildMessage}
 * disponível no objeto ERROR de tipo {@link ExceptionDetails}.
 * </p>
 *
 * @author diegoneves
 * @see RuntimeException
 * @since 1.0.0
 */
public class ValidationUtilsException extends RuntimeException {

    public static final ExceptionDetails ERROR = ExceptionDetails.EXCEPTION_TYPE_NOT_THROWN;

    /**
     * Construtor para a classe {@link ValidationUtilsException}.
     * Este construtor aceita uma string representando a mensagem de erro detalhada a ser usada ao construir a exceção.
     * A mensagem é então passada para o método {@code buildMessage} do objeto {@code ERROR} (um instance de {@link ExceptionDetails}) para criar uma mensagem de erro formatada de maneira mais específica.
     * O resultado final é passado para o construtor da superclasse {@link RuntimeException} para a construção da instancia da exceção.
     *
     * @param message A string que representa a mensagem detalhada da exceção. Esta string será usada para criar uma mensagem de erro mais detalhada através do método {@code buildMessage} do objeto {@code ERROR}.
     * @throws RuntimeException Representa a exceção lançada quando ocorre um erro na validação de uma operação específica no contexto do seu uso.
     * @see ExceptionDetails#buildMessage(String)
     */
    public ValidationUtilsException(String message) {
        super(ERROR.buildMessage(message));
    }

    /**
     * Construtor que cria uma instância da exceção {@link ValidationUtilsException} com a mensagem e causa fornecidas.
     * A mensagem é usada para criar uma mensagem de erro mais detalhada, passada para a superclasse, {@link RuntimeException}.
     *
     * @param message A string que representa a mensagem detalhada da exceção, usada para criar uma mensagem de erro mais detalhada.
     * @param cause   A causa raiz da exceção, tipicamente uma instância de {@link Throwable} que levou à ocorrência desta exceção.
     *                Esta informação é usada para depuração e rastreamento do erro.
     */
    public ValidationUtilsException(String message, Throwable cause) {
        super(ERROR.buildMessage(message), cause);
    }

}
