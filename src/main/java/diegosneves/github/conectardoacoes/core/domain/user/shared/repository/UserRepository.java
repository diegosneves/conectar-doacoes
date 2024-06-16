package diegosneves.github.conectardoacoes.core.domain.user.shared.repository;

import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.repository.RepositoryContract;

/**
 * A interface {@link UserRepository} herda de {@link RepositoryContract} que é parametrizada com {@link UserContract}.
 * Ela fornece funcionalidades específicas ao usuário como adicionar, atualizar, deletar e procurar informações de usuários no banco de dados.
 *
 * <p> Os métodos herdados de {@link RepositoryContract} devem ser implementados em uma classe de repositório de usuário para fornecer o impacto
 * adequado para cada ação correspondente no banco de dados.
 * <p> Isso inclui encontrar usuário(s) por id, salvar usuário, deletar usuário pelo id e buscar todos os usuários.
 * <p> Os detalhes de cada operação são como segue:
 *
 * <ul>
 *     <li>{@link RepositoryContract#save(Object) save(UserContract entity)} - Salva ou atualiza as informações de um usuário  no banco de dados
 *     <li>{@link RepositoryContract#findById(String id) findById(String id)} - Retorna um usuário procurando pelo ID
 *     <li>{@link RepositoryContract#findAll() findAll()} -  Retorna todos os usuários do banco de dados
 *     <li>{@link RepositoryContract#deleteById(String id) deleteById(String id)} - Busca e deleta o usuário pelo ID
 * </ul>
 *
 * <p> Caso as operações de pesquisa não possam encontrar usuário(s) correspondente, eles retornarão um valor nulo ou uma lista vazia.
 *
 * @author diegoneves
 * @version 1.0.0
 * @see RepositoryContract
 * @see UserContract
 */
public interface UserRepository extends RepositoryContract<UserContract> {

}
