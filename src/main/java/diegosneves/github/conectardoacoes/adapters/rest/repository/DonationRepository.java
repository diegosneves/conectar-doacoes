package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Definição da interface do repositório para a entidade de doação {@link DonationEntity}.
 * Esta interface estende a interface CrudRepository do Spring Data JPA,
 * o que torna disponível metódos básicos de CRUD (Create, Read, Update, Delete).
 * <p/>
 * Esta interface define um método personalizado para buscar uma entidade de doação com base no ID.
 *
 * @Repository - Esta anotação é uma especialização da anotação @Component, permitindo detecção automática de classes.
 * Isso também traduzirá qualquer exceção de tempo de execução lançada por classes de repositório,
 * back-end de dados ou suporte de persistência para a exceção específica do Spring {@link org.springframework.dao.DataAccessException}.
 *
 * @author diegoneves
 * @since 1.0.0
 * @see org.springframework.data.repository.CrudRepository
 */
@Repository
public interface DonationRepository extends CrudRepository<DonationEntity, String> {

    /**
     * Este método é usado para encontrar uma entidade de doação com base no ID.
     *
     * @param id - O ID da entidade de doação que está sendo procurada.
     *               Este parâmetro é usado para identificar a entidade de doação a ser buscada.
     * @return Retorna um {@link Optional} que pode ou não conter uma entidade de doação, dependendo se um
     * correspondente foi encontrado ou não.
     * Optional é usado aqui para forçar o programador a pensar
     * sobre o caso em que a entidade de doação pode não ser encontrada.
     * Isso reduz o risco de lançar um NullPointerException não intencional.
     * Se uma entidade de doação correspondente for encontrada, ela será retornada, caso contrário,
     * um Optional vazio será retornado.
     *
     * @throws org.springframework.dao.DataAccessException - em caso de falha na persistência.
     */
    Optional<DonationEntity> findById(String id);

}
