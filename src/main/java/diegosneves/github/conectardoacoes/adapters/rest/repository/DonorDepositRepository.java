package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.model.DonorDeposit;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Controlador de acesso a dados para a entidade {@link DonorDeposit}.
 * <p>
 * Esta interface é responsável por fornecer operações CRUD básicas e específicas
 * para a entidade {@link DonorDeposit}, que representa um depósito de doações feito por um
 * doador no sistema.
 * <p>
 * A interface estende {@link CrudRepository}, que é fornecida pelo Spring Data JPA,
 * para herdar funcionalidades de manipulação da entidade {@link DonorDeposit} sem a necessidade
 * de implementação manual.
 * <p>
 * A anotação {@link Repository} indica que esta interface é um componente de repositório do Spring,
 * permitindo a descoberta automática de componentes e a injeção de dependência em outros componentes
 * de serviços do Spring.
 *
 * @author diegoneves
 * @see CrudRepository
 * @since 1.3.0
 */
@Repository
public interface DonorDepositRepository extends CrudRepository<DonorDeposit, String> {

    /**
     * Busca um depósito de doador associado a um usuário pelo endereço de e-mail do usuário.
     * <p>
     * Este método é usado para encontrar uma entidade {@link DonorDeposit} cuja entidade {@link UserEntity}
     * associada tenha o e-mail especificado. Utiliza a funcionalidade de consulta derivada do Spring Data JPA.
     *
     * @param email O endereço de e-mail do usuário associado ao depósito do doador.
     * @return Um {@link Optional} contendo a entidade {@link DonorDeposit} se encontrada,
     * ou um {@link Optional#empty()} se nenhuma entidade for encontrada para o e-mail fornecido.
     * @throws IllegalArgumentException se o e-mail fornecido for {@code null}.
     */
    Optional<DonorDeposit> findDonorDepositByUser_Email(String email);

}
