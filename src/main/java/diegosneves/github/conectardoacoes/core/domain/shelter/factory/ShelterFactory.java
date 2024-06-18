package diegosneves.github.conectardoacoes.core.domain.shelter.factory;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;

/**
 * Classe {@link ShelterFactory} responsável por fornecer métodos para criar instâncias da classe {@link Shelter}.
 * <p>
 * Esta classe é um exemplo de aplicação do padrão de projeto Factory, que fornece um método estático para a criação de instâncias de {@link Shelter}.
 * Neste caso, a criação do objeto Shelter é abstraída para a esta classe.
 * <p>
 * Esta classe destaca a declaração de um método público estático create, responsável pela criação de uma instance de {@link Shelter}.
 *
 * @author diegoneves
 * @since 1.0.0
 * @see Shelter
 */
public class ShelterFactory {

    private ShelterFactory() {}

    /**
     * Construtor estático para a classe {@link Shelter}.
     * <p>
     * Este método é responsável por criar uma nova instancia de {@link Shelter} com um UUID gerado dinamicamente, juntamente com os detalhes fornecidos.
     * <p>
     * Este método utiliza o método {@link UuidUtils#generateUuid} para gerar um UUID único para o novo objeto {@link Shelter}.
     *
     * @param shelterName A string que representa o nome do abrigo.
     * @param address A instância de {@link Address} que representa o endereço do abrigo.
     * @param responsibleUser A instância de {@link UserContract} quem representa o usuário responsável pelo abrigo.
     * @return {@link Shelter} A nova instância de Shelter com os detalhes fornecidos.
     * @throws ShelterCreationFailureException se qualquer informação do Abrigo fornecida for inválida.
     *
     */
    public static Shelter create(String shelterName, Address address, UserContract responsibleUser) {
        return new Shelter(UuidUtils.generateUuid(), shelterName, address, responsibleUser);
    }

}
