package diegosneves.github.conectardoacoes.adapters.rest.exception;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;

/**
 * Esta é a classe {@link DetailsFailureException}, que estende a classe {@link CustomException}.
 * É utilizada para lidar com exceções de detalhamento de falhas específicas.
 * <p>
 * Quando ocorre uma falha no detalhamento de termos, o sistema levanta esta exceção personalizada.
 * A exceção possui duas informações principais: o termo e o código de erro.
 * <p>
 * O termo é o identificador principal para o qual o detalhamento falhou.
 * O código de erro fornece mais detalhes sobre a natureza específica do erro ocorrido.
 * <p>
 * O construtor da classe utiliza o termo para obter os detalhes da exceção e
 * formata a mensagem do erro com o código de erro fornecido.
 *
 * @author diegoneves
 * @since 1.1.0
 */
public class DetailsFailureException extends CustomException {

    /**
     * Construtor para a classe {@link DetailsFailureException}.
     * <p>
     * Este construtor aceita um termo na forma de um inteiro, que serve como uma chave para recuperar
     * os detalhes da exceção da enumeração {@link ExceptionDetails}. O construtor chama o método {@code super}
     * da classe pai {@link CustomException} com os detalhes da exceção obtidos pelo termo.
     * <p>
     * Essa abordagem permite que os detalhes específicos da exceção, como a mensagem de erro e o status HTTP,
     * sejam facilmente recuperados a partir do termo fornecido, fornecendo assim mais contexto sobre
     * o motivo da falha e permitindo melhor rastreamento e resolução de erros.
     *
     * @param term o identificador principal para o qual o detalhamento falhou. Representa a chave do erro
     *             na enumeração {@link ExceptionDetails}.
     * @throws DetailsFailureException se o termo fornecido não corresponder a nenhum elemento na enumeração
     *                                 {@link ExceptionDetails}. A exceção é lançada pelo método {@link ExceptionDetails#getExceptionDetails(Integer)}
     *                                 que é chamado em {@link CustomException#obtainExceptionDetails(Integer)}.
     * @see ExceptionDetails
     * @see CustomException
     */
    public DetailsFailureException(Integer term) {
        super(obtainExceptionDetails(term));
    }

}
