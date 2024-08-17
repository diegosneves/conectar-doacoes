package diegosneves.github.conectardoacoes.adapters.rest.service;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DepositDTO;
import diegosneves.github.conectardoacoes.adapters.rest.model.DepositEntity;

/**
 * Interface de serviço para operações relacionadas à entidade de depósito.
 * <p>
 * Esta interface define os métodos necessários para a criação e manipulação
 * de objetos do tipo {@link DepositEntity}.
 *
 * @author diegoneves
 * @since 1.3.0
 */
public interface DepositEntityService {

    /**
     * Cria uma nova instância de {@link DepositEntity} com base nos dados fornecidos
     * pelo objeto {@link DepositDTO}.
     *
     * @param dto Objeto de transferência de dados contendo as informações necessárias
     *            para a criação de um novo depósito.
     * @return A nova instância de {@link DepositEntity} criada com os dados fornecidos.
     */
    DepositEntity create(DepositDTO dto);

}
