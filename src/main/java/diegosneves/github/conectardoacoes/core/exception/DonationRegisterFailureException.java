package diegosneves.github.conectardoacoes.core.exception;

import diegosneves.github.conectardoacoes.core.enums.ExceptionDetails;

/**
 * Classe de exceção personalizada para falhas no registro de doações.
 *
 * <p>
 * Esta é uma subclasse de {@link RuntimeException}, representando os
 * erros que podem ocorrer durante o processo de registro de uma nova
 * doação no sistema. Ela é associada a um conjunto predefinido de
 * detalhes de erros, especificamente: {@link ExceptionDetails#DONATION_CREATION_ERROR}.
 * </p>
 *
 * <p>
 * O erro específico que desencadeou essa exceção deve ser passado como uma
 * String para o construtor ao instanciar um novo objeto {@link DonationRegisterFailureException}.
 * </p>
 * @author diegoneves
 * @version 1.0
 * @see RuntimeException
 */
public class DonationRegisterFailureException extends RuntimeException {

    public static final ExceptionDetails ERROR = ExceptionDetails.DONATION_CREATION_ERROR;

    /**
     * Construtor que aceita uma mensagem de erro personalizada.
     *
     * <p>
     * Cria uma nova instância de {@link DonationRegisterFailureException}
     * com a mensagem de erro fornecida.
     * </p>
     *
     * @param message A mensagem de erro detalhada específica para a exceção ocorrida.
     */
    public DonationRegisterFailureException(String message) {
        super(ERROR.buildMessage(message));
    }

}
