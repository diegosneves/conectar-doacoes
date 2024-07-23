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

    protected CustomException(ExceptionDetails error) {
        super(error.formatErrorMessage());
        this.errorDetails = error;
    }

    protected CustomException(String message, ExceptionDetails error) {
        super(error.formatErrorMessage(message));
        this.errorDetails = error;
    }

    protected CustomException(Throwable cause, ExceptionDetails error) {
        super(error.formatErrorMessage(), cause);
        this.errorDetails = error;
    }

    protected CustomException(String message, Throwable cause, ExceptionDetails error) {
        super(error.formatErrorMessage(message), cause);
        this.errorDetails = error;
    }

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
