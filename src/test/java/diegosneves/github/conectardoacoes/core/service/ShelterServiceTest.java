package diegosneves.github.conectardoacoes.core.service;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.ShelterContract;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.shelter.shared.repository.ShelterContractRepository;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.ShelterServiceFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ShelterServiceTest {

    public static final String SHELTER_IDENTIFIER = "89142bda-7b0c-4421-af28-f9cadb316024";
    public static final String USER_UUID = "0ed6e6a1-882c-4d2d-83ee-67034ee1ab9f";
    public static final String USERNAME = "Fulano";
    public static final String USER_EMAIL = "teste@email.com";
    public static final String USER_PASSWORD = "senha";
    public static final String SHELTER_NAME = "Abrigo";
    public static final String ADDRESS_STREET = "Rua";
    public static final String BUILDING_NUMBER = "54";
    public static final String NEIGHBORHOOD = "Bairro";
    public static final String SHELTER_CITY = "Canoas";
    public static final String STATE_ABBREVIATION = "RS";
    public static final String SHELTER_ZIPCODE = "95000000";

    @InjectMocks
    private ShelterService service;

    @Mock
    private ShelterContractRepository repository;

    @Captor
    private ArgumentCaptor<Shelter> shelterCaptor;

    private Shelter shelter;
    private User user;
    private Address address;

    @BeforeEach
    void setUp() {
        this.user = new User(USER_UUID, USERNAME, USER_EMAIL, UserProfile.BENEFICIARY, USER_PASSWORD);
        this.address = new Address(ADDRESS_STREET, BUILDING_NUMBER, NEIGHBORHOOD, SHELTER_CITY, STATE_ABBREVIATION, SHELTER_ZIPCODE);
        this.shelter = new Shelter(SHELTER_IDENTIFIER, SHELTER_NAME, this.address, this.user);
    }

    @Test
    void shouldReturnShelterContract() {
        when(this.repository.persist(any(ShelterContract.class))).thenReturn(this.shelter);

        ShelterContract actual = this.service.createShelter(SHELTER_NAME, this.address, this.user);

        verify(this.repository, times(1)).persist(this.shelterCaptor.capture());

        assertNotNull(actual);
        assertNotNull(this.shelterCaptor.getValue());
        assertEquals(this.shelter, actual);
        ShelterContract expected = this.shelterCaptor.getValue();
        assertTrue(UuidUtils.isValidUUID(expected.getId()));
        assertEquals(SHELTER_NAME, expected.getShelterName());
        assertEquals(this.user, expected.getUser());
        assertEquals(this.address, expected.getAddress());
    }

    @Test
    void shouldThrowShelterCreationFailureExceptionWhenShelterNameIsNull() {

        Exception actual = assertThrows(Exception.class,
                () -> this.service.createShelter(null, this.address, this.user));

        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(actual);
        assertEquals(ShelterCreationFailureException.class, actual.getClass());
    }

    @Test
    void shouldThrowShelterCreationFailureExceptionWhenShelterNameIsEmpty() {

        Exception actual = assertThrows(Exception.class,
                () -> this.service.createShelter("", this.address, this.user));

        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(actual);
        assertEquals(ShelterCreationFailureException.class, actual.getClass());
    }

    @Test
    void shouldThrowShelterCreationFailureExceptionWhenShelterAddressIsNull() {

        Exception actual = assertThrows(Exception.class,
                () -> this.service.createShelter(SHELTER_NAME, null, this.user));

        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(actual);
        assertEquals(ShelterCreationFailureException.class, actual.getClass());
    }

    @Test
    void shouldThrowShelterCreationFailureExceptionWhenShelterUserIsNull() {

        Exception actual = assertThrows(Exception.class,
                () -> this.service.createShelter(SHELTER_NAME, this.address, null));

        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(actual);
        assertEquals(ShelterCreationFailureException.class, actual.getClass());
    }

    @Test
    void shouldRetrieveShelterContractUsingGivenShelterIdentifier() {
        when(this.repository.findEntityById(SHELTER_IDENTIFIER)).thenReturn(this.shelter);

        ShelterContract actual = this.service.getShelter(SHELTER_IDENTIFIER);

        verify(this.repository, times(1)).findEntityById(SHELTER_IDENTIFIER);

        assertNotNull(actual);
        assertEquals(this.shelter, actual);
    }

    @Test
    void shouldRetrieveNullShelterContractUsingGivenShelterIdentifier() {
        when(this.repository.findEntityById(SHELTER_IDENTIFIER)).thenReturn(null);

        ShelterContract actual = this.service.getShelter(SHELTER_IDENTIFIER);

        verify(this.repository, times(1)).findEntityById(SHELTER_IDENTIFIER);

        assertNull(actual);
    }

    @Test
    void shouldThrowExceptionWhenGivenNullShelterId() {
        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.getShelter(null));

        verify(this.repository, never()).findEntityById(anyString());

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGivenEmptyShelterId() {
        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.getShelter(""));

        verify(this.repository, never()).findEntityById(anyString());

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGivenInvalidShelterId() {
        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.getShelter("idInvalid"));

        verify(this.repository, never()).findEntityById(anyString());

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
        assertEquals(UuidUtilsException.class, exception.getCause().getClass());
    }

    @Test
    void shouldChangeShelterNameWhenGivenValidShelterIdentifier() {
        String newShelterName = "newShelterName";
        when(this.repository.findEntityById(SHELTER_IDENTIFIER)).thenReturn(this.shelter);

        this.service.changeShelterName(SHELTER_IDENTIFIER, newShelterName);

        verify(this.repository, times(1)).findEntityById(SHELTER_IDENTIFIER);
        verify(this.repository, times(1)).persist(this.shelterCaptor.capture());

        assertNotNull(shelterCaptor.getValue());
        Shelter updatedShelter = this.shelterCaptor.getValue();
        assertEquals(newShelterName, updatedShelter.getShelterName());
        assertEquals(SHELTER_IDENTIFIER, updatedShelter.getId());
        assertEquals(this.user, updatedShelter.getUser());
        assertEquals(this.address, updatedShelter.getAddress());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenShelterNameIsEmpty() {
        String newShelterName = "";

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.changeShelterName(SHELTER_IDENTIFIER, newShelterName));

        verify(this.repository, never()).findEntityById(anyString());
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenShelterNameIsNull() {

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.changeShelterName(SHELTER_IDENTIFIER, null));

        verify(this.repository, never()).findEntityById(anyString());
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenShelterIdIsInvalidOnChangeShelterName() {

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.changeShelterName("SHELTER_IDENTIFIER", SHELTER_NAME));

        verify(this.repository, never()).findEntityById(anyString());
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
        assertEquals(UuidUtilsException.class, exception.getCause().getClass());
    }

    @Test
    void shouldUpdateAndSaveShelterAddressGivenValidShelterId() {
        Address addressUpdate = new Address(ADDRESS_STREET, "377", NEIGHBORHOOD, "Esteio", STATE_ABBREVIATION, SHELTER_ZIPCODE);
        when(this.repository.findEntityById(SHELTER_IDENTIFIER)).thenReturn(this.shelter);

        this.service.changeAddress(SHELTER_IDENTIFIER, addressUpdate);

        verify(this.repository, times(1)).findEntityById(SHELTER_IDENTIFIER);
        verify(this.repository, times(1)).persist(this.shelterCaptor.capture());

        assertNotNull(shelterCaptor.getValue());
        Shelter updatedShelter = this.shelterCaptor.getValue();
        assertEquals(addressUpdate, updatedShelter.getAddress());
        assertEquals(SHELTER_IDENTIFIER, updatedShelter.getId());
        assertEquals(this.user, updatedShelter.getUser());
        assertNotEquals(this.address, updatedShelter.getAddress());
        assertEquals(SHELTER_NAME, updatedShelter.getShelterName());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenShelterIdIsEmptyOnChangeAddress() {
        Address addressUpdate = new Address(ADDRESS_STREET, "377", NEIGHBORHOOD, "Esteio", STATE_ABBREVIATION, SHELTER_ZIPCODE);

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.changeAddress("", addressUpdate));

        verify(this.repository, never()).findEntityById(anyString());
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenShelterIdIsNullOnChangeAddress() {
        Address addressUpdate = new Address(ADDRESS_STREET, "377", NEIGHBORHOOD, "Esteio", STATE_ABBREVIATION, SHELTER_ZIPCODE);

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.changeAddress(null, addressUpdate));

        verify(this.repository, never()).findEntityById(anyString());
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenShelterIdIsInvalidOnChangeAddress() {
        Address addressUpdate = new Address(ADDRESS_STREET, "377", NEIGHBORHOOD, "Esteio", STATE_ABBREVIATION, SHELTER_ZIPCODE);

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.changeAddress("SHELTER_IDENTIFIER", addressUpdate));

        verify(this.repository, never()).findEntityById(anyString());
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
        assertEquals(UuidUtilsException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenNewAddressIsNullOnChangeAddress() {

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.changeAddress(SHELTER_IDENTIFIER, null));

        verify(this.repository, never()).findEntityById(anyString());
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.ERROR_MESSAGE_ADDRESS_NULL), exception.getMessage());
    }

    @Test
    void shouldAddDonationToShelter() {
        Donation donation = new Donation("item", 1);

        when(this.repository.findEntityById(SHELTER_IDENTIFIER)).thenReturn(this.shelter);

        this.service.addDonation(SHELTER_IDENTIFIER, donation);

        verify(this.repository, times(1)).findEntityById(SHELTER_IDENTIFIER);
        verify(this.repository, times(1)).persist(this.shelterCaptor.capture());

        assertNotNull(shelterCaptor.getValue());
        Shelter updatedShelter = this.shelterCaptor.getValue();
        assertFalse(updatedShelter.getDonations().isEmpty());
        assertEquals(1, updatedShelter.getDonations().size());
        assertEquals(donation, updatedShelter.getDonations().get(0));
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenShelterIdIsNullOnAddDonation() {
        Donation donation = new Donation("item", 1);

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.addDonation(null, donation));

        verify(this.repository, never()).findEntityById(anyString());
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenShelterIdIsEmptyOnAddDonation() {
        Donation donation = new Donation("item", 1);

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.addDonation(" ", donation));

        verify(this.repository, never()).findEntityById(anyString());
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenShelterIdIsInvalidOnAddDonation() {
        Donation donation = new Donation("item", 1);

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.addDonation("SHELTER_IDENTIFIER", donation));

        verify(this.repository, never()).findEntityById(anyString());
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
        assertEquals(UuidUtilsException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenDonationIsNullOnAddDonation() {

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class,
                () -> this.service.addDonation(SHELTER_IDENTIFIER, null));

        verify(this.repository, never()).findEntityById(anyString());
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.DONATION_REQUIRED_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldReturnListOfDonations() {
        Donation donation = new Donation("item", 1);
        this.shelter.addDonation(donation);
        when(this.repository.findEntityById(SHELTER_IDENTIFIER)).thenReturn(this.shelter);

        List<Donation> list = this.service.getDonations(SHELTER_IDENTIFIER);

        verify(this.repository, times(1)).findEntityById(SHELTER_IDENTIFIER);

        assertNotNull(list);
        assertFalse(this.shelter.getDonations().isEmpty());
        assertEquals(1, list.size());
        assertEquals(donation, list.get(0));
    }

    @Test
    void shouldReturnListOfDonationsEmpty() {
        when(this.repository.findEntityById(SHELTER_IDENTIFIER)).thenReturn(this.shelter);

        List<Donation> list = this.service.getDonations(SHELTER_IDENTIFIER);

        verify(this.repository, times(1)).findEntityById(SHELTER_IDENTIFIER);

        assertNotNull(list);
        assertTrue(this.shelter.getDonations().isEmpty());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenShelterIdIsNullOnGetDonations() {

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class, () -> this.service.getDonations(null));

        verify(this.repository, never()).findEntityById(anyString());

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenShelterIdIsEmptyOnGetDonations() {

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class, () -> this.service.getDonations(""));

        verify(this.repository, never()).findEntityById(anyString());

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowShelterServiceFailureExceptionWhenShelterIdIsInvalidOnGetDonations() {

        ShelterServiceFailureException exception = assertThrows(ShelterServiceFailureException.class, () -> this.service.getDonations("SHELTER_IDENTIFIER"));

        verify(this.repository, never()).findEntityById(anyString());

        assertNotNull(exception);
        assertEquals(ShelterServiceFailureException.ERROR.buildMessage(ShelterService.INVALID_SHELTER_ID_MESSAGE), exception.getMessage());
    }

}
