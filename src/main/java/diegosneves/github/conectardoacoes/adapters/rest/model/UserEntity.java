package diegosneves.github.conectardoacoes.adapters.rest.model;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserEntity {

    @Id
    private String id;
    private String userName;
    private String email;
    private UserProfileType userProfile;
    private String userPassword;

}
