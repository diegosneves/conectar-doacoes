package diegosneves.github.conectardoacoes.core.domain.shelter.entity.value;

import diegosneves.github.conectardoacoes.core.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DonationTest {

    @Test
    void donationObjectShouldBeInitializedWithCorrectValues() {
        Donation donation = new Donation("Item 01", 2);

        assertNotNull(donation);
        assertEquals("Item 01", donation.getDescription());
        assertEquals(2, donation.getAmount());
    }

    @Test
    void donationObjectShouldBeInitializedWithDefaultAmountWhenGivenAmountIsZero() {
        Donation donation = new Donation("Item 02", 0);

        assertNotNull(donation);
        assertEquals("Item 02", donation.getDescription());
        assertEquals(Donation.DEFAULT_DONATION_AMOUNT, donation.getAmount());
    }

    @Test
    void shouldNotBeInitializedWithNullDescription() {
        DonationRegisterFailureException exception = assertThrows(DonationRegisterFailureException.class, () -> new Donation(null, 1));
        assertNotNull(exception);
        assertEquals(ExceptionDetails.DONATION_CREATION_ERROR.buildMessage(Donation.INVALID_DESCRIPTION_ERROR), exception.getMessage());
    }

    @Test
    void shouldNotBeInitializedWithEmptyDescription() {
        DonationRegisterFailureException exception = assertThrows(DonationRegisterFailureException.class, () -> new Donation(" ", 1));
        assertNotNull(exception);
        assertEquals(ExceptionDetails.DONATION_CREATION_ERROR.buildMessage(Donation.INVALID_DESCRIPTION_ERROR), exception.getMessage());
    }

    @Test
    void shouldNotBeInitializedWithNullAmount() {
        DonationRegisterFailureException exception = assertThrows(DonationRegisterFailureException.class, () -> new Donation("null", null));
        assertNotNull(exception);
        assertEquals(ExceptionDetails.DONATION_CREATION_ERROR.buildMessage(Donation.INVALID_QUANTITY), exception.getMessage());
    }


}
