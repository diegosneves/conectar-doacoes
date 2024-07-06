package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DonationMapperTest {

    public static final String DONATION_ID = "047bcc50-3193-4ef1-bbcc-5031938ef1a5";
    public static final String DESCRIPTION = "Descricao";
    public static final int AMOUNT = 1;


    private DonationMapper donationMapper;

    private DonationEntity donationEntity;

    @BeforeEach
    void setUp() {
        this.donationMapper = new DonationMapper();
        this.donationEntity = DonationEntity.builder()
                .id(DONATION_ID)
                .description(DESCRIPTION)
                .amount(AMOUNT)
                .build();
    }

    @Test
    void shouldMapFromDonationEntityToDonationWithNegativeAmount() {
        this.donationEntity.setAmount(-25);
        Donation donation = this.donationMapper.mapFrom(this.donationEntity);

        assertNotNull(donation);
        assertEquals(DONATION_ID, donation.getId());
        assertEquals(DESCRIPTION, donation.getDescription());
        assertEquals(AMOUNT, donation.getAmount());
    }

    @Test
    void shouldMapFromDonationEntityToDonationWithZeroAmount() {
        this.donationEntity.setAmount(0);
        Donation donation = this.donationMapper.mapFrom(this.donationEntity);

        assertNotNull(donation);
        assertEquals(DONATION_ID, donation.getId());
        assertEquals(DESCRIPTION, donation.getDescription());
        assertEquals(AMOUNT, donation.getAmount());
    }

    @Test
    void shouldThrowExceptionWhenDonationEntityIsNull() {

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.donationMapper.mapFrom(null));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(DonationEntity.class.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDonationEntityHaveYourIdNull() {
        this.donationEntity.setId(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.donationMapper.mapFrom(this.donationEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(DonationEntity.class.getSimpleName())), exception.getMessage());
        assertEquals(DonationRegisterFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "id"})
    void shouldThrowExceptionWhenDonationEntityHaveYourIdEmptyOrInvalid(String value) {
        this.donationEntity.setId(value);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.donationMapper.mapFrom(this.donationEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(DonationEntity.class.getSimpleName())), exception.getMessage());
        assertEquals(DonationRegisterFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenDonationEntityHaveYourDescriptionNull() {
        this.donationEntity.setDescription(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.donationMapper.mapFrom(this.donationEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(DonationEntity.class.getSimpleName())), exception.getMessage());
        assertEquals(DonationRegisterFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenDonationEntityHaveYourDescriptionEmpty() {
        this.donationEntity.setDescription("");

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.donationMapper.mapFrom(this.donationEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(DonationEntity.class.getSimpleName())), exception.getMessage());
        assertEquals(DonationRegisterFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenDonationEntityHaveYourAmountNull() {
        this.donationEntity.setAmount(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.donationMapper.mapFrom(this.donationEntity));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(DonationEntity.class.getSimpleName())), exception.getMessage());
        assertEquals(DonationRegisterFailureException.class, exception.getCause().getClass());
    }

}
