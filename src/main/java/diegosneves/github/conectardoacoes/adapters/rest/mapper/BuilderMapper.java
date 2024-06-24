package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.exception.ConstructorDefaultUndefinedException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
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

    private BuilderMapper() {
    }

    /**
     * Mapeia os campos do objeto de origem para os campos da classe de destino.
     *
     * @param <T>              o tipo da classe de destino
     * @param destinationClass a classe a ser mapeada
     * @param source           o objeto de origem que será convertido no objeto de destino
     * @return uma instância da classe de destino com seus campos preenchidos
     * @throws ConstructorDefaultUndefinedException se a classe de destino não tiver um construtor padrão
     * @throws MapperFailureException               se ocorrer um erro ao mapear os campos
     */
    public static <T> T mapTo(Class<T> destinationClass, Object source) throws ConstructorDefaultUndefinedException, MapperFailureException {

        var destinationFields = destinationClass.getDeclaredFields();

        T mappedInstance = null;

        try {
            Constructor<?>[] constructors = destinationClass.getConstructors();
            Constructor<?> defaultConstructor = null;
            for (Constructor<?> constructor : constructors) {
                if (constructor.getParameterCount() == 0) {
                    defaultConstructor = constructor;
                    break;
                }
            }
            if (defaultConstructor != null) {
                mappedInstance = (T) defaultConstructor.newInstance();
            } else if (constructors.length > 0) {
                Constructor<?> nextConstructor = constructors[0];

                var params = nextConstructor.getParameters();
                Object[] paramValues = new Object[params.length];

                for (int i = 0; i < params.length; i++) {
                    Field field = findFieldWithName(source, params[i].getName(), params[i].getType());
                    if (field == null) {
                        continue;
                    }
                    field.setAccessible(true);
                    paramValues[i] = field.get(source);
                }
                mappedInstance = (T) nextConstructor.newInstance(paramValues);

            } else {
                log.error(ConstructorDefaultUndefinedException.ERROR.formatErrorMessage(destinationClass.getName()));
                throw new ConstructorDefaultUndefinedException(destinationClass.getName());
            }

        } catch (Exception e) {
            log.error(MapperFailureException.ERROR.formatErrorMessage(destinationClass.getName()), e);
            throw new MapperFailureException(destinationClass.getName(), e);
        }

        for (Field field : destinationFields) {
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
     * Método utilitário privado para encontrar e retornar um objeto {@link Field} de nome específico de uma instância de objeto fornecida (source).
     * O objeto {@link Field} retornado é aquele cujo nome corresponde ao parâmetro de nome fornecido e é do tipo atribuível ao parâmetro de tipo fornecido (paramType).
     *
     * @param source    A instância de objeto cujo campo está sendo procurado.
     * @param name      O nome do campo que está sendo procurado.
     * @param paramType O tipo de classe do campo que está sendo procurado.
     * @return Retorna um objeto Field se um campo correspondente for encontrado. Retorna null se nenhum campo correspondente for encontrado.
     */
    private static Field findFieldWithName(Object source, String name, Class<?> paramType) {
        for (Field field : source.getClass().getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(name)) {
                if (!field.getType().isAssignableFrom(paramType)) {
                    continue;
                }
                return field;
            }
        }
        return null;
    }

    /**
     * Mapeia os campos do objeto de origem para os campos da classe de destino.
     *
     * @param <T>              o tipo da classe de destino
     * @param <E>              o tipo do objeto de origem
     * @param destinationClass a classe a ser mapeada
     * @param source           o objeto de origem a ser convertido no objeto de destino
     * @param strategy         a estratégia a ser usada para construir o objeto de destino (opcional)
     * @return uma instância da classe destino com seus campos preenchidos
     */
    public static <T, E> T mapTo(Class<T> destinationClass, E source, MapperStrategy<T, E> strategy) {
        if (strategy == null) {
            return BuilderMapper.mapTo(destinationClass, source);
        }
        return strategy.mapFrom(source);
    }

}
