package diegosneves.github.conectardoacoes.adapters.rest.service;

import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.AddressEntityFailuresException;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;

/**
 * A Interface {@link AddressEntityService} é responsável pela manipulação de entidades de endereços na camada de serviço
 * do sistema. Possui métodos que traduzem os requerimentos de negócio da aplicação para operações realizadas na
 * camada de persistência de dados.
 * <p>
 * Como parte de suas responsabilidades, lida com a conversão entre DTOs e entidades de endereço, garantindo
 * a validação necessária dos dados e gerenciando o processo de persistência na base de dados.
 * <p>
 * Os serviços oferecidos por esta interface incluem a criação de novas entidades de endereço a partir de DTOs de endereço,
 * possuindo mecanismos de tratamento de exceções que são acionados quando há falhas durante esta criação.
 * <p>
 * Os métodos declarados devem ser implementados por uma classe de serviço concreta para que sejam executados.
 *
 * @author diegoneves
 * @since 1.1.0
 */
public interface AddressEntityService {

    /**
     * Cria e salva uma entidade de endereço {@link Address} com base nas informações fornecidas no DTO de Endereço {@link AddressDTO}.
     * <p>
     * Este método recebe um objeto {@link AddressDTO}, que contém as informações de endereço, e retorna um objeto {@link Address},
     * que representa a entidade de endereço criada e salva.
     * <p>
     * O processamento inclui a conversão do DTO de endereço em uma entidade de endereço, validando as informações e salvando a entidade na base de dados.
     * Em caso de falha na criação da entidade de endereço, uma exceção {@link AddressEntityFailuresException} será lançada.
     *
     * @param address Um objeto {@link AddressDTO} contendo as informações do endereço para criação da entidade de endereço.
     * @return A entidade de endereço {@link Address} criada e salva.
     * @throws AddressEntityFailuresException Se ocorrer uma falha durante a criação da entidade de endereço.
     */
    Address createAndSaveAddressFromDto(AddressDTO address) throws AddressEntityFailuresException;

}
