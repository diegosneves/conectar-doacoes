package diegosneves.github.conectardoacoes.adapters.rest.mapper;

/**
 * A interface {@link MapperStrategy} define uma estratégia para executar operações de mapeamento de objetos.
 *
 * @author diegosneves
 * @since 1.0.0
 *
 * @param <T> o tipo da classe de destino
 * @param <E> o tipo do objeto de origem
 */
public interface MapperStrategy<T, E> {

    /**
     * Executa a estratégia para realizar uma operação de mapeamento entre objetos.
     *
     * @param source o objeto de source que será convertido no objeto de destino
     * @return uma instância da classe de destino com seus campos preenchidos
     */
    T mapFrom(E source);

}
