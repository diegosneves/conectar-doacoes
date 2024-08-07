package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * A classe {@code UserEntityMapper} implementa a interface {@link MapperStrategy} e é usada para mapear um objeto do tipo {@link User} para um objeto de entidade {@link UserEntity}.
 * <p>
 * Essa classe é essencial para a camada de persistência do aplicativo, pois permite uma conversão eficiente entre objetos do domínio do problema e entidades que podem ser persistidas em um banco de dados.
 * </p>
 *
 * @author diegoneves
 * @since 1.0.0
 */
@Slf4j
public class UserEntityMapper implements MapperStrategy<UserEntity, UserContract> {

    public static final Class<UserContract> USER_CLASS = UserContract.class;
    public static final String MAPPING_ERROR_LOG = "Ocorreu um erro durante o processo de mapeamento do objeto UserContract para UserEntity. Detalhes do erro: {}";

    /**
     * Este método é usado para mapear um objeto de origem do tipo {@link User} para um objeto de entidade {@link UserEntity}.
     * <p>
     * Primeiro, realiza uma verificação de not-null-or-empty do objeto de origem utilizando a utilidade {@link ValidationUtils}.
     * Se o objeto de origem for nulo ou vazio, um {@link UserEntityFailuresException} será lançado com a mensagem de erro gerada por {@link ExceptionDetails}.
     * </p>
     * <p>
     * Em seguida, tenta mapear o objeto de origem para a classe {@link UserEntity} usando a utilidade {@link BuilderMapper}.
     * Após o mapeamento bem-sucedido, ele então tenta configurar o perfil do usuário no objeto de entidade mapeado. O perfil do usuário é obtido a partir do perfil do usuário do objeto de origem e convertido para uma enumeração de {@link UserProfileType}.
     * </p>
     * <p>
     * Se um {@link RuntimeException} é lançado durante o mapeamento ou a configuração do perfil do usuário, um novo {@link UserEntityFailuresException} é lançado com a mensagem de erro gerada por {@link ExceptionDetails} e a exceção original anexada para fins de rastreamento.
     * </p>
     * O objeto {@link UserEntity} mapeado, ou nulo se não foi possível realizar o mapeamento, é retornado ao chamador.
     *
     * @param source o objeto do tipo {@link User} que é usado como base para criar um objeto de entidade {@link UserEntity}.
     * @return um objeto {@link UserEntity} mapeado a partir do objeto de origem do tipo {@link User},
     * ou nulo se o mapeamento não foi possível devido a exceções durante o mapeamento ou a configuração do perfil do usuário da entidade.
     * @throws UserEntityFailuresException se o objeto de origem é nulo ou vazio, ou se um erro ocorrer durante o mapeamento ou a configuração do perfil do usuário.
     */
    @Override
    public UserEntity mapFrom(UserContract source) {
        ValidationUtils.validateNotNullOrEmpty(source, CLASS_MAPPING_FAILURE, USER_CLASS.getSimpleName(), UserEntityFailuresException.class);
        UserEntity userEntity;
        try {
            userEntity = BuilderMapper.mapTo(UserEntity.class, source);
            userEntity.setUserProfile(Enum.valueOf(UserProfileType.class, source.getUserProfile().name()));
        } catch (RuntimeException e) {
            log.error(MAPPING_ERROR_LOG, e.getMessage(), e);
            throw new UserEntityFailuresException(CLASS_MAPPING_FAILURE, USER_CLASS.getSimpleName(), e);
        }
        return userEntity;
    }

}
