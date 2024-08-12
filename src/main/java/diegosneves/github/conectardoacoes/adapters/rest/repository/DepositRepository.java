package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.model.DepositEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório responsável pelas operações CRUD para a entidade {@link DepositEntity}.
 *
 * <p>Essa interface herda de {@link CrudRepository}, que fornece métodos básicos para
 * operações de criação, leitura, atualização e exclusão (CRUD). Com isso, não é necessário
 * implementar esses métodos manualmente.</p>
 *
 * <p>A anotação {@link Repository} indica que essa interface é um bean do Spring e que deve
 * ser tratada como um componente de repositório, o que permite a injeção de dependência em
 * outras partes do aplicativo.</p>
 *
 * <p>Parametrização:</p>
 * <ul>
 *   <li><b>DepositEntity</b>: Tipo da entidade para a qual o repositório será utilizado.</li>
 *   <li><b>String</b>: Tipo do identificador único (ID) da entidade DepositEntity.</li>
 * </ul>
 *
 * @see CrudRepository
 * @author diegoneves
 * @since 1.3.0
 */
@Repository
public interface DepositRepository extends CrudRepository<DepositEntity, String> {

}
