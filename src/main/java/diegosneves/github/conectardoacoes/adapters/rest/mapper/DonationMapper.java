package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;

/**
 * Esta classe implementa a interface {@link MapperStrategy} para mapear a entidade {@link DonationEntity} para o objeto de domínio {@link Donation}.
 * É confiável para a conversão de uma entidade do banco de dados para o objeto do domínio.
 *
 * <p> A classe usa a estratégia definida pela interface MapperStrategy
 * para converter um {@link DonationEntity} para um objeto de domínio doação. </p>
 *
 * @author diegoneves
 * @since 1.0.0
 * @see Donation
 * @see DonationEntity
 * @see MapperStrategy
 */
public class DonationMapper implements MapperStrategy<Donation, DonationEntity> {

    /**
     * Mapeia a entidade de doação do banco de dados para uma instância do objeto de domínio doação.
     *
     * <p> O método aceita uma entidade {@link DonationEntity} como input e cria uma nova instância do domínio de doação com base nesse input.
     * O mapeamento é feito da seguinte maneira: </p>
     *
     * <p> A entidade passada é primeiramente validada para verificar se não é nula. Se a entidade for nula, uma exceção
     * {@link ShelterEntityFailuresException} é lançada com uma mensagem de erro formatada. </p>
     *
     * <p> Em seguida, uma tentativa é feita para criar uma nova instância do domínio de doação, usando os valores retornados
     * pelos métodos {@code getId()}, {@code getDescription()} e {@code getAmount()} da entidade {@link DonationEntity}. </p>
     *
     * <p> Se a tentativa falhar por algum motivo (por exemplo, se os valores retornados não passarem nas verificações de
     * validação no construtor do domínio de doação), uma {@link DonationRegisterFailureException} é capturada e uma {@link ShelterEntityFailuresException}
     * é lançada com uma mensagem de erro formatada e a exceção original anexada. </p>
     *
     * <p> Se a criação da nova instância de Doação for bem-sucedida, essa instância é retornada. </p>
     *
     * @param source o objeto {@link DonationEntity} que será mapeado para o objeto de domínio doação. Não deve ser nulo.
     * @return uma nova instância do domínio doação, mapeada dos campos de uma entidade {@link DonationEntity}.
     * @throws ShelterEntityFailuresException se a entidade de entrada for nula ou se houver uma falha durante a criação da nova instância de Doação.
     */
    @Override
    public Donation mapFrom(DonationEntity source) {
        ValidationUtils.validateNotNullOrEmpty(
                source,
                MapperFailureException.ERROR.formatErrorMessage(DonationEntity.class.getSimpleName()),
                ShelterEntityFailuresException.class);

        Donation donation;
        try {
            donation = new Donation(source.getId(), source.getDescription(), source.getAmount());
        } catch (DonationRegisterFailureException e) {
            throw new ShelterEntityFailuresException(MapperFailureException.ERROR.formatErrorMessage(DonationEntity.class.getSimpleName()), e);
        }
        return donation;
    }
}
