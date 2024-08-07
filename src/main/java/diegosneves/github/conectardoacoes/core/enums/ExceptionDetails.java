package diegosneves.github.conectardoacoes.core.enums;

/**
 * Este é um Enum que contém detalhes de várias exceções relacionadas ao processo de criação de várias entidades, como Usuário, Donação, Abrigo e Endereço.
 * Cada constante neste Enum representa um cenário específico de erro e contém uma mensagem de erro associada.
 * Esta mensagem de erro pode ser formatada com um parâmetro adicional que pode ser fornecido durante a construção de uma mensagem de erro.
 *
 * <p> Exemplo de uso:
 * O código abaixo mostra como você usaria o ExceptionDetails para construir uma mensagem de erro para um erro na criação de um usuário:
 * <pre>
 *     {@code
 *  public static final ExceptionDetails ERROR = ExceptionDetails.ADDRESS_CREATION_ERROR;
 *
 *  public AddressCreationFailureException(String message) {
 *    super(ERROR.buildMessage(message));
 * }
 * }
 * </pre>
 * </p>
 *
 * @author diegoneves
 * @since 1.0.0
 */
public enum ExceptionDetails {

    USER_CREATION_ERROR("Um erro ocorreu ao tentar criar um usuario devido ao seguinte motivo: %s"),
    USER_MANIPULATION_ERROR("Ocorreu um erro no UserService ao tentar manipular um usuário. Motivo: %s"),
    SHELTER_MANIPULATION_ERROR("Ocorreu um erro no ShelterService ao tentar manipular um abrigo. Motivo: %s"),
    DONATION_CREATION_ERROR("Um erro ocorreu ao tentar registrar uma doação devido ao seguinte motivo: %s"),
    SHELTER_CREATION_ERROR("Um erro ocorreu ao tentar criar um Abrigo devido ao seguinte motivo: %s"),
    ADDRESS_CREATION_ERROR("Um erro ocorreu ao tentar criar um endereço devido ao seguinte motivo: %s"),
    INVALID_UUID_FORMAT_MESSAGE("O ID %s precisa estar no formato UUID"),
    EXCEPTION_TYPE_NOT_THROWN("Não foi possível lançar a exceção do tipo %s");

    private final String message;

    ExceptionDetails(String message) {
        this.message = message;
    }

    /**
     * Este método constrói e retorna uma mensagem de erro a partir da mensagem de erro associada à constante Enum e do parâmetro fornecido.
     *
     * @param message um detalhe de erro específico
     * @return uma mensagem de erro completa e formatada
     */
    public String buildMessage(String message) {
        return String.format(this.message, message);
    }

}
