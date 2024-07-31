package diegosneves.github.conectardoacoes.core.service;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class DonationServiceTest {

    public static final String DONATION_DESCRIPTION = "item";
    public static final int AMOUNT = 1;

    @InjectMocks
    private DonationService service;

    @Test
    void shouldCreateDonation() {

        Donation donation = this.service.createDonation(DONATION_DESCRIPTION, 2);

        assertNotNull(donation);
        assertNotNull(donation.getId());
        assertTrue(UuidUtils.isValidUUID(donation.getId()));
        assertEquals(DONATION_DESCRIPTION, donation.getDescription());
        assertEquals(2, donation.getAmount());
    }

    @Test
    void shouldThrowExceptionWhenDonationDescriptionIsNull() {

        DonationRegisterFailureException exception = assertThrows(DonationRegisterFailureException.class, () -> this.service.createDonation(null, AMOUNT));

        assertNotNull(exception);
        assertEquals(DonationRegisterFailureException.ERROR.buildMessage(Donation.INVALID_DESCRIPTION_ERROR), exception.getMessage());
        assertNull(exception.getCause());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowExceptionWhenDonationDescriptionIsEmpty(String description) {
        DonationRegisterFailureException exception = assertThrows(DonationRegisterFailureException.class, () -> this.service.createDonation(description, AMOUNT));

        assertNotNull(exception);
        assertEquals(DonationRegisterFailureException.ERROR.buildMessage(Donation.INVALID_DESCRIPTION_ERROR), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenDonationAmountIsNull() {

        DonationRegisterFailureException exception = assertThrows(DonationRegisterFailureException.class, () -> this.service.createDonation(DONATION_DESCRIPTION, null));

        assertNotNull(exception);
        assertEquals(DonationRegisterFailureException.ERROR.buildMessage(Donation.INVALID_QUANTITY), exception.getMessage());
        assertNull(exception.getCause());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 1})
    void shouldThrowExceptionWhenDonationAmountIsNegative(Integer amount) {

        Donation donation = this.service.createDonation(DONATION_DESCRIPTION, amount);

        assertNotNull(donation);
        assertNotNull(donation.getId());
        assertTrue(UuidUtils.isValidUUID(donation.getId()));
        assertEquals(DONATION_DESCRIPTION, donation.getDescription());
        assertEquals(AMOUNT, donation.getAmount());
    }

}
