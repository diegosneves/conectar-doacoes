package diegosneves.github.conectardoacoes.adapters.rest.exception;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;

/**
 * A classe {@link ShelterEntityFailuresException} representa uma exceção específica que será lançada
 * quando ocorrer uma falha em uma operação relacionada ao {@link ShelterEntity}.
 * Herda de {@link RuntimeException} e adiciona mensagens de erro detalhadas para os erros que podem ocorrer
 * durante as operações de manipulação dos dados da entidade {@link Shelter}.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public class ShelterEntityFailuresException extends CustomException {

    /**
     * O construtor da classe {@link ShelterEntityFailuresException}.
     * <p>
     * Este construtor recebe um termo como parâmetro, que é um número inteiro. Este termo é usado para obter os detalhes
     * de uma exceção específica, como a mensagem de erro, o código HTTP associado ao erro, etc.
     * <p>
     * Este construtor faz uso do método de classe {@link #obtainExceptionDetails(Integer)}, que busca os detalhes de uma
     * exceção na enumeração {@link ExceptionDetails} baseado no termo fornecido.
     * <p>
     * O minimalismo deste construtor facilita sua utilização ao longo das várias partes do código onde é necessário
     * lançar esta exceção.
     * <p>
     * Caso o termo fornecido não corresponda a nenhum elemento na enumeração {@link ExceptionDetails}, uma exceção do
     * tipo {@link DetailsFailureException} é lançada pelo método {@link #obtainExceptionDetails(Integer)}.
     *
     * @param term um número inteiro que representa a chave do erro na enumeração {@link ExceptionDetails}.
     * @throws DetailsFailureException se nenhuma correspondência for encontrada na enumeração {@link ExceptionDetails}
     *                                 para o termo fornecido.
     */
    public ShelterEntityFailuresException(Integer term) {
        super(obtainExceptionDetails(term));
    }

    /**
     * Uma exceção que será lançada quando ocorrer uma falha em uma operação relacionada ao {@link ShelterEntity}.
     * A exceção contém uma mensagem de erro detalhada.
     *
     * @param message A mensagem específica da exceção.
     */
    public ShelterEntityFailuresException(Integer term, String message) {
        super(obtainExceptionDetails(term), message);
    }

    /**
     * Construtor para a classe {@link ShelterEntityFailuresException}.
     * Este construtor aceita dois parâmetros:
     * <br>
     * {@link Integer} - representa a chave para o erro na enumeração {@link ExceptionDetails}, e
     * <br>
     * {@link Throwable} - representa a causa do erro.
     * <p>
     * O termo é usado para obter os detalhes de uma exceção específica, como a mensagem de erro,
     * o código de erro HTTP associado, etc., através do método {@link #obtainExceptionDetails(Integer)},
     * que busca o detalhe da exceção na enumeração {@link ExceptionDetails}.
     * Se nenhum erro correspondente for encontrado na enumeração {@link ExceptionDetails} para o termo fornecido,
     * {@link DetailsFailureException} é lançada pelo método {@link #obtainExceptionDetails(Integer)}.
     * <p>
     * A simplicidade deste construtor facilita seu uso em várias partes do código
     * onde é necessário lançar essa exceção.
     *
     * @param term  um inteiro que é usado como uma chave para o erro na enumeração {@link ExceptionDetails}.
     * @param cause a causa original do erro.
     * @throws DetailsFailureException se nenhum erro correspondente for encontrado no {@link ExceptionDetails}
     *                                 enumeração para o termo fornecido.
     */
    public ShelterEntityFailuresException(Integer term, Throwable cause) {
        super(obtainExceptionDetails(term), cause);
    }

    /**
     * Uma exceção que será lançada quando ocorrer uma falha em uma operação relacionada ao {@link ShelterEntity}.
     * A exceção contém uma mensagem de erro detalhada e a causa original do erro.
     *
     * @param message A mensagem específica da exceção.
     * @param cause   A causa original do erro.
     */
    public ShelterEntityFailuresException(Integer term, String message, Throwable cause) {
        super(obtainExceptionDetails(term), message, cause);
    }

}
