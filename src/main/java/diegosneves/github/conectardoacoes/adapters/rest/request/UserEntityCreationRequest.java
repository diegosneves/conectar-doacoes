package diegosneves.github.conectardoacoes.adapters.rest.request;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta classe representa um objeto de solicitação para a criação de uma nova entidade de usuário.
 * Ela é usada principalmente para coletar detalhes sobre o novo usuário para criar um registro correspondente no banco de dados.
 * <p>
 * Os detalhes a serem coletados incluem:
 * <ul>
 *     <li><strong>userName</strong>: o nome de usuário escolhido pelo novo usuário.</li>
 *     <li><strong>email</strong>: o endereço de e-mail do novo usuário.</li>
 *     <li><strong>userProfile</strong>: o tipo de perfil do usuário que classifica o novo usuário em uma de várias categorias possíveis (por exemplo, administrador, usuário regular, etc.).</li>
 *     <li><strong>userPassword</strong>: a senha escolhida pelo novo usuário.</li>
 * </ul>
 * <p>
 * Os atributos da classe estão sujeitos a alterações conforme o processamento prossegue.
 * <p>
 * Atenção: Esta classe faz uso das anotações Lombok para reduzir a verbosidade do código Java.
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
public class UserEntityCreationRequest {

    private String userName;
    private String email;
    private UserProfileType userProfile;
    private String userPassword;

}
