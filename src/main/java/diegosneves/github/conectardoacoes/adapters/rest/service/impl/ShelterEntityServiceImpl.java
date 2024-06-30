package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import diegosneves.github.conectardoacoes.adapters.rest.dto.UserEntityDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelterEntityServiceImpl implements ShelterEntityService {

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
        UserContract userContract = this.userEntityService.searchUserByEmail(request.getResponsibleUserEmail());
        Address address = createAddress(request.getAddress());
        AddressEntity addressEntity = new AddressEntityMapper().mapFrom(address);
        this.addressRepository.save(addressEntity);
        Shelter shelterContract = ShelterFactory.create(request.getShelterName(), address, userContract);
        ShelterEntity shelterEntity = new ShelterEntityMapper().mapFrom((Shelter) this.repository.persist(shelterContract));
        return ShelterCreatedResponse.builder()
                .id(shelterEntity.getId())
                .shelterName(shelterEntity.getShelterName())
                .address(BuilderMapper.mapTo(AddressDTO.class, shelterEntity.getAddress()))
                .responsibleUser(BuilderMapper.mapTo(UserEntityDTO.class, shelterEntity.getResponsibleUser()))
                .build();
    }

    private Address createAddress(AddressDTO address) {
        return AddressFactory.create(address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState(), address.getZip());
    }

}
