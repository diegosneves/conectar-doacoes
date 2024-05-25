package diegosneves.github.conectardoacoes.core.domain.user.entity;

import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.UserCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;

public class User implements UserContract {

    public static final String USERNAME_REQUIRED = "O nome do %s não deve ser nulo ou vazio";
    public static final String PROFILE_NOT_PROVIDED = "O perfil do usuário deve ser informado";
    public static final String USER_ID_REQUIRED = "O ID do usuário deve ser informado";
    public static final String EMAIL_NOT_PROVIDED = "O Email deve ser informado";
    public static final String PASSWORD_NOT_PROVIDED = "O Password deve ser informado";

    private final String id;
    private String userName;
    private final String email;
    private final UserProfile userProfile;
    private String userPassword;

    public User(String id, String userName, String email, UserProfile userProfile, String userPassword) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.userProfile = userProfile;
        this.userPassword = userPassword;
        this.validateData();
    }

    private void validateData() throws UserCreationFailureException {
        if (this.userProfile == null) {
            throw new UserCreationFailureException(PROFILE_NOT_PROVIDED);
        }
        if (this.userName == null || this.userName.isBlank()) {
            throw new UserCreationFailureException(String.format(USERNAME_REQUIRED, this.userProfile));
        }
        try {
            UuidUtils.isValidUUID(this.id);
        } catch (UuidUtilsException e) {
            throw new UserCreationFailureException(USER_ID_REQUIRED, e);
        }
        if (this.email == null || this.email.isBlank()) {
            throw new UserCreationFailureException(EMAIL_NOT_PROVIDED);
        }
        if (this.userPassword == null || this.userPassword.isBlank()) {
            throw new UserCreationFailureException(PASSWORD_NOT_PROVIDED);
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public String getUserPassword() {
        return this.userPassword;
    }

    @Override
    public UserProfile getUserProfile() {
        return this.userProfile;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void changeUserPassword(String password) throws UserCreationFailureException {
        if (password == null || password.isBlank()) {
            throw new UserCreationFailureException(PASSWORD_NOT_PROVIDED);
        }
        this.userPassword = password;
    }

    @Override
    public void changeUserName(String updatedUsername) {
        if (updatedUsername == null || updatedUsername.isBlank()) {
            throw new UserCreationFailureException(String.format(USERNAME_REQUIRED, this.userProfile));
        }
        this.userName = updatedUsername;
    }
}
