package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.MapperStrategy;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.ShelterEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.ShelterMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.ShelterContract;
import diegosneves.github.conectardoacoes.core.domain.shelter.shared.repository.ShelterContractRepository;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Este repositório é responsável pela persistência de dados relacionados a abrigos ({@link Shelter}).
 * <p>
 * Faz uso de interfaces do Spring Data JPA para implementação do CRUD (Criação, Leitura, Atualização e Deleção) padrão, além de
 * herdar de um contrato específico do Shelter para implementar suas próprias operações básicas (Encontrar por ID, Buscar todas,
 * Persistir e Deletar por ID).
 * <p>
 * Mecanismos de mapeamento são usados para desacoplar a conversão entre entidades JPA e objetos de domínio.
 *
 * @author diegoneves
 * @see ShelterContractRepository
 * @see CrudRepository
 * @since 1.0.0
 */
@Repository
public interface ShelterRepository extends ShelterContractRepository, CrudRepository<ShelterEntity, String> {


    Integer INVALID_ID_MESSAGE = 19;
    Integer SHELTER_ERROR_MESSAGE = 21;
    Integer INVALID_UUID_FORMAT_MESSAGE = 6;

    /**
     * Retorna uma nova instância do {@link MapperStrategy} para mapear uma entidade {@link ShelterEntity} para a classe de domínio {@link Shelter}.
     * O {@link MapperStrategy} é uma estratégia de design que desacopla a conversão de objeto para objeto para uma complexidade reduzida e manutenção mais fácil.
     *
     * @return uma nova instância de {@link ShelterMapper} que implementa a interface {@link MapperStrategy}
     */
    private ShelterMapper getShelterMapper() {
        return new ShelterMapper();
    }

    /**
     * Valida o valor de ID (identificador) fornecido. A validação ocorre em duas etapas:
     * 1. Verifica se o ID fornecido não é nulo e nem vazio, lançando uma exceção do tipo {@link ShelterEntityFailuresException}
     * com uma mensagem de erro correspondente se a validação falhar.
     * 2. Verifica se o ID fornecido é um UUID válido, lançando uma exceção do tipo {@link ShelterEntityFailuresException}
     * com a causa original da falha se a validação falhar.
     * Note que este método é privado e deve ser usado apenas dentro da interface {@link ShelterRepository}.
     *
     * @param id O ID que será validado. Deve ser uma string não nula e não vazia representando um UUID válido.
     * @throws ShelterEntityFailuresException se o ID fornecido for nulo, vazio ou não for um UUID válido.
     * @see ValidationUtils#validateNotNullOrEmpty(Object, String, Class)
     * @see UuidUtils#isValidUUID(String)
     */
    private void validateId(String id) throws ShelterEntityFailuresException {
        ValidationUtils.validateNotNullOrEmpty(id, INVALID_ID_MESSAGE, ShelterEntityFailuresException.class);
        try {
            UuidUtils.isValidUUID(id);
        } catch (UuidUtilsException e) {
            throw new ShelterEntityFailuresException(INVALID_UUID_FORMAT_MESSAGE, id, e);
        }
    }

    /**
     * Recebe uma lista de entidades {@link ShelterEntity}, realiza o mapeamento das instâncias de {@link ShelterEntity} para {@link Shelter} e retorna uma lista de Shelters.
     * Este método é particularmente útil na conversão de entidades do banco de dados para objetos de domínio, para uso em outras partes da aplicação.
     *
     * @param entities Uma lista de instâncias de {@link ShelterEntity} que se pretende mapear para instâncias de {@link Shelter}
     * @return Uma lista de instâncias de {@link ShelterContract} obtidas a partir do mapeamento das entidades recebidas. Se a lista de entrada for vazia, o método irá retornar uma lista vazia.
     * @see MapperStrategy
     * @see ShelterEntity
     * @see Shelter
     */
    private List<ShelterContract> mapEntityList(List<ShelterEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream().map(this.getShelterMapper()::mapFrom).toList();
    }

