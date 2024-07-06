package diegosneves.github.conectardoacoes.core.domain.shelter.factory;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.exception.AddressCreationFailureException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;

/**
 * Fábrica estática para a criação de instâncias da classe {@link Address}.
 * <p>
 * Esta fábrica oculta a lógica de criação de um endereço, incluindo a geração de um UUID único para cada endereço criado.
 * Ao encapsular o processo de criação de endereços dentro desta fábrica, o código externo é simplificado e a responsabilidade do controle de versões é mantida dentro da classe {@link AddressFactory} em vez de ser espalhada por várias partes do programa.
 * <p>
 * Nota: Esta classe foi projetada para ser usada apenas para a criação de endereços. Não deve ser sub-classificada ou usada como referência. Para criar um novo endereço, use o método {@link #create} desta classe.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public class AddressFactory {

    /**
     * Construtor privado.
     * <p>
     * Isso é usado para assegurar que nenhuma instância da classe {@link AddressFactory} seja criada. A classe {@link AddressFactory} deve ser usada apenas através de chamadas a seu método {@link #create}.
     * Como tal, um objeto {@link AddressFactory} nunca deve ser instanciado.
     */
    private AddressFactory() {
    }

    /**
     * Método estático para criação de uma nova instância de {@link Address}.
     * <p>
     * Este método é responsável por criar uma nova instância de {@link Address}, que representa um endereço físico, utilizando
     * os parâmetros fornecidos e gerando um UUID para o novo Endereço.
     *
     * @param street       A string que representa a rua do endereço.
     * @param number       A string que representa o número da residência no endereço.
     * @param neighborhood A string que representa o bairro do endereço.
     * @param city         A string que representa a cidade do endereço.
     * @param state        A string que representa o estado do endereço.
     * @param zip          A string que representa o código postal do endereço.
     * @return Uma nova instância da classe {@link Address} com os parâmetros fornecidos.
     * @throws AddressCreationFailureException Se algum dos parâmetros fornecidos estiver nulo ou em branco,
     *                                         conforme validado pela classe {@link Address}.
     * @see Address
     */
    public static Address create(String street, String number, String neighborhood, String city, String state, String zip) throws AddressCreationFailureException {
        return new Address(UuidUtils.generateUuid(), street, number, neighborhood, city, state, zip);
    }
}
