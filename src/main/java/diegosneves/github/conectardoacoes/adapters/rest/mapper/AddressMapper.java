package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.exception.AddressEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.AddressEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.exception.AddressCreationFailureException;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * A classe {@link AddressMapper} implementa a interface de estratégia de Mapeamento {@link MapperStrategy}
 * para converter objetos {@link AddressEntity} em objetos {@link Address}.
 * <p>
 * Esta classe é responsável por mapear uma entidade {@link AddressEntity} que representa um registro de endereço
 * no banco de dados para um objeto {@link Address} usado no nível de aplicativo.
 * <p>
 * Esta classe também valida os dados da {@link AddressEntity} e lança uma exceção
 * {@link AddressEntityFailuresException} caso seja fornecida uma {@link AddressEntity} inválida.
 * <p>
 * Contém uma mensgem erro predefinida para entidades de endereço inválidas: "Deve ser fornecida uma entidade de Endereço que seja válida."
 *
 * @author diegoneves
 * @since 1.0.0
 */
@Slf4j
public class AddressMapper implements MapperStrategy<Address, AddressEntity> {


    public static final Class<AddressEntity> ADDRESS_ENTITY_TYPE = AddressEntity.class;
    public static final String MAPPING_ERROR_LOG = "Ocorreu um erro durante o processo de mapeamento do objeto AddressEntity para Address. Detalhes do erro: {}";

    /**
     * Realiza a conversão de um objeto {@link AddressEntity} para um objeto {@link Address}.
     * <p>
     * Este método utiliza a biblioteca de utilidades de validação para verificar a entidade de endereço.
     * <p>
     * Em caso de falha na criação da instância de {@link Address}, um {@link AddressCreationFailureException} é lançado
     * e a exceção é reenviada pela {@link AddressEntityFailuresException} para sinalizar o erro de mapeamento.
     *
     * @param source O objeto {@link AddressEntity} fornecido que deve ser mapeado para um objeto {@link Address}.
     * @return Retorna um novo objeto {@link Address} com todos os campos preenchidos com dados oriundos do {@link AddressEntity}.
     * @throws AddressEntityFailuresException Se a entidade do endereço fornecida for nula ou inválida.
     */
    @Override
    public Address mapFrom(AddressEntity source) {
        ValidationUtils.validateNotNullOrEmpty(source, CLASS_MAPPING_FAILURE, ADDRESS_ENTITY_TYPE.getSimpleName(), AddressEntityFailuresException.class);
        Address address;
        try {
            address = new Address(source.getId(), source.getStreet(), source.getNumber(), source.getNeighborhood(), source.getCity(), source.getState(), source.getZip());
        } catch (AddressCreationFailureException e) {
            log.error(MAPPING_ERROR_LOG, e.getMessage(), e);
            throw new AddressEntityFailuresException(CLASS_MAPPING_FAILURE, ADDRESS_ENTITY_TYPE.getSimpleName(), e);
        }
        return address;
    }
}
