package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import diegosneves.github.conectardoacoes.adapters.rest.dto.UserEntityDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.AddressEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.ShelterEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.AddressEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.AddressRepository;
import diegosneves.github.conectardoacoes.adapters.rest.repository.DonationRepository;
import diegosneves.github.conectardoacoes.adapters.rest.repository.ShelterRepository;
import diegosneves.github.conectardoacoes.adapters.rest.request.ShelterCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.ShelterCreatedResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.ShelterEntityService;
import diegosneves.github.conectardoacoes.adapters.rest.service.UserEntityService;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.ShelterContract;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.factory.AddressFactory;
import diegosneves.github.conectardoacoes.core.domain.shelter.factory.ShelterFactory;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.exception.AddressCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelterEntityServiceImpl implements ShelterEntityService {

    public static final String SHELTER_CREATION_ERROR_MESSAGE = "Erro na criação do Abrigo. Confirme se todos os campos do Abrigo estão corretos e tente novamente.";
    public static final String ADDRESS_CREATION_ERROR = "Erro na criação do endereço. Confirme se todos os campos do endereço estão corretos e tente novamente.";
    public static final String ERROR_MAPPING_ADDRESS = "Erro durante o mapeamento do endereço para persistência";
    public static final String USER_NOT_FOUND = "Usuário não encontrado";


    private final ShelterRepository repository;
    private final DonationRepository donationRepository;
    private final AddressRepository addressRepository;
    private final UserEntityService userEntityService;

    @Autowired
    public ShelterEntityServiceImpl(ShelterRepository repository, DonationRepository donationRepository, AddressRepository addressRepository, UserEntityService userEntityService) {
        this.repository = repository;
        this.donationRepository = donationRepository;
        this.addressRepository = addressRepository;
        this.userEntityService = userEntityService;
    }

    @Override
    public ShelterCreatedResponse createShelter(ShelterCreationRequest request) {
        ShelterContract shelterContract = this.createAndReturnShelterInstance(request);
        ShelterEntity shelterEntity = this.mapShelterAndSaveToRepository(shelterContract);
        return constructShelterCreatedResponse(shelterEntity);
    }

    private static ShelterCreatedResponse constructShelterCreatedResponse(ShelterEntity shelterEntity) {
        return ShelterCreatedResponse.builder()
                .id(shelterEntity.getId())
                .shelterName(shelterEntity.getShelterName())
                .address(BuilderMapper.mapTo(AddressDTO.class, shelterEntity.getAddress()))
                .responsibleUser(BuilderMapper.mapTo(UserEntityDTO.class, shelterEntity.getResponsibleUser()))
                .build();
    }

    private Shelter createAndReturnShelterInstance(ShelterCreationRequest request) throws ShelterEntityFailuresException {
        UserContract userContract = this.findUserByResponsibleEmail(request.getResponsibleUserEmail());
        Address address = this.createAndSaveAddressFromDto(request.getAddress());
        Shelter newShelter;
        try {
            newShelter = ShelterFactory.create(request.getShelterName(), address, userContract);
        } catch (ShelterCreationFailureException e) {
            throw new ShelterEntityFailuresException(SHELTER_CREATION_ERROR_MESSAGE, e);
        }
        return newShelter;
    }

    private UserContract findUserByResponsibleEmail(String responsibleUserEmail) throws ShelterEntityFailuresException {
        UserContract foundUser;
        try {
            foundUser = this.userEntityService.searchUserByEmail(responsibleUserEmail);
        } catch (UserEntityFailuresException e) {
            throw new ShelterEntityFailuresException(USER_NOT_FOUND, e);
        }
        return foundUser;
    }

    private Address createAndSaveAddressFromDto(AddressDTO address) throws ShelterEntityFailuresException {
        ValidationUtils.validateNotNullOrEmpty(address, ADDRESS_CREATION_ERROR, ShelterEntityFailuresException.class);
        Address newAddress;
        try {
            newAddress = AddressFactory.create(address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState(), address.getZip());
        } catch (AddressCreationFailureException e) {
            throw new ShelterEntityFailuresException(ADDRESS_CREATION_ERROR, e);
        }
        this.mapAddressAndSaveToRepository(newAddress);
        return newAddress;
    }

    private void mapAddressAndSaveToRepository(Address address) throws ShelterEntityFailuresException {
        AddressEntity addressEntity;
        try {
            addressEntity = BuilderMapper.mapTo(new AddressEntityMapper(), address);
        } catch (RuntimeException e) {
            throw new ShelterEntityFailuresException(ERROR_MAPPING_ADDRESS, e);
        }
        this.addressRepository.save(addressEntity);
    }

    private ShelterEntity mapShelterAndSaveToRepository(ShelterContract shelterContract) {
        ShelterEntity newShelterEntity;
        try {
            ShelterContract savedContract = this.repository.persist(shelterContract);
            newShelterEntity = BuilderMapper.mapTo(new ShelterEntityMapper(), savedContract);
        } catch (RuntimeException e) {
            throw new ShelterEntityFailuresException(SHELTER_CREATION_ERROR_MESSAGE, e);
        }
        return newShelterEntity;
    }

}
