package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;

/**
 * Classe responsável por implementar a estratégia de mapeamento para objetos {@link Donation}
 * para objetos {@link DonationEntity}.
 * Essa classe permite a conversão de instâncias de {@link Donation} em objetos {@link DonationEntity}, que
 * podem ser persistidos no banco de dados.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public class DonationEntityMapper implements MapperStrategy<DonationEntity, Donation> {

    public static final Class<Donation> DONATION_CLASS = Donation.class;

    /**
     * Mapeia o objeto {@link Donation} fornecido para um objeto {@link DonationEntity}.
     * <p>
     * Este método recebe um objeto {@link Donation}, valida se o objeto não é nulo através do utilitário {@link ValidationUtils},
     * e em seguida, usa o mapeador {@link BuilderMapper} para mapear o objeto {@link Donation} para um objeto {@link DonationEntity}.
     *
     * @param source o objeto {@link Donation} a ser mapeado
     * @return um objeto {@link DonationEntity} que representa a entidade de doação
     * @throws ShelterEntityFailuresException se o objeto {@link Donation} fornecido for nulo
     * @throws MapperFailureException         se ocorrer um erro durante o mapeamento
     */
    @Override
    public DonationEntity mapFrom(Donation source) {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(
                source,
                MapperFailureException.ERROR.formatErrorMessage(DONATION_CLASS.getSimpleName()),
                ShelterEntityFailuresException.class);
        return BuilderMapper.mapTo(DonationEntity.class, source);
    }
}
