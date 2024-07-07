package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.exception.ConstructorDefaultUndefinedException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
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

    public static final String SOURCE_OBJECT_NULL_ERROR_MESSAGE = "O objeto que está sendo mapeado não deve ser nulo. Verifique se o objeto foi corretamente inicializado.";
    public static final String STRATEGY_CANNOT_BE_NULL = "O objeto MapperStrategy não pode ser nulo";

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

        T mappedInstance;

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
            } catch (Exception ignored) {}
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
     * Esta é uma função auxiliar genérica para converter (mapear) um objeto de origem em um destino desejado,
     * usando uma estratégia de mapeamento fornecida. Ele fornece utilidade na redução de código duplicado ao
     * lidar com conversões de objetos em projetos. A função utiliza Generics para manter a flexibilidade,
     * permitindo que qualquer objeto seja mapeado para qualquer outro.
     * <p>
     * Esta função verifica primeiro se a estratégia fornecida e o objeto de origem não são nulos.
     * Se algum deles for nulo, a função lançará uma exceção {@link IllegalArgumentException}.
     * <p>
     * O propósito deste método é fornecer um mapeamento eficaz de objetos e garantir a validade dos dados a serem mapeados.
     *
     * @param <T>      O tipo do objeto de destino, para o qual o objeto de origem será mapeado.
     * @param <E>      O tipo do objeto de origem que será mapeado para o objeto de destino.
     * @param strategy A estratégia de mapeamento que define a lógica de como o objeto de origem deve ser mapeado para o objeto de destino.
     *                 Os detalhes sobre como implementar a estratégia são responsabilidade do desenvolvedor.
     * @param source   O objeto de origem a ser mapeado. A estrutura desse objeto depende do objeto definido durante a invocação.
     * @return Retorna um novo objeto do tipo de destino (T) com seus campos mapeados do objeto de origem.
     * @throws IllegalArgumentException será lançada se a estratégia de mapeamento ou o objeto de origem for null.
     *
     * @see MapperStrategy
     * @see ValidationUtils
     */
    public static <T, E> T mapTo(MapperStrategy<T, E> strategy, E source) {
        ValidationUtils.validateNotNullOrEmpty(strategy, STRATEGY_CANNOT_BE_NULL, IllegalArgumentException.class);
        ValidationUtils.validateNotNullOrEmpty(source, SOURCE_OBJECT_NULL_ERROR_MESSAGE, IllegalArgumentException.class);
        return strategy.mapFrom(source);
    }

}
