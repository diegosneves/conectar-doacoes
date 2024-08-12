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
