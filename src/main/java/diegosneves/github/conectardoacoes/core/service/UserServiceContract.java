package diegosneves.github.conectardoacoes.core.service;

import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.UserCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UserServiceFailureException;

/**
 * Interface para o contrato de serviço do usuário. Define os métodos funcionais para todos os serviços de usuário.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public interface UserServiceContract {

    /**
     * Método para criar usuário.
     *
     * @param username    nome de usuário exclusivo.
     * @param email       e-mail do usuário.
     * @param userProfile perfil do usuário.
     * @param password    senha da conta do usuário.
     * @return Um {@link UserContract} instância contendo detalhes do usuário.
     * @throws UserCreationFailureException se ocorrer um erro ao criar o usuário.
     */
    UserContract createUser(String username, String email, UserProfile userProfile, String password) throws UserCreationFailureException;

    /**
     * Método para obter detalhes do usuário.
     *
     * @param userId ID único do usuário.
     * @return A {@link UserContract} instância contendo detalhes do usuário.
     * @throws UserServiceFailureException se ocorrer um erro ao buscar detalhes do usuário.
     */
    UserContract getUserById(String userId);

    /**
     * Método para obter detalhes do usuário por email.
     * <p>
     * Este método é usado quando precisamos encontrar um usuário especificamente pelo seu endereço de e-mail. Isso é útil,
     * por exemplo, quando um usuário esquece seu nome de usuário e só pode fornecer um e-mail. Este método retorna um objeto
     * {@link UserContract} que contém todos os detalhes do usuário.
     * <p>
     * É importante garantir que o e-mail fornecido para este método seja válido e existente na base de dados. Se o e-mail
     * fornecido não estiver associado a nenhum usuário, este método retornará null.
     *
     * @param userEmail O endereço de e-mail do usuário que está sendo procurado.
     * @return {@link UserContract} Uma instância de {@link UserContract} que contém os detalhes do usuário procurado. Se nenhum
     * usuário for encontrado com o e-mail fornecido, este método retornará null.
     */
    UserContract getUserByEmail(String userEmail);

    /**
     * Método para alterar a senha do usuário.
     *
     * @param userId      ID único do usuário.
     * @param newPassword Nova senha do usuário.
     * @throws UserServiceFailureException se ocorrer um erro ao alterar a senha do usuário.
     */
    void changePassword(String userId, String newPassword);

    /**
     * Método para alterar o nome de usuário.
     *
     * @param userId      ID único do usuário.
     * @param newUsername Novo nome de usuário.
     * @throws UserServiceFailureException se ocorrer um erro ao alterar o nome de usuário.
     */
    void changeUserName(String userId, String newUsername);

}
