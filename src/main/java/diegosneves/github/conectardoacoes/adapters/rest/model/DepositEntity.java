package diegosneves.github.conectardoacoes.adapters.rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa uma entidade de depósito no sistema.
 * <p>
 * Esta classe mapeia a tabela "deposits" no banco de dados e
 * contém as informações pertinentes de um depósito, como o
 * identificador, a descrição e o valor.
 *
 * @author diegoneves
 * @since 1.3.0
 */
@Entity
@Table(name = "deposits")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DepositEntity {

    @Id
    private String id;
    private String description;
    private Integer amount;

}
