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

/**
 * Representa um usuário no sistema. Cada usuário tem um identificador único, nome de usuário,
 * endereço de e-mail, perfil de usuário e senha.
 *
 * <p>Anotações usadas na classe:</p>
 * <ul>
 *     <li>{@code @Entity} - Anotação do JPA que denota que esta é uma classe de entidade.</li>
 *     <li>{@code @Table} - Anotação do JPA usada para especificar detalhes da tabela de banco de dados correspondente.</li>
 *     <li>{@code @AllArgsConstructor} - Anotação do Lombok para gerar um construtor com um parâmetro para cada campo.</li>
 *     <li>{@code @NoArgsConstructor} - Anotação do Lombok para gerar um construtor sem parâmetros.</li>
 *     <li>{@code @Builder} - Anotação do Lombok para suportar o padrão Builder de design para a construção de objetos.</li>
 *     <li>{@code @Getter} - Anotação do Lombok para gerar getters para todos os campos.</li>
 *     <li>{@code @Setter} - Anotação do Lombok para gerar setters para todos os campos.</li>
 * </ul>
 *
 * <p>Os atributos da classe incluem:</p>
 * <ul>
 *     <li>{@code userId} - O identificador único do usuário. Chave primária para a tabela do banco de dados.</li>
 *     <li>{@code userName} - O nome de usuário escolhido pelo usuário. Deve ser único.</li>
 *     <li>{@code email} - O endereço de email do usuário. Usado para comunicação e notificações.</li>
 *     <li>{@code userProfile} - O perfil do usuário que determina suas permissões e acessos.</li>
 *     <li>{@code userPassword} - A senha escolhida pelo usuário para acesso seguro ao sistema.</li>
 * </ul>
 */
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
