package diegosneves.github.conectardoacoes.core.domain.shelter.entity.value;

import diegosneves.github.conectardoacoes.core.exception.AddressCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddressTest {


    public static final String ADDRESS_ID = "f3a7d3c9-16a9-41fd-a7d3-c916a981fd2f";
    public static final String STREET = "Rua";
    public static final String NUMBER = "123";
    public static final String NEIGHBORHOOD = "centro";
    public static final String CITY = "cidade";
    public static final String STATE = "Estado";
    public static final String ZIP = "93200100";

    @Test
    void validateAddressFields() {
        Address address = new Address(ADDRESS_ID, STREET, NUMBER, NEIGHBORHOOD, CITY, STATE, ZIP);

        assertEquals(ADDRESS_ID, address.getId());
        assertEquals(STREET, address.getStreet());
        assertEquals(NUMBER, address.getNumber());
        assertEquals(NEIGHBORHOOD, address.getNeighborhood());
        assertEquals(ZIP, address.getZip());
        assertEquals(CITY, address.getCity());
        assertEquals(STATE, address.getState());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "id"})
    void shouldThrowExceptionForEmptyOrInvalidAddressId(String value) {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(value, STREET, NUMBER, NEIGHBORHOOD, CITY, STATE, ZIP));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.INVALID_ID_MESSAGE), exception.getMessage());
        assertEquals(UuidUtilsException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionForNullAddressId() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(null, STREET, NUMBER, NEIGHBORHOOD, CITY, STATE, ZIP));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.INVALID_ID_MESSAGE), exception.getMessage());
        assertEquals(UuidUtilsException.class, exception.getCause().getClass());
    }


    @Test
    void shouldThrowExceptionForEmptyStreetName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(ADDRESS_ID, "", NUMBER, NEIGHBORHOOD, CITY, STATE, ZIP));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.STREET_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullStreetName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(ADDRESS_ID,null, NUMBER, NEIGHBORHOOD, CITY, STATE, ZIP));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.STREET_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyNumber() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(ADDRESS_ID, STREET, " ", NEIGHBORHOOD, CITY, STATE, ZIP));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.RESIDENCE_NUMBER_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullNumber() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(ADDRESS_ID, "null", null, NEIGHBORHOOD, CITY, STATE, ZIP));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.RESIDENCE_NUMBER_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyNeighborhoodName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(ADDRESS_ID, STREET, NUMBER, "", CITY, STATE, ZIP));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.NEIGHBORHOOD_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullNeighborhoodName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(ADDRESS_ID, "null", NUMBER, null, CITY, STATE, ZIP));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.NEIGHBORHOOD_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyCityName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(ADDRESS_ID, "rua", NUMBER, NEIGHBORHOOD, " ", STATE, ZIP));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.CITY_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullCityName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(ADDRESS_ID, "null", NUMBER, NEIGHBORHOOD, null, STATE, ZIP));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.CITY_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyStateName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(ADDRESS_ID, "rua", NUMBER, NEIGHBORHOOD, CITY, "    ", ZIP));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.STATE_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullStateName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(ADDRESS_ID, "null", NUMBER, NEIGHBORHOOD, CITY, null, ZIP));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.STATE_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyZipCode() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(ADDRESS_ID, "rua", NUMBER, NEIGHBORHOOD, CITY, STATE, ""));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.CEP_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullZipCode() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(ADDRESS_ID, "null", NUMBER, NEIGHBORHOOD, CITY, STATE, null));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.CEP_ERROR_MESSAGE), exception.getMessage());
    }

}
