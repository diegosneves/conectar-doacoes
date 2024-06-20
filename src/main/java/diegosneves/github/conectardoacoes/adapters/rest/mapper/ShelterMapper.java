package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;

/**
 * Implementação da interface {@link MapperStrategy} para a conversão entre a entidade {@link ShelterEntity} e a classe de domínio {@link Shelter}.
 * Fornece funcionalidades para mapear um objeto {@link ShelterEntity} em um objeto {@link Shelter}.
 * Utiliza a classe {@link UserMapper} para mapear o usuário responsável ({@link UserEntity}) para um objeto {@link User}.
 *
 * @author diegoneves
 * @since 1.0.0
 * @see MapperStrategy
 */
public class ShelterMapper implements MapperStrategy<Shelter, ShelterEntity> {

    /**
     * Mapeia uma entidade de abrigo ({@link ShelterEntity}) para um objeto de abrigo de domínio ({@link Shelter}).
     * Realiza a conversão de uma entidade persistida de um abrigo, junto com suas relações, para uma representação de abrigo de domínio.
     * Para mapear a entidade do usuário responsável ({@link UserEntity}), esta implementação utiliza uma instância de {@link UserMapper}.
     *
     * @param origem a entidade de origem que representa um abrigo no banco de dados
     * @return uma instância da classe de domínio {@link Shelter}, com seus campos preenchidos com os valores correspondentes da entidade de origem
     */
    @Override
    public Shelter mapFrom(ShelterEntity origem) {
        MapperStrategy<User, UserEntity> mapperStrategy = new UserMapper();
        return new Shelter(
                origem.getId(),
                origem.getShelterName(),
                BuilderMapper.mapTo(Address.class, origem.getAddress()),
                mapperStrategy.mapFrom(origem.getResponsibleUser()));
    }
}
