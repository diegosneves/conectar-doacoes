package diegosneves.github.conectardoacoes.adapters.rest.request;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DonationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Representa a solicitação para receber uma doação.
 * <p>
 * Esta classe fornece métodos para definir e obter
 * o email do responsável e a lista de doações.
 *
 * <p> É usada quando uma solicitação de receber doação é feita
 * e é necessário encapsular as informações presentes nessa solicitação.
 * </p>
 *
 * <p> Os atributos incluem:
 * <ul>
 *     <li> {@link String responsibleEmail} : O email do responsável pela doação </li>
 *     <li> {@link DonationDTO donationDTOS} : Lista de doações no formato DTO (Data Transfer Object) </li>
 * </ul>
 * </p>
 *
 * @author diegoneves
 * @since 1.1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReceiveDonationRequest {

    private String responsibleEmail;
    private List<DonationDTO> donationDTOS;

}
