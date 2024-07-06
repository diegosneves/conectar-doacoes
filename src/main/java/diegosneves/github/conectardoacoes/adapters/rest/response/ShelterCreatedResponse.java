package diegosneves.github.conectardoacoes.adapters.rest.response;

import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import diegosneves.github.conectardoacoes.adapters.rest.dto.UserEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe modelo {@code DTO} para a resposta de criação de um abrigo.
 * <p>
 * Esta classe contém informações sobre a resposta da criação de um abrigo, incluindo o {@code id} do novo abrigo,
 * o {@code nome} do abrigo, o {@code endereço} do abrigo, e o {@code usuário responsável} pela criação do abrigo.
 * </p>
 *
 * @author diegoneves
 * @since 1.0.0
 * @see AddressDTO
 * @see UserEntityDTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShelterCreatedResponse {

    private String id;
    private String shelterName;
    private AddressDTO address;
    private UserEntityDTO responsibleUser;

}
