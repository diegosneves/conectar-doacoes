package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.exception.ConstructorDefaultUndefinedException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * A classe {@link BuilderMapper} fornece métodos para mapear os campos de um objeto fonte
 * para os campos de uma classe destino.
 *
 * @author diegoneves
 * @since 1.0.0
 */
@Slf4j
public class BuilderMapper {

    private BuilderMapper(){}

    /**
     * Mapeia os campos do objeto de origem para os campos da classe de destino.
     *
     * @param <T> o tipo da classe de destino
     * @param destinationClass a classe a ser mapeada
     * @param source o objeto de origem que será convertido no objeto de destino
     * @return uma instância da classe de destino com seus campos preenchidos
     * @throws ConstructorDefaultUndefinedException se a classe de destino não tiver um construtor padrão
     * @throws MapperFailureException se ocorrer um erro ao mapear os campos
     */
    public static <T> T mapTo(Class<T> destinationClass, Object source) throws ConstructorDefaultUndefinedException, MapperFailureException {

        var destinationFields = destinationClass.getDeclaredFields();

        T mappedInstance = null;

        try {
            mappedInstance = destinationClass.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            log.error(ConstructorDefaultUndefinedException.ERROR.formatErrorMessage(destinationClass.getName()), e);
            throw new ConstructorDefaultUndefinedException(destinationClass.getName(), e);
        } catch (Exception e) {
            log.error(MapperFailureException.ERROR.formatErrorMessage(destinationClass.getName()), e);
            throw new MapperFailureException(destinationClass.getName(), e);
        }

        for(Field field : destinationFields) {
            field.setAccessible(true);
            try {
                var sourceField = source.getClass().getDeclaredField(field.getName());
                sourceField.setAccessible(true);
                field.set(mappedInstance, sourceField.get(source));
            } catch (Exception ignored) {
            }
        }

        return mappedInstance;
    }

    /**
     * Mapeia os campos do objeto de origem para os campos da classe de destino.
     *
     * @param <T> o tipo da classe de destino
     * @param <E> o tipo do objeto de origem
     * @param destinationClass a classe a ser mapeada
     * @param source o objeto de origem a ser convertido no objeto de destino
     * @param strategy a estratégia a ser usada para construir o objeto de destino (opcional)
     * @return uma instância da classe destino com seus campos preenchidos
     */
    public static <T, E> T mapTo(Class<T> destinationClass, E source, MapperStrategy<T, E> strategy) {
		if (strategy == null) {
			return BuilderMapper.mapTo(destinationClass, source);
		}

		return strategy.mapFrom(source);
	}

}
