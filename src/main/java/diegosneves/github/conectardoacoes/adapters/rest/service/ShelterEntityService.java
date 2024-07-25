package diegosneves.github.conectardoacoes.adapters.rest.service;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DonationDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.adapters.rest.request.ReceiveDonationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.request.ShelterCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.ShelterInformationResponse;
import diegosneves.github.conectardoacoes.adapters.rest.response.ShelterCreatedResponse;

/**
 * Interface para a criação de um novo abrigo no sistema.
 * <p>
 * Esta interface fornece um contrato para a implementação de um serviço responsável por
 * criar abrigos no sistema.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public interface ShelterEntityService {

    /**
     * Este método é responsável por criar um novo abrigo no sistema.
     * Ele recebe um objeto {@link ShelterCreationRequest}, que contém as informações necessárias para criar o abrigo,
     * como o nome do abrigo, o endereço e o e-mail do usuário responsável por criar o abrigo.
     *
     * @param request um objeto {@link ShelterCreationRequest} contendo as informações necessárias para criar um novo abrigo.
     * @return um objeto {@link ShelterCreatedResponse} contendo as informações do abrigo criado.
     * @throws ShelterEntityFailuresException se ocorrer algum erro durante a criação do abrigo. Isso pode acontecer
     *                                        se o usuário responsável não for encontrado, se houver um erro na criação do endereço ou
     *                                        se houver um erro na criação do abrigo em si.
     */
    ShelterCreatedResponse createShelter(ShelterCreationRequest request);

    /**
     * Método para lidar com o recebimento de doações para um abrigo.
     * <p>
     * Este método aceita um objeto {@link ReceiveDonationRequest}, que contém o e-mail do responsável pela doação
     * e uma lista de doações no formato DTO. O método processa estas doações, associa-as ao abrigo correspondente
     * com base no e-mail fornecido e retorna uma resposta detalhando a doação recebida.
     *
     * <p> O método busca o abrigo responsável pela doação usando o e-mail fornecido. Se o abrigo não for encontrado,
     * o método retornará null para indicar que a doação não foi recebida.
     *
     * <p> Depois de encontrar o abrigo, o método procede à conversão dos {@link DonationDTO} em {@link DonationEntity}
     * que são então adicionados à lista de doações do abrigo. Finalmente, o abrigo atualizado é salvo no repositório.
     *
     * <p> O método retorna um {@link ShelterInformationResponse} que contém informações detalhadas sobre as doações
     * recebidas, incluindo o nome do abrigo, o nome e e-mail do responsável e a lista de doações recebidas.
     *
     * @param request um objeto {@link ReceiveDonationRequest} que contém as informações da doação.
     * @return um objeto {@link ShelterInformationResponse} que contém informações das doações recebidas,
     * ou null se o abrigo correspondente ao e-mail fornecido não for encontrado.
     */
    ShelterInformationResponse receiveDonation(ReceiveDonationRequest request);

    /**
     * Método para encontrar um abrigo pelo email do responsável.
     * <p>
     * Este método aceita um objeto {@link String}, que contém o e-mail do responsável pelo abrigo,
     * com base no e-mail fornecido, e retorna uma resposta detalhando as informações deste abrigo.
     * <p>
     * O método retorna um {@link ShelterInformationResponse} que contém informações detalhadas sobre o abrigo
     * e as doações recebidas, incluindo o nome do abrigo, o nome e e-mail do responsável, e a lista de doações recebidas.
     *
     * @return um objeto {@link ShelterInformationResponse} que contém informações das doações recebidas.
     *
     * @throws ShelterEntityFailuresException se o usuário informado não for do tipo beneficiário ou não estiver cadastrado na base de dados.
     * A mensagem de erro será incluída na exceção.
     */

    ShelterInformationResponse findShelterByUserResponsibleEmail(String userResponsibleEmail);

}
