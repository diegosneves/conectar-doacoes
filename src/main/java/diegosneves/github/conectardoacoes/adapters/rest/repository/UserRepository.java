package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.domain.user.shared.repository.UserContractRepository;
import java.util.Collections;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * A interface do repositório de usuário, {@link UserRepository}, estende as interfaces {@link UserContractRepository} e {@link CrudRepository}.
 * <p>
 * @Repository - Esta anotação é uma especialização da anotação @Component, permitindo detecção automática de classes.
 * Isso também traduzirá qualquer exceção de tempo de execução lançada por classes de repositório,
 * back-end de dados ou suporte de persistência para a exceção específica do Spring {@link org.springframework.dao.DataAccessException}.
 * @author diegoneves
 * @since 1.0.0
 * @see UserContractRepository
 * @see CrudRepository
 */
@Repository
public interface UserRepository extends UserContractRepository, CrudRepository<UserEntity, String> {

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
        return null;
    }

    @Override
    default List<UserContract> retrieveAll() {
        return Collections.emptyList();
    }

    @Override
    default UserContract persist(UserContract entity) {
        return null;
    }

    @Override
    default void deleteEntityById(String id) {

    }

}