    /**
     * Este método é usado para encontrar e retornar uma entidade {@link ShelterEntity} pelo ID.
     * <p>
     * Primeiro, é executada a função findById do Repositório do Spring, que retorna um {@link Optional} de {@link ShelterEntity}.
     * Então verifica-se se o {@link Optional} está vazio. Se estiver vazio, significa que nenhuma entidade foi encontrada com o ID fornecido, e o método retorna {@code null}.
     * Se o {@link Optional} não estiver vazio, o método tenta mapear a {@link ShelterEntity} encontrada para uma instância de {@link ShelterContract}.
     * <p>
     * Uma {@link ShelterCreationFailureException} pode ser lançada durante o mapeamento. Se isso acontecer, o método lança uma nova {@link ShelterEntityFailuresException} com uma mensagem de erro adequada.
     *
     * @param id A String que representa o ID único da entidade {@link ShelterEntity} a ser encontrada. Deve ser um valor não nulo e não vazio.
     * @return Uma instância de {@link ShelterContract} que representa a entidade {@link Shelter} mapeada da {@link ShelterEntity} encontrada. Se nenhuma entidade for encontrada com o id fornecido, o método retornará null.
     * @throws ShelterEntityFailuresException se ocorrer uma exceção {@link ShelterCreationFailureException} durante o mapeamento da entidade {@link ShelterEntity} para a instância de {@link ShelterContract}.
     */
    @Override
    default ShelterContract findEntityById(String id) {
        ShelterEntity entityToMap = this.getShelterEntityById(id);
        if (entityToMap == null) return null;
        return this.getShelterMapper().mapFrom(entityToMap);
    }

    /**
     * Este método privado tem como objetivo recuperar um objeto {@link ShelterEntity} com base no id fornecido.
     * Ele faz isso primeiro validando o id fornecido usando o método {@code validateId(String id)}.
     * A validação garante que o id não seja nulo nem vazio e que seja um UUID válido.
     * Caso contrário, uma exceção {@link ShelterEntityFailuresException} é lançada.
     * <p>
     * Após a validação bem-sucedida do id, o método {@code findById(String id)} é chamado
     * para obter o {@link Optional} de {@link ShelterEntity} correspondente ao id fornecido.
     * <p>
     * Se o {@link Optional} estiver presente, o valor é retornado. Caso contrário, {@code null} é retornado.
     *
     * @param id A string que representa o UUID da entidade {@link ShelterEntity} a ser recuperada.
     *           Deve ser um UUID válido. Caso contrário, uma exceção {@link ShelterEntityFailuresException} será lançada.
     * @return um objeto {@link ShelterEntity} correspondente ao id fornecido ou {@code null} se nenhum {@link ShelterEntity} corresponder ao id fornecido.
     * @throws ShelterEntityFailuresException Se o id fornecido for nulo, vazio ou não for um UUID válido.
     * @see #validateId(String)
     */
    private ShelterEntity getShelterEntityById(String id) {
        this.validateId(id);
        Optional<ShelterEntity> entity = this.findById(id);
        return entity.orElse(null);
    }

    /**
     * Recupera todos os abrigos do banco de dados.
     * <p>
     * Esse método recupera todas as entidades de {@link ShelterEntity} presentes no banco de dados, usando o método {@code findAll} da interface {@link CrudRepository}.
     * Em seguida, realiza o mapeamento dessas entidades para o contrato de abrigo {@link ShelterContract}, usando a função {@code mapEntityList}.
     * Portanto, este método é responsável por converter as entidades de abrigo representadas no banco de dados em contratos de abrigo que podem ser usados na lógica de negócios da aplicação.
     * <p>
     *
     * @return Uma lista de contratos de abrigo {@link ShelterContract}. Se não houver entidades de abrigo no banco de dados, retorna uma lista vazia.
     */
    @Override
    default List<ShelterContract> retrieveAll() {
        List<ShelterEntity> shelterEntityList = (List<ShelterEntity>) this.findAll();
        return this.mapEntityList(shelterEntityList);
    }

