package diegosneves.github.conectardoacoes.adapters.rest.request;

import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe modelo para o Pedido de Criação do Abrigo {@link ShelterCreationRequest}. Esta classe é usada para a criação de um novo abrigo.
 * Contém informações essenciais para a criação de um abrigo, incluindo o nome do abrigo,
 * um {@link AddressDTO} representando o endereço do abrigo, e o {@code e-mail} do usuário responsável pelo abrigo.
 *
 * <p> Exemplo de uso:
 * <pre>
 *     {@code
 *     ShelterCreatedRequest shelterRequest = ShelterCreatedRequest.builder()
 *     .shelterName("Little Paws")
 *     .addressDTO(address)
 *     .responsibleUserEmail("admin@littlepaws.com")
 *     .build();
 *     }
 * </pre>
 *
 * @author diegoneves
 * @since 1.0.0
 * @see AddressDTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShelterCreationRequest {

    private String shelterName;
    private AddressDTO address;
    private String responsibleUserEmail;

}
