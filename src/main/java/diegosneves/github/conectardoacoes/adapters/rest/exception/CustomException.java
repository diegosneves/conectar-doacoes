package diegosneves.github.conectardoacoes.adapters.rest.exception;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;

/**
 * Classe abstrata CustomException que estende a classe RuntimeException.
 * Esta classe é designada para lidar com exceções personalizadas no aplicativo.
 *
 * <p>A classe contém um objeto estático de {@code ExectionDetails} que pode ser usado para fornecer
 * detalhes adicionais sobre a exceção. As exceções podem ser construídas com uma mensagem de erro específica e/ou com
 * uma causa raiz, permitindo o rastreamento do erro com mais facilidade.
 *
 * <p>A classe também fornece um método estático para obter detalhes excepcionais com base em termos de exceção {@code Integer}.
 *
 * <p> É importante destacar que esta classe é abstrata e deve ser estendida para uso e para casos de exceção personalizados.
 *
 * @author diegoneves
 * @since 1.1.0
 */
public abstract class CustomException extends RuntimeException {

    private final ExceptionDetails errorDetails;

    /**
     * Este é um construtor protegido usado para criar uma nova instância de {@link CustomException} com um objeto {@link ExceptionDetails} especificado.
     * <p>
     * O objeto ExceptionDetails fornecido encapsula informações detalhadas de erro que são usadas para formatar a mensagem de erro
     * que é passada para a classe {@link RuntimeException} pai. O objeto {@link ExceptionDetails} também é armazenado para recuperação posterior,
     * permitindo que os detalhes sobre o erro estejam disponíveis ao tratar a exceção.
     *
     * @param error o objeto {@link ExceptionDetails} que contém as informações detalhadas de erro
     */
    protected CustomException(ExceptionDetails error) {
        super(error.formatErrorMessage());
        this.errorDetails = error;
    }

    /**
     * Este é um construtor protegido usado para criar uma nova instância de {@link CustomException}.
     * A mensagem de erro fornecida é formatada pelo objeto {@link ExceptionDetails} e passada para
     * o construtor superclass {@link RuntimeException}.
     * Além disso, o objeto {@link ExceptionDetails} é armazenado para recuperação posterior.
     *
     * @param message A mensagem de erro personalizada.
     * @param error   O objeto {@link ExceptionDetails} que contém as informações detalhadas do erro.
     */
    protected CustomException(String message, ExceptionDetails error) {
        super(error.formatErrorMessage(message));
        this.errorDetails = error;
    }

    /**
     * Este é um construtor protegido utilizado para criar uma nova instância de {@link CustomException}.
     *
     * <p>
     * A instância é criada com uma causa {@link Throwable} especificada e um objeto {@link ExceptionDetails} também fornecido.
     * A mensagem de erro formatada pelo objeto {@link ExceptionDetails} e a causa são passadas para o construtor da superclasse {@link RuntimeException}.
     * O objeto {@link ExceptionDetails} é então armazenado neste objeto {@link CustomException} para recuperação futura.
     *
     * @param cause a causa (que é salva para recuperação posterior por {@link Throwable#getCause()}) (A null value is permitted,
     *              and indicates that the cause is nonexistent or unknown).
     * @param error o objeto {@link ExceptionDetails} que contém as informações mais detalhadas do erro.
     */
    protected CustomException(Throwable cause, ExceptionDetails error) {
        super(error.formatErrorMessage(), cause);
        this.errorDetails = error;
    }

    /**
     * Construtor protegido para criar uma nova instância de {@link CustomException}.
     * <p>
     * Este construtor constrói o {@link CustomException} com uma mensagem personalizada de erro,
     * uma causa (representada por um objeto {@link Throwable}) e um objeto detalhado da exceção
     * (representado por um objeto {@link ExceptionDetails}).
     * <p>
     * A mensagem de erro é formatada pelo objeto {@link ExceptionDetails} e a causa são passadas
     * para o construtor da superclasse {@link RuntimeException}. O objeto {@link ExceptionDetails}
     * é armazenado dentro do objeto {@link CustomException} criado para a utilização futura.
     *
     * @param message A mensagem customizada de erro.
     * @param cause   A causa do erro para fins de debug, representada por um objeto {@link Throwable}.
     *                Um valor nulo é permitido, e indica que a causa é inexistente ou desconhecida.
     * @param error   O objeto {@link ExceptionDetails} que contém as informações mais detalhadas do erro.
     */
    protected CustomException(String message, Throwable cause, ExceptionDetails error) {
        super(error.formatErrorMessage(message), cause);
        this.errorDetails = error;
    }

    /**
     * Método getter para recuperar os detalhes do erro associados a esta {@link CustomException}.
     *
     * <p>
     * Este método é usado para recuperar a instância de {@link ExceptionDetails} que foi fornecida
     * ao construir esta {@link CustomException}. O objeto {@link ExceptionDetails} encapsula
     * informações detalhadas sobre o erro que causou esta exceção, tais como a mensagem de erro e
     * quaisquer outros dados relevantes relativos ao erro.
     * </p>
     *
     * @return o objeto {@link ExceptionDetails} que encapsula as informações detalhadas sobre o
     * erro que causou esta exceção. Isto pode ser nulo se nenhum detalhe foi fornecido quando a
     * exceção foi construída.
     */
    public ExceptionDetails getErrorDetails() {
        return this.errorDetails;
    }

    /**
     * Método que busca os detalhes de uma exceção na enumeração {@link ExceptionDetails}.
     * <p>
     * Este método retorna uma instância de {@link ExceptionDetails} com base no termo fornecido. O termo representa
     * a chave do erro que queremos recuperar. O método faz uso do método
     * {@link ExceptionDetails#getExceptionDetails(Integer)} para obter a instância de {@link ExceptionDetails}.
     * Se nenhuma instância for encontrada para o termo fornecido, o método {@link ExceptionDetails#getExceptionDetails(Integer)}
     * lançará uma exceção do tipo {@link DetailsFailureException}.
     * <p>
     * Este método é utilizado para fornecer detalhes sobre uma exceção específica, como a mensagem de erro,
     * o {@code HttpStatus} associado ao erro e etc.
     *
     * @param term um número inteiro representando a chave do erro na enumeração {@link ExceptionDetails}.
     * @return uma instância de {@link ExceptionDetails} representando os detalhes da exceção,
     * incluindo a mensagem de erro e o status HTTP associado ao erro.
     * @throws DetailsFailureException se nenhum elemento da enumeração corresponder ao termo fornecido.
     */
    protected static ExceptionDetails obtainExceptionDetails(Integer term) {
        return ExceptionDetails.getExceptionDetails(term);
    }
}
