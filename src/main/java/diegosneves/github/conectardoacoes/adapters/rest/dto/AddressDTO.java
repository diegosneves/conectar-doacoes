package diegosneves.github.conectardoacoes.adapters.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe modelo {@code DTO} para o endereço. Contém informações básicas de endereço, como rua, número, bairro,
 * cidade, estado e CEP.
 * <p/>
 * Uso:
 * <pre>
 *     {@code
 *     AddressDTO address = AddressDTO.builder()
 *     .street("Main St")
 *     .number("42")
 *     .neighborhood("Centro")
 *     .city("Rio de Janeiro")
 *     .state("RJ")
 *     .zip("20031-040")
 *     .build();
 * }
 * </pre>
 *
 * @author diegoneves
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressDTO {

    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String zip;

}
