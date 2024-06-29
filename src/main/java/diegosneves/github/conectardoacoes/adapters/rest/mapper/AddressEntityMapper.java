package diegosneves.github.conectardoacoes.adapters.rest.mapper;


import diegosneves.github.conectardoacoes.adapters.rest.exception.AddressEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.model.AddressEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;

/**
 * Implementação da interface {@link MapperStrategy} para mapear um objeto {@link Address} para sua correspondente entidade {@link AddressEntity}.
 * Esta classe é utilizada para converter um objeto {@link Address}, que representa um endereço no sistema, em um objeto {@link AddressEntity}
 * que pode ser armazenado no banco de dados na tabela "address".
 * <p>
 * Enquanto essa classe é específica para o mapeamento de objetos de endereço, ela implementa a interface {@link MapperStrategy},
 * permitindo que seja usada em um contexto mais amplo de mapeamento de objetos.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public class AddressEntityMapper implements MapperStrategy<AddressEntity, Address> {

    /**
     * O {@code Class} do objeto de endereço, usado para formatar as mensagens de erro.
     */
    public static final Class<Address> ADDRESS_CLASS = Address.class;

    /**
     * Implementa o mapeamento de um objeto {@link Address} para um objeto {@link AddressEntity}.
     * Inicialmente valida o objeto de origem não é nulo e por fim emprega um {@link BuilderMapper} para realizar a operação de mapeamento.
     *
     * @param source O objeto de origem que será convertido em um objeto de destino.
     * @return Uma nova instância do {@link AddressEntity} com valores mapeados do objeto {@link Address}.
     * @throws AddressEntityFailuresException Se a origem estiver nula ou vazia.
     */
    @Override
    public AddressEntity mapFrom(Address source) {
        ValidationUtils.validateNotNullOrEmpty(source, MapperFailureException.ERROR.formatErrorMessage(ADDRESS_CLASS.getSimpleName()), AddressEntityFailuresException.class);
        return BuilderMapper.mapTo(AddressEntity.class, source);
    }

}
