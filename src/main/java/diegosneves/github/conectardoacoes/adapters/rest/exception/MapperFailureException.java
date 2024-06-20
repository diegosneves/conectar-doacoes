package diegosneves.github.conectardoacoes.adapters.rest.exception;


import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;

/**
 * {@link MapperFailureException} é uma classe de exceção personalizada. Ela tem como finalidade gerenciar
 * falhas ocorridas durante o processo de mapeamento, e proporcionar mensagens de erro mais detalhadas.
 * Herdando de {@link RuntimeException}, ela representa exceções em tempo de execução que podem ser lançadas
 * durante a operação normal da JVM.
 *
 * @see RuntimeException
 * @author diegoneves
 * @since 1.0.0
 */
public class MapperFailureException extends RuntimeException {

    public static final ExceptionDetails ERROR = ExceptionDetails.CLASS_MAPPING_FAILURE;

    /**
     * Esta é uma classe de exceção personalizada usada para gerenciar e fornecer mensagens mais
     * informativas quando uma falha ocorre durante o mapeamento.
     *
     * @param message A mensagem específica da exceção.
     * @param e       A exceção generica causada durante o mapeamento.
     */
    public MapperFailureException(String message, Throwable e) {
        super(ERROR.formatErrorMessage(message), e);
    }

}
