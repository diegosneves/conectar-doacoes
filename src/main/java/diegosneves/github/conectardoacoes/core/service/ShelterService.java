package diegosneves.github.conectardoacoes.core.service;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.ShelterContract;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.shelter.factory.ShelterFactory;
import diegosneves.github.conectardoacoes.core.domain.shelter.shared.repository.ShelterRepository;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.ShelterServiceFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;

import java.util.List;

/**
 * A classe de serviço {@link ShelterService} é responsável pelas operações de negócios relacionadas a abrigos.
 * Essas operações incluem criação de abrigos, busca por abrigos e alterações nos atributos dos abrigos.
 * Esta classe implementa a interface {@link ShelterServiceContract}.
 *
 * @author diegoneves
 * @version 1.0.0
 * @see ShelterServiceContract
 * @see ShelterRepository
 */
public class ShelterService implements ShelterServiceContract {

    public static final String INVALID_SHELTER_ID_MESSAGE = "A ID especificada para o abrigo é inválida.";
    public static final String INVALID_SHELTER_NAME_ERROR_MESSAGE = "O nome do Abrigo fornecido é inválido.";
    public static final String ERROR_MESSAGE_ADDRESS_NULL = "O Endereço fornecido não deve ser nulo";
    public static final String DONATION_REQUIRED_ERROR_MESSAGE = "A Doação fornecida deve ser válida.";

    private final ShelterRepository shelterRepository;

    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    /**
     * Este método cria um novo {@link ShelterContract} utilizando {@link ShelterFactory#create}.
     * O novo objeto {@link Shelter} é salvo usando o método {@link ShelterRepository#save}.
     *
     * @param shelterName     O nome do abrigo como uma string.
     * @param address         uma instancia do objeto {@link Address} representando o endereço do abrigo
     * @param responsibleUser um objeto {@link UserContract} representando o usuário responsável pelo abrigo
     * @return um novo objeto {@link ShelterContract}
     * @throws ShelterCreationFailureException quando uma falha ocorre durante a criação do {@link ShelterContract}
     * @see ShelterFactory
     * @see ShelterRepository
     */
    @Override
    public ShelterContract createShelter(String shelterName, Address address, UserContract responsibleUser) throws ShelterCreationFailureException {
        Shelter newShelter = ShelterFactory.create(shelterName, address, responsibleUser);
        return this.shelterRepository.save(newShelter);
    }

    /**
     * Retorna um objeto {@link ShelterContract} baseado no ID do abrigo fornecido.
     *
     * @param shelterId O ID do abrigo como uma string.
     * @return um objeto {@link ShelterContract} que corresponde ao ID do abrigo fornecido
     * @throws ShelterServiceFailureException quando uma falha ocorre durante a busca pelo abrigo
     */
    @Override
    public ShelterContract getShelter(String shelterId) throws ShelterServiceFailureException {
        validateShelterId(shelterId);
        return this.shelterRepository.findById(shelterId);
    }

    /**
     * Valida o ID do abrigo fornecido. Primeiro, verifica se o ID do abrigo é nulo ou vazio usando
     * {@link ValidationUtils#checkNotNullAndNotEmptyOrThrowException}. Em seguida, tenta verificar se o ID do abrigo é um UUID válido
     * usando {@link UuidUtils#isValidUUID}.
     * Se qualquer uma dessas verificações falhar, ele lançará uma {@link ShelterServiceFailureException} com uma mensagem que indica
     * que o ID do abrigo fornecido é inválido.
     *
     * @param shelterId O ID do abrigo a ser validado.
     * @throws ShelterServiceFailureException Se o ID do abrigo fornecido for nulo, vazio ou não for um UUID válido.
     */
    private static void validateShelterId(String shelterId) throws ShelterServiceFailureException {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(shelterId, INVALID_SHELTER_ID_MESSAGE, ShelterServiceFailureException.class);
        try {
            UuidUtils.isValidUUID(shelterId);
        } catch (UuidUtilsException e) {
            throw new ShelterServiceFailureException(INVALID_SHELTER_ID_MESSAGE, e);
        }
    }

    /**
     * Este método é usado para alterar o nome do abrigo identificado pelo ID fornecido.
     * <p>
     * Primeiro, o método valida se o novo nome não é nulo nem vazio usando
     * {@link ValidationUtils#checkNotNullAndNotEmptyOrThrowException}. Se a validação falhar,
     * uma {@link ShelterServiceFailureException} é lançada com uma mensagem indicando que o novo nome do abrigo é inválido.
     * <p>
     * O nome do abrigo recuperado é então alterado para o novo nome fornecido usando o método
     * {@link ShelterContract#changeShelterName}.
     * <p>
     * Finalmente, o abrigo com o nome atualizado é salvo no repositório usando {@link ShelterRepository#save}.
     *
     * @param shelterId O ID do abrigo cujo nome será alterado. Deve ser uma identificação válida de um abrigo existente.
     * @param newName   O novo nome para o abrigo. Ele não pode ser {@code null} ou uma String vazia.
     * @throws ShelterServiceFailureException Se o novo nome do abrigo fornecido for inválido (ou seja, nulo ou vazio),
     *                                        ou se ocorrer um problema ao recuperar o abrigo com o ID fornecido ou ao salvar o abrigo com o nome alterado.
     */
    @Override
    public void changeShelterName(String shelterId, String newName) throws ShelterServiceFailureException {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(newName, INVALID_SHELTER_NAME_ERROR_MESSAGE, ShelterServiceFailureException.class);
        ShelterContract updatedShelter = this.getShelter(shelterId);
        updatedShelter.changeShelterName(newName);
        this.shelterRepository.save(updatedShelter);
    }

