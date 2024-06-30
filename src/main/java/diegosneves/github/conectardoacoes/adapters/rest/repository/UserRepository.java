package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.domain.user.shared.repository.UserContractRepository;
import java.util.Collections;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends UserContractRepository, CrudRepository<UserEntity, String> {

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
