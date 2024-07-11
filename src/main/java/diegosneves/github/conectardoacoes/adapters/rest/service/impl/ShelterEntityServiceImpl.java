package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import diegosneves.github.conectardoacoes.adapters.rest.dto.UserEntityDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.ShelterEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.AddressEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.ShelterRepository;
import diegosneves.github.conectardoacoes.adapters.rest.request.ShelterCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.ShelterCreatedResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.AddressEntityService;
import diegosneves.github.conectardoacoes.adapters.rest.service.ShelterEntityService;
import diegosneves.github.conectardoacoes.adapters.rest.service.UserEntityService;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.ShelterContract;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.factory.ShelterFactory;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Esta classe é responsável pela implementação dos métodos necessários para gerenciar abrigos no sistema.
 *
 * @author diegoneves
 * @see ShelterEntityService
 * @since 1.0.0
 */
@Service
@Slf4j
public class ShelterEntityServiceImpl implements ShelterEntityService {

    public static final String SHELTER_CREATION_ERROR_MESSAGE = "Erro na criação do Abrigo. Confirme se todos os campos do Abrigo estão corretos e tente novamente.";
    public static final String USER_RESPONSIBLE_EMAIL_NOT_FOUND_ERROR = "Ops! Não conseguimos encontrar o e-mail do usuário responsável. Por gentileza, tente novamente.";
    public static final String REQUEST_VALIDATION_ERROR_MESSAGE = "Por favor, forneça uma requisição de criação de Abrigo preenchida corretamente.";
    public static final String RESPONSIBLE_USER_PROFILE_INVALID = "O usuário deve possuir o perfil de responsável.";
    public static final String RESPONSIBLE_USER_ALREADY_IN_USE = "Este usuário já possui responsabilidade sobre outro abrigo.";

    public static final String SHELTER_CREATION_SUCCESS_LOG = "Novo abrigo criado com sucesso. Detalhes: ID do Abrigo: {} - Email do Usuário Responsável: {}";
    public static final String SHELTER_CREATION_FAILURE_LOG = "Falha ao instanciar e retornar um Abrigo. Causa: {}";
    public static final String RESPONSIBLE_USER_VERIFICATION_ERROR_LOG = "Falha na verificação do usuário responsável. A causa do erro é: {}";
    public static final String USER_NOT_FOUND_ERROR_LOG = "Não foi possível localizar o usuário com o email: {}. Motivo: {}";
    public static final String SHELTER_REGISTRATION_SUCCESS_LOG = "O Abrigo foi registrado e armazenado com sucesso. ID do Abrigo: {} - Email do usuário responsável {}";
    public static final String SHELTER_DATA_MAPPING_SAVING_FAILED_LOG = "Falha ao mapear e salvar os dados do Abrigo: {}";


    private final ShelterRepository repository;
    private final AddressEntityService addressService;
    private final UserEntityService userEntityService;

    @Autowired
    public ShelterEntityServiceImpl(ShelterRepository repository, AddressEntityService addressService, UserEntityService userEntityService) {
        this.repository = repository;
        this.addressService = addressService;
        this.userEntityService = userEntityService;
    }

    /**
     * Este método é responsável por criar um novo abrigo no sistema.
     * Ele recebe um objeto {@link ShelterCreationRequest}, que contém as informações necessárias para criar o abrigo,
     * como o nome do abrigo, o endereço e o e-mail do usuário responsável por criar o abrigo.
     * <p>
     * Internamente, este método realiza as seguintes operações:
     * <ol>
     *     <li>Cria uma nova instância de {@link ShelterContract} através do método <code>createAndReturnShelterInstance</code>,
     *     utilizando as informações do objeto {@link ShelterCreationRequest} fornecido.</li>
     *     <li>Mapeia o objeto {@link ShelterContract} criado para um objeto {@link ShelterEntity} e o salva no repositório
     *     do sistema utilizando o método {@code mapShelterAndSaveToRepository}.</li>
     *     <li>Constrói uma resposta para a criação do abrigo utilizando o objeto {@link ShelterEntity} salvo e o retorna.</li>
     * </ol>
     *
     * @param request um objeto {@link ShelterCreationRequest} contendo as informações necessárias para criar um novo abrigo.
     * @return um objeto {@link ShelterCreatedResponse} contendo as informações do abrigo criado.
     * @throws ShelterEntityFailuresException se ocorrer algum erro durante a criação do abrigo. Isso pode acontecer
     *                                        se o usuário responsável não for encontrado, se houver um erro na criação do endereço ou
     *                                        se houver um erro na criação do abrigo em si.
     */
    @Override
    public ShelterCreatedResponse createShelter(ShelterCreationRequest request) {
        ValidationUtils.validateNotNullOrEmpty(request, REQUEST_VALIDATION_ERROR_MESSAGE, ShelterEntityFailuresException.class);
        ShelterContract shelterContract = this.createAndReturnShelterInstance(request);
        ShelterEntity shelterEntity = this.mapShelterAndSaveToRepository(shelterContract);
        return constructShelterCreatedResponse(shelterEntity);
    }

