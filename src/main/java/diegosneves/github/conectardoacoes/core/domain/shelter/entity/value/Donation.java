package diegosneves.github.conectardoacoes.core.domain.shelter.entity.value;

import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;
import lombok.Getter;

/**
 * Entidade que representa uma doação.
 * Uma doação é caracterizada pela sua descrição e quantidade.
 *
 * @author diegoneves
 * @version 1.0.0
 */
@Getter
public class Donation {

    public static final String INVALID_DESCRIPTION_ERROR = "A descrição da doação está vazia ou nula";
    public static final String INVALID_QUANTITY = "A quantidade deve ser maior que zero";
    public static final int DEFAULT_DONATION_AMOUNT = 1;

    private String description;
    private Integer amount;

    /**
     * Construtor da entidade Donation.
     *
     * @param description Descrição da doação.
     * @param amount      Quantidade da doação.
     * @throws DonationRegisterFailureException se a descrição ou a quantidade da doação forem inválidos.
     */
    public Donation(String description, Integer amount) {
        this.description = description;
        this.amount = this.defaultAmount(amount);
        this.validateData();
    }

    /**
     * Valida os dados da doação, lançando uma exceção quando inválidos.
     *
     * @throws DonationRegisterFailureException se a descrição da doação for nula ou vazia.
     */
    private void validateData() throws DonationRegisterFailureException {
        if (this.description == null || this.description.trim().isEmpty()) {
            throw new DonationRegisterFailureException(INVALID_DESCRIPTION_ERROR);
        }
    }

    /**
     * Fornecer o valor padrão para a quantidade quando o valor informado for nulo ou inválido.
     *
     * @param amount O valor da quantidade informado.
     * @return O valor padrão se a quantidade informada for nula ou inválida, caso contrário a quantidade informada.
     * @throws DonationRegisterFailureException se a quantia é nula.
     */
    private Integer defaultAmount(Integer amount) throws DonationRegisterFailureException {
        if (amount == null) {
            throw new DonationRegisterFailureException(INVALID_QUANTITY);
        }
        return (amount < DEFAULT_DONATION_AMOUNT) ? DEFAULT_DONATION_AMOUNT : amount;
    }
}
