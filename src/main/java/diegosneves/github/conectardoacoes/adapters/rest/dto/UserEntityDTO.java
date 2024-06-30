package diegosneves.github.conectardoacoes.adapters.rest.dto;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Classe modelo {@code DTO} para um usuário. Contém informações básicas sobre um usuário,
 * incluindo o {@code id} do usuário, o {@code nome do usuário}, o {@code e-mail} e o
 * {@code tipo de perfil do usuário}.
 * <p/>
 * Os tipos de perfil de usuário são definidos na enumeração {@link UserProfileType}. Os tipos atuais de perfil
 * suportados são {@code 'Doador'} e {@code 'Beneficiário'}.
 * <br/>
 * Por exemplo, para criar uma instância de UserEntityDTO, você pode usar:
 * <pre>
 *     {@code
 *     UserEntityDTO user = UserEntityDTO.builder()
 *         .id("3f2f8ab2-e9ee-41fa-af8a-b2e9ee91fac7")
 *         .userName("John Doe")
 *         .email("john.doe@example.com")
 *         .userProfile(UserProfileType.DONOR) // pode ser DONOR ou BENEFICIARY
 *         .build();
 * }
 * </pre>
 *
 * @author diegoneves
 * @since 1.0.0
 * @see UserProfileType
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntityDTO {

    private String id;
    private String userName;
    private String email;
    private UserProfileType userProfile;

}
