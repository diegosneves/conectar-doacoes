package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.UserCreationFailureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    public static final String USER_UUID = "a0b93a5a-be45-4339-b93a-5abe4523393a";
    public static final String USERNAME = "Usuario";
    public static final String USER_EMAIL = "email@teste.com";
    public static final String USER_PASSWORD = "Senha";

    private UserMapper userMapper;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        this.userMapper = new UserMapper();
        this.userEntity = UserEntity.builder()
                .id(USER_UUID)
                .userName(USERNAME)
                .email(USER_EMAIL)
                .userProfile(UserProfileType.DONOR)
                .userPassword(USER_PASSWORD)
                .build();
    }

    @Test
    void shouldMapUserEntityToUser() {
        UserContract user = this.userMapper.mapFrom(this.userEntity);

        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());
        assertEquals(USER_UUID, user.getId());
        assertEquals(USER_EMAIL, user.getEmail());
        assertEquals(UserProfile.DONOR, user.getUserProfile());
        assertEquals(USER_PASSWORD, user.getUserPassword());
    }

    @Test
    void shouldThrowExceptionWhenUserEntityIsNull() {
        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userMapper.mapFrom(null));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(MapperStrategy.CLASS_MAPPING_FAILURE).formatErrorMessage(UserMapper.USER_ENTITY_CLASS.getSimpleName()), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserProfileTypeIsNull() {
        this.userEntity.setUserProfile(null);

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userMapper.mapFrom(this.userEntity));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(MapperStrategy.CLASS_MAPPING_FAILURE).formatErrorMessage(UserMapper.USER_ENTITY_CLASS.getSimpleName()), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsNull() {
        this.userEntity.setUserName(null);

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userMapper.mapFrom(this.userEntity));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(MapperStrategy.CLASS_MAPPING_FAILURE).formatErrorMessage(UserMapper.USER_ENTITY_CLASS.getSimpleName()), exception.getMessage());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsEmpty() {
        this.userEntity.setUserName(" ");

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userMapper.mapFrom(this.userEntity));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(MapperStrategy.CLASS_MAPPING_FAILURE).formatErrorMessage(UserMapper.USER_ENTITY_CLASS.getSimpleName()), exception.getMessage());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenUserEmailIsNull() {
        this.userEntity.setEmail(null);

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userMapper.mapFrom(this.userEntity));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(MapperStrategy.CLASS_MAPPING_FAILURE).formatErrorMessage(UserMapper.USER_ENTITY_CLASS.getSimpleName()), exception.getMessage());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenUserEmailIsEmpty() {
        this.userEntity.setEmail("");

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userMapper.mapFrom(this.userEntity));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(MapperStrategy.CLASS_MAPPING_FAILURE).formatErrorMessage(UserMapper.USER_ENTITY_CLASS.getSimpleName()), exception.getMessage());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenUserPasswordIsNull() {
        this.userEntity.setUserPassword(null);

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userMapper.mapFrom(this.userEntity));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(MapperStrategy.CLASS_MAPPING_FAILURE).formatErrorMessage(UserMapper.USER_ENTITY_CLASS.getSimpleName()), exception.getMessage());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenUserPasswordIsEmpty() {
        this.userEntity.setUserPassword(" ");

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userMapper.mapFrom(this.userEntity));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(MapperStrategy.CLASS_MAPPING_FAILURE).formatErrorMessage(UserMapper.USER_ENTITY_CLASS.getSimpleName()), exception.getMessage());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenUserEntityIdIsNull() {
        this.userEntity.setId(null);

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userMapper.mapFrom(this.userEntity));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(MapperStrategy.CLASS_MAPPING_FAILURE).formatErrorMessage(UserMapper.USER_ENTITY_CLASS.getSimpleName()), exception.getMessage());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "null"})
    void shouldThrowExceptionWhenUserEntityIdIsBlackOrInvalid(String value) {
        this.userEntity.setId(value);

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userMapper.mapFrom(this.userEntity));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(MapperStrategy.CLASS_MAPPING_FAILURE).formatErrorMessage(UserMapper.USER_ENTITY_CLASS.getSimpleName()), exception.getMessage());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

}