    /**
     * Este método privado é responsável por construir uma resposta para a criação de um abrigo.
     * Essa resposta é modelada pela classe {@link ShelterCreatedResponse}.
     *
     * <p>
     * O método aceita como parâmetro um objeto {@link ShelterEntity}, que representa um abrigo que foi armazenado no banco de dados.
     * Este objeto é usado para extrair informações pertinentes que serão incluídas na resposta.
     * </p>
     *
     * <p>
     * O método segue essas principais etapas:
     * </p>
     * <ol>
     *   <li> Inicia a construção de um objeto {@link ShelterCreatedResponse} através do método builder().</li>
     *   <li> Configura o ID da resposta para corresponder ao ID da {@link ShelterEntity} fornecida.</li>
     *   <li> Configura o nome do abrigo na resposta para corresponder ao nome do abrigo na {@link ShelterEntity} fornecida.</li>
     *   <li> Configura o endereço na resposta para corresponder ao endereço da {@link ShelterEntity} fornecida, convertendo a
     *       {@link AddressEntity} para {@link AddressDTO} com o auxílio do método {@code mapTo()} da classe {@link BuilderMapper}.</li>
     *   <li> Configura o usuário responsável na resposta para corresponder ao usuário responsável na {@link ShelterEntity} fornecida,
     *       convertendo a {@link UserEntity} para {@link UserEntityDTO} com o auxílio do método {@code mapTo()} da classe {@link BuilderMapper}.</li>
     *   <li> Finaliza a construção do objeto {@link ShelterCreatedResponse} e o retorna.</li>
     * </ol>
     *
     * @param shelterEntity um objeto {@link ShelterEntity} que representa um abrigo armazenado no banco de dados.
     * @return retorna um objeto {@link ShelterCreatedResponse} que contém as informações do abrigo recém-criado.
     */
    private static ShelterCreatedResponse constructShelterCreatedResponse(ShelterEntity shelterEntity) {
        return ShelterCreatedResponse.builder()
                .id(shelterEntity.getId())
                .shelterName(shelterEntity.getShelterName())
                .address(BuilderMapper.mapTo(AddressDTO.class, shelterEntity.getAddress()))
                .responsibleUser(BuilderMapper.mapTo(UserEntityDTO.class, shelterEntity.getResponsibleUser()))
                .build();
    }

