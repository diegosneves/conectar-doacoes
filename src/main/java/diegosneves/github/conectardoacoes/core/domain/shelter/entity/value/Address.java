package diegosneves.github.conectardoacoes.core.domain.shelter.entity.value;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.exception.AddressCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import lombok.Getter;

/**
 * Classe {@link Address} representa um endereço físico.
 * <p>
 * Um endereço é composto pelo id, rua, número, bairro, cidade, estado e CEP (código postal).
 * Cada um destes campos é uma string e é validado no momento da criação de um objeto {@link Address}.
 * Se um destes campos estiver faltando ou em branco, uma exceção {@link AddressCreationFailureException} será lançada.
 * <p>
 * Este classe é utilizada na criação de entidades tais como {@link Shelter}, que precisam de uma representação de endereço.
 *
 * @author diegoneves
 * @since 1.0.0
 */
@Getter
public class Address {

    public static final String STREET_NAME_ERROR_MESSAGE = "Por favor, certifique-se de que o nome da rua foi inserido corretamente.";
    public static final String RESIDENCE_NUMBER_ERROR_MESSAGE = "Por favor, certifique-se de que o numero da residência foi inserido corretamente.";
    public static final String NEIGHBORHOOD_NAME_ERROR_MESSAGE = "Por favor, certifique-se de que o nome do bairro foi inserido corretamente.";
    public static final String CITY_NAME_ERROR_MESSAGE = "Por favor, certifique-se de que o nome da cidade foi inserido corretamente.";
    public static final String STATE_NAME_ERROR_MESSAGE = "Por favor, certifique-se de que o nome do Estado foi inserido corretamente.";
    public static final String CEP_ERROR_MESSAGE = "Por favor, certifique-se de que o CEP foi inserido corretamente.";
    public static final String INVALID_ID_MESSAGE = "Deve ser fornecido um ID válido";

    private final String id;
    private final String street;
    private final String number;
    private final String neighborhood;
    private final String city;
    private final String state;
    private final String zip;

    /**
     * Construtor para a classe {@link Address}. Cada um dos parâmetros é usado para definir os detalhes de um endereço.
     *
     * @param id       A string que representa o UUID do endereço.
     * @param street       A string que representa a rua do endereço.
     * @param number       A string que representa o número da residência no endereço.
     * @param neighborhood A string que representa o bairro do endereço.
     * @param city         A string que representa a cidade do endereço.
     * @param state        A string que representa o estado do endereço.
     * @param zip          A string que representa o código postal do endereço.
     * @throws AddressCreationFailureException Se qualquer um dos parâmetros estiver nulo ou em branco.
     *                                         <p>
     *                                         Utiliza o método {@link #validateData} para garantir que cada campo fornecido é válido.
     *                                         Isso é feito através da verificação de que cada campo não seja nulo nem em branco.
     *                                         Se algum campo for inválido, será lançada uma exceção {@link AddressCreationFailureException} específica para aquele campo.
     */
    public Address(String id, String street, String number, String neighborhood, String city, String state, String zip) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.validateData();
    }

    /**
     * Método sem retorno que valida os campos de um endereço.
     * <p>
     * Este método examina cada campo (rua, número, bairro, cidade, estado e CEP) e chama um segundo método de validação
     * passando o valor atual do campo e uma mensagem de erro específica para aquele campo.<p>
     * Se algum dos campos estiver nulo ou em branco, ele lançará a exceção {@link AddressCreationFailureException} com
     * a mensagem de erro específica para aquele campo.
     */
    private void validateData() {
        try {
            UuidUtils.isValidUUID(this.id);
        } catch (UuidUtilsException e) {
            throw new AddressCreationFailureException(INVALID_ID_MESSAGE, e);
        }
        this.validateData(this.street, STREET_NAME_ERROR_MESSAGE);
        this.validateData(this.number, RESIDENCE_NUMBER_ERROR_MESSAGE);
        this.validateData(this.neighborhood, NEIGHBORHOOD_NAME_ERROR_MESSAGE);
        this.validateData(this.city, CITY_NAME_ERROR_MESSAGE);
        this.validateData(this.state, STATE_NAME_ERROR_MESSAGE);
        this.validateData(this.zip, CEP_ERROR_MESSAGE);
    }

    /**
     * Método sem retorno que valida uma string representando um campo de um endereço.
     *
     * @param value        A String contendo o valor atual do campo do endereço.
     * @param errorMessage A String contendo a mensagem de erro a ser usada se a validação falhar.
     *                     <p>
     *                     Este método verifica se o valor passado é nulo ou está em branco.
     *                     Se o valor estiver nulo ou em branco, ele lançará a exceção `AddressCreationFailureException` com a mensagem de erro passada.
     * @throws AddressCreationFailureException Se {@code value} estiver nulo ou em branco.
     */
    private void validateData(String value, String errorMessage) throws AddressCreationFailureException {
        if (value == null || value.trim().isEmpty()) {
            throw new AddressCreationFailureException(errorMessage);
        }
    }

}
