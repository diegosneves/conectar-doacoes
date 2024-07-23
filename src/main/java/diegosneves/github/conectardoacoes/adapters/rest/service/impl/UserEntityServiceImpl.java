package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.UserEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.UserMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.UserRepository;
import diegosneves.github.conectardoacoes.adapters.rest.request.UserEntityCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.UserEntityCreatedResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.UserEntityService;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.domain.user.factory.UserFactory;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * A classe {@link UserEntityServiceImpl} implementa a interface {@link UserEntityService} e
 * lida com a lógica de negócios das operações de usuário.
 * A classe é marcada como um componente do Spring com a anotação {@link Service},
 * o que permite ao Spring detectar e gerenciar as instâncias dessa classe.
 * <p>
 * Além disso, a classe lida com cenários de erros e exceções, lançando uma exceção personalizada
 * chamada {@link UserEntityFailuresException} quando um problema ocorre.
 *
 * @author diegoneves
 * @see UserEntityService
 * @see UserEntityFailuresException
 * @see Service
 * @since 1.0.0
 */
@Service
@Slf4j
public class UserEntityServiceImpl implements UserEntityService {

    public static final Integer INVALID_EMAIL_ERROR_MESSAGE = 29;
    public static final Integer EMAIL_NOT_FOUND_ERROR_MESSAGE = 8;
    public static final Integer EMAIL_ALREADY_IN_USE = 10;
    public static final Integer USER_CREATION_FAILURE_MESSAGE = 31;
    public static final Integer USER_PROFILE_VALIDATION_FAILURE = 33;
    public static final Integer MISSING_USER_ENTITY_REQUEST_ERROR_MESSAGE = 27;

    public static final String USER_CREATION_ERROR_LOG = "Ocorreu um erro ao tentar criar um usuário a partir da solicitação de criação UserEntityCreationRequest: {}";
    public static final String EMAIL_DUPLICATE_LOG = "O endereço de email {} já está associado a uma conta existente";
    public static final String USER_CREATION_SUCCESS_LOG = "Usuario criado com sucesso. Detalhes: ID: {} - Email: {}";


    private final UserRepository userRepository;

    @Autowired
    public UserEntityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserContract searchUserByEmail(String email) throws UserEntityFailuresException {
        ValidationUtils.validateNotNullOrEmpty(email, INVALID_EMAIL_ERROR_MESSAGE, UserEntityFailuresException.class);
        UserEntity foundUser = this.userRepository.findByEmail(email).orElseThrow(() -> new UserEntityFailuresException(EMAIL_NOT_FOUND_ERROR_MESSAGE, email));
        return BuilderMapper.mapTo(new UserMapper(), foundUser);
    }

    @Override
    public UserEntityCreatedResponse createUserEntity(UserEntityCreationRequest request) throws UserEntityFailuresException {
        ValidationUtils.validateNotNullOrEmpty(request, MISSING_USER_ENTITY_REQUEST_ERROR_MESSAGE, UserEntityFailuresException.class);
        this.checkIfEmailAlreadyInUse(request.getEmail());
        UserEntity newUser = createUserEntityFromCreationRequest(request);
        return BuilderMapper.mapTo(UserEntityCreatedResponse.class, this.userRepository.save(newUser));
    }

    /**
     * Método auxiliar usado para converter uma instância de {@link UserEntityCreationRequest} para uma instância de {@link UserEntity}.
     * <p>
     * Este método recebe uma solicitação de criação de entidade de usuário, gera um contrato de usuário correspondente através de
     * {@link UserEntityServiceImpl#createUserFromRequest(UserEntityCreationRequest)} e usa um {@link UserEntityMapper} para mapear
     * o contrato de usuário para uma entidade de usuário.
     *
     * @param request Uma instância de {@link UserEntityCreationRequest} representando a solicitação de criação de uma nova entidade de usuário.
     * @return userEntity Uma instância de {@link UserEntity} representando a entidade de usuário recém-criada.
     * @throws UserEntityFailuresException Se ocorrer um erro durante a criação do usuário ou mapeamento de contrato de usuário para entidade de usuário.
     *                                     A exceção encapsula e fornece mais detalhes sobre a natureza específica do erro.
     * @see UserEntityCreationRequest
     * @see UserEntity
     * @see UserEntityMapper
     */
    private static UserEntity createUserEntityFromCreationRequest(UserEntityCreationRequest request) {
        UserContract userContract = createUserFromRequest(request);
        return BuilderMapper.mapTo(new UserEntityMapper(), userContract);
    }

