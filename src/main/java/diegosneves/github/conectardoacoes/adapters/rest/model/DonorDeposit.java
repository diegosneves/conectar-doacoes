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
 * Classe que representa um depósito feito por um doador.
 * <p>
 * Esta classe mapeia a tabela "donor_deposits" no banco de dados e
 * contém informações sobre o doador e seus depósitos.
 * <p>
 * Atributos:
 * <ul>
 *  <li>id: Identificador único do depósito.</li>
 *  <li>user: Referência para a entidade UserEntity que representa o doador.</li>
 *  <li>deposits: Lista de depósitos associados ao doador.</li>
 * </ul>
 *
 * @author diegoneves
 * @since 1.3.0
 */
@Entity
@Table(name = "donor_deposits")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DonorDeposit {

    @Id
    private String id;
    @OneToOne
    private UserEntity user;
    @OneToMany
    private List<DepositEntity> deposits;

}