    /**
     * Este método é usado para alterar o endereço do abrigo identificado pelo ID fornecido.
     * <p>
     * Primeiro, o método valida se o novo endereço não é nulo usando
     * {@link ValidationUtils#checkNotNullAndNotEmptyOrThrowException}. Se a validação falhar,
     * uma {@link ShelterServiceFailureException} é lançada com uma mensagem indicando que o novo endereço do abrigo é inválido.
     * <p>
     * O endereço do abrigo recuperado é então alterado para o novo endereço fornecido usando o método
     * {@link ShelterContract#changeAddress}.
     * <p>
     * Finalmente, o abrigo com o endereço atualizado é salvo no repositório usando {@link ShelterRepository#save}.
     *
     * @param shelterId O ID do abrigo cujo endereço será alterado. Deve ser uma identificação válida de um abrigo existente.
     * @param address   O novo endereço para o abrigo. Ele não pode ser {@code null} ou uma instância inválida de {@link Address}.
     * @throws ShelterServiceFailureException Se o novo endereço do abrigo fornecido for inválido (ou seja, null ou instância inválida),
     *                                        ou se ocorrer um problema ao recuperar o abrigo com o ID fornecido ou ao salvar o abrigo com o endereço alterado.
     */
    @Override
    public void changeAddress(String shelterId, Address address) throws ShelterServiceFailureException {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(address, ERROR_MESSAGE_ADDRESS_NULL, ShelterServiceFailureException.class);
        ShelterContract updatedShelter = this.getShelter(shelterId);
        updatedShelter.changeAddress(address);
        this.shelterRepository.save(updatedShelter);
    }

    /**
     * Este método é responsável por adicionar uma {@link Donation} a um {@link ShelterContract} específico, identificado por seu id.
     * <p>
     * Primeiro, ele confirma que o objeto {@link Donation} não é nulo usando
     * {@link ValidationUtils#checkNotNullAndNotEmptyOrThrowException}. Se este objeto {@link Donation} for nulo,
     * ele lança uma {@link ShelterServiceFailureException}.
     * <p>
     * Em seguida, ele usa o id do abrigo fornecido para obter o {@link ShelterContract} correspondente.
     * Este {@link ShelterContract} é então atualizado adicionando a {@link Donation} fornecida.
     * <p>
     * Finalmente, o {@link ShelterContract} atualizado é salvo no repositorio usando {@link ShelterRepository#save}.
     *
     * @param shelterId O ID do abrigo ao qual a doação será adicionada. Deve ser uma identificação válida de um abrigo existente.
     * @param donation  Uma instancia do objeto {@link Donation} representando a doação a ser adicionada.
     * @throws ShelterServiceFailureException Se a doação fornecida for inválida (ou seja, nula ou vazia),
     *                                        ou se ocorrer um problema ao recuperar o abrigo com o ID fornecido ou ao salvar o abrigo com a doação adicionada.
     */
    @Override
    public void addDonation(String shelterId, Donation donation) throws ShelterServiceFailureException {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(donation, DONATION_REQUIRED_ERROR_MESSAGE, ShelterServiceFailureException.class);
        ShelterContract updatedShelter = this.getShelter(shelterId);
        updatedShelter.addDonation(donation);
        this.shelterRepository.save(updatedShelter);
    }

    /**
     * Este método é responsável por recuperar a lista de {@link Donation} de um {@link ShelterContract} específico, identificado por seu id.
     * <p>
     * Primeiro, ele usa o id do abrigo fornecido para obter o {@link ShelterContract} correspondente.
     * A lista de {@link Donation} deste {@link ShelterContract} específico é então retornada.
     * <p>
     * Note que este método pode lançar uma {@link ShelterServiceFailureException}. Isto ocorrerá se houver um problema ao tentar recuperar o {@link ShelterContract} com o id fornecido.
     *
     * @param shelterId O ID do abrigo do qual as doações serão recuperadas. Deve ser uma identificação válida de um abrigo existente.
     * @return Uma lista de {@link Donation} que foram feitas para o abrigo identificado pelo id fornecido.
     * @throws ShelterServiceFailureException Se ocorrer um problema ao tentar recuperar o {@link ShelterContract} com o id fornecido.
     */
    @Override
    public List<Donation> getDonations(String shelterId) throws ShelterServiceFailureException {
        ShelterContract shelter = this.getShelter(shelterId);
        return shelter.getDonations();
    }
}
