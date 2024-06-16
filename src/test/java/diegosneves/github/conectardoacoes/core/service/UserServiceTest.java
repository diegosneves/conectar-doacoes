package diegosneves.github.conectardoacoes.core.service;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.ShelterContract;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.domain.user.shared.repository.UserRepository;
import diegosneves.github.conectardoacoes.core.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UserCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UserServiceFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    public static final String USER_IDENTIFIER = "89142bda-7b0c-4421-af28-f9cadb316024";
    public static final String USER_UUID = "0ed6e6a1-882c-4d2d-83ee-67034ee1ab9f";
    public static final String USERNAME = "Fulano";
    public static final String USER_EMAIL = "teste@email.com";
    public static final String USER_PASSWORD = "senha";

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    private User user;

    @BeforeEach
    void setUp() {
        this.user = new User(USER_UUID, USERNAME, USER_EMAIL, UserProfile.BENEFICIARY, USER_PASSWORD);
    }

    @Test
    void shouldCreateUserAndReturnCreatedUser() {
        when(this.userRepository.save(any(UserContract.class))).thenReturn(this.user);

        UserContract actual = this.userService.createUser(USERNAME, USER_EMAIL, UserProfile.BENEFICIARY, USER_PASSWORD);

        verify(this.userRepository, times(1)).save(this.userCaptor.capture());

        assertNotNull(this.userCaptor.getValue());
        User returnedUser = this.userCaptor.getValue();
        assertNotNull(actual);
        assertTrue(UuidUtils.isValidUUID(returnedUser.getId()));
        assertEquals(USER_UUID, actual.getId());
        assertEquals(USERNAME, returnedUser.getUsername());
        assertEquals(USERNAME, actual.getUsername());
        assertEquals(USER_EMAIL, returnedUser.getEmail());
        assertEquals(USER_EMAIL, actual.getEmail());
        assertEquals(UserProfile.BENEFICIARY, returnedUser.getUserProfile());
        assertEquals(UserProfile.BENEFICIARY, actual.getUserProfile());
        assertEquals(USER_PASSWORD, returnedUser.getUserPassword());
        assertEquals(USER_PASSWORD, actual.getUserPassword());
    }

    @Test
    void shouldThrowUserCreationFailureExceptionWhenUsernameIsNull() {

        Exception actual = assertThrows(Exception.class,
                () -> this.userService.createUser(null, USER_EMAIL, UserProfile.BENEFICIARY, USER_PASSWORD));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.class, actual.getClass());
    }

    @Test
    void shouldThrowUserCreationFailureExceptionWhenUsernameIsEmpty() {

        Exception actual = assertThrows(Exception.class,
                () -> this.userService.createUser(" ", USER_EMAIL, UserProfile.BENEFICIARY, USER_PASSWORD));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.class, actual.getClass());
    }

    @Test
    void shouldThrowUserCreationFailureExceptionWhenUserEmailIsNull() {

        Exception actual = assertThrows(Exception.class,
                () -> this.userService.createUser(USERNAME, null, UserProfile.BENEFICIARY, USER_PASSWORD));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.class, actual.getClass());
    }

    @Test
    void shouldThrowUserCreationFailureExceptionWhenUserEmailIsEmpty() {

        Exception actual = assertThrows(Exception.class,
                () -> this.userService.createUser(USERNAME, "", UserProfile.BENEFICIARY, USER_PASSWORD));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.class, actual.getClass());
    }

    @Test
    void shouldThrowUserCreationFailureExceptionWhenUserProfileIsNull() {

        Exception actual = assertThrows(Exception.class,
                () -> this.userService.createUser(USERNAME, USER_EMAIL, null, USER_PASSWORD));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.class, actual.getClass());
    }

    @Test
    void shouldThrowUserCreationFailureExceptionWhenUserPasswordIsNull() {

        Exception actual = assertThrows(Exception.class,
                () -> this.userService.createUser(USERNAME, USER_EMAIL, UserProfile.BENEFICIARY, null));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.class, actual.getClass());
    }

    @Test
    void shouldThrowUserCreationFailureExceptionWhenUserPasswordIsEmpty() {

        Exception actual = assertThrows(Exception.class,
                () -> this.userService.createUser(USERNAME, USER_EMAIL, UserProfile.BENEFICIARY, " "));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.class, actual.getClass());
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsNullOnCreateUser() {

        UserCreationFailureException actual = assertThrows(UserCreationFailureException.class,
                () -> this.userService.createUser(null, USER_EMAIL, UserProfile.BENEFICIARY, USER_PASSWORD));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(String.format(User.USERNAME_REQUIRED, UserProfile.BENEFICIARY)), actual.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsEmptyOnCreateUser() {

        UserCreationFailureException actual = assertThrows(UserCreationFailureException.class,
                () -> this.userService.createUser(" ", USER_EMAIL, UserProfile.BENEFICIARY, USER_PASSWORD));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(String.format(User.USERNAME_REQUIRED, UserProfile.BENEFICIARY)), actual.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNullOnCreateUser() {

        UserCreationFailureException actual = assertThrows(UserCreationFailureException.class,
                () -> this.userService.createUser(USERNAME, null, UserProfile.BENEFICIARY, USER_PASSWORD));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.EMAIL_NOT_PROVIDED), actual.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmptyOnCreateUser() {

        UserCreationFailureException actual = assertThrows(UserCreationFailureException.class,
                () -> this.userService.createUser(USERNAME, "", UserProfile.BENEFICIARY, USER_PASSWORD));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.EMAIL_NOT_PROVIDED), actual.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserProfileIsNullOnCreateUser() {

        UserCreationFailureException actual = assertThrows(UserCreationFailureException.class,
                () -> this.userService.createUser(USERNAME, USER_EMAIL, null, USER_PASSWORD));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.PROFILE_NOT_PROVIDED), actual.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNullOnCreateUser() {

        UserCreationFailureException actual = assertThrows(UserCreationFailureException.class,
                () -> this.userService.createUser(USERNAME, USER_EMAIL, UserProfile.BENEFICIARY, null));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.PASSWORD_NOT_PROVIDED), actual.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsEmptyOnCreateUser() {

        UserCreationFailureException actual = assertThrows(UserCreationFailureException.class,
                () -> this.userService.createUser(USERNAME, USER_EMAIL, UserProfile.BENEFICIARY, "   "));

        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(actual);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.PASSWORD_NOT_PROVIDED), actual.getMessage());
    }

    @Test
    void shouldGetUserById() {
        when(this.userRepository.findById(USER_UUID)).thenReturn(this.user);

        UserContract actual = this.userService.getUser(USER_UUID);

        verify(this.userRepository, times(1)).findById(USER_UUID);

        assertNotNull(actual);
        assertEquals(this.user, actual);
    }

    @Test
    void shouldReturnNullWhenUserNotFoundOnGetUser() {
        when(this.userRepository.findById(USER_IDENTIFIER)).thenReturn(null);

        UserContract actual = this.userService.getUser(USER_IDENTIFIER);

        verify(this.userRepository, times(1)).findById(USER_IDENTIFIER);

        assertNull(actual);
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenNullUserIdProvidedOnGetUser() {

        UserServiceFailureException actual = assertThrows(UserServiceFailureException.class, () -> this.userService.getUser(null));

        verify(this.userRepository, never()).findById(anyString());

        assertNotNull(actual);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.INVALID_IDENTIFIER_ERROR_MESSAGE), actual.getMessage());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenEmptyUserIdProvidedOnGetUser() {

        UserServiceFailureException actual = assertThrows(UserServiceFailureException.class, () -> this.userService.getUser("   "));

        verify(this.userRepository, never()).findById(anyString());

        assertNotNull(actual);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.INVALID_IDENTIFIER_ERROR_MESSAGE), actual.getMessage());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenInvalidUserIdProvidedOnGetUser() {

        UserServiceFailureException actual = assertThrows(UserServiceFailureException.class, () -> this.userService.getUser("id"));

        verify(this.userRepository, never()).findById(anyString());

        assertNotNull(actual);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.INVALID_IDENTIFIER_ERROR_MESSAGE), actual.getMessage());
        assertEquals(UuidUtilsException.class, actual.getCause().getClass());
    }

    @Test
    void shouldChangeUserPasswordAndPersistTheChange() {
        String newPassword = "newPassword";
        when(this.userRepository.findById(USER_UUID)).thenReturn(this.user);

        this.userService.changePassword(USER_UUID, newPassword);

        verify(this.userRepository, times(1)).findById(USER_UUID);
        verify(this.userRepository, times(1)).save(this.userCaptor.capture());

        assertNotNull(userCaptor.getValue());
        User updatedUser = userCaptor.getValue();
        assertEquals(USER_UUID, updatedUser.getId());
        assertEquals(USERNAME, updatedUser.getUsername());
        assertEquals(USER_EMAIL, updatedUser.getEmail());
        assertEquals(newPassword, updatedUser.getUserPassword());
        assertEquals(UserProfile.BENEFICIARY, updatedUser.getUserProfile());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenNewPasswordIsNull() {

        UserServiceFailureException exception = assertThrows(UserServiceFailureException.class,
                () -> this.userService.changePassword(USER_UUID, null));

        verify(this.userRepository, never()).findById(USER_UUID);
        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(exception);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.INVALID_NEW_PASSWORD_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenNewPasswordIsEmpty() {
        String newPassword = " ";

        UserServiceFailureException exception = assertThrows(UserServiceFailureException.class,
                () -> this.userService.changePassword(USER_UUID, newPassword));

        verify(this.userRepository, never()).findById(USER_UUID);
        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(exception);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.INVALID_NEW_PASSWORD_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenUserNotFound() {
        String newPassword = "newPassword";
        when(this.userRepository.findById(USER_UUID)).thenReturn(null);

        UserServiceFailureException exception = assertThrows(UserServiceFailureException.class,
                () -> this.userService.changePassword(USER_UUID, newPassword));

        verify(this.userRepository, times(1)).findById(USER_UUID);
        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(exception);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.USER_NOT_FOUND_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenNullUserIdProvidedOnChangePassword() {

        UserServiceFailureException actual = assertThrows(UserServiceFailureException.class, () -> this.userService.changePassword(null, "newPassword"));

        verify(this.userRepository, never()).findById(anyString());

        assertNotNull(actual);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.INVALID_IDENTIFIER_ERROR_MESSAGE), actual.getMessage());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenEmptyUserIdProvidedOnChangePassword() {

        UserServiceFailureException actual = assertThrows(UserServiceFailureException.class, () -> this.userService.changePassword("   ", "newPassword"));

        verify(this.userRepository, never()).findById(anyString());

        assertNotNull(actual);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.INVALID_IDENTIFIER_ERROR_MESSAGE), actual.getMessage());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenInvalidUserIdProvidedOnChangePassword() {

        UserServiceFailureException actual = assertThrows(UserServiceFailureException.class, () -> this.userService.changePassword("Invalid", "newPassword"));

        verify(this.userRepository, never()).findById(anyString());

        assertNotNull(actual);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.INVALID_IDENTIFIER_ERROR_MESSAGE), actual.getMessage());
        assertEquals(UuidUtilsException.class, actual.getCause().getClass());
    }

    @Test
    void shouldChangeUsernameAndPersistTheChange() {
        String newUsername = "newUsername";
        when(this.userRepository.findById(USER_UUID)).thenReturn(this.user);

        this.userService.changeUserName(USER_UUID, newUsername);

        verify(this.userRepository, times(1)).findById(USER_UUID);
        verify(this.userRepository, times(1)).save(this.userCaptor.capture());

        assertNotNull(userCaptor.getValue());
        User updatedUser = userCaptor.getValue();
        assertEquals(USER_UUID, updatedUser.getId());
        assertEquals(newUsername, updatedUser.getUsername());
        assertEquals(USER_EMAIL, updatedUser.getEmail());
        assertEquals(USER_PASSWORD, updatedUser.getUserPassword());
        assertEquals(UserProfile.BENEFICIARY, updatedUser.getUserProfile());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenNewUsernameIsNull() {

        UserServiceFailureException exception = assertThrows(UserServiceFailureException.class,
                () -> this.userService.changeUserName(USER_UUID, null));

        verify(this.userRepository, never()).findById(USER_UUID);
        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(exception);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.USERNAME_INVALID_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenNewUsernameIsEmpty() {
        String newUsername = " ";

        UserServiceFailureException exception = assertThrows(UserServiceFailureException.class,
                () -> this.userService.changeUserName(USER_UUID, newUsername));

        verify(this.userRepository, never()).findById(USER_UUID);
        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(exception);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.USERNAME_INVALID_ERROR_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenUserNotFoundOnChangeUsername() {
        String newUsername = "newUsername";
        when(this.userRepository.findById(USER_UUID)).thenReturn(null);

        UserServiceFailureException exception = assertThrows(UserServiceFailureException.class,
                () -> this.userService.changeUserName(USER_UUID, newUsername));

        verify(this.userRepository, times(1)).findById(USER_UUID);
        verify(this.userRepository, never()).save(any(UserContract.class));

        assertNotNull(exception);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.USER_NOT_FOUND_MESSAGE), exception.getMessage());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenNullUserIdProvidedOnChangeUsername() {

        UserServiceFailureException actual = assertThrows(UserServiceFailureException.class,
                () -> this.userService.changeUserName(null, "newUsername"));

        verify(this.userRepository, never()).findById(anyString());

        assertNotNull(actual);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.INVALID_IDENTIFIER_ERROR_MESSAGE), actual.getMessage());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenEmptyUserIdProvidedOnChangeUsername() {

        UserServiceFailureException actual = assertThrows(UserServiceFailureException.class,
                () -> this.userService.changeUserName("   ", "newUsername"));

        verify(this.userRepository, never()).findById(anyString());

        assertNotNull(actual);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.INVALID_IDENTIFIER_ERROR_MESSAGE), actual.getMessage());
    }

    @Test
    void shouldThrowUserServiceFailureExceptionWhenInvalidUserIdProvidedOnChangeUsername() {

        UserServiceFailureException actual = assertThrows(UserServiceFailureException.class,
                () -> this.userService.changeUserName("ID", "newUsername"));

        verify(this.userRepository, never()).findById(anyString());

        assertNotNull(actual);
        assertEquals(UserServiceFailureException.ERROR.buildMessage(UserService.INVALID_IDENTIFIER_ERROR_MESSAGE), actual.getMessage());
        assertEquals(UuidUtilsException.class, actual.getCause().getClass());
    }

}