    /**
     * Cria uma nova instância de {@link Shelter} e a retorna.
     * <p>
     * Este método é responsável por criar uma nova instância de {@link Shelter} com base nas informações fornecidas no
     * objeto {@link ShelterCreationRequest}. A nova instância de abrigo é criada usando o método {@code create} do
     * {@link ShelterFactory}.
     * </p>
     * <p>
     * Antes de criar o abrigo, o método realiza as seguintes ações:
     * <ul>
     *     <li>Encontra o {@link UserContract} responsável pelo abrigo através do e-mail fornecido no
     *     {@link ShelterCreationRequest}, usando o método {@code findUserByResponsibleEmail}.</li>
     *     <li>Cria e salva um {@link Address} com base nas informações de endereço fornecidas no
     *     {@link ShelterCreationRequest}, usando o método {@code createAndSaveAddressFromDto}.</li>
     * </ul>
     * </p>
     *
     * @param request um objeto {@link ShelterCreationRequest} contendo as informações para a criação do abrigo.
     * @return newShelter a nova instância de {@link Shelter} criada.
     * @throws ShelterEntityFailuresException se ocorrer algum erro durante a criação da entidade Shelter. Isto pode ser devido a
     *                                        um erro ao encontrar o usuário responsável, um erro ao salvar o endereço, ou um
     *                                        erro na própria criação do Shelter.
     */
    private Shelter createAndReturnShelterInstance(ShelterCreationRequest request) throws ShelterEntityFailuresException {
        UserContract userContract = this.validateResponsibleUSer(request.getResponsibleUserEmail());
        Shelter newShelter;
        try {
            Address address = this.addressService.createAndSaveAddressFromDto(request.getAddress());
            newShelter = ShelterFactory.create(request.getShelterName(), address, userContract);
            log.info(SHELTER_CREATION_SUCCESS_LOG, newShelter.getId(), newShelter.getUser().getEmail());
        } catch (RuntimeException e) {
            log.error(SHELTER_CREATION_FAILURE_LOG, e.getMessage(), e);
            throw new ShelterEntityFailuresException(SHELTER_CREATION_ERROR_MESSAGE, e);
        }
        return newShelter;
    }

    /**
     * Valida um usuário responsável por meio do seu e-mail.
     * Esse método busca um {@link UserContract} com base no e-mail fornecido, verifica se seu perfil de usuário é {@code DONOR} e também verifica se este usuário já é responsável por algum abrigo.
     *
     * @param responsibleUserEmail um {@link String} que representa o e-mail do usuário responsável. Este parâmetro é usado para localizar o {@link UserContract} apropriado.
     * @return {@link UserContract} que corresponde ao e-mail fornecido, se todas as validações passarem.
     * @throws ShelterEntityFailuresException se o perfil do usuário responsável for {@code DONOR} ou se um {@link UserContract} com o e-mail especificado já estiver em uso como um responsável por abrigo.
     * @implNote Este método utiliza o seguinte fluxo de lógica:
     * <ol>
     *     <li>Localiza um {@link UserContract} usando {@link #findUserByResponsibleEmail(String)}.</li>
     *     <li>Verifica se este {@link UserContract} é um {@code DONOR} usando {@code UserProfile.DONOR.equals(Object)}.</li>
     *     <li>Verifica se este {@link UserContract} já existe como um usuário responsável por um abrigo usando {@link ShelterRepository#findShelterEntitiesByResponsibleUser_Email(String)}.</li>
     *     <li>Lança uma {@link ShelterEntityFailuresException} se qualquer uma das condições acima for verdadeira.</li>
     * </ol>
     */
    private UserContract validateResponsibleUSer(String responsibleUserEmail) throws ShelterEntityFailuresException {
        UserContract responsibleUser = this.findUserByResponsibleEmail(responsibleUserEmail);
        Optional<ShelterEntity> shelterOptional = this.repository.findShelterEntitiesByResponsibleUser_Email(responsibleUserEmail);
        throwShelterEntityFailuresExceptionIfNecessary(UserProfile.DONOR.equals(responsibleUser.getUserProfile()), RESPONSIBLE_USER_PROFILE_INVALID);
        throwShelterEntityFailuresExceptionIfNecessary(shelterOptional.isPresent(), RESPONSIBLE_USER_ALREADY_IN_USE);
        return responsibleUser;
    }

    /**
     * Este método verifica uma condição e lança uma exceção personalizada com uma mensagem de erro passada quando
     * a condição é atendida (ou seja, quando o parâmetro {@code needToThrowAnException} for verdadeiro).
     *
     * @param needToThrowAnException a condição Booleana segundo a qual a exceção deve ser lançada.
     *                               Se for verdadeiro, a {@link ShelterEntityFailuresException} será lançada.
     * @param errorMessage           a String que representa a mensagem detalhada da exceção. Esta mensagem é utilizada
     *                               quando a exceção é lançada.
     * @throws ShelterEntityFailuresException a exceção customizada que será lançada quando {@code needToThrowAnException} for verdadeiro.
     */
    private static void throwShelterEntityFailuresExceptionIfNecessary(Boolean needToThrowAnException, String errorMessage) throws ShelterEntityFailuresException {
        if (Boolean.TRUE.equals(needToThrowAnException)) {
            log.error(RESPONSIBLE_USER_VERIFICATION_ERROR_LOG, errorMessage);
            throw new ShelterEntityFailuresException(errorMessage);
        }
    }

