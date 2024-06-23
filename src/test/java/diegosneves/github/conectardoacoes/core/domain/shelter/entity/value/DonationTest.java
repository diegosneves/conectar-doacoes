package diegosneves.github.conectardoacoes.core.domain.shelter.entity.value;

import diegosneves.github.conectardoacoes.core.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DonationTest {

    public static final String DONATION_ID = "5945ced6-9f60-4c75-85ce-d69f60fc75c4";
    public static final String DONATION_DESCRIPTION = "Item 01";
    public static final int DONATION_AMOUNT = 2;

    @Test
    void donationObjectShouldBeInitializedWithCorrectValues() {
        Donation donation = new Donation(DONATION_ID, DONATION_DESCRIPTION, DONATION_AMOUNT);

        assertNotNull(donation);
        assertEquals(DONATION_ID, donation.getId());
        assertEquals(DONATION_DESCRIPTION, donation.getDescription());
        assertEquals(DONATION_AMOUNT, donation.getAmount());
    }

    @Test
    void donationObjectShouldBeInitializedWithDefaultAmountWhenGivenAmountIsZero() {
        Donation donation = new Donation(DONATION_ID, DONATION_DESCRIPTION, 0);

        assertNotNull(donation);
        assertEquals(DONATION_ID, donation.getId());
        assertEquals(DONATION_DESCRIPTION, donation.getDescription());
        assertEquals(Donation.DEFAULT_DONATION_AMOUNT, donation.getAmount());
    }

    @Test
    void shouldNotBeInitializedWithNullDescription() {
        DonationRegisterFailureException exception = assertThrows(DonationRegisterFailureException.class, () -> new Donation(DONATION_ID,null, 1));
        assertNotNull(exception);
        assertEquals(ExceptionDetails.DONATION_CREATION_ERROR.buildMessage(Donation.INVALID_DESCRIPTION_ERROR), exception.getMessage());
    }

    @Test
    void shouldNotBeInitializedWithEmptyDescription() {
        DonationRegisterFailureException exception = assertThrows(DonationRegisterFailureException.class, () -> new Donation(DONATION_ID," ", -1));
        assertNotNull(exception);
        assertEquals(ExceptionDetails.DONATION_CREATION_ERROR.buildMessage(Donation.INVALID_DESCRIPTION_ERROR), exception.getMessage());
    }

    @Test
    void shouldNotBeInitializedWithNullAmount() {
        DonationRegisterFailureException exception = assertThrows(DonationRegisterFailureException.class, () -> new Donation(DONATION_ID,"null", null));
        assertNotNull(exception);
        assertEquals(ExceptionDetails.DONATION_CREATION_ERROR.buildMessage(Donation.INVALID_QUANTITY), exception.getMessage());
    }

    @Test
    void shouldNotBeInitializedWithNullId() {
        DonationRegisterFailureException exception = assertThrows(DonationRegisterFailureException.class, () -> new Donation(null, DONATION_DESCRIPTION, DONATION_AMOUNT));
        assertNotNull(exception);
        assertEquals(ExceptionDetails.DONATION_CREATION_ERROR.buildMessage(Donation.INVALID_ID_MESSAGE), exception.getMessage());
        assertEquals(UuidUtilsException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "null"})
    void shouldNotBeInitializedWithEmptyOrInvalidId(String id) {
        DonationRegisterFailureException exception = assertThrows(DonationRegisterFailureException.class, () -> new Donation(id, DONATION_DESCRIPTION, DONATION_AMOUNT));
        assertNotNull(exception);
        assertEquals(ExceptionDetails.DONATION_CREATION_ERROR.buildMessage(Donation.INVALID_ID_MESSAGE), exception.getMessage());
        assertEquals(UuidUtilsException.class, exception.getCause().getClass());
    }

}
