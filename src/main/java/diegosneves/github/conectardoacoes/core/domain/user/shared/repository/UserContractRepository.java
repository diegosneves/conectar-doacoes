package diegosneves.github.conectardoacoes.core.domain.user.shared.repository;

import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.repository.RepositoryContract;

/**
 * A interface {@link UserContractRepository} herda de {@link RepositoryContract} que é parametrizada com {@link UserContract}.
 * Ela fornece funcionalidades específicas ao usuário como adicionar, atualizar, deletar e procurar informações de usuários no banco de dados.
 *
 * <p> Os métodos herdados de {@link RepositoryContract} devem ser implementados em uma classe de repositório de usuário para fornecer o impacto
 * adequado para cada ação correspondente no banco de dados.
 * <p> Isso inclui encontrar usuário(s) por id, salvar usuário, deletar usuário pelo id e buscar todos os usuários.
 * <p> Os detalhes de cada operação são como segue:
 *
 * <ul>
 *     <li>{@link RepositoryContract#persist(Object) persist(UserContract entity)} - Salva ou atualiza as informações de um usuário  no banco de dados
 *     <li>{@link RepositoryContract#findEntityById(String id) findEntityById(String id)} - Retorna um usuário procurando pelo ID
 *     <li>{@link RepositoryContract#retrieveAll() retrieveAll()} -  Retorna todos os usuários do banco de dados
 *     <li>{@link RepositoryContract#deleteEntityById(String id) deleteEntityById(String id)} - Busca e deleta o usuário pelo ID
 * </ul>
 *
 * <p> Caso as operações de pesquisa não possam encontrar usuário(s) correspondente, eles retornarão um valor nulo ou uma lista vazia.
 *
 * @author diegoneves
 * @see RepositoryContract
 * @see UserContract
 * @since 1.0.0
 */
public interface UserContractRepository extends RepositoryContract<UserContract> {

    /**
     * Este método está na interface {@link UserContractRepository} e ele retorna um objeto {@link UserContract}
     * ao procurar um usuário pelo seu endereço de email.
     *
     * <p> Em situações reais, esta função pode ser usada para recuperar as informações de um usuário para fins de login ou registro.
     * Quando estamos implementando um processo de login, podemos procurar o usuário que se encontra com o endereço de email fornecido.
     * Se pudermos encontrar tal usuário, recuperamos os detalhes do usuário e o habilitamos para o próximo passo no processo de login. </p>
     *
     * <p> Mesmo caso para o processo de registro, procuramos o email e vemos se algum usuário já está registrado com o endereço de email fornecido.
     * Se for esse o caso, não permitimos mais o processo de registro e sugerimos que o usuário faça login em vez de se registrar. </p>
     *
     * <p> Para implementar essa função na classe de repositório real, podemos usar como base um método de busca por email fornecido pelo Spring Data JPA.</p>
     *
     * @param userEmail endereço de email do usuário como uma String. Utilizado para procurar um usuário no banco de dados.
     *                  Ele não pode ser nulo ou vazio.
     * @return um {@link UserContract} que representa as informações de usuário correspondentes ao email.
     * Se nenhum usuário for encontrado com o email fornecido, retornará um valor nulo.
     * @see UserContract
     * @see RepositoryContract
     */
    UserContract findUserEntityByUserEmail(String userEmail);

}
