package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.MapperStrategy;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.UserEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    public static final String USER_ID = "574371b9-ae17-4f07-8371-b9ae175f0721";
    public static final String USER_NAME = "Fulano";
    public static final String USER_EMAIL = "email@teste.com";
    public static final String USER_PASSWORD = "Senha";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    private User user;

    @BeforeEach
    void setUp() {
        this.user = new User(USER_ID, USER_NAME, USER_EMAIL, UserProfile.DONOR, USER_PASSWORD);
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
    void shouldFindUserByEmail() {

        persistEntity(new UserEntityMapper(), this.user);

        Optional<UserEntity> foundUser = this.repository.findByEmail(USER_EMAIL);

        assertFalse(foundUser.isEmpty());
        UserEntity foundUserEntity = foundUser.get();
        assertEquals(USER_ID, foundUserEntity.getId());
        assertEquals(USER_NAME, foundUserEntity.getUserName());
        assertEquals(USER_EMAIL, foundUserEntity.getEmail());
        assertEquals(UserProfileType.DONOR, foundUserEntity.getUserProfile());
        assertEquals(USER_PASSWORD, foundUserEntity.getUserPassword());
    }

    @Test
    void shouldNotFindUserByEmail() {

        persistEntity(new UserEntityMapper(), this.user);

        Optional<UserEntity> foundUser = this.repository.findByEmail("teste@email.com");

        assertTrue(foundUser.isEmpty());
    }

}
