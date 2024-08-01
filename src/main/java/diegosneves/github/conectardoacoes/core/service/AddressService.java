package diegosneves.github.conectardoacoes.core.service;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.factory.AddressFactory;
import diegosneves.github.conectardoacoes.core.exception.AddressCreationFailureException;

/**
 * Essa classe é responsável por fornecer os serviços relacionados ao endereço.
 * Ela implementa todas as assinaturas de métodos definidas na interface AddressServiceContract.
 *
 * <p>A classe inclui o seguinte método:</p>
 * 1. {@code createAddress()} que é usado para criar um objeto endereço.
 *
 * <p>A classe {@link AddressService} faz uso do padrão de projeto Factory para a criação do objeto Address.
 * Isso permite que a criação de um objeto Address seja feita de uma maneira que adira ao princípio da responsabilidade única.</p>
 *
 * <p>O método {@code createAddress()} pode lançar uma exceção {@link AddressCreationFailureException}, que deve ser manuseada corretamente
 * no local onde o método é chamado.</p>
 *
 * @see AddressServiceContract
 * @author diegoneves
 * @since 1.2.0
 */
public class AddressService implements AddressServiceContract {

    @Override
    public Address createAddress(String street, String number, String neighborhood, String city, String state, String zip) throws AddressCreationFailureException {
        return AddressFactory.create(street, number, neighborhood, city, state, zip);
    }
}
