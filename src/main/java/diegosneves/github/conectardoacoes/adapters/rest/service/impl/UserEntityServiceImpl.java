package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.UserMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.UserRepository;
import diegosneves.github.conectardoacoes.adapters.rest.service.UserEntityService;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @since 1.0.0
 * @see UserEntityService
 * @see UserEntityFailuresException
 * @see Service
 */
@Service
public class UserEntityServiceImpl implements UserEntityService {

    public static final String INVALID_EMAIL_ERROR_MESSAGE = "Não foi informado nenhum email. Por favor, forneça um email válido.";
    public static final String EMAIL_NOT_FOUND_ERROR_MESSAGE = "Não foi possivel encontrar um usuário com o email [ %s ].";


    private final UserRepository userRepository;

    @Autowired
    public UserEntityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Este método é usado para obter uma entidade de usuário pelo seu email.
     * Primeiramente, o método verifica se o valor do parâmetro de email não é nulo ou vazio.
     * Se o valor do email for nulo ou vazio, uma exceção {@link UserEntityFailuresException} é lançada ao usuário
     * com uma mensagem de erro relevante.
     * <p>
     * Se o email for válido, o método tentará encontrar uma entidade de usuário que corresponda ao email
     * usando a interface {@link UserRepository}.
     * Se não for encontrada uma entidade de usuário para o email fornecido,
     * o método lançará uma exceção {@link UserEntityFailuresException} com uma mensagem de erro apropriada.
     *<p></p>
     * @param email A string que representa o email do usuário que será procurado no repositório.
     * @return A entidade do usuário correspondente ao email fornecido.
     * @throws UserEntityFailuresException Se nenhuma entidade de usuário puder ser encontrada para o email fornecido
     * ou o valor do email for nulo ou vazio.
     */
    @Override
    public UserContract searchUserByEmail(String email) throws UserEntityFailuresException {
        ValidationUtils.validateNotNullOrEmpty(email, INVALID_EMAIL_ERROR_MESSAGE, UserEntityFailuresException.class);
        UserEntity foundUser = this.userRepository.findByEmail(email).orElseThrow(() -> new UserEntityFailuresException(String.format(EMAIL_NOT_FOUND_ERROR_MESSAGE, email)));
        return BuilderMapper.mapTo(new UserMapper(), foundUser);
    }

}
