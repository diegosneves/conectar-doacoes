package diegosneves.github.conectardoacoes.adapters.rest.service;

import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.repository.UserRepository;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;

/**
 * Definição da interface para o serviço que lida com operações relacionadas à entidade do usuário.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public interface UserEntityService {

    /**
     * Este método é usado para obter uma entidade de usuário pelo seu email.
     * Primeiramente, o método verifica se o valor do parâmetro de email não é nulo ou vazio.
     * Se o valor do email for nulo ou vazio, uma exceção {@link UserEntityFailuresException} é lançada ao usuário
     * com uma mensagem de erro relevante.
     * <p>
     * Se o email for válido, o método tentará encontrar uma entidade de usuário que corresponda ao email
     * usando a interface {@link UserRepository}.
     * Se não for encontrada uma entidade de usuário para o email fornecido,
     * o método lançará uma exceção {@link UserEntityFailuresException} com uma mensagem de erro apropriada.
     *<p></p>
     * @param email A string que representa o email do usuário que será procurado no repositório.
     * @return A entidade do usuário correspondente ao email fornecido.
     * @throws UserEntityFailuresException Se nenhuma entidade de usuário puder ser encontrada para o email fornecido
     * ou o valor do email for nulo ou vazio.
     */
    UserContract searchUserByEmail(String email) throws UserEntityFailuresException;

}
