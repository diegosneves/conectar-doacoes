package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.UserRepository;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserEntityServiceImplTest {

    public static final String USER_ID = "f5f3c02d-2863-46a0-b3c0-2d286336a0a2";
    public static final String USERNAME = "Fulano";
    public static final String USER_EMAIL = "email@test.com";
    public static final String USER_PASSWORD = "Senha";


    @InjectMocks
    private UserEntityServiceImpl userEntityService;

    @Mock
    private UserRepository userRepository;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        this.userEntity = UserEntity.builder()
                .id(USER_ID)
                .userName(USERNAME)
                .email(USER_EMAIL)
                .userProfile(UserProfileType.BENEFICIARY)
                .userPassword(USER_PASSWORD)
                .build();
    }

    @Test
    void shouldReturnUserEntityByEmail() {
        when(this.userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.ofNullable(this.userEntity));

        User entity = (User) this.userEntityService.searchUserByEmail("email@test.com");

        verify(this.userRepository, times(1)).findByEmail(anyString());

        assertNotNull(entity);
        assertEquals(USER_ID, entity.getId());
        assertEquals(USERNAME, entity.getUsername());
        assertEquals(USER_EMAIL, entity.getEmail());
        assertEquals(UserProfile.BENEFICIARY, entity.getUserProfile());
        assertEquals(USER_PASSWORD, entity.getUserPassword());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        String email = "email@teste.com";
        when(this.userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.ofNullable(this.userEntity));

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.searchUserByEmail(email));

        verify(this.userRepository, times(1)).findByEmail(anyString());

        assertNotNull(exception);
        assertEquals(UserEntityFailuresException.ERROR.formatErrorMessage(String.format(UserEntityServiceImpl.EMAIL_NOT_FOUND_ERROR_MESSAGE, email)), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        String email = "";
        when(this.userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.ofNullable(this.userEntity));

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.searchUserByEmail(email));

        verify(this.userRepository, never()).findByEmail(anyString());

        assertNotNull(exception);
        assertEquals(UserEntityFailuresException.ERROR.formatErrorMessage(UserEntityServiceImpl.INVALID_EMAIL_ERROR_MESSAGE), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        when(this.userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.ofNullable(this.userEntity));

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.searchUserByEmail(null));

        verify(this.userRepository, never()).findByEmail(anyString());

        assertNotNull(exception);
        assertEquals(UserEntityFailuresException.ERROR.formatErrorMessage(UserEntityServiceImpl.INVALID_EMAIL_ERROR_MESSAGE), exception.getMessage());
        assertNull(exception.getCause());
    }

}
