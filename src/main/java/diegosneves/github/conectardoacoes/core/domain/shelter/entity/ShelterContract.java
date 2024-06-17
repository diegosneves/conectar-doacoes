package diegosneves.github.conectardoacoes.core.domain.shelter.entity;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;

import java.util.List;

/**
 * Interface {@link ShelterContract} define os métodos que representam os principais comportamentos e a divulgação de informações de uma entidade {@link Shelter}.
 * <p>
 * Essa interface é fundamental para definir os contratos para identificação do Abrigo, gerenciamento e atualização de endereço e nome do abrigo,
 * além da adição de doações e recuperação de um usuário responsável pelo abrigo.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public interface ShelterContract {

    /**
     * Retorna a identificação do refúgio na forma de uma String.
     *
     * @return uma String que representa a identificação única do refúgio
     */
    String getId();

    /**
     * Retorna o nome do refúgio na forma de uma String.
     *
     * @return uma String que representa o nome do refúgio.
     */
    String getShelterName();

    /**
     * Retorna o endereço do refúgio na forma de um objeto {@link Address}.
     *
     * @return um objeto Address que representa o endereço do refúgio.
     */
    Address getAddress();

    /**
     * Retorna uma lista de todas as doações associadas ao Abrigo.
     * <p>
     * Cada item na lista é um objeto {@link Donation}, que representa uma
     * doação feita ao Abrigo. A doação contém informações sobre a descrição
     * e a quantidade da doação.
     * <p>
     * Este método não recebe nenhum parâmetro.
     *
     * @return Uma lista de objetos {@link Donation} que representam as
     * doações feitas ao Abrigo. Se não houver doações, este método retornará
     * uma lista vazia.
     */
    List<Donation> getDonations();

    /**
     * Este método é usado para alterar o endereço do abrigo.
     * <p>
     * Antes de alterar o endereço, este método verifica se o novo endereço fornecido não é nulo. Se o endereço for nulo, o método lançará uma {@link ShelterCreationFailureException}. Isto garante que o endereço do abrigo sempre seja válido.
     * Se o endereço passar por esta verificação, ele será atribuído como o novo endereço do abrigo, substituindo o endereço antigo.
     *
     * @param address O novo endereço que deve ser atribuído ao abrigo.
     *                Deve ser uma instância da classe {@link Address}, que representa um endereço físico e não pode ser nulo.
     * @throws ShelterCreationFailureException Se o endereço fornecido for nulo.
     */
    void changeAddress(Address address) throws ShelterCreationFailureException;

    /**
     * Altera o nome do abrigo.
     * Esse método recebe o novo nome do abrigo como parâmetro e substitui o nome atual
     * armazenado no objeto {@link Shelter} pelo novo nome fornecido (não vazio e não nulo).
     * Antes da atribuição, realiza uma verificação para certificar-se de que o novo
     * nome do abrigo não é nulo nem vazio. Se essa verificação falhar,
     * uma exceção {@link ShelterCreationFailureException} será lançada.
     *
     * @param shelterName O novo nome do abrigo. Este nome deve ser um valor String não nulo e não vazio.
     * @throws ShelterCreationFailureException Se o parâmetro shelterName for nulo ou vazio.
     */
    void changeShelterName(String shelterName) throws ShelterCreationFailureException;

    /**
     * Adiciona uma doação à lista de doações do abrigo.
     * <p>
     * Este método primeiramente verifica se a doação não é nula, se for, uma {@link ShelterCreationFailureException} será lançada com uma mensagem de erro adequada.
     * Se a doação passar pela verificação, então ela é adicionada à lista interna de doações do abrigo.
     *
     * @param donation Um objeto da classe Donation que representa a doação que será adicionada ao abrigo.
     *                 Se a doação for nula, o método irá lançar uma exceção.
     * @throws ShelterCreationFailureException Se a doação for nula.
     */
    void addDonation(Donation donation) throws ShelterCreationFailureException;

    /**
     * Retorna o usuário associado ao refúgio na forma de um objeto {@link UserContract}.
     *
     * @return um objeto {@link UserContract} que representa o usuário responsável pelo refúgio.
     */
    UserContract getUser();

}
