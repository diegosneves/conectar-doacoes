package diegosneves.github.conectardoacoes.core.enums;

public enum ExceptionDetails {

    USER_NAME_INVALID("Um erro ocorreu ao tentar criar um usuario devido ao seguinte motivo: %s"),
    INVALID_UUID_FORMAT_MESSAGE("O ID %s precisa estar no formato UUID");

    private final String message;

    ExceptionDetails(String message) {
        this.message = message;
    }

    public String buildMessage(String message) {
        return String.format(this.message, message);
    }

}
