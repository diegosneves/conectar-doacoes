package diegosneves.github.conectardoacoes.adapters.rest.enums;

import org.springframework.http.HttpStatus;

/**
 * A classe {@link ExceptionDetails} é uma enumeração que define várias mensagens de exceções.
 * Cada mensagem corresponde a uma condição específica de validação ou erro
 * que pode ocorrer durante as operações.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public enum ExceptionDetails {

    CONSTRUCTOR_DEFAULT_UNDEFINED("Classe [ %s ] deve declarar um construtor padrão.", HttpStatus.NOT_IMPLEMENTED),
    CLASS_MAPPING_FAILURE("Falha ao tentar mapear a classe [ %s ].", HttpStatus.BAD_REQUEST),
    SHELTER_OPERATION_FAILURE("Ocorreu um erro ao realizar uma operação no Abrigo. Motivo: %s", HttpStatus.BAD_REQUEST),
    DONATION_OPERATION_FAILURE("Ocorreu um erro ao registrar uma doação. Motivo: %s", HttpStatus.BAD_REQUEST),
    ADDRESS_OPERATION_FAILURE("Ocorreu um erro ao realizar uma operação no Endereço. Motivo: %s", HttpStatus.BAD_REQUEST),
    USER_OPERATION_FAILURE("Ocorreu um erro ao realizar uma operação com o Usuário. Motivo: %s", HttpStatus.BAD_REQUEST),
    INVALID_UUID_FORMAT_MESSAGE("O ID %s precisa estar no formato UUID", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    ExceptionDetails(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }


    /**
     * Formata uma mensagem com a entrada fornecida e retorna a mensagem formatada.
     *
     * @param message A mensagem de entrada que será formatada.
     * @return A mensagem após a formatação.
     */
    public String formatErrorMessage(String message) {
        return String.format(this.message, message);
    }

    /**
     * Retorna o código de status HTTP associado ao erro.
     *
     * @return O código numérico do status HTTP relacionado com o erro.
     */
    public int getStatusCodeValue() {
        return this.httpStatus.value();
    }

    /**
     * Obtém o status HTTP associado ao erro.
     *
     * @return O código de status HTTP relacionado ao erro.
     */
    public HttpStatus getHttpStatusCode() {
        return this.httpStatus;
    }

}
