package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.UserCreationFailureException;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementação da interface {@link MapperStrategy} para a conversão entre a entidade {@link UserEntity} e a classe de domínio {@link User}.
 * Fornece funcionalidades para mapear um objeto {@link UserEntity} em um objeto {@link User}.
 * <p>
 * A classe {@link UserMapper} tem a função de converter os dados de um objeto {@link UserEntity}, que são compatíveis com o modelo de
 * dados do banco, para um objeto {@link User}, que é o modelo de negócio do sistema.
 * <p>
 * Como é uma implementação da interface {@link MapperStrategy}, a classe {@link UserMapper} é obrigada a implementar o método {@code mapFrom}.
 *
 * @author diegoneves
 * @see MapperStrategy
 * @since 1.0.0
 */
@Slf4j
public class UserMapper implements MapperStrategy<UserContract, UserEntity> {

    public static final Class<UserEntity> USER_ENTITY_CLASS = UserEntity.class;
    public static final String MAPPING_ERROR_LOG = "Ocorreu um erro durante o processo de mapeamento do objeto UserEntity para UserContract. Detalhes do erro: {}";

    /**
     * Mapeia uma entidade de usuário ({@link UserEntity}) para um objeto de usuário de domínio ({@link User}).
     * Realiza a conversão de uma entidade persistida de um usuário para uma representação de usuário de domínio.
     * <p>
     * O mapeamento é realizado atribuindo cada campo do objeto {@link User} com os valores correspondentes do objeto {@link UserEntity}.
     * Além disso, o perfil do usuário é obtido utilizando o método valueOf da classe Enum, que retornará o perfil
     * correspondente de acordo com o nome do perfil informado.
     *
     * @param source a entidade de origem que representa um usuário no banco de dados
     * @return uma instância da classe de domínio {@link User}, com seus campos preenchidos com os valores correspondentes da entidade de origem
     */
    @Override
    public UserContract mapFrom(UserEntity source) {
        this.validateData(source);
        this.validateData(source.getUserProfile());
        User mappedUser;
        try {
            mappedUser = new User(
                    source.getId(),
                    source.getUserName(),
                    source.getEmail(),
                    Enum.valueOf(UserProfile.class, source.getUserProfile().name()),
                    source.getUserPassword());
        } catch (UserCreationFailureException e) {
            log.error(MAPPING_ERROR_LOG, e.getMessage(), e);
            throw new UserEntityFailuresException(MapperFailureException.ERROR.formatErrorMessage(USER_ENTITY_CLASS.getSimpleName()), e);
        }
        return mappedUser;
    }

    /**
     * Este método é usado para verificar se o dado fornecido é nulo
     * ou vazio (no caso de Strings) e lança uma exceção se alguma dessas condições for atendida.
     * Este método é útil para validar os dados antes de operações que requerem que esses dados não sejam nulos ou vazios.
     *
     * @param <T>  o tipo de dado a ser validado. Como este é um método genérico, ele pode aceitar qualquer tipo de objeto.
     * @param data o dado a ser validado.
     * @throws UserEntityFailuresException se o dado fornecido for nulo ou, no caso de Strings, estiver vazio.
     *                                     A mensagem da exceção será uma mensagem de erro formatada a partir do {@link MapperFailureException#ERROR} anexada com a
     *                                     simplificação do nome da classe {@link UserEntity}.
     * @see ValidationUtils
     * @see MapperFailureException
     */
    private <T> void validateData(T data) throws UserEntityFailuresException {
        ValidationUtils.validateNotNullOrEmpty(data, MapperFailureException.ERROR.formatErrorMessage(USER_ENTITY_CLASS.getSimpleName()), UserEntityFailuresException.class);
    }

}
