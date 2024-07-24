package diegosneves.github.conectardoacoes.adapters.rest.exception;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;

/**
 * A classe {@link UserEntityFailuresException} representa uma exceção específica que será lançada
 * quando ocorrer uma falha em uma operação relacionada ao {@link UserEntity}.
 * Herda de {@link RuntimeException} e adiciona mensagens de erro detalhadas para os erros que podem ocorrer
 * durante as operações de manipulação dos dados da entidade {@link User}.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public class UserEntityFailuresException extends CustomException {

    /**
     * Construtor da exceção {@link UserEntityFailuresException}. Este construtor recebe um código de termo
     * que representa o erro ocorrido.
     * <p>
     * O código de termo é utilizado para obter os detalhes correspondentes à excecção através do método
     * {@link #obtainExceptionDetails(Integer)}. Cada exceção possui uma mensagem de erro associada, um
     * código de estado HTTP, entre outros detalhes, que são encapsulados na instância de {@link ExceptionDetails}
     * retornada pelo método mencionado.
     * <p>
     * A mensagem detelhada da exceção é então passada para a classe parent {@link CustomException} através
     * do método super().
     *
     * @param term um número inteiro representando a chave do erro na enumeração {@link ExceptionDetails}.
     * @throws DetailsFailureException se nenhum elemento da enumeração corresponder ao termo fornecido. Esta
     *                                 exceção seria lançada pelo método {@link #obtainExceptionDetails(Integer)}.
     */
    public UserEntityFailuresException(Integer term) {
        super(obtainExceptionDetails(term));
    }

    /**
     * Uma exceção que será lançada quando ocorrer uma falha em uma operação relacionada ao {@link UserEntity}.
     * A exceção contém uma mensagem de erro detalhada.
     *
     * @param message A mensagem específica da exceção.
     */
    public UserEntityFailuresException(Integer term, String message) {
        super(message, obtainExceptionDetails(term));
    }

    /**
     * Constrói uma nova instância da classe de exceção {@link UserEntityFailuresException} com um {@code term}
     * específico que representa o tipo de erro e uma causa original da exceção.
     * <p>
     * Este construtor é comumente utilizado quando se quer encapsular uma exceção de baixo nível dentro
     * da exceção personalizada {@code UserEntityFailuresException}. O {@code cause} é usado para preservar
     * o rastreamento de pilha da exceção original.
     * <p>
     * O {@code term} é um número inteiro utilizado para descobrir os detalhes da exceção na enumeração
     * {@code ExceptionDetails}. Cada erro/exceção tem um correlato na enumeração que determina varias informações
     * como o código de estado HTTP, mensagem de erro padrão, etc.
     *
     * @param term  um numero inteiro que representa a chave do erro na enumeração {@link ExceptionDetails}.
     *              Este valor é utilizado para obter os detalhes da exceção corrente.
     * @param cause a causa original (pode ser recuperada pelo método {@link Throwable#getCause()}),
     *              (A causa é permitida ser null, indicando que a causa é inexistente ou desconhecida)
     * @throws DetailsFailureException se nenhum elemento da enumeração corresponder ao {@code term} fornecido.
     *                                 Esta exceção seria lançada pelo método {@link #obtainExceptionDetails(Integer)}.
     */
    public UserEntityFailuresException(Integer term, Throwable cause) {
        super(cause, obtainExceptionDetails(term));
    }

    /**
     * Uma exceção que será lançada quando ocorrer uma falha em uma operação relacionada ao {@link UserEntity}.
     * A exceção contém uma mensagem de erro detalhada e a causa original do erro.
     *
     * @param message A mensagem específica da exceção.
     * @param cause   A causa original do erro.
     */
    public UserEntityFailuresException(Integer term, String message, Throwable cause) {
        super(message, cause, obtainExceptionDetails(term));
    }

}
