package diegosneves.github.conectardoacoes.core.domain.shelter.entity;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface {@link ShelterContract}, que representa um abrigo na aplicação.
 * Uma instância desta classe representa um abrigo com uma identificação, um nome de referência, um endereço, um usuário responsável, e uma lista de doações.
 *
 * @author diegoneves
 * @version 1.0.0
 * @see ShelterContract
 */
public class Shelter implements ShelterContract {

    public static final String ID_VALIDATION_FAILURE = "Erro ao tentar validar o ID do Abrigo";
    public static final String ADDRESS_REQUIRED_ERROR = "É necessário fornecer um endereço";
    public static final String DONATION_REQUIRED_ERROR = "Uma doação deve ser fornecida para adicionar ao abrigo";
    public static final String RESPONSIBLE_REQUIRED_ERROR = "Um Abrigo deve possuir um responsável";
    public static final String SHELTER_NAME_REQUIRED_ERROR = "Um nome de referência deve ser designado para o abrigo";

    private final String id;
    private String shelterName;
    private Address address;
    private UserContract responsibleUser;
    private List<Donation> donations;


    /**
     * Construtor da classe {@link Shelter} que inicializa a classe com valores fornecidos e valida o conjunto de dados presentes.
     *
     * @param id              A identificação do abrigo, que é obrigatório.
     * @param shelterName     O nome de referência do abrigo, que é obrigatório.
     * @param address         O endereço do abrigo, que é obrigatório.
     * @param responsibleUser O usuário responsável pelo abrigo, que é obrigatório.
     * @throws ShelterCreationFailureException se os dados fornecidos forem inválidos.
     */
    public Shelter(String id, String shelterName, Address address, UserContract responsibleUser) {
        this.id = id;
        this.shelterName = shelterName;
        this.address = address;
        this.responsibleUser = responsibleUser;
        this.donations = new ArrayList<>();
        this.validateData();
    }

    /**
     * Este método privado é usado para validar os dados do objeto {@link Shelter} antes de sua criação.
     * Realiza uma série de verificações ignorando nulidade e vazio para garantir a integridade dos dados.
     *
     * <p>Os passos seguidos por este método são:
     * <ul>
     *   <li>Invoca o método {@link UuidUtils#isValidUUID(String)} para verificar a validade do ID do Abrigo.</li>
     *   <li>Verifica se o campo {@link String shelterName} não é nulo e nem vazio.</li>
     *   <li>Verifica se o campo {@link Address address} não é nulo.</li>
     *   <li>Verifica se o campo {@link UserContract responsibleUser} não é nulo.</li>
     * </ul>
     * <p>
     * Se alguma dessas verificações falhar, o método lançará uma {@link ShelterCreationFailureException}.
     *
     * @throws ShelterCreationFailureException é lançada se o ID do Abrigo for inválido ou se algum dos campos necessários (shelterName, address, responsibleUser) estiver faltando.
     */
    private void validateData() throws ShelterCreationFailureException {
        try {
            UuidUtils.isValidUUID(this.id);
        } catch (UuidUtilsException e) {
            throw new ShelterCreationFailureException(ID_VALIDATION_FAILURE, e);
        }
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(this.shelterName, SHELTER_NAME_REQUIRED_ERROR, ShelterCreationFailureException.class);
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(this.address, ADDRESS_REQUIRED_ERROR, ShelterCreationFailureException.class);
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(this.responsibleUser, RESPONSIBLE_REQUIRED_ERROR, ShelterCreationFailureException.class);
    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getShelterName() {
        return this.shelterName;
    }

    @Override
    public Address getAddress() {
        return this.address;
    }

    @Override
    public UserContract getUser() {
        return this.responsibleUser;
    }

    @Override
    public void changeShelterName(String shelterName) throws ShelterCreationFailureException {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(shelterName, SHELTER_NAME_REQUIRED_ERROR, ShelterCreationFailureException.class);
        this.shelterName = shelterName;
    }

    @Override
    public void changeAddress(Address address) throws ShelterCreationFailureException {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(address, ADDRESS_REQUIRED_ERROR, ShelterCreationFailureException.class);
        this.address = address;
    }

    @Override
    public void addDonation(Donation donation) throws ShelterCreationFailureException {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(donation, DONATION_REQUIRED_ERROR, ShelterCreationFailureException.class);
        this.donations.add(donation);
    }
}
