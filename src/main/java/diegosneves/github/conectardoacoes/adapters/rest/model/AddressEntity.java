package diegosneves.github.conectardoacoes.adapters.rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Objeto de entidade {@link AddressEntity} que representa um objeto endereço no banco de dados mapeado para a tabela "address".
 * <p>
 * Esta classe é uma entidade do JPA que representa a tabela "address".
 * <p>
 * Anotado com Lombok, o que significa que os construtores, getters e setters são gerados automaticamente.
 *
 * @author diegoneves
 * @since 1.0.0
 */
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String zip;

}
