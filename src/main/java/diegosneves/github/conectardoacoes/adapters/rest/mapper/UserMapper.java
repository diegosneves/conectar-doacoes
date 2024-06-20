package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;

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
 * @since 1.0.0
 * @see MapperStrategy
 */
public class UserMapper implements MapperStrategy<User, UserEntity> {

    /**
     * Mapeia uma entidade de usuário ({@link UserEntity}) para um objeto de usuário de domínio ({@link User}).
     * Realiza a conversão de uma entidade persistida de um usuário para uma representação de usuário de domínio.
     * <p>
     * O mapeamento é realizado atribuindo cada campo do objeto {@link User} com os valores correspondentes do objeto {@link UserEntity}.
     * Além disso, o perfil do usuário é obtido utilizando o método valueOf da classe Enum, que retornará o perfil
     * correspondente de acordo com o nome do perfil informado.
     *
     * @param origem a entidade de origem que representa um usuário no banco de dados
     * @return uma instância da classe de domínio {@link User}, com seus campos preenchidos com os valores correspondentes da entidade de origem
     */
    @Override
    public User mapFrom(UserEntity origem) {
        return new User(
                origem.getId(),
                origem.getUserName(),
                origem.getEmail(),
                Enum.valueOf(UserProfile.class, origem.getUserProfile().name()),
                origem.getUserPassword());
    }
}
