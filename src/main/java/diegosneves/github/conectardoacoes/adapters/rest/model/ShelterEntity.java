package diegosneves.github.conectardoacoes.adapters.rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Objeto de entidade {@link ShelterEntity} que representa um objeto abrigo no banco de dados mapeado para a tabela "shelters".
 * <p>
 * Esta classe é uma entidade do JPA que representa a tabela "shelters".
 * <p>
 * Anotado com Lombok, o que significa que os construtores, getters e setters são gerados automaticamente.
 *
 * @author diegoneves
 * @since 1.0.0
 */
@Entity
@Table(name = "shelters")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShelterEntity {

    @Id
    private String id;
    private String shelterName;
    @OneToOne
    private AddressEntity address;
    @OneToOne
    private UserEntity responsibleUser;
    @OneToMany
    private List<DonationEntity> donations;

}
