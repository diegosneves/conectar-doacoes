package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.model.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Java Persistence API (JPA) Repository para {@link AddressEntity}.
 * Esta interface estende {@link CrudRepository}, que fornece métodos como save(…), findOne(…), findAll(), count(), delete(…) etc.
 * <p>
 * Aciona consultas de banco de dados relacionadas a {@link AddressEntity}.
 *
 * @Repository indica que esta interface é um Repositório Spring Data JPA, um mecanismo que fornece
 * métodos de manipulação de dados comuns sem a necessidade de escrever nossa própria lógica de consulta.
 * O Spring Data JPA também se integra bem ao Hibernate, uma implementação popular do padrão Java Persistence API.
 *
 * @author diegoneves
 * @since 1.0.0
 */
@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, String> {

    /**
     * Retorna um conteiner Optional encapsulando o {@link AddressEntity} encontrado pelo Id.
     *
     * @param id String, identificador da entidade de endereço a ser procurada. Deve ser não nulo.
     * @return um Optional de {@link AddressEntity}. Este Optional será não nulo, mas pode estar vazio se nenhum AddressEntity
     * com o ID fornecido for encontrado.
     */
    Optional<AddressEntity> findById(String id);

}
