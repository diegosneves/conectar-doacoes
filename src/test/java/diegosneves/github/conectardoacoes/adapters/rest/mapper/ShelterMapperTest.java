package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.AddressEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.AddressEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.ShelterContract;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShelterMapperTest {

    private ShelterMapper shelterMapper;

    private ShelterEntity shelterEntity;
    private AddressEntity addressEntity;
    private UserEntity userEntity;
    private DonationEntity donationEntity;

    @BeforeEach
    void setUp() {
        this.shelterMapper = new ShelterMapper();

        this.addressEntity = AddressEntity.builder()
                .id("0b6a4b56-9a6a-49d4-aa4b-569a6a09d49d")
                .street("Rua")
                .number("256")
                .neighborhood("Bairro")
                .city("Cidade")
                .state("Estado")
                .zip("97110220")
                .build();

        this.userEntity = UserEntity.builder()
                .id("60306bda-1ef2-4228-b06b-da1ef26228b1")
                .userName("Usuario")
                .email("email@teste.com")
                .userProfile(UserProfileType.BENEFICIARY)
                .userPassword("Senha")
                .build();

        this.donationEntity = DonationEntity.builder()
                .id("e9bca351-bdbe-4e46-bca3-51bdbe4e464f")
                .description("Descricao")
                .amount(10)
                .build();

        this.shelterEntity = ShelterEntity.builder()
                .id("5515cf68-f172-4b6f-95cf-68f1725b6f9c")
                .shelterName("Shelter Entity")
                .address(this.addressEntity)
                .responsibleUser(this.userEntity)
                .donations(List.of(this.donationEntity))
                .build();

    }

    @Test
    void shouldMapShelterEntityToShelter() {

        ShelterContract shelter = this.shelterMapper.mapFrom(this.shelterEntity);

        assertNotNull(shelter);
        assertInstanceOf(Shelter.class, shelter);
        assertEquals(this.shelterEntity.getShelterName(), shelter.getShelterName());
        assertEquals(this.shelterEntity.getId(), shelter.getId());
        assertNotNull(shelter.getAddress());
        assertEquals(this.addressEntity.getStreet(), shelter.getAddress().getStreet());
        assertEquals(this.addressEntity.getNumber(), shelter.getAddress().getNumber());
        assertEquals(this.addressEntity.getNeighborhood(), shelter.getAddress().getNeighborhood());
        assertEquals(this.addressEntity.getCity(), shelter.getAddress().getCity());
        assertEquals(this.addressEntity.getState(), shelter.getAddress().getState());
        assertEquals(this.addressEntity.getZip(), shelter.getAddress().getZip());
        assertNotNull(shelter.getUser());
        assertEquals(this.userEntity.getId(), shelter.getUser().getId());
        assertEquals(this.userEntity.getUserName(), shelter.getUser().getUsername());
        assertEquals(this.userEntity.getEmail(), shelter.getUser().getEmail());
        assertEquals(this.userEntity.getUserPassword(), shelter.getUser().getUserPassword());
        assertNotNull(shelter.getUser().getUserProfile());
        assertEquals(UserProfile.BENEFICIARY, shelter.getUser().getUserProfile());
        assertNotNull(shelter.getDonations());
        assertFalse(shelter.getDonations().isEmpty());
        assertEquals(this.shelterEntity.getDonations().size(), shelter.getDonations().size());
        assertEquals(this.donationEntity.getDescription(), shelter.getDonations().get(0).getDescription());
        assertEquals(this.donationEntity.getAmount(), shelter.getDonations().get(0).getAmount());
    }

    @Test
    void shouldCorrectlyMapShelterEntityToShelter() {
        this.shelterEntity.setDonations(new ArrayList<>());

        ShelterContract shelter = this.shelterMapper.mapFrom(this.shelterEntity);

        assertNotNull(shelter);
        assertInstanceOf(Shelter.class, shelter);
        assertEquals(this.shelterEntity.getShelterName(), shelter.getShelterName());
        assertEquals(this.shelterEntity.getId(), shelter.getId());
        assertNotNull(shelter.getAddress());
        assertEquals(this.addressEntity.getStreet(), shelter.getAddress().getStreet());
        assertEquals(this.addressEntity.getNumber(), shelter.getAddress().getNumber());
        assertEquals(this.addressEntity.getNeighborhood(), shelter.getAddress().getNeighborhood());
        assertEquals(this.addressEntity.getCity(), shelter.getAddress().getCity());
        assertEquals(this.addressEntity.getState(), shelter.getAddress().getState());
        assertEquals(this.addressEntity.getZip(), shelter.getAddress().getZip());
        assertNotNull(shelter.getUser());
        assertEquals(this.userEntity.getId(), shelter.getUser().getId());
        assertEquals(this.userEntity.getUserName(), shelter.getUser().getUsername());
        assertEquals(this.userEntity.getEmail(), shelter.getUser().getEmail());
        assertEquals(this.userEntity.getUserPassword(), shelter.getUser().getUserPassword());
        assertNotNull(shelter.getUser().getUserProfile());
        assertEquals(UserProfile.BENEFICIARY, shelter.getUser().getUserProfile());
        assertNotNull(shelter.getDonations());
        assertTrue(shelter.getDonations().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenShelterEntityIdIsEmpty() {
        this.shelterEntity.setId("");

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class,
                () -> this.shelterMapper.mapFrom(this.shelterEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(this.shelterEntity.getClass().getSimpleName())), exception.getMessage());
        assertEquals(ShelterCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenShelterEntityIdIsNull() {
        this.shelterEntity.setId(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class,
                () -> this.shelterMapper.mapFrom(this.shelterEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(this.shelterEntity.getClass().getSimpleName())), exception.getMessage());
        assertEquals(ShelterCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenShelterEntityIdIsInvalid() {
        this.shelterEntity.setId("idInvalida");

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class,
                () -> this.shelterMapper.mapFrom(this.shelterEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(this.shelterEntity.getClass().getSimpleName())), exception.getMessage());
        assertEquals(ShelterCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenShelterEntityNameIsEmpty() {
        this.shelterEntity.setShelterName("  ");

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class,
                () -> this.shelterMapper.mapFrom(this.shelterEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(this.shelterEntity.getClass().getSimpleName())), exception.getMessage());
        assertEquals(ShelterCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenShelterEntityNameIsNull() {
        this.shelterEntity.setShelterName(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class,
                () -> this.shelterMapper.mapFrom(this.shelterEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(this.shelterEntity.getClass().getSimpleName())), exception.getMessage());
        assertEquals(ShelterCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenShelterEntityAddressIsNull() {
        this.shelterEntity.setAddress(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class,
                () -> this.shelterMapper.mapFrom(this.shelterEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(this.shelterEntity.getClass().getSimpleName())), exception.getMessage());
        assertEquals(AddressEntityFailuresException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenShelterEntityResponsibleUserIsNull() {
        this.shelterEntity.setResponsibleUser(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class,
                () -> this.shelterMapper.mapFrom(this.shelterEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(this.shelterEntity.getClass().getSimpleName())), exception.getMessage());
        assertEquals(UserEntityFailuresException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenShelterEntityDonationDescriptionIsNull() {
        this.donationEntity.setDescription(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class,
                () -> this.shelterMapper.mapFrom(this.shelterEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(Donation.class.getSimpleName())), exception.getMessage());
        assertEquals(DonationRegisterFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenShelterEntityDonationDescriptionIsEmpty() {
        this.donationEntity.setDescription("  ");

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class,
                () -> this.shelterMapper.mapFrom(this.shelterEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(Donation.class.getSimpleName())), exception.getMessage());
        assertEquals(DonationRegisterFailureException.class, exception.getCause().getClass());
    }

}
