package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.MapperStrategy;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.UserEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.UserMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.domain.user.shared.repository.UserContractRepository;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A interface do repositório de usuário, {@link UserRepository}, estende as interfaces {@link UserContractRepository} e {@link CrudRepository}.
 * <p>
 *
 * @author diegoneves
 * @see UserContractRepository
 * @see CrudRepository
 * @since 1.0.0
 */
@Repository
public interface UserRepository extends UserContractRepository, CrudRepository<UserEntity, String> {

    Integer INVALID_ID_MESSAGE = 19;
    Integer REQUIRED_USER_ERROR_MESSAGE = 35;
    Integer INVALID_UUID_FORMAT_MESSAGE = 6;

    /**
     * Este método busca um usuário pelo email fornecido e retorna um {@link Optional} de {@link UserEntity}.
     * Se nenhum usuário for encontrado com o email fornecido,
     * este método retornará um {@link Optional} vazio.
     *
     * @param email O email do usuário a ser buscado.
     * @return um Optional de UserEntity.
     */
    Optional<UserEntity> findByEmail(String email);


    @Override
    default UserContract findEntityById(String id) {
        UserEntity userEntity = this.retrieveUserEntityById(id);
        if (userEntity == null) return null;
        return this.getUserMapper().mapFrom(userEntity);
    }

    /**
     * Este método retorna uma entidade de usuário com base no ID fornecido.
     * Antes de tentar recuperar o usuário, o método valida o ID do usuário fornecido.
     *
     * <p>Depois de validar o ID do usuário, usando o método {@link #validateUserId(String)}, este método tenta buscar a entidade do
     * usuário. Se a entidade do usuário não existir, retorna null.</p>
     *
     * @param id O ID da entidade do usuário a ser retornada. Não pode ser nulo ou uma String vazia.
     * @return A {@link UserEntity} que corresponde ao ID fornecido. Se nenhuma {@link UserEntity} corresponder ao fornecido, este
     * método retorna null.
     * @throws UserEntityFailuresException Se o ID do usuário fornecido for nulo, vazio ou não for um UUID válido.
     * @see #validateUserId(String)
     */
    private UserEntity retrieveUserEntityById(String id) {
        this.validateUserId(id);
        Optional<UserEntity> optionalUser = this.findById(id);
        return optionalUser.orElse(null);
    }

    /**
     * Este método é responsável por criar e retornar uma nova instância de {@link UserMapper}.
     * <p>
     * A classe {@link UserMapper} é uma implementação da interface {@link MapperStrategy} que fornece
     * funcionalidade para mapear um objeto {@link UserEntity} (que é compatível com o modelo de dados do banco)
     * para um objeto {@link User}, que representa o modelo de negócio do sistema. Assim, a classe {@link UserMapper}
     * ajuda na conversão entre o modelo de dados e o modelo de negócio quando estamos lidando com informações de usuário.
     *
     * @return uma nova instância de {@link UserMapper}.
     */
    private UserMapper getUserMapper() {
        return new UserMapper();
    }

    /**
     * Método de validação do ID do usuário.
     * Este método realiza duas verificações principais:
     * <ol>
     *     <li>Certifica-se de que o ID do usuário não é nulo ou vazio.</li>
     *     <li>Certifica-se de que o ID do usuário é um UUID válido.</li>
     * </ol>
     * <p>
     * Caso alguma dessas verificações falhe, uma {@link UserEntityFailuresException} será lançada.
     *
     * @param id O ID do usuário que deve ser validado.
     * @throws UserEntityFailuresException Se o ID do usuário for nulo, vazio ou não for um UUID válido.
     */
    private void validateUserId(String id) throws UserEntityFailuresException {
        ValidationUtils.validateNotNullOrEmpty(id, INVALID_ID_MESSAGE, UserEntityFailuresException.class);
        try {
            UuidUtils.isValidUUID(id);
        } catch (UuidUtilsException e) {
            throw new UserEntityFailuresException(INVALID_UUID_FORMAT_MESSAGE, id, e);
        }
    }

    @Override
    default List<UserContract> retrieveAll() {
        List<UserEntity> allUsers = (List<UserEntity>) this.findAll();
        return this.mapEntityList(allUsers);
    }

    /**
     * Este método é usado para mapear uma lista de objetos {@link UserEntity} para uma lista de objetos {@link UserContract}.
     *
     * <p>
     * Se a lista de {@link UserEntity} fornecida é nula ou vazia, o método retorna uma lista vazia imutável. Caso contrário,
     * o método itera sobre esta lista, mapeando cada objeto {@link UserEntity} para sua representação como
     * um objeto {@link UserContract}, usando {@link UserMapper#mapFrom(UserEntity)} para realizar a transformação.
     * </p>
     *
     * @param entities uma lista de {@link UserEntity} a serem mapeados para objetos {@link UserContract}. Pode ser nula ou vazia.
     * @return uma lista de objetos {@link UserContract} correspondente aos {@link UserEntity}'s fornecidos.
     * Se o {@link UserEntity} for nulo ou vazio, uma lista vazia imutável é retornada.
     */
    private List<UserContract> mapEntityList(List<UserEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this.getUserMapper()::mapFrom).toList();
    }


    @Override
    default UserContract persist(UserContract entity) {
        ValidationUtils.validateNotNullOrEmpty(entity, REQUIRED_USER_ERROR_MESSAGE, UserEntityFailuresException.class);
        UserEntity userEntity = BuilderMapper.mapTo(new UserEntityMapper(), entity);
        return BuilderMapper.mapTo(this.getUserMapper(), this.save(userEntity));
    }

    @Override
    default void deleteEntityById(String id) {
        UserEntity targetEntity = this.retrieveUserEntityById(id);
        if (targetEntity == null) return;
        this.delete(targetEntity);
    }

}
