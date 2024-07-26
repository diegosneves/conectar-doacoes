package diegosneves.github.conectardoacoes.adapters.rest.exception;

import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;

/**
 * A classe {@link DonationEntityFailuresException} representa uma exceção específica que será lançada
 * quando ocorrer uma falha em uma operação relacionada ao {@link DonationEntity}.
 * Herda de {@link RuntimeException} e adiciona mensagens de erro detalhadas para os erros que podem ocorrer
 * durante as operações de manipulação dos dados da entidade {@link Donation}.
 *
 *
 * @author diegoneves
 * @since 1.1.0
 */
public class DonationEntityFailuresException extends CustomException {

    /**
    * Uma exceção que será lançada quando ocorrer uma falha em uma operação relacionada ao {@link DonationEntity}.
    * A exceção contém uma mensagem de erro detalhada.
    *
    * @param message A mensagem específica da exceção.
    */
    public DonationEntityFailuresException(Integer term, String message) {
        super(message, obtainExceptionDetails(term));
    }

    /**
    * Uma exceção que será lançada quando ocorrer uma falha em uma operação relacionada ao {@link DonationEntity}.
    * A exceção contém uma mensagem de erro detalhada e a causa original do erro.
    *
    * @param message A mensagem específica da exceção.
    * @param cause A causa original do erro.
    */
    public DonationEntityFailuresException(Integer term, String message, Throwable cause) {
        super(message, cause, obtainExceptionDetails(term));
    }

}
