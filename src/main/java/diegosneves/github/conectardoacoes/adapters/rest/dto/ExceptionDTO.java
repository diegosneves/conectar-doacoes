package diegosneves.github.conectardoacoes.adapters.rest.dto;

/**
 * A classe {@link ExceptionDTO} representa um objeto de transferência de dados
 * para transportar informações de exceção.
 * Ela contém a mensagem e o código de status da exceção.
 *
 * @author diegosneves
 * @since 1.0.0
 */
public record ExceptionDTO(String message, int statusCode) {
}
