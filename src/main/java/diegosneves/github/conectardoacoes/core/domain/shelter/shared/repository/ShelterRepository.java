package diegosneves.github.conectardoacoes.core.domain.shelter.shared.repository;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.ShelterContract;
import diegosneves.github.conectardoacoes.core.repository.RepositoryContract;

/**
 * A interface {@link ShelterRepository} herda da interface {@link RepositoryContract}.
 * Ela define o contrato para um repositório que persiste e recupera as entidades {@link ShelterContract}.
 * <p>
 * Tem as seguintes operações básicas:
 *  - Encontrar uma entidade {@link Shelter} pelo seu identificador único
 *  - Encontrar todas as instâncias da entidade {@link Shelter}
 *  - Salvar uma instância da entidade {@link Shelter}
 *  - Deletar uma entidade {@link Shelter} através seu identificador único
 *
 * <p>
 * O uso desta interface é específico para operações relacionados com objeto {@link ShelterContract}
 * e as suas implementações devem ser consideradas para trabalhar com dados da entidade Shelter.
 * <p>
 *
 * @author diegoneves
 * @since 1.0.0
 * @see RepositoryContract
 * @see Shelter
 */
public interface ShelterRepository extends RepositoryContract<ShelterContract> {

}
