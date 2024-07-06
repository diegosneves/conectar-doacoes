package diegosneves.github.conectardoacoes.core.domain.shelter.factory;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;

/**
 * Classe utilitária para fornecer funções de fábrica para a criação de instâncias {@link Donation}.
 *
 * <p>
 * Essa classe é usada para isolar a lógica de criação de uma instância de doação, garantindo que sempre seja adequada
 * para uso assim que for criada.
 * </p>
 *
 * <p>
 * Essa classe é uma classe de utilitário e não pode ser instanciada. Ela só fornece um método estático.
 * </p>
 *
 * @author diegoneves
 * @since 1.0.0
 */
public class DonationFactory {

    /**
     * Construtor privado para evitar a instânciação de uma classe de utilitário.
     */
    private DonationFactory() {}

    /**
     * Cria e retorna uma nova instância de doação.
     *
     * @param description Descrição da doação a ser criada.
     * @param amount A quantidade da doação a ser criada.
     * @return Uma nova instância de doação.
     *
     * @throws DonationRegisterFailureException Se as regras de validação da doação falharem.
     * @throws UuidUtilsException Se a geração do UUID falhar.
     */
    public static Donation created(String description, Integer amount) {
        return new Donation(UuidUtils.generateUuid(), description, amount);
    }

}
