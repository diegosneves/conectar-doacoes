package diegosneves.github.conectardoacoes.adapters.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A classe {@link DepositDTO} é um Data Transfer Object (DTO) que fornece uma maneira simples de transportar dados entre processos.
 * Ele é responsável por representar a entidade "Doação" em operações onde apenas uma transferência de dados simples é necessária,
 * sem comportamentos adicionais.
 * <p>
 * <p>A classe {@link DepositDTO} inclui duas propriedades:</p>
 * <ol>
 *    <li><code>description</code> - Uma string que descreve a doação.</li>
 *    <li><code>amount</code> - Um inteiro que representa a quantidade da doação.</li>
 * </ol>
 * <p>
 * Ela contém os construtores {@code @AllArgsConstructor} e {@code @NoArgsConstructor}, que permitem a criação de instâncias com todos os atributos ou sem nenhum atributo, respectivamente.
 * Além disso, possui os métodos {@code @getter} e {@code @setter} para cada propriedade, permitindo a recuperação e modificação de cada propriedade, respectivamente.
 * A anotação {@code @Builder} implementa o padrão <b>Builder</b> para criar objetos da classe de maneira mais legível e segura.
 *
 * @author diegoneves
 * @since 1.3.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepositDTO {

    private String description;
    private Integer amount;

}
