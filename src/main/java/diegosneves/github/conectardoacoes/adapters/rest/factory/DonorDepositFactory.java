package diegosneves.github.conectardoacoes.adapters.rest.factory;

import diegosneves.github.conectardoacoes.adapters.rest.model.DonorDeposit;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;

import java.util.ArrayList;

/**
 * Esta classe é responsável por fornecer métodos de fábrica para criar instâncias de {@link DonorDeposit}.
 * Esta classe contém métodos estáticos e não pode ser instanciada.
 *
 * @author diegoneves
 * @see DonorDeposit
 * @see UserEntity
 * @since 1.3.0
 */
public class DonorDepositFactory {

    private DonorDepositFactory() {
    }

    /**
     * Cria uma nova instância de {@link DonorDeposit} associada a um usuário fornecido.
     * <p>
     * Cada novo depósito é inicializado com um UUID único gerado, o usuário fornecido,
     * e uma lista vazia de depósitos.
     * </p>
     *
     * @param userEntity A entidade {@link UserEntity} que representa o usuário (doador) associado ao depósito.
     * @return Uma nova instância de {@link DonorDeposit}.
     * @throws IllegalArgumentException Se o userEntity fornecido for {@code null}.
     */
    public static DonorDeposit create(UserEntity userEntity) {
        return new DonorDeposit(UuidUtils.generateUuid(), userEntity, new ArrayList<>());
    }
}
