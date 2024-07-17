package diegosneves.github.conectardoacoes.core.utils;

import diegosneves.github.conectardoacoes.core.exception.ValidationUtilsException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe {@link ValidationUtils} é um utilitário que fornece métodos para validar dados de entrada.
 * Contém todos os métodos estáticos e não pode ser instanciada.
 *
 * @author diegoneves
 * @since 1.0.0
 */
@Slf4j
public class ValidationUtils {

    private ValidationUtils() {
    }

    /**
     * Valida a entrada com base nas condições para nulo e vazio (no caso de String).
     * Lança uma exceção com a mensagem de erro fornecida, se uma destas condições for verdadeira.
     * Este método pode ser utilizado para a validação de entradas onde dados são obrigatórios.
     *
     * @param <T>             o tipo de objeto a ser verificado
     * @param input           o objeto a ser validado
     * @param errorMessage    a mensagem de erro a ser anexada à exceção em caso de falhas na validação
     * @param customException a classe da exceção RuntimeException a ser lançada
     * @throws RuntimeException se o objeto fornecido for nulo ou se fora uma instância de String e estiver vazia
     */
    public static <T> void validateNotNullOrEmpty(T input, String errorMessage, Class<? extends RuntimeException> customException) {
        if (input == null || (input instanceof String string && string.trim().isEmpty())) {
            throwException(errorMessage, customException);
        }
    }

    /**
     * Garante que a lista de entrada não é nula e não está vazia.
     * Se a lista de entrada é nula ou vazia, uma exceção {@link RuntimeException} customizada é lançada com a mensagem de erro fornecida.
     * <p>
     * Este método pode ser usado quando se deseja garantir que um objeto Lista seja não nulo e não vazio, evitando a detecção tardiamente de valores nulos ou listas vazias.
     *
     * @param inputList       a Lista de objetos para verificar.
     * @param errorMessage    a mensagem de erro para incluir na exceção se a lista de entrada for nula ou vazia.
     * @param customException a classe de exceção RuntimeException a ser lançada.
     * @throws RuntimeException se a lista de entrada for nula ou vazia.
     */
    public static <T> void ensureListIsNotNullOrEmpty(List<T> inputList, String errorMessage, Class<? extends RuntimeException> customException) {
        if (inputList == null || inputList.isEmpty()) {
            throwException(errorMessage, customException);
        }
    }

    /**
     * Método utilitário genérico para garantir que uma lista não seja nula. Esse método recebe uma lista e retorna uma lista vazia se a lista de entrada for nula. Se a lista de entrada não for nula, a lista de entrada será retornada sem modificação.
     *
     * @param inputList a lista de entrada para verificar se é nula
     * @return a lista de entrada se não for nula, ou uma nova lista vazia se a lista de entrada for nula
     */
    public static <T> List<T> ensureListIsNotNull(List<T> inputList) {
        if (inputList == null) {
            return new ArrayList<>();
        }
        return inputList;
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
            var exception = exceptionConstructor.newInstance(message);
            log.error(message, exception);
            throw exception;
        } catch (ReflectiveOperationException e) {
            log.error(runtimeExceptionClass.getSimpleName(), e);
            throw new ValidationUtilsException(runtimeExceptionClass.getSimpleName(), e);
        }
    }

}
