package diegosneves.github.conectardoacoes.core.domain.user.entity;

import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.domain.user.factory.UserFactory;
import diegosneves.github.conectardoacoes.core.exception.UserCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserTest {

    private static final String UUID_TEST = "4658a51c-3840-453e-bc69-e2b4cff191a4";

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCorrectlyCreateAndValidateUserWithGivenParameters() {
        User user = UserFactory.create("Nome", "email", UserProfile.DONOR, "senha");

        assertTrue(UuidUtils.isValidUUID(user.getId()));
        assertEquals("Nome", user.getUsername());
        assertEquals("email", user.getEmail());
        assertEquals(UserProfile.DONOR, user.getUserProfile());
        assertEquals("senha", user.getUserPassword());
    }

    @Test
    void shouldThrowExceptionForInvalidUserIdDuringUserCreation() {
        String value = "";
        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () -> new User(value, "Nome", "email", UserProfile.DONOR, "senha"));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.USER_ID_REQUIRED), exception.getMessage());
        assertEquals(UuidUtilsException.ERROR.buildMessage(value), exception.getCause().getMessage());
    }

    @Test
    void shouldThrowExceptionOnInvalidUserId() {
        String value = "8789-asdf";
        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () -> new User(value, "Nome", "email", UserProfile.DONOR, "senha"));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.USER_ID_REQUIRED), exception.getMessage());
        assertEquals(UuidUtilsException.ERROR.buildMessage(value), exception.getCause().getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserIdIsNullDuringUserCreation() {
        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () -> new User(null, "Nome", "email", UserProfile.DONOR, "senha"));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.USER_ID_REQUIRED), exception.getMessage());
        assertEquals(UuidUtilsException.ERROR.buildMessage(UuidUtils.UUID_REQUIRED), exception.getCause().getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsNullDuringUserCreation() {
        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () -> new User(UUID_TEST, null, "email", UserProfile.DONOR, "senha"));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(String.format(User.USERNAME_REQUIRED, UserProfile.DONOR)), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsEmptyDuringUserCreation() {
        String value = " ";
        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () -> new User(UUID_TEST, value, "email", UserProfile.DONOR, "senha"));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(String.format(User.USERNAME_REQUIRED, UserProfile.DONOR)), exception.getMessage());
    }

    @Test
    void shouldThrowUserCreationFailureExceptionWhenEmailIsNotProvided() {
        String value = " ";
        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () -> new User(UUID_TEST, "value", value, UserProfile.DONOR, "senha"));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.EMAIL_NOT_PROVIDED), exception.getMessage());
    }

    @Test
    void shouldThrowUserCreationFailureExceptionWhenEmailIsNull() {
        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () -> new User(UUID_TEST, "value", null, UserProfile.DONOR, "senha"));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.EMAIL_NOT_PROVIDED), exception.getMessage());
    }

    @Test
    void shouldThrowUserCreationFailureExceptionWhenUserProfileIsNotProvided() {
        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () -> new User(UUID_TEST, "value", "email", null, "senha"));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.PROFILE_NOT_PROVIDED), exception.getMessage());
    }

    @Test
    void shouldThrowUserCreationFailureExceptionWhenPasswordIsBlank() {
        String value = " ";
        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () -> new User(UUID_TEST, "value", "value", UserProfile.DONOR, value));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.PASSWORD_NOT_PROVIDED), exception.getMessage());
    }

    @Test
    void shouldThrowUserCreationFailureExceptionWhenPasswordIsNull() {
        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () -> new User(UUID_TEST, "value", "value", UserProfile.DONOR, null));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.PASSWORD_NOT_PROVIDED), exception.getMessage());
    }

    @Test
    void shouldChangeUserPasswordSuccessfully() {
        String newPassword = "newPassword";
        User user = new User(UUID_TEST, "Nome", "email", UserProfile.BENEFICIARY, "senha");

        user.changeUserPassword(newPassword);

        assertEquals(newPassword, user.getUserPassword());
    }

    @Test
    void shouldThrowExceptionOnPasswordChangeWithBlankInput() {
        String newPassword = " ";
        User user = new User(UUID_TEST, "Nome", "email", UserProfile.BENEFICIARY, "senha");

        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () ->  user.changeUserPassword(newPassword));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.PASSWORD_NOT_PROVIDED), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionOnPasswordChangeWithNullInput() {
        User user = new User(UUID_TEST, "Nome", "email", UserProfile.BENEFICIARY, "senha");

        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () ->  user.changeUserPassword(null));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(User.PASSWORD_NOT_PROVIDED), exception.getMessage());
    }

    @Test
    void shouldChangeUserNameSuccessfully() {
        String newUsername = "newUsername";
        User user = new User(UUID_TEST, "Nome", "email", UserProfile.BENEFICIARY, "senha");

        user.changeUserName(newUsername);

        assertEquals(newUsername, user.getUsername());
    }

    @Test
    void shouldThrowExceptionOnNameChangeWithBlankInput() {
        String newUsername = " ";
        User user = new User(UUID_TEST, "Nome", "email", UserProfile.BENEFICIARY, "senha");

        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () ->  user.changeUserName(newUsername));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(String.format(User.USERNAME_REQUIRED, UserProfile.BENEFICIARY)), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionOnNameChangeWithNullInput() {
        User user = new User(UUID_TEST, "Nome", "email", UserProfile.BENEFICIARY, "senha");

        UserCreationFailureException exception = assertThrows(UserCreationFailureException.class, () ->  user.changeUserName(null));

        assertNotNull(exception);
        assertEquals(UserCreationFailureException.ERROR.buildMessage(String.format(User.USERNAME_REQUIRED, UserProfile.BENEFICIARY)), exception.getMessage());
    }

}