    /**
     * Persiste uma instância de {@link ShelterContract} na representação do banco de dados.
     * <p>
     * Primeiro, o método mapeia a instância de {@link ShelterContract} fornecida para seu equivalente {@link ShelterEntity}.
     * A conversão é feita usando uma instância de {@link ShelterEntityMapper}.
     * <p>
     * O {@link ShelterEntity} resultante é então passado para o método {@code save} da interface {@link CrudRepository}.
     * Este método salva a entidade no banco de dados e retorna a entidade persistida.
     * <p>
     * Finalmente, a {@link ShelterEntity} persistida é mapeada de volta para um {@link ShelterContract} usando uma instância de {@link ShelterMapper}.
     * Este {@link ShelterContract} mapeado é então retornado.
     * <p>
     * Este método é uma implementação direta da operação padrão de persistência fornecida pela interface {@link CrudRepository}.
     *
     * @param entity A instância de {@link ShelterContract} que será persistida. Deve ser um valor não nulo.
     * @return uma instância de {@link ShelterContract} que representa a entidade persistida.
     * @throws ShelterEntityFailuresException se a entidade fornecida for nula.
     * @see CrudRepository
     */
    @Override
    default ShelterContract persist(ShelterContract entity) {
        ValidationUtils.validateNotNullOrEmpty(entity, SHELTER_ERROR_MESSAGE, ShelterEntityFailuresException.class);
        ShelterEntity shelterEntity = BuilderMapper.mapTo(new ShelterEntityMapper(), entity);
        return BuilderMapper.mapTo(this.getShelterMapper(), this.save(shelterEntity));
    }

    /**
     * Este método é usado para excluir uma entidade {@link ShelterEntity} pelo ID.
     * <p>
     * Primeiramente, o método tenta recuperar a entidade {@link ShelterEntity} correspondente ao ID fornecido por meio
     * do método {@link #getShelterEntityById(String) getShelterEntityById(String id)}. Se o método não conseguir encontrar qualquer entidade {@link ShelterEntity}
     * correspondente ao ID fornecido, o método termina e não realiza nenhuma ação.
     * <p>
     * Se o método {@link #getShelterEntityById(String) getShelterEntityById(String id)} retornar uma entidade válida, essa entidade é passada para o método
     * {@link #delete(Object) delete(ShelterEntity targetEntity)}, que se encarrega de excluir a entidade do banco de dados.
     * <p>
     * Este método é uma extensão direta do método {@code delete(ID id)} da interface {@link org.springframework.data.repository.CrudRepository}.
     *
     * @param id A String que representa o ID único da entidade {@link ShelterEntity} a ser excluída.
     *           Deve ser um valor não nulo e não vazio.
     * @see ShelterEntity
     * @see #getShelterEntityById(String)
     */
    @Override
    default void deleteEntityById(String id) {
        ShelterEntity targetEntity = this.getShelterEntityById(id);
        if (targetEntity == null) {
            return;
        }
        this.delete(targetEntity);
    }

    /**
     * Este método é usado para buscar uma entidade {@link Shelter} pelo e-mail do usuário responsável.
     *
     * @param responsibleUserEmail O e-mail do usuário responsável. Este é um parâmetro de entrada usado para filtrar a busca pela entidade Shelter. Deve ser um e-mail válido em formato de string.
     * @return Um objeto {@link Optional} que inclui a instância de {@link ShelterEntity} se o usuário responsável com o e-mail fornecido for encontrado.
     * Se não houver uma correspondência para o e-mail fornecido, um {@link Optional} vazio será retornado. Note que {@link Optional} é usado para evitar erros de {@link NullPointerException}.
     * @throws IllegalArgumentException se o parâmetro de e-mail fornecido for nulo, vazio ou não em um formato de e-mail válido.
     */
    Optional<ShelterEntity> findShelterEntitiesByResponsibleUser_Email(String responsibleUserEmail);

}
