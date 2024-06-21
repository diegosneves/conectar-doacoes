package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;

/**
 * Implementação da interface {@link MapperStrategy} para a conversão entre a entidade {@link ShelterEntity} e a classe de domínio {@link Shelter}.
 * Fornece funcionalidades para mapear um objeto {@link ShelterEntity} em um objeto {@link Shelter}.
 * Utiliza a classe {@link UserMapper} para mapear o usuário responsável ({@link UserEntity}) para um objeto {@link User}.
 *
 * @author diegoneves
 * @see MapperStrategy
 * @since 1.0.0
 */
public class ShelterMapper implements MapperStrategy<Shelter, ShelterEntity> {

    /**
     * Mapeia uma entidade de abrigo ({@link ShelterEntity}) para um objeto de abrigo de domínio ({@link Shelter}).
     * Realiza a conversão de uma entidade persistida de um abrigo, junto com suas relações, para uma representação de abrigo de domínio.
     * Para mapear a entidade do usuário responsável ({@link UserEntity}), esta implementação utiliza uma instância de {@link UserMapper}.
     *
     * @param source a entidade de origem que representa um abrigo no banco de dados
     * @return uma instância da classe de domínio {@link Shelter}, com seus campos preenchidos com os valores correspondentes da entidade de origem
     */
    @Override
    public Shelter mapFrom(ShelterEntity source) {

        Shelter constructedShelter = null;
        try {
            constructedShelter = new Shelter(
                    source.getId(),
                    source.getShelterName(),
                    new AddressMapper().mapFrom(source.getAddress()),
                    new UserMapper().mapFrom(source.getResponsibleUser()));
        } catch (RuntimeException e) {
            throw new ShelterEntityFailuresException(MapperFailureException.ERROR.formatErrorMessage(source.getClass().getSimpleName()), e);
        }
        this.mappedDonationsToShelter(source, constructedShelter);
        return constructedShelter;
    }


    /**
     * Mapeia as doações de uma entidade de abrigo {@link ShelterEntity} para um abrigo construído {@link Shelter}. <p>
     * <p>
     * Este método privado é usado quando um objeto {@link Shelter} está sendo construído a partir de um objeto {@link ShelterEntity}.
     * Sua tarefa principal é garantir que todas as doações associadas à entidade de abrigo sejam devidamente mapeadas e atribuídas ao novo objeto Shelter.
     *
     * <p>
     * O método funciona da seguinte maneira:
     * <p>
     * - Verifica se há doações associadas à entidade de abrigo. Se não houver, o método retorna imediatamente e nenhuma doação é adicionada ao abrigo.<br><br>
     * - Se houver doações, o método percorre a lista de doações na entidade. Para cada {@link DonationEntity} na lista, ele cria um novo objeto {@link Donation} com a descrição e o valor da entidade da doação.<br><br>
     * - Esse novo objeto de Doação é adicionado ao objeto de abrigo construído por meio do método addDonation.<br>
     *
     * @param shelterEntity      A entidade de abrigo de onde as doações serão extraídas. Este é um objeto {@link ShelterEntity} cujas doações estão sendo mapeadas.
     * @param constructedShelter O objeto Shelter que está sendo construído. As doações extraídas da entidade de abrigo serão atribuídas a este objeto.
     * @throws ShelterEntityFailuresException Se uma falha ocorrer durante a operação de adicionar a doação ao abrigo ou durante o registro da doação.
     */
    private void mappedDonationsToShelter(ShelterEntity shelterEntity, Shelter constructedShelter) throws ShelterEntityFailuresException {
        if (shelterEntity.getDonations().isEmpty()) {
            return;
        }
        for (DonationEntity donationEntity : shelterEntity.getDonations()) {
            try {
                constructedShelter.addDonation(new Donation(donationEntity.getDescription(), donationEntity.getAmount()));
            } catch (DonationRegisterFailureException e) {
                throw new ShelterEntityFailuresException(MapperFailureException.ERROR.formatErrorMessage(Donation.class.getSimpleName()), e);
            }
        }
    }
}
