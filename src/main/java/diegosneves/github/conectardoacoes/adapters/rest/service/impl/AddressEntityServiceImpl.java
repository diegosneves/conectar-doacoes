package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.adapter.RetrieveAddressAdapter;
import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressApiResponseDTO;
import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.AddressEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.AddressEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.AddressEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.AddressRepository;
import diegosneves.github.conectardoacoes.adapters.rest.response.AddressApiResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.AddressEntityService;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.exception.AddressCreationFailureException;
import diegosneves.github.conectardoacoes.core.service.AddressService;
import diegosneves.github.conectardoacoes.core.service.AddressServiceContract;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * A classe {@link AddressEntityServiceImpl} é responsável por fornecer serviços relacionados ao endereço.
 * Isso compreende a criação e armazenamento de um objeto endereço.
 * Para o armazenamento é utilizado um repositório de endereços injetado no serviço como dependência.
 * <p>
 * Ao iniciar, o serviço valida se o objeto endereço não é nulo nem vazio.
 * Em caso de falha na validação, é lançada uma exceção {@link AddressEntityFailuresException} com a mensagem específica.
 * <p>
 * Depois, é criada uma nova instância de {@link Address} usando {@link AddressServiceContract}, que pode gerar exceções durante a criação,
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
@Slf4j
public class AddressEntityServiceImpl implements AddressEntityService {

    public static final Integer ADDRESS_CREATION_ERROR = 23;
    public static final Integer ERROR_MAPPING_ADDRESS = 25;

    public static final String CREATION_FAILURE_LOG = "Falha na tentativa de criação de um novo Endereço. Causa do erro: {}";
    public static final String ADDRESS_MAPPING_ERROR_LOG = "Ocorreu uma falha ao tentar mapear o Endereço. Causa do erro: {}";
    public static final String ADDRESS_REGISTRATION_SUCCESS_LOG = "Novo endereço registrado com êxito! Identificador do endereço (ID): {} - Código Postal (ZIPCODE): {}";
    public static final int ZIPCODE_INVALID_FAILURE = 37;


    private final AddressRepository repository;
    private final AddressServiceContract addressServiceContract;
    private final RetrieveAddressAdapter addressAdapter;

    public AddressEntityServiceImpl(AddressRepository repository, RetrieveAddressAdapter addressAdapter) {
        this.repository = repository;
        this.addressAdapter = addressAdapter;
        this.addressServiceContract = new AddressService();
    }

    @Override
    public Address createAndSaveAddressFromDto(AddressDTO address) throws AddressEntityFailuresException {
        ValidationUtils.validateNotNullOrEmpty(address, ADDRESS_CREATION_ERROR, AddressEntityFailuresException.class);
        Address newAddress;
        try {
            newAddress = this.addressServiceContract.createAddress(address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState(), address.getZip());
        } catch (AddressCreationFailureException e) {
            log.error(CREATION_FAILURE_LOG, e.getMessage(), e);
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
            log.error(ADDRESS_MAPPING_ERROR_LOG, e.getMessage(), e);
            throw new AddressEntityFailuresException(ERROR_MAPPING_ADDRESS, e);
        }
        this.repository.save(addressEntity);
        log.info(ADDRESS_REGISTRATION_SUCCESS_LOG, address.getId(), address.getZip());
    }

    @Override
    public AddressApiResponseDTO restrieveAddress(String zipcode) {
        ValidationUtils.validateNotNullOrEmpty(zipcode, ZIPCODE_INVALID_FAILURE, AddressEntityFailuresException.class);
        AddressApiResponse addressApiResponse = this.addressAdapter.retrieveAddress(zipcode);
        return addressApiResponse.convertToDTO();
    }
}
