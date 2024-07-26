package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.UserRepository;
import diegosneves.github.conectardoacoes.adapters.rest.request.UserEntityCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.UserEntityCreatedResponse;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.UserCreationFailureException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    private UserEntity userEntity;
    private UserEntityCreationRequest request;

    @BeforeEach
    void setUp() {
        this.userEntity = UserEntity.builder()
                .id(USER_ID)
                .userName(USERNAME)
                .email(USER_EMAIL)
                .userProfile(UserProfileType.BENEFICIARY)
                .userPassword(USER_PASSWORD)
                .build();

        this.request = UserEntityCreationRequest.builder()
                .userName(USERNAME)
                .email(USER_EMAIL)
                .userProfile(UserProfileType.DONOR)
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
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.EMAIL_NOT_FOUND_ERROR_MESSAGE).formatErrorMessage(email), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        String email = "";
        when(this.userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.ofNullable(this.userEntity));

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.searchUserByEmail(email));

        verify(this.userRepository, never()).findByEmail(anyString());

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.INVALID_EMAIL_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        when(this.userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.ofNullable(this.userEntity));

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.searchUserByEmail(null));

        verify(this.userRepository, never()).findByEmail(anyString());

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.INVALID_EMAIL_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateUserEntity() {
        this.userEntity.setUserProfile(UserProfileType.DONOR);
        when(this.userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.empty());
        when(this.userRepository.save(any(UserEntity.class))).thenReturn(this.userEntity);

        UserEntityCreatedResponse actual = this.userEntityService.createUserEntity(this.request);

        verify(this.userRepository, times(1)).findByEmail(USER_EMAIL);
        verify(this.userRepository, times(1)).save(this.userEntityCaptor.capture());

        assertNotNull(actual);
        UserEntity captorValue = this.userEntityCaptor.getValue();
        assertNotNull(captorValue);
        assertTrue(UuidUtils.isValidUUID(captorValue.getId()));
        assertEquals(USERNAME, captorValue.getUserName());
        assertEquals(USER_EMAIL, captorValue.getEmail());
        assertEquals(USER_PASSWORD, captorValue.getUserPassword());
        assertEquals(UserProfileType.DONOR, captorValue.getUserProfile());
        assertEquals(USER_ID, actual.getId());
        assertEquals(USERNAME, actual.getUserName());
        assertEquals(USER_EMAIL, actual.getEmail());
        assertEquals(UserProfileType.DONOR, actual.getUserProfile());
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        when(this.userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.ofNullable(this.userEntity));

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.createUserEntity(this.request));

        verify(this.userRepository, times(1)).findByEmail(USER_EMAIL);
        verify(this.userRepository, never()).save(any(UserEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.EMAIL_ALREADY_IN_USE).formatErrorMessage(USER_EMAIL), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenRequestUserEmailIsNull() {
        this.request.setEmail(null);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.createUserEntity(this.request));

        verify(this.userRepository, never()).findByEmail(anyString());
        verify(this.userRepository, never()).save(any(UserEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.INVALID_EMAIL_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void shouldThrowExceptionWhenRequestUserEmailIsBlank(String requestUserEmail) {
        this.request.setEmail(requestUserEmail);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.createUserEntity(this.request));

        verify(this.userRepository, never()).findByEmail(anyString());
        verify(this.userRepository, never()).save(any(UserEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.INVALID_EMAIL_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenRequestUsernameIsNull() {
        this.request.setUserName(null);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.createUserEntity(this.request));

        verify(this.userRepository, times(1)).findByEmail(anyString());
        verify(this.userRepository, never()).save(any(UserEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.USER_CREATION_FAILURE_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void shouldThrowExceptionWhenRequestUsernameIsBlank(String requestUserValue) {
        this.request.setUserName(requestUserValue);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.createUserEntity(this.request));

        verify(this.userRepository, times(1)).findByEmail(anyString());
        verify(this.userRepository, never()).save(any(UserEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.USER_CREATION_FAILURE_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenRequestUserPasswordIsNull() {
        this.request.setUserPassword(null);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.createUserEntity(this.request));

        verify(this.userRepository, times(1)).findByEmail(anyString());
        verify(this.userRepository, never()).save(any(UserEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.USER_CREATION_FAILURE_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void shouldThrowExceptionWhenRequestUserPasswordIsBlank(String requestUserValue) {
        this.request.setUserPassword(requestUserValue);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.createUserEntity(this.request));

        verify(this.userRepository, times(1)).findByEmail(anyString());
        verify(this.userRepository, never()).save(any(UserEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.USER_CREATION_FAILURE_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(UserCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenRequestUserProfileIsNull() {
        this.request.setUserProfile(null);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.createUserEntity(this.request));

        verify(this.userRepository, times(1)).findByEmail(anyString());
        verify(this.userRepository, never()).save(any(UserEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.USER_PROFILE_VALIDATION_FAILURE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenRequestIsNull() {

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () -> this.userEntityService.createUserEntity(null));

        verify(this.userRepository, never()).findByEmail(anyString());
        verify(this.userRepository, never()).save(any(UserEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.MISSING_USER_ENTITY_REQUEST_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowUserEntityFailuresExceptionWhenTheEmailPassedIsNotFound(){

        String email = "email@teste.com";

        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () ->
                this.userEntityService.findUserByEmail(email));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.EMAIL_NOT_FOUND_ERROR_MESSAGE).formatErrorMessage(email), exception.getMessage());
        assertNull(exception.getCause());

    }

    @Test
    void shouldThrowUserEntityFailuresExceptionWhenTheEmailPassIsNull(){


        UserEntityFailuresException exception = assertThrows(UserEntityFailuresException.class, () ->
                this.userEntityService.findUserByEmail(null));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(UserEntityServiceImpl.INVALID_EMAIL_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());

    }

    @Test
    void testFindByEmail_WhenEmailIsValid_ShouldReturnUserEntityCreatedResponseObject(){

        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.ofNullable(this.userEntity));

        UserEntityCreatedResponse userByEmail = userEntityService.findUserByEmail(USER_EMAIL);

        assertNotNull(userByEmail);
        assertEquals(USER_ID, userByEmail.getId());
        assertEquals(USERNAME, userByEmail.getUserName());
        assertEquals(USER_EMAIL, userByEmail.getEmail());
        assertEquals(UserProfileType.BENEFICIARY, userByEmail.getUserProfile());

        verify(userRepository, times(1)).findByEmail(USER_EMAIL);

    }

}
