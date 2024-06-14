package diegosneves.github.conectardoacoes.core.domain.shelter.entity.value;

import diegosneves.github.conectardoacoes.core.exception.AddressCreationFailureException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddressTest {


    @Test
    void validateAddressFields() {
        Address address = new Address("Rua", "123", "centro", "cidade", "Estado", "93200100");

        assertEquals("Rua", address.getStreet());
        assertEquals("123", address.getNumber());
        assertEquals("centro", address.getNeighborhood());
        assertEquals("93200100", address.getZip());
        assertEquals("cidade", address.getCity());
        assertEquals("Estado", address.getState());
    }

    @Test
    void shouldThrowExceptionForEmptyStreetName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address("", "123", "centro", "cidade", "Estado", "93200100"));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.STREET_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullStreetName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address(null, "123", "centro", "cidade", "Estado", "93200100"));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.STREET_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyNumber() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address("Rua", " ", "centro", "cidade", "Estado", "93200100"));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.RESIDENCE_NUMBER_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullNumber() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address("null", null, "centro", "cidade", "Estado", "93200100"));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.RESIDENCE_NUMBER_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyNeighborhoodName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address("Rua", "123", "", "cidade", "Estado", "93200100"));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.NEIGHBORHOOD_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullNeighborhoodName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address("null", "123", null, "cidade", "Estado", "93200100"));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.NEIGHBORHOOD_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyCityName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address("rua", "123", "centro", " ", "Estado", "93200100"));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.CITY_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullCityName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address("null", "123", "centro", null, "Estado", "93200100"));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.CITY_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyStateName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address("rua", "123", "centro", "cidade", "    ", "93200100"));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.STATE_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullStateName() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address("null", "123", "centro", "cidade", null, "93200100"));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.STATE_NAME_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyZipCode() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address("rua", "123", "centro", "cidade", "Estado", ""));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.CEP_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNullZipCode() {

        AddressCreationFailureException exception = assertThrows(AddressCreationFailureException.class, () -> new Address("null", "123", "centro", "cidade", "Estado", null));

        assertNotNull(exception);
        assertEquals(AddressCreationFailureException.ERROR.buildMessage(Address.CEP_ERROR_MESSAGE), exception.getMessage());
    }

}
