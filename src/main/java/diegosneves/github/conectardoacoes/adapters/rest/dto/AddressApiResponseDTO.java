package diegosneves.github.conectardoacoes.adapters.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Essa é a classe {@link AddressApiResponseDTO} que representa a resposta da API para um endereço.
 * Esta classe contém todos os detalhes referentes a um endereço que o serviço pode retornar.
 *
 * <p> A classe é montada utilizando o padrão de projeto Builder para facilitar a criação de
 * instâncias e melhorar a legibilidade do código. Os atributos incluem dados como {@code rua},
 * {@code bairro}, {@code cidade}, {@code estado} e {@code CEP} do endereço.
 *
 * <p> Os seguintes atributos estão presentes nesta classe:
 * <ul>
 *     <li> {@code street} - Um campo String que representa o nome da rua do endereço.
 *     <li> {@code neighborhood} - Um campo String que representa o bairro do endereço.
 *     <li> {@code city} - Um campo String que representa a cidade do endereço.
 *     <li> {@code state} - Um campo String que representa o estado do endereço.
 *     <li> {@code zip} - Um campo String que representa o CEP (Código de Endereçamento Postal) do endereço.
 * </ul>
 *
 * @author diegoneves
 * @since 1.2.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressApiResponseDTO {

    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String zip;

}
