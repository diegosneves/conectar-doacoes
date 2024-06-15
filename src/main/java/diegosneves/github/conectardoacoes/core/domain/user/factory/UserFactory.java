package diegosneves.github.conectardoacoes.core.domain.user.factory;

import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.UserCreationFailureException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;

/**
 * Classe de utilidade para a criação de usuário.
 * <p>
 * Oferece um método estático para criar um usuário com todas as informações necessárias
 * como nome de usuário, e-mail, perfil de usuário e senha.
 * Além disso, gera um identificador UUID para o novo usuário.
 * </p>
 * <p>
 * Esta classe foi projetada para ser usada em todo o código que precisa criar um novo usuário,
 * permitindo uma abordagem consistente para a criação de usuário.
 * </p>
 * <p>
 * Esta classe não pode ser instanciada.
 * </p>
 *
 * @author diegoneves
 * @version 1.0.0
 * @see User
 */
public class UserFactory {

    private UserFactory() {

    }

    /**
     * Cria um novo usuário com todas as informações necessárias e um identificador UUID gerado.
     * <p>
     * Este método utiliza o método {@link UuidUtils#generateUuid} para gerar um UUID único para o novo objeto {@link User}.
     * @param username    O nome de usuário desejado para o novo usuário. Não deve ser nulo ou vazio.
     * @param email       O e-mail do novo usuário. Não deve ser nulo ou vazio.
     * @param userProfile O perfil do usuário {@link UserProfile} para o novo usuário. Não deve ser nulo.
     * @param password    A senha para o novo usuário. Não deve ser nula ou vazia.
     * @return O usuário criado com todas as informações fornecidas e um identificador UUID.
     * @throws UserCreationFailureException se qualquer informação de usuário fornecida for inválida.
     */
    public static User create(String username, String email, UserProfile userProfile, String password) {
        return new User(UuidUtils.generateUuid(), username, email, userProfile, password);
    }

}
