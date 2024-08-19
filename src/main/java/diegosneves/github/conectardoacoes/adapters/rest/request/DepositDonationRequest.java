package diegosneves.github.conectardoacoes.adapters.rest.request;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DepositDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * A classe DepositDonationRequest representa uma solicitação de doação por depósito.
 * <p>
 * Esta classe armazena informações sobre um pedido de doação, incluindo o e-mail do responsável
 * e uma lista de objetos do tipo DepositDTO que contêm os detalhes dos depósitos.
 * <p>
 * Está equipada com as seguintes anotações do Lombok:
 * <ul>
 *     <li>{@code @AllArgsConstructor} - Gera um construtor com um argumento para cada campo na classe.</li>
 *     <li>{@code @NoArgsConstructor} - Gera um construtor sem argumentos.</li>
 *     <li>{@code @Builder} - Implementa o padrão de projeto Builder para facilitar a criação de objetos.</li>
 *     <li>{@code @Getter} - Gera métodos getters para todos os campos.</li>
 *     <li>{@code @Setter} - Gera métodos setters para todos os campos.</li>
 * </ul>
 * <p>
 * Exemplos de utilização:
 * <pre>{@code
 * DepositDonationRequest request = DepositDonationRequest.builder()
 *     .responsibleEmail("responsavel@example.com")
 *     .depositDTOS(new ArrayList<>())
 *     .build();
 * }</pre>
 *
 * @author diegoneves
 * @since 1.3.0
 * @see DepositDTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DepositDonationRequest {

    private String responsibleEmail;
    private List<DepositDTO> depositDTOS;

}
