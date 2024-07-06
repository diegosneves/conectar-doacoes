package diegosneves.github.conectardoacoes.core.domain.shelter.entity.value;

import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import lombok.Getter;

/**
 * Entidade que representa uma doação.
 * Uma doação é caracterizada pela sua descrição e quantidade.
 *
 * @author diegoneves
 * @since 1.0.0
 */
@Getter
public class Donation {

    public static final String INVALID_DESCRIPTION_ERROR = "A descrição da doação está vazia ou nula";
    public static final String INVALID_QUANTITY = "A quantidade deve ser maior que zero";
    public static final int DEFAULT_DONATION_AMOUNT = 1;
    public static final String INVALID_ID_MESSAGE = "Deve fornecer um ID válido";

    private final String id;
    private final String description;
    private final Integer amount;

    /**
     * Construtor para criar uma nova instância de doação.
     *
     * <p>
     * Esse construtor aceita três argumentos que representam o id, a descrição e a quantidade da doação.
     * Ele valida os argumentos fornecidos e pode lançar uma {@code DonationRegisterFailureException}
     * se a descrição ou a quantidade fornecida forem inválidas.
     * </p>
     *
     * @param id          O identificador único para a doação.
     * @param description Descrição detalhada da doação.
     * @param amount      Quantidade de doação.
     * @throws DonationRegisterFailureException Se a descrição é nula ou vazia, ou se a quantidade é nula.
     */
    public Donation(String id, String description, Integer amount) {
        this.id = id;
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
        try {
            UuidUtils.isValidUUID(this.id);
        } catch (UuidUtilsException e) {
            throw new DonationRegisterFailureException(INVALID_ID_MESSAGE, e);
        }
        ValidationUtils.validateNotNullOrEmpty(this.description, INVALID_DESCRIPTION_ERROR, DonationRegisterFailureException.class);
    }

    /**
     * Fornecer o valor padrão para a quantidade quando o valor informado for nulo ou inválido.
     *
     * @param amount O valor da quantidade informado.
     * @return O valor padrão se a quantidade informada for nula ou inválida, caso contrário a quantidade informada.
     * @throws DonationRegisterFailureException se a quantia é nula.
     */
    private Integer defaultAmount(Integer amount) throws DonationRegisterFailureException {
        ValidationUtils.validateNotNullOrEmpty(amount, INVALID_QUANTITY, DonationRegisterFailureException.class);
        return (amount < DEFAULT_DONATION_AMOUNT) ? DEFAULT_DONATION_AMOUNT : amount;
    }
}
