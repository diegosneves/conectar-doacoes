package diegosneves.github.conectardoacoes.core.utils;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
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

    public static final String CUSTOM_EXCEPTION_ERROR = "CustomException. Uma exceção personalizada é necessária.";

    private ValidationUtils() {
    }

    public static <T> void validateNotNullOrEmpty(T input, Integer term, Class<? extends RuntimeException> customException) {
        if (input == null || (input instanceof String string && string.trim().isEmpty())) {
            throwException(term, customException);
        }
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
     * Este método genérico garante que o objeto de entrada (um parâmetro genérico) não seja nulo ou vazio.
     * Se o objeto for uma instância de String, ele verifica se a string, depois de ser aparada, ainda é vazia.
     * Se o objeto for nulo ou uma instância de String que é vazia após o trim, ele lança uma exceção.
     *
     * @param input           - O objeto genérico que será verificado quanto à nulidade ou vazio. Isso pode ser qualquer tipo de objeto.
     * @param term            - É um termo numérico que será usado na função de manipulação de exceção. Sua aplicação específica será determinada pela função que vai lidar com a exceção.
     * @param errorMessage    - Esta é uma mensagem de erro personalizada que será passada para a função de manipulação de exceção. Isso permite que os usuários determinem a mensagem específica que será exibida quando a exceção for acionada.
     * @param customException - A classe da exceção personalizada que será lançada se o objeto de entrada (input) for nulo ou se for uma instância da String que é vazia após ser cortada. Isso permite que o tipo exato de exceção que será lançada possa ser determinado em tempo de execução.
     * @throws RuntimeException     - Se o objeto 'input' verificado é nulo ou uma instância de uma String que é vazia quando cortada, este método irá lançar uma instância da subclasse de RuntimeException especificada pelo parâmetro 'customException'.
     * @throws NullPointerException - Se o parâmetro 'customException' for nulo, a implementação desta função provavelmente lançará um NullPointerException.
     */
    public static <T> void validateNotNullOrEmpty(T input, Integer term, String errorMessage, Class<? extends RuntimeException> customException) {
        if (input == null || (input instanceof String string && string.trim().isEmpty())) {
            throwException(term, errorMessage, customException);
        }
    }

    /**
     * Garante que a lista fornecida como parâmetro não seja nula nem vazia.
     * <p>
     * Este método verifica se a lista inputList fornecida é nula ou vazia. Se a lista for nula ou vazia,
     * ele lança uma exceção personalizada do tipo fornecido. Além disso, valida se a exceção personalizada
     * fornecida é instanciável e lança uma IllegalArgumentException se não for.
     * </p>
     *
     * @param inputList       A lista que deve ser verificada para nulidade e vazio.
     * @param term            Um termo ou parâmetro arbitrário que será usado durante a criação e lançamento de exceções.
     * @param customException A classe da exceção personalizada a ser lançada se a lista estiver nula ou vazia.
     * @throws IllegalArgumentException Se a classe customException não for uma RuntimeException instanciável.
     */
    public static <T> void ensureListIsNotNullOrEmpty(List<T> inputList, Integer term, Class<? extends RuntimeException> customException) {
        validateCustomException(customException);
        if (inputList == null || inputList.isEmpty()) {
            throwException(term, customException);
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
        validateCustomException(customException);
        if (inputList == null || inputList.isEmpty()) {
            throwException(errorMessage, customException);
        }
    }

    /**
     * Valida se a lista fornecida está vazia ou é {@code null}.
     * Se a lista é nula ou vazia, lança uma exceção definida pelo usuário com uma mensagem de erro e termo especificado.
     *
     * @param <T>             O tipo genérico da lista.
     * @param inputList       A lista a ser verificada. Pode ser de qualquer tipo {@code <T>}.
     * @param term            Um número inteiro que pode ser usado para representar um termo de erro ou código de erro específico.
     * @param errorMessage    A mensagem de erro que deve ser incluída na exceção lançada caso a lista seja nula ou vazia.
     * @param customException A classe de exceção definida pelo usuário a ser lançada caso a lista seja nula ou vazia.
     *                        Deve estender {@code RuntimeException}.
     * @throws ValidationUtilsException O método primeiro valida a instância {@code customException} para garantir que seja válida e instanciável.
     *                                  Em seguida, verifica se a {@code inputList} é nula ou vazia. Se for uma dessas, a exceção {@code customException} é lançada
     *                                  com o {@code term} e a {@code errorMessage} fornecidos
     */
    public static <T> void ensureListIsNotNullOrEmpty(List<T> inputList, Integer term, String errorMessage, Class<? extends RuntimeException> customException) {
        validateCustomException(customException);
        if (inputList == null || inputList.isEmpty()) {
            throwException(term, errorMessage, customException);
        }
    }

    /**
     * Valida se uma {@code customException} especificada não é nula.
     * <p>
     * O método {@code validateCustomException} é um método estático e privado que recebe como parâmetro um objeto do tipo Class que
     * é um subtipo de {@link RuntimeException}. O método então verifica se o parâmetro {@code customException} é nulo e, em caso afirmativo,
     * lança uma {@link ValidationUtilsException} com uma mensagem de erro personalizada.
     *
     * @param customException objeto da subclasse de {@link RuntimeException} para ser validado
     * @throws ValidationUtilsException se o {@code customException} for nulo
     */
    private static void validateCustomException(Class<? extends RuntimeException> customException) {
        if (customException == null) {
            throw new ValidationUtilsException(CUSTOM_EXCEPTION_ERROR);
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

    private static void throwException(Integer term, Class<? extends RuntimeException> runtimeExceptionClass) {
        try {
            Constructor<? extends RuntimeException> exceptionConstructor = runtimeExceptionClass.getConstructor(Integer.class);
            var exception = exceptionConstructor.newInstance(term);
            log.error(ExceptionDetails.getExceptionDetails(term).formatErrorMessage(), exception);
            throw exception;
        } catch (ReflectiveOperationException e) {
            log.error(runtimeExceptionClass.getSimpleName(), e);
            throw new ValidationUtilsException(runtimeExceptionClass.getSimpleName(), e);
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
            var exception = exceptionConstructor.newInstance(message);
            log.error(message, exception);
            throw exception;
        } catch (ReflectiveOperationException e) {
            log.error(runtimeExceptionClass.getSimpleName(), e);
            throw new ValidationUtilsException(runtimeExceptionClass.getSimpleName(), e);
        }
    }

    /**
     * Método que cria e lança exceções do tipo {@link RuntimeException} ou suas subclasses de acordo com os parâmetros fornecidos.
     * Além disso, essa função gerencia a possibilidade de uma {@link ReflectiveOperationException} decorrente da tentativa de instanciar a exceção solicitada.
     * Nesse caso, a função criará e lançará uma instância de {@link ValidationUtilsException} com a mensagem da {@link ReflectiveOperationException} original.
     *
     * @param term                  um Integer que é usado como parâmetro para a função de construtor da {@link RuntimeException} desejada.
     * @param message               uma mensagem de String que é passada para a função de construtor da {@link RuntimeException} desejada
     *                              e usada para geração de logs de erro.
     * @param runtimeExceptionClass a classe de exceção Class<? extends RuntimeException> que é desejada para ser lançada.
     *
     *                              <p>Uso:</p>
     *                              <code>throwException(100, "Valor inválido", IllegalArgumentException.class);</code>
     * @throws ValidationUtilsException é lançado quando ocorre um {@code ReflectiveOperationException} na tentativa de construir a exceção desejada.
     *                                  A {@code ValidationUtilsException} recebe o nome simples da classe de exceção desejada e a {@code ReflectiveOperationException} original.
     */
    private static void throwException(Integer term, String message, Class<? extends RuntimeException> runtimeExceptionClass) {
        try {
            Constructor<? extends RuntimeException> exceptionConstructor = runtimeExceptionClass.getConstructor(Integer.class, String.class);
            var exception = exceptionConstructor.newInstance(term, message);
            log.error(message, exception);
            throw exception;
        } catch (ReflectiveOperationException e) {
            log.error(runtimeExceptionClass.getSimpleName(), e);
            throw new ValidationUtilsException(runtimeExceptionClass.getSimpleName(), e);
        }
    }

}
