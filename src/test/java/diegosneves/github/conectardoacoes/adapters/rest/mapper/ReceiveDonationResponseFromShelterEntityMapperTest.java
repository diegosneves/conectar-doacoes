package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.adapters.rest.response.ReceiveDonationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class ReceiveDonationResponseFromShelterEntityMapperTest {

    public static final String SHELTER_NAME = "Abrigo";
    public static final String DESCRIPTION = "Mochila";
    public static final int AMOUNT = 2;
    public static final String USER_NAME = "Fulano";
    public static final String USER_EMAIL = "fulano@gmail.com";


    private ReceiveDonationResponseFromShelterEntityMapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = new ReceiveDonationResponseFromShelterEntityMapper();
    }

    @Test
    void shouldMapToDonationResponse() {
        ShelterEntity shelterEntity = generateShelterEntity();

        ReceiveDonationResponse response = this.mapper.mapFrom(shelterEntity);

        assertNotNull(response);
        assertEquals(SHELTER_NAME, response.getShelterName());
        assertEquals(USER_NAME, response.getResponsibleName());
        assertEquals(USER_EMAIL, response.getResponsibleEmail());
        assertNotNull(response.getDonationDTOS());
        assertFalse(response.getDonationDTOS().isEmpty());
        assertEquals(1, response.getDonationDTOS().size());
        assertEquals(DESCRIPTION, response.getDonationDTOS().get(0).getDescription());
        assertEquals(AMOUNT, response.getDonationDTOS().get(0).getAmount());
    }

    private static ShelterEntity generateShelterEntity() {
        return ShelterEntity.builder()
                .shelterName(SHELTER_NAME)
                .responsibleUser(generateTestUserEntity())
                .donations(List.of(generateDonationEntity()))
                .build();
    }

    private static UserEntity generateTestUserEntity() {
        return UserEntity.builder()
                .userName(USER_NAME)
                .email(USER_EMAIL)
                .build();
    }

    private static DonationEntity generateDonationEntity() {
        return DonationEntity.builder()
                .description(DESCRIPTION)
                .amount(AMOUNT)
                .build();
    }

    @Test
    void shouldMapShelterEntityWithoutDonationsToReceiveDonationResponse() {
        ShelterEntity shelterEntity = generateShelterEntity();
        shelterEntity.setDonations(null);

        ReceiveDonationResponse response = this.mapper.mapFrom(shelterEntity);

        assertNotNull(response);
        assertEquals(SHELTER_NAME, response.getShelterName());
        assertEquals(USER_NAME, response.getResponsibleName());
        assertEquals(USER_EMAIL, response.getResponsibleEmail());
        assertNotNull(response.getDonationDTOS());
        assertTrue(response.getDonationDTOS().isEmpty());
    }

    @Test
    void shouldMapShelterEntityWithEmptyDonationListToReceiveDonationResponse() {
        ShelterEntity shelterEntity = generateShelterEntity();
        shelterEntity.setDonations(new ArrayList<>());

        ReceiveDonationResponse response = this.mapper.mapFrom(shelterEntity);

        assertNotNull(response);
        assertEquals(SHELTER_NAME, response.getShelterName());
        assertEquals(USER_NAME, response.getResponsibleName());
        assertEquals(USER_EMAIL, response.getResponsibleEmail());
        assertNotNull(response.getDonationDTOS());
        assertTrue(response.getDonationDTOS().isEmpty());
    }

}
