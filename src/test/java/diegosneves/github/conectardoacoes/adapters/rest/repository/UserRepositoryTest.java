package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.MapperStrategy;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.UserEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.UserCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        this.persistEntity(new UserEntityMapper(), this.user);

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

        this.persistEntity(new UserEntityMapper(), this.user);

        Optional<UserEntity> foundUser = this.repository.findByEmail("teste@email.com");

        assertTrue(foundUser.isEmpty());
    }

    @Test
    void shouldReturnUserWhenUserExistsInRepository() {
        this.persistEntity(new UserEntityMapper(), this.user);

        UserContract foundUser = this.repository.findEntityById(USER_ID);

        assertNotNull(foundUser);
        assertEquals(USER_ID, foundUser.getId());
        assertEquals(USER_NAME, foundUser.getUsername());
        assertEquals(USER_EMAIL, foundUser.getEmail());
        assertEquals(UserProfile.DONOR, foundUser.getUserProfile());
        assertEquals(USER_PASSWORD, foundUser.getUserPassword());
    }

    @Test
    void shouldReturnNullWhenUserDoesNotExistInRepository() {
        UserContract foundUser = this.repository.findEntityById(USER_ID);

        assertNull(foundUser);
    }

    @Test
    void verifyExceptionThrownWhenInvalidIdIsProviedToFindEntityById() {
        String invalidId = "invalidId";
        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.repository.findEntityById(invalidId));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserRepository.INVALID_UUID_FORMAT_MESSAGE).formatErrorMessage(invalidId), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(UuidUtilsException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void verifyExceptionThrownWhenEmptyIdIsProvidedToFindEntityById(String value) {
        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.repository.findEntityById(value));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserRepository.INVALID_ID_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void verifyExceptionThrownWhenNullIdIsProvidedToFindEntityById() {
        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.repository.findEntityById(null));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserRepository.INVALID_ID_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldRetrieveUserContract() {
        this.persistEntity(new UserEntityMapper(), this.user);

        List<UserContract> userContracts = this.repository.retrieveAll();

        assertNotNull(userContracts);
        assertFalse(userContracts.isEmpty());
        assertEquals(1, userContracts.size());
    }

    @Test
    void shouldReturnEmptyListWhenNoUsersPersisted() {
        List<UserContract> userContracts = this.repository.retrieveAll();

        assertNotNull(userContracts);
        assertTrue(userContracts.isEmpty());
    }

    @Test
    @SneakyThrows
    void shouldReturnEmptyListWhenNullPassedToMapEntityList() {
        Method method = UserRepository.class.getDeclaredMethod("mapEntityList", List.class);
        method.setAccessible(true);

        List<UserContract> userContracts = (List<UserContract>) method.invoke(this.repository, new Object[]{null});

        assertNotNull(userContracts);
        assertTrue(userContracts.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsNull() {
        UserEntity userEntity = new UserEntityMapper().mapFrom(this.user);
        userEntity.setUserName(null);
        this.persistEntity(userEntity);

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.repository.retrieveAll());

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(MapperStrategy.CLASS_MAPPING_FAILURE).formatErrorMessage(UserEntity.class.getSimpleName()), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldPersistAndRetrieveUserAttributesCorrectly() {
        UserContract persisted = this.repository.persist(this.user);

        assertNotNull(persisted);
        assertEquals(USER_ID, persisted.getId());
        assertEquals(USER_NAME, persisted.getUsername());
        assertEquals(USER_EMAIL, persisted.getEmail());
        assertEquals(UserProfile.DONOR, persisted.getUserProfile());
        assertEquals(USER_PASSWORD, persisted.getUserPassword());
    }

    @Test
    void shouldThrowExceptionWhenPersistNullUser() {
        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.repository.persist(null));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserRepository.REQUIRED_USER_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldDeleteUserEntityCorrectly() {
        this.persistEntity(new UserEntityMapper(), this.user);

        this.repository.deleteEntityById(USER_ID);

        assertNull(this.repository.findById(USER_ID).orElse(null));
    }

    @Test
    void shouldNotAffectDatabaseWhenAttemptToDeleteNonExistingUserEntity() {
        this.persistEntity(new UserEntityMapper(), this.user);
        String uuidNonexistent = "777e9377-526d-402c-be93-77526d402cd3";

        this.repository.deleteEntityById(uuidNonexistent);

        assertNull(this.repository.findById(uuidNonexistent).orElse(null));
        assertNotNull(this.repository.findById(USER_ID).orElse(null));
    }

    @Test
    void shouldThrowExceptionWhenDeletingEntityWithNullId() {

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.repository.deleteEntityById(null));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserRepository.INVALID_ID_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void shouldThrowExceptionWhenDeletingEntityWithInvalidId(String id) {

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.repository.deleteEntityById(id));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserRepository.INVALID_ID_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenDeletingEntityWithNullIdAndCatchUuidException() {
        String invalidId = "777e9377";

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.repository.deleteEntityById(invalidId));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserRepository.INVALID_UUID_FORMAT_MESSAGE).formatErrorMessage(invalidId), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(UuidUtilsException.class, exception.getCause().getClass());
    }

    @Test
    void shouldFindUserContractByEmail() {
        this.persistEntity(new UserEntityMapper(), this.user);

        UserContract foundUser = this.repository.findUserEntityByUserEmail(USER_EMAIL);

        assertNotNull(foundUser);
        assertEquals(USER_ID, foundUser.getId());
        assertEquals(USER_NAME, foundUser.getUsername());
        assertEquals(USER_EMAIL, foundUser.getEmail());
        assertEquals(UserProfile.DONOR, foundUser.getUserProfile());
        assertEquals(USER_PASSWORD, foundUser.getUserPassword());
    }

    @Test
    void shouldNotFindNonExistingUser() {
        this.persistEntity(new UserEntityMapper(), this.user);

        UserContract foundUser = this.repository.findUserEntityByUserEmail("USER_EMAIL");

        assertNull(foundUser);
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        this.persistEntity(new UserEntityMapper(), this.user);

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class,
                () -> this.repository.findUserEntityByUserEmail(null));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(29).formatErrorMessage(), exception.getMessage());
    }

}
