package diegosneves.github.conectardoacoes.adapters.rest.mapper;


import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.AddressEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe {@link ShelterEntityMapper} que implementa a interface {@link MapperStrategy} para executar operações de mapeamento entre a classe {@link Shelter} e a classe {@link ShelterEntity}.
 * <p>
 * Esta classe contém métodos que realizam o mapeamento de um objeto {@link Shelter} para um objeto {@link ShelterEntity}.
 * <p>
 * Esta classe usa a classes {@link AddressEntityMapper}, {@link UserEntityMapper} e {@link DonationEntityMapper} para mapear respectivas partes do objeto {@link Shelter}.
 * <p>
 * Também contém um método auxiliar para mapear uma lista de objetos {@link Donation}.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public class ShelterEntityMapper implements MapperStrategy<ShelterEntity, Shelter> {

    public static final Class<Shelter> SHELTER_CLASS = Shelter.class;

    /**
     * Método que converte a fonte, um objeto da classe {@link Shelter}, para um novo objeto da classe {@link ShelterEntity}.
     * <p>
     * O objeto de origem é verificado para não ser nulo ou vazio, caso contrário, uma {@link ShelterEntityFailuresException} é lançada.
     * Cada campo do objeto de origem é mapeado para um campo correspondente no objeto de destino. Os objetos {@link Address} e {@link UserContract} do objeto de origem são convertidos em {@link AddressEntity} e {@link UserEntity}, respectivamente, através dos mappers apropriados.
     * As doações em {@link Shelter} são mapeadas para {@link DonationEntity} usando o método {@code getDonationEntities}.
     * <p>
     * Se ocorrer uma exceção durante o processo de mapeamento, uma {@link ShelterEntityFailuresException} é lançada, encapsulando a exceção original.
     *
     * @param source objeto {@link Shelter} que deve ser mapeado
     * @return um novo objeto {@link ShelterEntity} que é o resultado do mapeamento.
     * @throws ShelterEntityFailuresException se a fonte {@link Shelter} for {@code null} ou vazia, ou se ocorrer um erro ao mapear qualquer um dos campos.
     */
    @Override
    public ShelterEntity mapFrom(Shelter source) {
        ValidationUtils.checkNotNullAndNotEmptyOrThrowException(source, MapperFailureException.ERROR.formatErrorMessage(SHELTER_CLASS.getSimpleName()), ShelterEntityFailuresException.class);
        ShelterEntity shelterEntity = null;

        try {
            shelterEntity = ShelterEntity.builder()
                    .id(source.getId())
                    .shelterName(source.getShelterName())
                    .address(new AddressEntityMapper().mapFrom(source.getAddress()))
                    .responsibleUser(new UserEntityMapper().mapFrom((User) source.getUser()))
                    .donations(getDonationEntities(source.getDonations()))
                    .build();
        } catch (RuntimeException e) {
            throw new ShelterEntityFailuresException(MapperFailureException.ERROR.formatErrorMessage(SHELTER_CLASS.getSimpleName()), e);
        }
        return shelterEntity;
    }

    /**
     * Método que converte a lista de doações da classe {@link Donation} em uma lista de objetos {@link DonationEntity}.
     * <p>
     * Esse método recebe como parâmetro uma lista de objetos {@link Donation} e, usando a classe {@link DonationEntityMapper}, transforma cada objeto {@link Donation} em um objeto {@link DonationEntity}, retornando uma lista completa desses objetos.
     * <p>
     * Se a lista fornecida for nula ou vazia, o método retornará uma nova lista vazia.
     *
     * @param list uma lista de objetos {@link Donation} que deve ser convertida
     * @return uma lista de objetos {@link DonationEntity} resultante da conversão. Se a lista fornecida for nula ou vazia, retorna uma nova lista vazia.
     */
    private static List<DonationEntity> getDonationEntities(List<Donation> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        return list.stream().map(new DonationEntityMapper()::mapFrom).toList();
    }

}