    /**
     * Este é um método privado na classe {@link ShelterEntityServiceImpl} que é responsável por procurar um usuário por meio do seu e-mail.
     * <p>
     * O método é designado para encontrar um {@link UserContract} com base no e-mail do usuário responsável fornecido como um
     * parâmetro string.
     *
     * <p>
     * O método se encarrega de chamar a função {@code searchUserByEmail} da entidade de serviço do usuário. Se for encontrada alguma
     * exceção {@link UserEntityFailuresException} durante a execução da função {@code searchUserByEmail}, este método irá pegá-la e lançar
     * uma nova exceção do tipo {@link ShelterEntityFailuresException} com uma mensagem {@code USER_NOT_FOUND}, acompanhada da exceção original.
     * </p>
     *
     * @param responsibleUserEmail é uma string que representa o e-mail do usuário responsável pelo abrigo que estamos
     *                             procurando. Este parâmetro é utilizado para executar a pesquisa do usuário na entidade de serviço do usuário.
     * @return retorna um objeto {@link UserContract} que representa o usuário encontrato. Este objeto contém todos os detalhes
     * do usuário que foi buscado por meio de seu e-mail.
     * @throws ShelterEntityFailuresException é uma exceção que é lançada se houver algum problema ao procurar o usuário
     *                                        na entidade de serviço do usuário. Esta exceção é acompanhada com a mensagem {@code USER_NOT_FOUND} que descreve o motivo do
     *                                        lançamento da exceção.
     */
    private UserContract findUserByResponsibleEmail(String responsibleUserEmail) throws ShelterEntityFailuresException {
        UserContract foundUser;
        try {
            foundUser = this.userEntityService.searchUserByEmail(responsibleUserEmail);
        } catch (UserEntityFailuresException e) {
            log.error(USER_NOT_FOUND_ERROR_LOG, responsibleUserEmail, e.getMessage(), e);
            throw new ShelterEntityFailuresException(USER_RESPONSIBLE_EMAIL_NOT_FOUND_ERROR, e);
        }
        return foundUser;
    }

    /**
     * Este método é responsável por mapear um contrato de abrigo para uma entidade de abrigo e salvá-la no repositório.
     * <p>
     * O método primeiro tenta persistir o contrato de abrigo no repositório.
     * Se a operação for bem-sucedida, ele mapeia o contrato de abrigo salvo para uma nova entidade de abrigo usando um mapeador.
     * Se ocorrer uma {@link RuntimeException} durante qualquer uma dessas operações, ela é capturada e tratada lançando uma {@link ShelterEntityFailuresException}.
     *
     * @param shelterContract é o contrato de abrigo que será mapeado para uma entidade de abrigo e salvo.
     * @return Retorna a nova entidade de abrigo que foi mapeada e salva com sucesso.
     * @throws ShelterEntityFailuresException essa exceção é lançada quando ocorre um erro durante a criação da entidade de abrigo.
     *                                        A causa original da falha é encapsulada dentro desta exceção para permitir uma depuração mais eficiente.
     */
    private ShelterEntity mapShelterAndSaveToRepository(ShelterContract shelterContract) {
        ShelterEntity newShelterEntity;
        try {
            ShelterContract savedContract = this.repository.persist(shelterContract);
            newShelterEntity = BuilderMapper.mapTo(new ShelterEntityMapper(), savedContract);
            log.info(SHELTER_REGISTRATION_SUCCESS_LOG, newShelterEntity.getId(), newShelterEntity.getResponsibleUser().getEmail());
        } catch (RuntimeException e) {
            log.error(SHELTER_DATA_MAPPING_SAVING_FAILED_LOG, e.getMessage(), e);
            throw new ShelterEntityFailuresException(SHELTER_CREATION_ERROR_MESSAGE, e);
        }
        return newShelterEntity;
    }

}
