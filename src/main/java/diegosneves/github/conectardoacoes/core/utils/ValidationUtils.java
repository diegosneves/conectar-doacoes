package diegosneves.github.conectardoacoes.core.utils;

import diegosneves.github.conectardoacoes.core.exception.ValidationUtilsException;

import java.lang.reflect.Constructor;

/**
 * A classe {@link ValidationUtils} é um utilitário que fornece métodos para validar dados de entrada.
 * Contém todos os métodos estáticos e não pode ser instanciada.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public class ValidationUtils {

    private ValidationUtils() {
    }

    /**
     * Valida a entrada com base nas condições para nulo e vazio (no caso de String).
     * Lança uma exceção com a mensagem de erro fornecida, se uma destas condições for verdadeira.
     * Este método pode ser utilizado para a validação de entradas onde dados são obrigatórios.
     *
     * @param <T>             o tipo de objeto a ser verificado
     * @param input          o objeto a ser validado
     * @param errorMessage    a mensagem de erro a ser anexada à exceção em caso de falhas na validação
     * @param customException a classe da exceção RuntimeException a ser lançada
     * @throws RuntimeException se o objeto fornecido for nulo ou se fora uma instância de String e estiver vazia
     */
    public static <T> void validateNotNullOrEmpty(T input, String errorMessage, Class<? extends RuntimeException> customException) {
        if (input == null || (input instanceof String && ((String) input).trim().isEmpty())) {
            throwException(errorMessage, customException);
        }
    }

    /**
     * Lança uma exceção customizada com a mensagem de erro fornecida.
     *
     * @param message               a mensagem de erro a ser anexada à exceção
     * @param runtimeExceptionClass a classe da exceção RuntimeException a ser lançada
     * @throws ValidationUtilsException se houve um erro ao tentar lançar a exceção
     */
    private static void throwException(String message, Class<? extends RuntimeException> runtimeExceptionClass) {
        try {
            Constructor<? extends RuntimeException> exceptionConstructor = runtimeExceptionClass.getConstructor(String.class);
            throw exceptionConstructor.newInstance(message);
        } catch (ReflectiveOperationException e) {
            throw new ValidationUtilsException(runtimeExceptionClass.getSimpleName(), e);
        }
    }

}
