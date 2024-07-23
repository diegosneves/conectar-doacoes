package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DonationEntityMapperTest {

    public static final String DONATION_ID = "28387d3e-e9cc-45b9-b87d-3ee9cc35b912";
    public static final String DESCRIPTION = "Descricao";
    public static final int AMOUNT = 1;


    private DonationEntityMapper mapper;

    private Donation donation;

    @BeforeEach
    void setUp() {
        this.mapper = new DonationEntityMapper();
        this.donation = new Donation(DONATION_ID, DESCRIPTION, AMOUNT);

    }

    @Test
    void shouldMapToDonationEntity() {

        DonationEntity donationEntity = this.mapper.mapFrom(this.donation);

        assertNotNull(donationEntity);
        assertEquals(DONATION_ID, donationEntity.getId());
        assertEquals(DESCRIPTION, donationEntity.getDescription());
        assertEquals(AMOUNT, donationEntity.getAmount());
    }

    @Test
    void shouldThrowExceptionIfDonationIsNull() {

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.mapper.mapFrom(null));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(MapperStrategy.CLASS_MAPPING_FAILURE).formatErrorMessage(Donation.class.getSimpleName()), exception.getMessage());
    }

}
