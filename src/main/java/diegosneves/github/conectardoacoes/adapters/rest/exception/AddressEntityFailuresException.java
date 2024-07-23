package diegosneves.github.conectardoacoes.adapters.rest.exception;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;

/**
 * A classe {@link AddressEntityFailuresException} é uma subclasse da classe {@link RuntimeException}.
 * Esta é uma exceção personalizada que é lançada quando ocorre um erro durante a execução de uma operação
 * relacionada a uma entidade de endereço, como mapeamento ou validação.
 * <p>
 * As mensagens de erro personalizadas para esta exceção são definidas através das constantes na enumeração
 * {@link ExceptionDetails}.
 * <p>
 * Sendo uma {@link RuntimeException}, não é obrigatório que seja declarada na cláusula `throws` dos métodos
 * que a lançam, e ela não precisa ser capturada explicitamente.
 * <p>
 *
 * @author diegoneves
 * @see RuntimeException
 * @since 1.0.0
 */
public class AddressEntityFailuresException extends CustomException {

    /**
     * Este é um construtor da classe {@link AddressEntityFailuresException}.
     * <p>
     * Cria uma instância de {@link AddressEntityFailuresException} com uma mensagem de erro personalizada,
     * determinada a partir do código de erro fornecido no parâmetro term.
     * O código de erro é usado para buscar na enumeração {@link ExceptionDetails} a mensagem da exceção correspondente.
     * Após a recuperação, a mensagem é então passada para o construtor da superclasse {@link RuntimeException} através da chamada super().
     * <p>
     * Se nenhuma mensagem de erro corresponder ao código fornecido, uma exceção do tipo {@link DetailsFailureException} será lançada.
     *
     * @param term o código de erro que identifica a mensagem a ser usada para esta exceção. Deve corresponder a uma das
     *             constantes definidas na enumeração {@link ExceptionDetails}.
     * @throws DetailsFailureException Se o termo fornecido não corresponder a nenhuma das constantes em {@link ExceptionDetails}.
     * @see RuntimeException
     * @see ExceptionDetails
     */
    public AddressEntityFailuresException(Integer term) {
        super(obtainExceptionDetails(term));
    }

    /**
     * Este construtor para a classe {@link AddressEntityFailuresException} recebe uma string
     * que representa uma mensagem personalizada de erro. A mensagem fornecida é então formatada
     * por meio do método {@code ERROR.formatErrorMessage(message)}, onde {@code ERROR} é uma
     * constante da enumeração {@link ExceptionDetails}.
     * <p>
     * A mensagem formatada é então passada para o construtor da superclasse {@link RuntimeException}
     * através da chamada {@code super()}.
     *
     * @param message A mensagem personalizada de erro que será formatada e passada
     *                para a superclasse {@link RuntimeException}.
     */
    public AddressEntityFailuresException(Integer term, String message) {
        super(message, obtainExceptionDetails(term));
    }

    /**
     * Construtor da classe {@link AddressEntityFailuresException}.
     * <p>
     * Este construtor utiliza um inteiro genérico 'term' e uma instância da classe {@link Throwable} 'cause'.
     * As informações de 'term' são utilizadas para obtenção dos detalhes da exceção através do método
     * {@link #obtainExceptionDetails(Integer)}. Em seguida, o construtor invoca o construtor da superclasse
     * {@link CustomException} com 'cause' e os detalhes da exceção obtidos anteriormente.
     *
     * @param term  O código de erro usado para buscar na enumeração {@link ExceptionDetails} a mensagem correspondente à exceção.
     *              Se o 'term' fornecido não corresponder a nenhuma das constantes em {@link ExceptionDetails}, este construtor
     *              lançará uma exceção do tipo {@link DetailsFailureException}.
     * @param cause A causa raiz que causou a exceção. Isto é útil para rastrear e registrar a exceção que desencadeou esta exceção.
     * @throws DetailsFailureException Se o 'term' fornecido não corresponder a nenhuma das constantes em {@link ExceptionDetails}.
     * @see Throwable
     * @see CustomException
     * @see ExceptionDetails
     */
    public AddressEntityFailuresException(Integer term, Throwable cause) {
        super(cause, obtainExceptionDetails(term));
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
    public AddressEntityFailuresException(Integer term, String message, Throwable cause) {
        super(message, cause, obtainExceptionDetails(term));
    }
}
