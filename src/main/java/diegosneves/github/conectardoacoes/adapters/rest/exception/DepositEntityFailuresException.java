package diegosneves.github.conectardoacoes.adapters.rest.exception;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.model.DepositEntity;

/**
 * A classe {@link DepositEntityFailuresException} representa uma exceção específica que será lançada
 * quando ocorrer uma falha em uma operação relacionada ao {@link DepositEntity}.
 * Herda de {@link RuntimeException} e adiciona mensagens de erro detalhadas para os erros que podem ocorrer
 * durante as operações de manipulação dos dados da entidade {@link DepositEntity}.
 *
 * @author diegoneves
 * @since 1.3.0
 */
public class DepositEntityFailuresException extends CustomException {

    /**
     * Construtor da exceção {@link DepositEntityFailuresException}.
     * <p>
     * Este construtor cria uma instância de {@link DepositEntityFailuresException} usando um termo específico.
     * O termo é utilizado para obter os detalhes da exceção a partir da enumeração {@link ExceptionDetails}.
     * A mensagem de erro é gerada com base nesses detalhes.
     * </p>
     *
     * @param term um número inteiro que representa a chave do erro na enumeração {@link ExceptionDetails}.
     *             Este termo é utilizado para buscar os detalhes da exceção, incluindo a mensagem de erro e o status HTTP associado ao erro.
     * @throws DetailsFailureException se nenhum elemento da enumeração {@link ExceptionDetails} corresponder ao termo fornecido.
     */
    public DepositEntityFailuresException(Integer term) {
        super(obtainExceptionDetails(term));
    }

    /**
     * Uma exceção que será lançada quando ocorrer uma falha em uma operação relacionada ao {@link DepositEntity}.
     * A exceção contém uma mensagem de erro detalhada.
     *
     * @param message A mensagem específica da exceção.
     */
    public DepositEntityFailuresException(Integer term, String message) {
        super(obtainExceptionDetails(term), message);
    }

    /**
     * Uma exceção que será lançada quando ocorrer uma falha em uma operação relacionada ao {@link DepositEntity}.
     * A exceção contém uma mensagem de erro detalhada e a causa original do erro.
     *
     * @param message A mensagem específica da exceção.
     * @param cause   A causa original do erro.
     */
    public DepositEntityFailuresException(Integer term, String message, Throwable cause) {
        super(obtainExceptionDetails(term), message, cause);
    }

}
