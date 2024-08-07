package diegosneves.github.conectardoacoes.adapters.rest.exception;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;

/**
 * Classe {@link ExternalApiFailureException} se estende da classe {@link CustomException} e é utilizada para lidar com os erros de interação com APIs externas.
 * <p>
 * Quando um método que invoca uma API externa encontra erros, ele gera uma nova instância desta exceção, passando informações relevantes como argumentos para o construtor.
 * Estas informações incluem um código de erro em forma de número inteiro, uma mensagem de erro customizada, e a causa original do erro.
 * Esta exceção é então capturada pela lógica do programa para fins de tratamento de erro e logging.
 * <p>
 * A chave do erro, representada por um número inteiro, é utilizada para recuperar informações detalhadas da exceção ({@link ExceptionDetails}) associadas a tal erro através do método
 * {@link ExternalApiFailureException#obtainExceptionDetails}.
 * Note que se nenhum {@link ExceptionDetails} é encontrado para a referida chave, uma {@link CustomException} poderá ser lançada pelo método {@link ExternalApiFailureException#obtainExceptionDetails}.
 * <p>
 * Portanto, a intenção desta classe é encapsular os detalhes de erros que podem ocorrer durante a interação com APIs externas, permitindo que o restante do código lide com esses erros
 * de uma maneira mais abstrata e controlada.
 *
 * @author diegoneves
 * @since 1.2.0
 * @see CustomException
 * @see RuntimeException
 */
public class ExternalApiFailureException extends CustomException {

    /**
     * Construtor para criar uma nova instância de {@link ExternalApiFailureException}.
     * <p>
     * Este construtor é chamado quando há um erro ao tentar recuperar informações da API externa.
     * Ele passa os detalhes do erro na forma de um número inteiro (representando uma chave específica de erro),
     * uma mensagem de erro personalizada e a causa original do erro para o construtor da superclasse {@link CustomException}.
     * <p>
     * Os detalhes da exceção são obtidos chamando o método {@link ExternalApiFailureException#obtainExceptionDetails}
     * com o termo fornecido como argumento. Esta chamada pode resultar em uma {@link CustomException} se nenhum {@link ExceptionDetails}
     * for encontrado para o termo dado.
     *
     * @param term    A chave do erro, representado por um número inteiro. Usado para recuperar o {@link ExceptionDetails} associado.
     * @param message A mensagem de erro customizada.
     * @param cause   A causa root que levou a esta exceção, habitualmente a exceção jogada pela API externa. Representada
     *                como um objeto {@link Throwable}. Um valor nulo é permitido e indica que a causa é inexistente ou desconhecida.
     * @throws ExternalApiFailureException A exceção lançada quando uma chamada para a API externa falha.
     */
    public ExternalApiFailureException(Integer term, String message, Throwable cause) {
        super(obtainExceptionDetails(term), message, cause);
    }
}
