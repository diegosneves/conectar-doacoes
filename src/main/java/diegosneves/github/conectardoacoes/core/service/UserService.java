package diegosneves.github.conectardoacoes.core.service;

import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.domain.user.factory.UserFactory;
import diegosneves.github.conectardoacoes.core.domain.user.shared.repository.UserRepository;
import diegosneves.github.conectardoacoes.core.exception.UserCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UserServiceFailureException;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;

/**
 * A classe {@link UserService} implementa um contratato do serviço do usuário {@link UserServiceContract}.
 * Ela fornece os métodos para gerenciar usuários, incluindo criação de um novo usuário, recuperação de um usuário pelo seu ID,
 * alteração da senha do usuário e alteração do nome de usuário.
 * <p>Isso é feito por meio da interação com o repositório de usuários {@link UserRepository}, onde os dados do usuário são armazenados.
 * <p>Os métodos implementados nesta classe realizam checagens de validação para garantir que os dados do usuário sejam válidos.
 * Se quaisquer dados inválidos forem fornecidos, como um ID de usuário, senha ou nome de usuário nulo ou em branco,
 * eles lançarão uma exceção {@link UserServiceFailureException}.
 *
 * @author diegoneves
 * @version 1.0.0
 * @see UserServiceContract
 * @see UserRepository
 * @see UserServiceFailureException
 */
public class UserService implements UserServiceContract {

    public static final String INVALID_IDENTIFIER_ERROR_MESSAGE = "Falha ao recuperar o usuário. Identificador fornecido possui valor inválido";
    public static final String INVALID_NEW_PASSWORD_MESSAGE = "A nova senha informada é invalida";
    public static final String USER_NOT_FOUND_MESSAGE = "Usuário não encontrado";
    public static final String USERNAME_INVALID_ERROR_MESSAGE = "O novo nome de usuário informado é inválido.";

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Cria um novo usuário com os detalhes fornecidos e armazena no repositório de usuários.
     *
     * @param username O nome de usuário para o novo usuário.
     * @param email O email para o novo usuário.
     * @param userProfile O perfil do novo usuário.
     * @param password A senha para o novo usuário.
     * @return A instância de {@link UserContract} que representa o usuário criado.
     * @throws UserCreationFailureException se alguma informação fornecida for inválida.
     */
    @Override
    public UserContract createUser(String username, String email, UserProfile userProfile, String password) throws UserCreationFailureException {
        User newUser = UserFactory.create(username, email, userProfile, password);
        return this.userRepository.save(newUser);
    }

    /**
     * Recupera um usuário pelo seu ID do repositório de usuários.
     *
     * @param userId O ID do usuário a ser recuperado.
     * @return A instância de {@link UserContract} que representa o usuário recuperado.
     * @throws UserServiceFailureException se o ID do usuário for nulo ou em branco.
     */
    @Override
    public UserContract getUser(String userId) {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(userId, INVALID_IDENTIFIER_ERROR_MESSAGE, UserServiceFailureException.class);
        return this.userRepository.findById(userId);
    }

    /**
     * Altera a senha do usuário especificado.
     *
     * @param userId O ID do usuário cuja senha será alterada.
     * @param newPassword A nova senha para o usuário.
     * @throws UserServiceFailureException se o ID do usuário ou a nova senha forem nulos ou em branco, ou se o usuário não for encontrado.
     */
    @Override
    public void changePassword(String userId, String newPassword) {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(userId, INVALID_IDENTIFIER_ERROR_MESSAGE, UserServiceFailureException.class);
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(newPassword, INVALID_NEW_PASSWORD_MESSAGE, UserServiceFailureException.class);
        UserContract retrievedUser = this.userRepository.findById(userId);
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(retrievedUser, USER_NOT_FOUND_MESSAGE, UserServiceFailureException.class);
        retrievedUser.changeUserPassword(newPassword);
        this.userRepository.save(retrievedUser);
    }

    /**
     * Altera o nome do usuário especificado.
     *
     * @param userId O ID do usuário cujo nome será alterado.
     * @param newUsername O novo nome de usuário para o usuário.
     * @throws UserServiceFailureException se o ID do usuário ou o novo nome de usuário forem nulos ou em branco, ou se o usuário não for encontrado.
     */
    @Override
    public void changeUserName(String userId, String newUsername) {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(userId, INVALID_IDENTIFIER_ERROR_MESSAGE, UserServiceFailureException.class);
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(newUsername, USERNAME_INVALID_ERROR_MESSAGE, UserServiceFailureException.class);
        UserContract retrievedUser = this.userRepository.findById(userId);
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(retrievedUser, USER_NOT_FOUND_MESSAGE, UserServiceFailureException.class);
        retrievedUser.changeUserName(newUsername);
        this.userRepository.save(retrievedUser);
    }
}
