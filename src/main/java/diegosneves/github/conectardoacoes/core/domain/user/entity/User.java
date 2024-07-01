package diegosneves.github.conectardoacoes.core.domain.user.entity;

import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.UserCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;

/**
 * Representa um usuário dentro do sistema.
 * <p>
 * Esta classe é responsável por manter as informações de um usuário e valida a criação dos mesmos.
 * As informações de um usuário incluem seu nome de usuário, perfil de usuário, e-mail e senha. Além disso,
 * cada usuário também possui um identificador único do tipo {@link java.util.UUID UUID}.
 * </p>
 * <p>
 * Cada usuário tem a capacidade de alterar a sua própria senha bem como o nome de usuário.
 * </p>
 * <h2>Estrutura de erros</h2>
 * <p>
 * Caso os dados do usuário falhem na validação, essa classe está preparada para lidar com esses problemas
 * e gera uma {@link UserCreationFailureException} adequada.
 * </p>
 * <h2>Nota sobre a implementação</h2>
 * <p>
 * Esta classe implementa a interface {@link UserContract}, que define o contrato para criação e manuseio dos usuários.
 * Além disso, faz uso efetivo da classe {@link UserProfile} para definir o perfil dos usuários.
 * </p>
 * <p>
 * As mensagens de erro de validação estão definidas como constantes para manter a classe limpa e dry (Don't Repeat Yourself).
 * </p>
 *
 * @author diegoneves
 * @since 1.0.0
 */
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

    /**
     * Valida os campos necessários para a criação de um novo Usuário.
     * <p>
     * Este método verifica as seguintes condições:
     * <ul>
     *   <li>Se o perfil do usuário está definido</li>
     *   <li>Se o nome do usuário está definido e não está em branco</li>
     *   <li>Se o ID do usuário é um UUID válido</li>
     *   <li>Se o email do usuário está definido e não está em branco</li>
     *   <li>Se a senha do usuário está definida e não está em branco</li>
     * </ul>
     * </p>
     * Se algum dos campos anteriores estiver ausente, em branco ou inválido,
     * este método lançará uma exceção {@link UserCreationFailureException} com uma mensagem
     * de erro adequada.
     *
     * @throws UserCreationFailureException lançada quando campo obrigatório está
     *                                      faltando, em branco ou é inválido
     */
    private void validateData() throws UserCreationFailureException {
        ValidationUtils.validateNotNullOrEmpty(this.userProfile, PROFILE_NOT_PROVIDED, UserCreationFailureException.class);
        ValidationUtils.validateNotNullOrEmpty(this.userName, String.format(USERNAME_REQUIRED, this.userProfile), UserCreationFailureException.class);
        try {
            UuidUtils.isValidUUID(this.id);
        } catch (UuidUtilsException e) {
            throw new UserCreationFailureException(USER_ID_REQUIRED, e);
        }
        ValidationUtils.validateNotNullOrEmpty(this.email, EMAIL_NOT_PROVIDED, UserCreationFailureException.class);
        ValidationUtils.validateNotNullOrEmpty(this.userPassword, PASSWORD_NOT_PROVIDED, UserCreationFailureException.class);
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
        ValidationUtils.validateNotNullOrEmpty(password, PASSWORD_NOT_PROVIDED, UserCreationFailureException.class);
        this.userPassword = password;
    }

    @Override
    public void changeUserName(String updatedUsername) {
        ValidationUtils.validateNotNullOrEmpty(updatedUsername, String.format(USERNAME_REQUIRED, this.userProfile), UserCreationFailureException.class);
        this.userName = updatedUsername;
    }
}
