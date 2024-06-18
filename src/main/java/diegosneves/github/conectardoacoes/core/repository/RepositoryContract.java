package diegosneves.github.conectardoacoes.core.repository;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Esta é uma interface de contrato de repositório genérica que define operações CRUD básicas.
 * A interface do repositório é genérica e pode ser usada com qualquer tipo de entidade.
 *
 * <p>
 * Esta interface define as seguintes operações:
 * - Encontrar uma entidade pelo seu identificador único ({@code String})
 * - Encontrar todas as instâncias de uma determinada entidade
 * - Salvar uma instância de uma entidade
 * - Deletar uma entidade pelo seu identificador único ({@code String})
 *
 * @param <T> o tipo de entidade com a qual essa interface de repositório trabalha.
 * @author diegoneves
 * @since 1.0.0
 */
public interface RepositoryContract<T> {

    /**
     * Encontra uma entidade pelo seu identificador.
     *
     * @param id o identificador único da entidade que deve ser procurada.
     * @return a entidade encontrada ou {@code null} se nenhuma entidade com o identificador especificado pôde ser encontrada.
     */
    T findById(String id);

    /**
     * Encontra todas as entidades de um determinado tipo.
     *
     * @return uma lista contendo todas as entidades ou uma lista vazia se não houver entidades.
     */
    List<T> findAll();

    /**
     * Salva uma entidade.
     *
     * @param entity a entidade que deve ser salva.
     * @return a entidade salva.
     * @throws IllegalArgumentException se a entidade passada como parâmetro for {@code null}.
     */
    T save(T entity);

    /**
     * Deleta uma entidade pelo seu identificador.
     *
     * @param id o identificador único da entidade que deve ser deletada.
     * @throws NoSuchElementException se nenhuma entidade com o identificador especificado pôde ser encontrada.
     */
    void deleteById(String id);

}
