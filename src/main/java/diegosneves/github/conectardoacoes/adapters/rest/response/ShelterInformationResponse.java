package diegosneves.github.conectardoacoes.adapters.rest.response;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DonationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Classe de resposta para recebimento de doações.
 * Esta classe é responsável por conter as informações necessárias que são retornadas quando um abrigo recebe uma doação.
 *
 * <p>A classe é construída usando o padrão Builder e possui os seguintes atributos:</p>
 * <ul>
 *   <li>{@link String shelterName}: o nome do abrigo que recebe a doação.</li>
 *   <li>{@link String responsibleName}: o nome da pessoa responsável pela gestão das doações no abrigo.</li>
 *   <li>{@link String responsibleEmail}: o email do responsável pela gestão das doações.</li>
 *   <li>{@link DonationDTO donationDTOS}: uma lista de doações recebidas, representadas como objetos da classe DonationDTO.</li>
 * </ul>
 *
 * @author diegoneves
 * @since 1.1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ShelterInformationResponse {

    private String shelterName;
    private String responsibleName;
    private String responsibleEmail;
    private List<DonationDTO> donationDTOS;

}
