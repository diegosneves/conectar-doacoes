package diegosneves.github.conectardoacoes.adapters.rest.response;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DepositDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * A classe `RegisteredDepositResponse` representa a resposta para um depósito registrado.
 *
 * <p>Esta classe contém as informações do usuário e a lista de depósitos associados a ele.
 * É utilizada para transferir os dados relacionados a um depósito registrado em um sistema bancário
 * ou financeiro.</p>
 *
 * <p>A anotação `@AllArgsConstructor` gera um construtor com um parâmetro para cada campo na classe.
 * A anotação `@NoArgsConstructor` gera um construtor sem parâmetros.
 * A anotação `@Builder` fornece um padrão de builder para facilitar a criação de instâncias da classe.
 * As anotações `@Getter` e `@Setter` geram automaticamente os métodos getter e setter para todos os campos.</p>
 *
 * <p>Os campos disponíveis nesta classe são:</p>
 * <ul>
 *     <li>{@code userName} - O nome do usuário</li>
 *     <li>{@code email} - O email do usuário</li>
 *     <li>{@code deposits} - A lista de depósitos associados ao usuário, representada por objetos {@link DepositDTO}</li>
 * </ul>
 *
 * <p>Exemplo de uso:</p>
 * <pre>
 *     {@code
 *     RegisteredDepositResponse response = RegisteredDepositResponse.builder()
 *         .userName("João Silva")
 *         .email("joao.silva@example.com")
 *         .deposits(List.of(new DepositDTO(...)))
 *         .build();
 *  }
 * </pre>
 *
 * @see DepositDTO
 * @author diegoneves
 * @since 1.3.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RegisteredDepositResponse {

    private String userName;
    private String email;
    private List<DepositDTO> deposits;

}