    /**
     * Método auxiliar para criar um {@link User} a partir de uma solicitação de criação de entidade de usuário {@link UserEntityCreationRequest}.
     * <p>
     * Este método é utilizado principalmente para transformar a solicitação de criação de entidade de usuário em uma instância concreta de {@link User}.
     * Ele lida com a validação da solicitação e a criação do usuário, lançando uma exceção {@link UserEntityFailuresException} caso algum problema ocorra.
     * A exceção lança detalhes específicos sobre a natureza do erro.
     * O perfil do usuário é validado e convertido para um tipo de enumeração {@link UserProfile} apropriado.
     * </p>
     * <h2> Exceções </h2>
     * <p>
     * O método lança a exceção {@link UserEntityFailuresException} nas seguintes situações:
     * <ul>
     * <li>Quando o campo userProfile da solicitação de criação de entidade de usuário está nulo.</li>
     * <li> Quando ocorrer uma exceção {@link RuntimeException} durante a criação do usuário no método {@link UserFactory#create}.</li>
     * </ul>
     * Nesses casos, uma {@link UserEntityFailuresException} será lançada com uma mensagem de erro adequada.
     * </p>
     *
     * @param request uma solicitação de criação de entidade de usuário formada por userName, email, userProfile e userPassword.
     * @return User uma instância de {@link User} representando o novo usuário criado.
     * @throws UserEntityFailuresException lançada quando a validação do userProfile falha ou é impossível criar o usuário devido a uma {@link RuntimeException}.
     * @see User
     * @see UserContract
     * @see UserEntityCreationRequest
     */
    private static UserContract createUserFromRequest(UserEntityCreationRequest request) {
        ValidationUtils.validateNotNullOrEmpty(request.getUserProfile(), USER_PROFILE_VALIDATION_FAILURE, UserEntityFailuresException.class);
        UserProfile userProfile = Enum.valueOf(UserProfile.class, request.getUserProfile().name());
        UserContract createdUser;
        try {
            createdUser = UserFactory.create(request.getUserName(), request.getEmail(), userProfile, request.getUserPassword());
            log.info(USER_CREATION_SUCCESS_LOG, createdUser.getId(), createdUser.getEmail());
        } catch (RuntimeException e) {
            log.error(USER_CREATION_ERROR_LOG, e.getMessage(), e);
            throw new UserEntityFailuresException(USER_CREATION_FAILURE_MESSAGE, e);
        }
        return createdUser;
    }

    /**
     * Este método recebe como parâmetro um endereço de email no formato String e verifica se este email já está
     * registrado no repositório de usuários. A verificação de registro é feita a partir do método
     * {@link UserRepository#findByEmail(String)}, que retorna um {@link Optional} de {@link UserEntity}.
     * <p>
     * Primeiro, o método utiliza a função {@link ValidationUtils#validateNotNullOrEmpty(Object, String, Class)}
     * para assegurar que o email fornecido não é nulo ou vazio.
     * <p>
     * Se a verificação retorna um {@link UserEntity}, isso significa que o endereço de email já existe na base de dados do
     * sistema, neste caso, uma exceção {@link UserEntityFailuresException} é lançada com uma mensagem predefinida
     * {@link #EMAIL_ALREADY_IN_USE}.
     * <p>
     * Já se a instância de {@link UserEntity} for vazia, ou seja, se o email não foi registrado anteriormente no sistema,
     * a operação prossegue normalmente sem lançar nenhuma exceção.
     *
     * @param email o endereço de email a ser verificado.
     * @throws UserEntityFailuresException Caso o email fornecido seja nulo ou vazio, ou, caso o email já esteja em uso por um usuário existente.
     */
    private void checkIfEmailAlreadyInUse(String email) {
        ValidationUtils.validateNotNullOrEmpty(email, INVALID_EMAIL_ERROR_MESSAGE, UserEntityFailuresException.class);
        Optional<UserEntity> existingUser = this.userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            log.error(EMAIL_DUPLICATE_LOG, email);
            throw new UserEntityFailuresException(EMAIL_ALREADY_IN_USE, email);
        }
    }
}
