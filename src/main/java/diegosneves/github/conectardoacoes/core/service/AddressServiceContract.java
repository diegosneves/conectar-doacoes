package diegosneves.github.conectardoacoes.core.service;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.exception.AddressCreationFailureException;

/**
 * Esta interface define um contrato para um serviço responsável pelo gerenciamento de endereços na aplicação.
 * <p>
 * O serviço de endereço é fundamental para definir, criar e validar instâncias de {@link Address}, que representam informações de endereço em nossa aplicação.
 * Este contrato especifica quais operações estão disponíveis para esses casos de uso.
 * <p>
 * A operação principal provida por esta interface é a criação de um novo endereço.
 * Os componentes que utilizam este serviço devem seguir o contrato estabelecido neste interface para assegurar que eles obedecem às regras de negócio ao criar novos endereços.
 * <p>
 * Esta interface é projetada para ser utilizada com uma implementação que siga o princípio da inversão de dependência, permitindo que diferentes implementações
 * possam ser injetadas conforme necessário sem afetar a funcionalidade geral do sistema.
 *
 * @author diegoneves
 * @since 1.2.0
 */
public interface AddressServiceContract {

    /**
     * Método utilizado para criar uma nova instância da classe {@link Address}.
     * <p>
     * Este método recebe uma série de parâmetros que representam diferentes partes de um endereço. Ele utiliza esses parâmetros para construir uma nova instância da classe Address.
     * A nova instância é então retornada para o código que chamou este método.
     * <p>
     * Se algum dos parâmetros estiver nulo ou em branco, este método lançará uma {@link AddressCreationFailureException}.
     *
     * @param street       O nome da rua do endereço. Não deve ser nulo ou em branco.
     * @param number       O número da residência no endereço. Não deve ser nulo ou em branco.
     * @param neighborhood O nome do bairro do endereço. Não deve ser nulo ou em branco.
     * @param city         O nome da cidade do endereço. Não deve ser nulo ou em branco.
     * @param state        O nome do estado do endereço. Não deve ser nulo ou em branco.
     * @param zip          O código postal do endereço. Não deve ser nulo ou em branco.
     * @return Uma nova instância da classe {@link Address} contendo as informações do endereço fornecido.
     * @throws AddressCreationFailureException Se algum dos parâmetros estiver nulo ou em branco.
     */
    Address createAddress(String street, String number, String neighborhood, String city, String state, String zip) throws AddressCreationFailureException;

}
