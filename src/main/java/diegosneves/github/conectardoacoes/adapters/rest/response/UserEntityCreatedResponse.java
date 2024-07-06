package diegosneves.github.conectardoacoes.adapters.rest.response;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta classe representa a resposta após a criação de uma entidade de usuário no sistema.
 * Ela contém informações relevantes sobre o usuário que foi criado, servindo como um objeto de transferência de dados (DTO).
 * <p>
 * Atributos:
 * <ul>
 *    <li><strong>id</strong>: Um identificador único para o usuário criado</li>
 *    <li><strong>userName</strong>: O nome de usuário escolhido pelo usuário</li>
 *    <li><strong>email</strong>: O endereço de e-mail fornecido pelo usuário</li>
 *    <li><strong>userProfile</strong>: O tipo de perfil associado a este usuário (por exemplo, administrador, usuário, etc.)</li>
 * </ul>
 * <p>
 * Essa classe é construída usando o padrão de design Builder para facilitar a criação de instâncias e é adotada
 * a biblioteca Lombok para evitar a verbosidade no código.
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
public class UserEntityCreatedResponse {

    private String id;
    private String userName;
    private String email;
    private UserProfileType userProfile;

}
