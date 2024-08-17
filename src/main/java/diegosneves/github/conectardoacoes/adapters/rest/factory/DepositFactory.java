package diegosneves.github.conectardoacoes.adapters.rest.factory;

import diegosneves.github.conectardoacoes.adapters.rest.model.DepositEntity;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;

/**
 * A classe {@code DepositFactory} é responsável por criar instâncias da {@link DepositEntity}.
 * <p>
 * Esta classe é utilitária e não pode ser instanciada, ou seja, todos os seus métodos são estáticos.
 * O principal método desta classe é o {@code createDepositEntity}, que gera uma instância de {@link DepositEntity}
 * utilizando um UUID gerado, uma descrição e um valor fornecidos.
 * </p>
 * <p>
 * Uso típico:
 * <pre>{@code
 * DepositEntity deposit = DepositFactory.createDepositEntity("Depósito de exemplo", 100);
 * }</pre>
 *
 * @author diegoneves
 * @since 1.3.0
 * @see DepositEntity
 */
public class DepositFactory {

    private DepositFactory() {
    }

    /**
     * Cria uma nova instância de {@link DepositEntity} com uma descrição fornecida e um valor.
     * <p>
     * Este método gera um novo UUID usando {@link UuidUtils#generateUuid()} e cria uma nova
     * instância de {@link DepositEntity} com o UUID gerado, a descrição fornecida e o valor fornecido.
     * </p>
     *
     * @param description Uma {@link String} representando a descrição do depósito.
     * @param amount      Um {@link Integer} representando o valor do depósito.
     * @return A nova instância de {@link DepositEntity} criada com o UUID gerado, a descrição fornecida e o valor fornecido.
     * @throws IllegalArgumentException se a descrição ou o valor forem nulos.
     */
    public static DepositEntity createDepositEntity(String description, Integer amount) {
        return new DepositEntity(UuidUtils.generateUuid(), description, amount);
    }

}
