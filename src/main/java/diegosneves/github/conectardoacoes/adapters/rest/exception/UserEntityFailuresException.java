package diegosneves.github.conectardoacoes.adapters.rest.exception;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;

/**
 * A classe {@link UserEntityFailuresException} representa uma exceção específica que será lançada
 * quando ocorrer uma falha em uma operação relacionada ao {@link UserEntity}.
 * Herda de {@link RuntimeException} e adiciona mensagens de erro detalhadas para os erros que podem ocorrer
 * durante as operações de manipulação dos dados da entidade {@link User}.
 *
 *
 * @author diegoneves
 * @since 1.0.0
 */
public class UserEntityFailuresException extends RuntimeException {

    public static final ExceptionDetails ERROR = ExceptionDetails.USER_OPERATION_FAILURE;

    /**
    * Uma exceção que será lançada quando ocorrer uma falha em uma operação relacionada ao {@link UserEntity}.
    * A exceção contém uma mensagem de erro detalhada.
    *
    * @param message A mensagem específica da exceção.
    */
    public UserEntityFailuresException(String message) {
        super(ERROR.formatErrorMessage(message));
    }

    /**
    * Uma exceção que será lançada quando ocorrer uma falha em uma operação relacionada ao {@link UserEntity}.
    * A exceção contém uma mensagem de erro detalhada e a causa original do erro.
    *
    * @param message A mensagem específica da exceção.
    * @param cause A causa original do erro.
    */
    public UserEntityFailuresException(String message, Throwable cause) {
        super(ERROR.formatErrorMessage(message), cause);
    }

}
