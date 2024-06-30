package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ConstructorDefaultUndefinedException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserEntityMapperTest {

    public static final String USER_UUID = "3c489878-c78d-4117-8898-78c78d011708";
    public static final String USER_NAME = "Usuario";
    public static final String USER_EMAIL = "email@teste.com";
    public static final String USER_PASSWORD = "Senha";

    private UserEntityMapper userEntityMapper;

    private User user;

    @BeforeEach
    void setUp() {
        this.userEntityMapper = new UserEntityMapper();
        this.user = new User(USER_UUID, USER_NAME, USER_EMAIL, UserProfile.DONOR, USER_PASSWORD);
    }

    @Test
    void shouldCorrectlyMapUserToUserEntity() {
        UserEntity userEntity = this.userEntityMapper.mapFrom(this.user);

        assertNotNull(userEntity);
        assertEquals(USER_UUID, userEntity.getId());
        assertEquals(USER_NAME, userEntity.getUserName());
        assertEquals(USER_EMAIL, userEntity.getEmail());
        assertEquals(USER_PASSWORD, userEntity.getUserPassword());
        assertEquals(UserProfileType.DONOR, userEntity.getUserProfile());
    }

    @Test
    void shouldThrowExceptionWhenMappingNullUser() {
        this.user = null;

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityMapper.mapFrom(this.user));

        assertNotNull(exception);
        assertEquals(UserEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(UserEntityMapper.USER_CLASS.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowWhenBuilderMapperFailsToMapUserToUserEntity() {

        try (MockedStatic<BuilderMapper> mockedBuilder = mockStatic(BuilderMapper.class)) {

            mockedBuilder.when(() -> BuilderMapper.mapTo(UserEntity.class, this.user)).thenThrow(ConstructorDefaultUndefinedException.class);

            UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityMapper.mapFrom(this.user));

            assertNotNull(exception);
            assertEquals(UserEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(UserEntityMapper.USER_CLASS.getSimpleName())), exception.getMessage());
            assertEquals(ConstructorDefaultUndefinedException.class, exception.getCause().getClass());
        }
    }

    @Test
    void shouldThrowWhenBuilderMapperFailToMapUserToUserEntity() {

        try (MockedStatic<BuilderMapper> mockedBuilder = mockStatic(BuilderMapper.class)) {

            mockedBuilder.when(() -> BuilderMapper.mapTo(UserEntity.class, this.user)).thenThrow(MapperFailureException.class);

            UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityMapper.mapFrom(this.user));

            assertNotNull(exception);
            assertEquals(UserEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(UserEntityMapper.USER_CLASS.getSimpleName())), exception.getMessage());
            assertEquals(MapperFailureException.class, exception.getCause().getClass());
        }
    }

}
