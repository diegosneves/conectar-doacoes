package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.MapperStrategy;
import diegosneves.github.conectardoacoes.adapters.rest.model.DepositEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonorDeposit;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class DonorDepositRepositoryTest {

    public static final String USER_ID = "574371b9-ae17-4f07-8371-b9ae175f0721";
    public static final String USER_NAME = "Fulano";
    public static final String USER_EMAIL = "email@teste.com";
    public static final String USER_PASSWORD = "Senha";

    public static final String DONATION_ID = "bf9b8d38-c6b3-4fd6-9b8d-38c6b3bfd69f";
    public static final String DONATION_DESCRIPTION = "Descrição";
    public static final int AMOUNT = 1;

    public static final String DONOR_DEPOSIT_ID = "5c5c4088-9dfa-4e15-9c40-889dfa6e15b1";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DonorDepositRepository repository;

    private UserEntity user;
    private DepositEntity deposit;

    private DonorDeposit donorDeposit;

    @BeforeEach
    void setUp() {
        this.user = new UserEntity(USER_ID, USER_NAME, USER_EMAIL, UserProfileType.DONOR, USER_PASSWORD);
        this.deposit = new DepositEntity(DONATION_ID, DONATION_DESCRIPTION, AMOUNT);
        this.donorDeposit = new DonorDeposit(DONOR_DEPOSIT_ID, this.user, List.of(this.deposit));
    }

    @Test
    void shouldRetrieveDonorDepositEntityWhenGivenValidUserEmail() {

        persistEntity(this.user);
        persistEntity(this.deposit);
        persistEntity(this.donorDeposit);

        Optional<DonorDeposit> actual = this.repository.findDonorDepositByUser_Email(USER_EMAIL);

        assertTrue(actual.isPresent());
        DonorDeposit donorDeposit = actual.get();
        assertNotNull(donorDeposit.getId());
        assertTrue(UuidUtils.isValidUUID(donorDeposit.getId()));
        assertEquals(USER_ID, donorDeposit.getUser().getId());
        assertEquals(USER_NAME, donorDeposit.getUser().getUserName());
        assertEquals(USER_EMAIL, donorDeposit.getUser().getEmail());
        assertEquals(UserProfileType.DONOR, donorDeposit.getUser().getUserProfile());
        assertNotNull(donorDeposit.getDeposits());
        assertEquals(1, donorDeposit.getDeposits().size());
    }

    private <T, E> void persistEntity(MapperStrategy<T, E> mapper, E entity) {
        this.entityManager.persist(mapper.mapFrom(entity));
        this.entityManager.flush();
    }

    private <T> void persistEntity(T entity) {
        this.entityManager.persist(entity);
        this.entityManager.flush();
    }

    @Test
    void shouldReturnNullDonorDepositEntityWhenGivenWrongUserEmail() {

        persistEntity(this.user);
        persistEntity(this.deposit);
        persistEntity(this.donorDeposit);

        Optional<DonorDeposit> actual = this.repository.findDonorDepositByUser_Email("USER_EMAIL");

        assertFalse(actual.isPresent());
    }

}
