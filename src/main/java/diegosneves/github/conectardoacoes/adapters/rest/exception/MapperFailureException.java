package diegosneves.github.conectardoacoes.adapters.rest.exception;


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
public class MapperFailureException extends CustomException {

    /**
     * Esta é uma classe de exceção personalizada usada para gerenciar e fornecer mensagens mais
     * informativas quando uma falha ocorre durante o mapeamento.
     *
     * @param message A mensagem específica da exceção.
     * @param e       A exceção generica causada durante o mapeamento.
     */
    public MapperFailureException(Integer term, String message, Throwable e) {
        super(message, e, obtainExceptionDetails(term));
    }

}
