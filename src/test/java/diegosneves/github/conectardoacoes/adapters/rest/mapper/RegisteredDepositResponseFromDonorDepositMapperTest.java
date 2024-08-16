package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.model.DepositEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonorDeposit;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.adapters.rest.response.RegisteredDepositResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class RegisteredDepositResponseFromDonorDepositMapperTest {

    public static final String DONOR_EMAIL = "teste@email.com";
    public static final String DONOR_DEPOSIT_ID = "2f717c50-48ad-4b38-b17c-5048ad4b38d4";

    public static final String USER_UUID = "e744f60b-f691-4735-84f6-0bf691273559";
    public static final String USER_NAME = "Fulano";
    public static final String USER_PASSWORD = "Senha";

    public static final String DEPOSIT_ID = "17087aa2-9dd8-4cc6-887a-a29dd84cc657";
    public static final String DESCRIPTION = "Item 01";
    public static final int AMOUNT = 1;


    @InjectMocks
    private RegisteredDepositResponseFromDonorDepositMapper mapper;

    private DonorDeposit donorDeposit;

    @BeforeEach
    void setUp() {
        UserEntity userEntity = new UserEntity(USER_UUID, USER_NAME, DONOR_EMAIL, UserProfileType.DONOR, USER_PASSWORD);
        DepositEntity depositEntity = new DepositEntity(DEPOSIT_ID, DESCRIPTION, AMOUNT);

        this.donorDeposit = DonorDeposit.builder()
                .id(DONOR_DEPOSIT_ID)
                .user(userEntity)
                .deposits(List.of(depositEntity))
                .build();
    }

    @Test
    void testMapFromWithValidDonorDeposit() {

        RegisteredDepositResponse response = this.mapper.mapFrom(this.donorDeposit);

        assertNotNull(response);
        assertEquals(USER_NAME, response.getUserName());
        assertEquals(DONOR_EMAIL, response.getEmail());
        assertNotNull(response.getDeposits());
        assertEquals(AMOUNT, response.getDeposits().size());
        assertEquals(DESCRIPTION, response.getDeposits().get(0).getDescription());
        assertEquals(AMOUNT, response.getDeposits().get(0).getAmount());
    }

    @Test
    void testMapFromWithNullDeposits() {
        this.donorDeposit.setDeposits(null);

        RegisteredDepositResponse response = this.mapper.mapFrom(this.donorDeposit);

        assertNotNull(response);
        assertEquals(USER_NAME, response.getUserName());
        assertEquals(DONOR_EMAIL, response.getEmail());
        assertNotNull(response.getDeposits());
        assertTrue(response.getDeposits().isEmpty());
    }

    @Test
    void testMapFromWithEmptyDeposits() {
        this.donorDeposit.setDeposits(new ArrayList<>());

        RegisteredDepositResponse response = this.mapper.mapFrom(this.donorDeposit);

        assertNotNull(response);
        assertEquals(USER_NAME, response.getUserName());
        assertEquals(DONOR_EMAIL, response.getEmail());
        assertNotNull(response.getDeposits());
        assertTrue(response.getDeposits().isEmpty());
    }

}
