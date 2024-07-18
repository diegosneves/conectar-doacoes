package diegosneves.github.conectardoacoes.adapters.rest.service;

import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.repository.UserRepository;
import diegosneves.github.conectardoacoes.adapters.rest.request.UserEntityCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.UserEntityCreatedResponse;
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
     * <p></p>
     *
     * @param email A string que representa o email do usuário que será procurado no repositório.
     * @return A entidade do usuário correspondente ao email fornecido.
     * @throws UserEntityFailuresException Se nenhuma entidade de usuário puder ser encontrada para o email fornecido
     *                                     ou o valor do email for nulo ou vazio.
     */
    UserContract searchUserByEmail(String email) throws UserEntityFailuresException;


    /**
     * Método para criar uma nova entidade do usuário. Primeiramente, verifica se a solicitação enviada está completa
     * e se os valores de seus atributos estão de acordo com a regra de negócio da aplicação. Caso contrário,
     * uma exceção {@link UserEntityFailuresException} é disparada com uma mensagem de erro detalhada.
     * <p>
     * Se os dados fornecidos estiverem corretos, o método criará um novo registro de usuário no banco de dados
     * usando a interface {@link UserRepository}. Ao final, um objeto {@link UserEntityCreatedResponse} é retornado
     * contendo todos os dados do novo usuário criado.
     * <p>
     *
     * @param request O objeto {@link UserEntityCreationRequest} que contém os dados necessários para a criação do novo usuário.
     * @return Um objeto {@link UserEntityCreatedResponse} contendo os dados do novo usuário criado.
     * @throws UserEntityFailuresException Se os dados fornecidos na solicitação estão incompletos ou inconsistentes
     *                                     ou se ocorrer algum problema durante a criação do novo usuário no banco de dados.
     */
    UserEntityCreatedResponse createUserEntity(UserEntityCreationRequest request) throws UserEntityFailuresException;

    /**
     * Este método é usado para obter uma response de usuário pelo seu email.

     * <p>
     * Se o email for válido, o método tentará encontrar uma entidade de usuário que corresponda ao email
     * usando a interface {@link UserRepository}.
     * Se não for encontrada uma entidade de usuário para o email fornecido,
     * o método lançará uma exceção {@link UserEntityFailuresException} com uma mensagem de erro apropriada.
     * <p></p>
     *
     * @param email A string que representa o email do usuário que será procurado no repositório.
     * @return Um objeto {@link UserEntityCreatedResponse}  de um usuário correspondente ao email fornecido.
     * @throws UserEntityFailuresException Se nenhuma entidade de usuário puder ser encontrada para o email fornecido
     *                                     ou o valor do email for nulo ou vazio.
     */
    UserEntityCreatedResponse findUserByEmail(String email);

}
