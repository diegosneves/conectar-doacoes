package diegosneves.github.conectardoacoes.adapters.rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
