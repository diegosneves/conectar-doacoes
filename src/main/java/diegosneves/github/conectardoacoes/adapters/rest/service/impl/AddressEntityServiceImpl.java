package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.AddressEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.AddressEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.AddressEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.AddressRepository;
import diegosneves.github.conectardoacoes.adapters.rest.service.AddressEntityService;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.factory.AddressFactory;
import diegosneves.github.conectardoacoes.core.exception.AddressCreationFailureException;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import org.springframework.stereotype.Service;

/**
 * A classe {@link AddressEntityServiceImpl} é responsável por fornecer serviços relacionados ao endereço.
 * Isso compreende a criação e armazenamento de um objeto endereço.
 * Para o armazenamento é utilizado um repositório de endereços injetado no serviço como dependência.
 * <p>
 * Ao iniciar, o serviço valida se o objeto endereço não é nulo nem vazio.
 * Em caso de falha na validação, é lançada uma exceção {@link AddressEntityFailuresException} com a mensagem específica.
 * <p>
 * Depois, é criada uma nova instância de {@link Address} usando {@link AddressFactory}, que pode gerar exceções durante a criação,
 * as quais são capturadas e tratadas, emitindo novas {@link AddressEntityFailuresException} em caso de erros.
 * <p>
 * Por fim, o serviço faz uso do objeto {@link Address} criado para armazená-lo no repositório correspondente,
 * e retorna o objeto armazenado.
 * <p>
 * A classe oferece dois métodos principais:</p>
 * <ol>
 *     <li><code>createAndSaveAddressFromDto</code> que cria um endereço a partir de um DTO e o salva no repositório.</li>
 *     <li><code>mapAddressAndSaveToRepository</code> que trata do mapeamento do objeto endereço para a entidade correspondente e posterior armazenamento no repositório.</li>
 * </ol>
 * <p>
 * Implementa a interface {@link AddressEntityService}.
 *
 * @author diegoneves
 * @since 1.1.0
 */
@Service
public class AddressEntityServiceImpl implements AddressEntityService {

    public static final String ADDRESS_CREATION_ERROR = "Erro na criação do endereço. Confirme se todos os campos do endereço estão corretos e tente novamente.";
    public static final String ERROR_MAPPING_ADDRESS = "Erro durante o mapeamento do endereço para persistência";


    private final AddressRepository repository;

    public AddressEntityServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address createAndSaveAddressFromDto(AddressDTO address) throws AddressEntityFailuresException {
        ValidationUtils.validateNotNullOrEmpty(address, ADDRESS_CREATION_ERROR, AddressEntityFailuresException.class);
        Address newAddress;
        try {
            newAddress = AddressFactory.create(address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState(), address.getZip());
        } catch (AddressCreationFailureException e) {
            throw new AddressEntityFailuresException(ADDRESS_CREATION_ERROR, e);
        }
        this.mapAddressAndSaveToRepository(newAddress);
        return newAddress;
    }

    /**
     * Este método é responsável por mapear um objeto de endereço para a entidade relevante e salvá-lo no repositório.
     * Usa o {@link BuilderMapper} para mapear o endereço, depois salva no repositório.
     *
     * @param address - Um objeto de endereço que precisa ser mapeado e salvo.
     * @throws AddressEntityFailuresException - Se ocorrer uma exceção durante o mapeamento, será lançada uma {@link AddressEntityFailuresException}.
     *                                        A exceção original será anexada como causa.
     */
    private void mapAddressAndSaveToRepository(Address address) throws AddressEntityFailuresException {
        AddressEntity addressEntity;
        try {
            addressEntity = BuilderMapper.mapTo(new AddressEntityMapper(), address);
        } catch (RuntimeException e) {
            throw new AddressEntityFailuresException(ERROR_MAPPING_ADDRESS, e);
        }
        this.repository.save(addressEntity);
    }

}
