package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ShelterEntityMapperTest {

    public static final String SHELTER_ID = "9f0b9d43-80ec-4399-8b9d-4380ecb399c7";
    public static final String SHELTER_NAME = "Abrigo";

    public static final String USER_ID = "21f29f51-2c17-458d-b29f-512c17058d97";
    public static final String USER_NAME = "Usuario";
    public static final String USER_EMAIL = "email@teste.com";
    public static final String USER_PASSWORD = "Senha";

    public static final String ADDRESS_ID = "501f97ac-9544-486b-9f97-ac9544486b72";
    public static final String STREET_NAME = "Rua";
    public static final String NUMBER = "123";
    public static final String NEIGHBORHOOD = "Bairro";
    public static final String CITY = "Cidade";
    public static final String STATE = "Estado";
    public static final String ZIP = "91234567";

    public static final String DONATION_ID = "76e97240-2f2b-4a74-a972-402f2b2a74fc";
    public static final String DONATION_DESCRIPTION = "Doacao";
    public static final int DONATION_AMOUNT = 1;


    private ShelterEntityMapper mapper;

    private Shelter shelter;

    private Donation donation;

    @BeforeEach
    void setUp() {
        this.mapper = new ShelterEntityMapper();
        this.donation = new Donation(DONATION_ID, DONATION_DESCRIPTION, DONATION_AMOUNT);
        Address address = new Address(ADDRESS_ID, STREET_NAME, NUMBER, NEIGHBORHOOD, CITY, STATE, ZIP);
        User user = new User(USER_ID, USER_NAME, USER_EMAIL, UserProfile.BENEFICIARY, USER_PASSWORD);
        this.shelter = new Shelter(SHELTER_ID, SHELTER_NAME, address, user);
    }

    @Test
    void shouldMapToShelterEntityWithDonations() {
        this.shelter.addDonation(this.donation);
        ShelterEntity shelterEntity = this.mapper.mapFrom(this.shelter);

        assertNotNull(shelterEntity, "Shelter entity should not be null");
        assertEquals(SHELTER_ID, shelterEntity.getId());
        assertEquals(SHELTER_NAME, shelterEntity.getShelterName());
        assertNotNull(shelterEntity.getAddress(), "Address is null");
        assertEquals(ADDRESS_ID, shelterEntity.getAddress().getId());
        assertEquals(STREET_NAME, shelterEntity.getAddress().getStreet());
        assertEquals(NUMBER, shelterEntity.getAddress().getNumber());
        assertEquals(NEIGHBORHOOD, shelterEntity.getAddress().getNeighborhood());
        assertEquals(CITY, shelterEntity.getAddress().getCity());
        assertEquals(STATE, shelterEntity.getAddress().getState());
        assertEquals(ZIP, shelterEntity.getAddress().getZip());
        assertNotNull(shelterEntity.getResponsibleUser(), "Responsible user is null");
        assertEquals(USER_ID, shelterEntity.getResponsibleUser().getId());
        assertEquals(USER_NAME, shelterEntity.getResponsibleUser().getUserName());
        assertEquals(USER_EMAIL, shelterEntity.getResponsibleUser().getEmail());
        assertEquals(UserProfileType.BENEFICIARY, shelterEntity.getResponsibleUser().getUserProfile());
        assertEquals(USER_PASSWORD, shelterEntity.getResponsibleUser().getUserPassword());
        assertNotNull(shelterEntity.getDonations(), "donations is null");
        assertFalse(shelterEntity.getDonations().isEmpty());
        assertEquals(DONATION_ID, shelterEntity.getDonations().get(0).getId());
        assertEquals(DONATION_DESCRIPTION, shelterEntity.getDonations().get(0).getDescription());
        assertEquals(DONATION_AMOUNT, shelterEntity.getDonations().get(0).getAmount());
    }

    @Test
    void shouldMapShelterToEntityWithoutDonations() {
        ShelterEntity shelterEntity = this.mapper.mapFrom(this.shelter);

        assertNotNull(shelterEntity, "Shelter entity should not be null");
        assertEquals(SHELTER_ID, shelterEntity.getId());
        assertEquals(SHELTER_NAME, shelterEntity.getShelterName());
        assertNotNull(shelterEntity.getAddress(), "Address is null");
        assertEquals(ADDRESS_ID, shelterEntity.getAddress().getId());
        assertEquals(STREET_NAME, shelterEntity.getAddress().getStreet());
        assertEquals(NUMBER, shelterEntity.getAddress().getNumber());
        assertEquals(NEIGHBORHOOD, shelterEntity.getAddress().getNeighborhood());
        assertEquals(CITY, shelterEntity.getAddress().getCity());
        assertEquals(STATE, shelterEntity.getAddress().getState());
        assertEquals(ZIP, shelterEntity.getAddress().getZip());
        assertNotNull(shelterEntity.getResponsibleUser(), "Responsible user is null");
        assertEquals(USER_ID, shelterEntity.getResponsibleUser().getId());
        assertEquals(USER_NAME, shelterEntity.getResponsibleUser().getUserName());
        assertEquals(USER_EMAIL, shelterEntity.getResponsibleUser().getEmail());
        assertEquals(UserProfileType.BENEFICIARY, shelterEntity.getResponsibleUser().getUserProfile());
        assertEquals(USER_PASSWORD, shelterEntity.getResponsibleUser().getUserPassword());
        assertNotNull(shelterEntity.getDonations(), "donations is null");
        assertTrue(shelterEntity.getDonations().isEmpty());
    }

    @Test
    @SneakyThrows
    void testMapFromShouldHandleNullDonationsFieldInShelter() {

        Field field = this.shelter.getClass().getDeclaredField("donations");
        field.setAccessible(true);

        field.set(this.shelter, null);

        ShelterEntity shelterEntity = this.mapper.mapFrom(this.shelter);

        assertNotNull(shelterEntity, "Shelter entity should not be null");
        assertEquals(SHELTER_ID, shelterEntity.getId());
        assertEquals(SHELTER_NAME, shelterEntity.getShelterName());
        assertNotNull(shelterEntity.getAddress(), "Address is null");
        assertEquals(ADDRESS_ID, shelterEntity.getAddress().getId());
        assertEquals(STREET_NAME, shelterEntity.getAddress().getStreet());
        assertEquals(NUMBER, shelterEntity.getAddress().getNumber());
        assertEquals(NEIGHBORHOOD, shelterEntity.getAddress().getNeighborhood());
        assertEquals(CITY, shelterEntity.getAddress().getCity());
        assertEquals(STATE, shelterEntity.getAddress().getState());
        assertEquals(ZIP, shelterEntity.getAddress().getZip());
        assertNotNull(shelterEntity.getResponsibleUser(), "Responsible user is null");
        assertEquals(USER_ID, shelterEntity.getResponsibleUser().getId());
        assertEquals(USER_NAME, shelterEntity.getResponsibleUser().getUserName());
        assertEquals(USER_EMAIL, shelterEntity.getResponsibleUser().getEmail());
        assertEquals(UserProfileType.BENEFICIARY, shelterEntity.getResponsibleUser().getUserProfile());
        assertEquals(USER_PASSWORD, shelterEntity.getResponsibleUser().getUserPassword());
        assertNotNull(shelterEntity.getDonations(), "donations is null");
        assertTrue(shelterEntity.getDonations().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenMappingFromNullShelter() {
        this.shelter = null;

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.mapper.mapFrom(this.shelter));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(ShelterEntityMapper.SHELTER_CLASS.getSimpleName())), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowExceptionWhenMappingFromNullShelterResponsibleUser() {
        Field field = this.shelter.getClass().getDeclaredField("responsibleUser");
        field.setAccessible(true);

        field.set(this.shelter, null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.mapper.mapFrom(this.shelter));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(ShelterEntityMapper.SHELTER_CLASS.getSimpleName())), exception.getMessage());
        assertEquals(UserEntityFailuresException.class, exception.getCause().getClass());
    }

}
