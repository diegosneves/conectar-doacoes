package diegosneves.github.conectardoacoes.core.service;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.ShelterContract;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.ShelterServiceFailureException;

import java.util.List;

/**
 * Interface {@link ShelterServiceContract} define vários métodos que manipulam um objeto {@link Shelter}.
 * Isso inclui criar um abrigo, alterar o nome e o endereço de um abrigo, adicionar uma doação e obter detalhes de um abrigo.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public interface ShelterServiceContract {

    /**
     * Cria um novo {@link Shelter} usando o nome do abrigo, endereço e o objeto do usuário responsável fornecidos.
     *
     * @param shelterName     nome do abrigo como uma String
     * @param address         uma instância do objeto {@link Address} representando o endereço do abrigo
     * @param responsibleUser um objeto {@link UserContract} representando o usuário responsável pelo abrigo
     * @return um objeto {@link ShelterContract} do novo abrigo criado
     * @throws ShelterCreationFailureException se um erro ocorrer durante a criação do abrigo
     */
    ShelterContract createShelter(String shelterName, Address address, UserContract responsibleUser) throws ShelterCreationFailureException;

    /**
     * Obtém os detalhes de um abrigo existente.
     *
     * @param shelterId ID do abrigo como uma String
     * @return um objeto {@link ShelterContract} com detalhes do abrigo
     * @throws ShelterServiceFailureException se um erro ocorrer durante a obtenção dos detalhes do abrigo
     */
    ShelterContract getShelter(String shelterId) throws ShelterServiceFailureException;

    /**
     * Muda o nome de um abrigo existente.
     *
     * @param shelterId ID do abrigo como uma String
     * @param newName   o novo nome do abrigo como uma String
     * @throws ShelterServiceFailureException se um erro ocorrer durante a alteração do nome do abrigo
     */
    void changeShelterName(String shelterId, String newName) throws ShelterServiceFailureException;

    /**
     * Muda o endereço de um abrigo existente.
     *
     * @param shelterId ID do abrigo como uma String
     * @param address   uma instância do objeto Address representando o novo endereço do abrigo
     * @throws ShelterServiceFailureException se um erro ocorrer durante a alteração do endereço do abrigo
     */
    void changeAddress(String shelterId, Address address) throws ShelterServiceFailureException;

    /**
     * Adiciona uma nova doação ao abrigo.
     *
     * @param shelterId ID do abrigo como uma String
     * @param donation  uma instância do objeto {@link Donation} representando a doação a ser adicionada
     * @throws ShelterServiceFailureException se um erro ocorrer durante a adição da doação ao abrigo
     */
    void addDonation(String shelterId, Donation donation) throws ShelterServiceFailureException;

    /**
     * Busca a lista de todas as {@link Donation} de um determinado abrigo.
     *
     * @param shelterId ID do abrigo como uma String
     * @return Uma lista de objetos {@link Donation}, que representa todas as doações recebidas pelo abrigo.
     * @throws ShelterServiceFailureException se um erro ocorrer durante a recuperação das doações.
     */
    List<Donation> getDonations(String shelterId) throws ShelterServiceFailureException;

}
