package diegosneves.github.conectardoacoes.core.domain.shelter.entity;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.shelter.factory.ShelterFactory;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShelterTest {

    private static final String UUID_TEST = "4658a51c-3840-453e-bc69-e2b4cff191a4";
    public static final String UUID_ERROR_MESSAGE = "UUID deve ser informado";
    public static final String DONATION_ID = "6a43fefb-5035-4a9d-83fe-fb5035ea9de7";
    public static final String DESCRIPTION = "Doação";
    public static final String ADDRESS_ID = "23dc581a-0900-4a62-9c58-1a09008a62a9";

    private Shelter shelter;
    private Address address;
    private UserContract user;

    @BeforeEach
    void setUp() {
        this.address = new Address(ADDRESS_ID, "Rua", "377", "Bairro", "Sapucaia", "RS", "93000000");
        this.user = new User(UuidUtils.generateUuid(), "User", "teste@teste.com", UserProfile.BENEFICIARY, "senha");
        this.shelter = new Shelter(UUID_TEST, "Abrigo", this.address, this.user);
    }

    @Test
    void shouldCreateShelterWithGivenDetails() {
        Shelter shelter = ShelterFactory.create("Casa 1", this.address, this.user);

        assertNotNull(shelter);
        assertTrue(UuidUtils.isValidUUID(shelter.getId()));
        assertEquals("Casa 1", shelter.getShelterName());
        assertEquals(this.address, shelter.getAddress());
        assertEquals(this.user, shelter.getUser());
    }

    @Test
    void shouldThrowExceptionWhenCreatingShelterWithInvalidUuid() {
        String uuidInvalid = "idInvalido";

        ShelterCreationFailureException exception = assertThrows(ShelterCreationFailureException.class,
                () -> new Shelter(uuidInvalid, "Casa 1", this.address, this.user));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.SHELTER_CREATION_ERROR.buildMessage(Shelter.ID_VALIDATION_FAILURE), exception.getMessage());
        assertInstanceOf(UuidUtilsException.class, exception.getCause());
        assertEquals(ExceptionDetails.INVALID_UUID_FORMAT_MESSAGE.buildMessage(uuidInvalid), exception.getCause().getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingShelterWithNullUuid() {

        ShelterCreationFailureException exception = assertThrows(ShelterCreationFailureException.class,
                () -> new Shelter(null, "Casa 1", this.address, this.user));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.SHELTER_CREATION_ERROR.buildMessage(Shelter.ID_VALIDATION_FAILURE), exception.getMessage());
        assertInstanceOf(UuidUtilsException.class, exception.getCause());
        assertEquals(ExceptionDetails.INVALID_UUID_FORMAT_MESSAGE.buildMessage(UUID_ERROR_MESSAGE), exception.getCause().getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingShelterWithEmptyUuid() {
        String uuidInvalid = " ";

        ShelterCreationFailureException exception = assertThrows(ShelterCreationFailureException.class,
                () -> new Shelter(uuidInvalid, "Casa 1", this.address, this.user));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.SHELTER_CREATION_ERROR.buildMessage(Shelter.ID_VALIDATION_FAILURE), exception.getMessage());
        assertInstanceOf(UuidUtilsException.class, exception.getCause());
        assertEquals(ExceptionDetails.INVALID_UUID_FORMAT_MESSAGE.buildMessage(uuidInvalid), exception.getCause().getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingShelterWithNullAddress() {

        ShelterCreationFailureException exception = assertThrows(ShelterCreationFailureException.class,
                () -> new Shelter(UUID_TEST, "Casa 1", null, this.user));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.SHELTER_CREATION_ERROR.buildMessage(Shelter.ADDRESS_REQUIRED_ERROR), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingShelterWithNullResponsibleUser() {

        ShelterCreationFailureException exception = assertThrows(ShelterCreationFailureException.class,
                () -> new Shelter(UUID_TEST, "Casa 1", this.address, null));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.SHELTER_CREATION_ERROR.buildMessage(Shelter.RESPONSIBLE_REQUIRED_ERROR), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingShelterWithNullShelterName() {
        ShelterCreationFailureException exception = assertThrows(ShelterCreationFailureException.class,
                () -> new Shelter(UUID_TEST, null, this.address, this.user));

        assertNotNull(exception);
        assertEquals(ShelterCreationFailureException.ERROR.buildMessage(Shelter.SHELTER_NAME_REQUIRED_ERROR), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingShelterWithEmptyShelterName() {
        ShelterCreationFailureException exception = assertThrows(ShelterCreationFailureException.class,
                () -> new Shelter(UUID_TEST, " ", this.address, this.user));

        assertNotNull(exception);
        assertEquals(ShelterCreationFailureException.ERROR.buildMessage(Shelter.SHELTER_NAME_REQUIRED_ERROR), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenChangeShelterNameWithNullShelterName() {
        ShelterCreationFailureException exception = assertThrows(ShelterCreationFailureException.class,
                () -> this.shelter.changeShelterName(null));

        assertNotNull(exception);
        assertEquals(ShelterCreationFailureException.ERROR.buildMessage(Shelter.SHELTER_NAME_REQUIRED_ERROR), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenChangeShelterNameWithEmptyShelterName() {
        ShelterCreationFailureException exception = assertThrows(ShelterCreationFailureException.class,
                () -> this.shelter.changeShelterName(" "));

        assertNotNull(exception);
        assertEquals(ShelterCreationFailureException.ERROR.buildMessage(Shelter.SHELTER_NAME_REQUIRED_ERROR), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldChangeShelterName() {
        String newShelterName = "newShelterName";
        Field field = Shelter.class.getDeclaredField("shelterName");
        field.setAccessible(true);

        this.shelter.changeShelterName(newShelterName);

        String actualShelterName = (String) field.get(this.shelter);

        assertNotNull(actualShelterName);
        assertEquals(newShelterName, actualShelterName);
    }

    @Test
    @SneakyThrows
    void shouldChangeAddress() {
        Address newAddress = new Address(ADDRESS_ID, "Rua", "3924", "Bairro2", "Esteio", "RS", "94000000");
        Field field = Shelter.class.getDeclaredField("address");
        field.setAccessible(true);

        this.shelter.changeAddress(newAddress);

        Address actualAddress = (Address) field.get(this.shelter);

        assertNotNull(actualAddress);
        assertEquals(newAddress, actualAddress);
    }

    @Test
    void shouldThrowExceptionWhenChangeAddressWithNullAddress() {
        ShelterCreationFailureException exception = assertThrows(ShelterCreationFailureException.class,
                () -> this.shelter.changeAddress(null));

        assertNotNull(exception);
        assertEquals(ShelterCreationFailureException.ERROR.buildMessage(Shelter.ADDRESS_REQUIRED_ERROR), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldAddDonation() {
        Donation newDonation = new Donation(DONATION_ID, DESCRIPTION, 1);

        this.shelter.addDonation(newDonation);

        List<Donation> actualDonations = this.shelter.getDonations();

        assertNotNull(actualDonations);
        assertFalse(actualDonations.isEmpty());
        assertEquals(1, actualDonations.size());
    }

    @Test
    void shouldThrowExceptionWhenAddDonationWithNullDonation() {
        ShelterCreationFailureException exception = assertThrows(ShelterCreationFailureException.class,
                () -> this.shelter.addDonation(null));

        assertNotNull(exception);
        assertEquals(ShelterCreationFailureException.ERROR.buildMessage(Shelter.DONATION_REQUIRED_ERROR), exception.getMessage());
    }

}
