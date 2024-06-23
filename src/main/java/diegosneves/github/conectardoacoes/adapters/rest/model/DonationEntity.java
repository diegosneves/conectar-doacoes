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
 * Objeto de entidade {@link DonationEntity} que representa um objeto doação no banco de dados mapeado para a tabela "donations".
 * <p>
 * Esta classe é uma entidade do JPA que representa a tabela "donations".
 * <p>
 * Anotado com Lombok, o que significa que os construtores, getters e setters são gerados automaticamente.
 *
 * @author diegoneves
 * @since 1.0.0
 */
@Entity
@Table(name = "donations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DonationEntity {

    @Id
    private String id;
    private String description;
    private Integer amount;

}
