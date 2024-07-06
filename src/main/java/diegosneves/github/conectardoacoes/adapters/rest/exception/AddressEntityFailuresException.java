package diegosneves.github.conectardoacoes.adapters.rest.exception;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;

/**
 * A classe {@link AddressEntityFailuresException} é uma subclasse da classe {@link RuntimeException}.
 * Esta é uma exceção personalizada que é lançada quando ocorre um erro durante a execução de uma operação
 * relacionada a uma entidade de endereço, como mapeamento ou validação.
 * <p>
 * As mensagens de erro personalizadas para esta exceção são definidas através das constantes na enumeração
 * {@link ExceptionDetails}. A mensagem de erro específica usada neste caso é a
 * {@link ExceptionDetails#ADDRESS_OPERATION_FAILURE}.
 * <p>
 * Sendo uma {@link RuntimeException}, não é obrigatório que seja declarada na cláusula `throws` dos métodos
 * que a lançam, e ela não precisa ser capturada explicitamente.
 * <p>
 * @author diegoneves
 * @since 1.0.0
 * @see RuntimeException
 */
public class AddressEntityFailuresException extends RuntimeException {

    public static final ExceptionDetails ERROR = ExceptionDetails.ADDRESS_OPERATION_FAILURE;

    /**
     * Este construtor para a classe {@link AddressEntityFailuresException} recebe uma string
     * que representa uma mensagem personalizada de erro. A mensagem fornecida é então formatada
     * por meio do método {@code ERROR.formatErrorMessage(message)}, onde {@code ERROR} é uma
     * constante da enumeração {@link ExceptionDetails}, especificamente
     * {@link ExceptionDetails#ADDRESS_OPERATION_FAILURE}, que guarda detalhes para uma operação
     * de endereço que falhou.
     * <p>
     * A mensagem formatada é então passada para o construtor da superclasse {@link RuntimeException}
     * através da chamada {@code super()}.
     * <p>
     * @param message A mensagem personalizada de erro que será formatada e passada
     *                para a superclasse {@link RuntimeException}.
     * @throws NullPointerException se a mensagem fornecida for {@code null}.
     */
    public AddressEntityFailuresException(String message) {
        super(ERROR.formatErrorMessage(message));
    }

    /**
     * Construtor que recebe uma mensagem de erro e uma exceção causal. Invoca o construtor de sua classe
     * mãe {@link RuntimeException} passando uma mensagem de erro formatada e a exceção causal.
     * A mensagem de erro é formatada usando o método {@code ERROR.formatErrorMessage(message)}, onde {@code ERROR}
     * é a constante {@link ExceptionDetails} especificada para operações de endereço que falham.
     *
     * @param message A mensagem de erro personalizada.
     * @param cause   A exceção causal.
     */
    public AddressEntityFailuresException(String message, Throwable cause) {
        super(ERROR.formatErrorMessage(message), cause);
    }
}
