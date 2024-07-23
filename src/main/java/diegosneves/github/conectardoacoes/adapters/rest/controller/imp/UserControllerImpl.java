package diegosneves.github.conectardoacoes.adapters.rest.controller.imp;

import diegosneves.github.conectardoacoes.adapters.rest.controller.UserController;
import diegosneves.github.conectardoacoes.adapters.rest.request.UserEntityCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.UserEntityCreatedResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Esta é a classe {@link UserControllerImpl} que implementa a interface {@link UserController}.
 * É anotada com {@link RestController}, o que significa que é um controlador de serviço web em que
 * cada método retorna um objeto de domínio (Entity) ao invés de uma view.
 * É responsável por lidar com todas as requisições HTTP que são enviadas para a URL {@code "/user"}.
 * <p>
 * Possui uma instância da classe de serviço {@link UserEntityService}, que é usada para implementar
 * a lógica de negócios relacionada aos usuários.
 *
 * @author diegoneves
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private final UserEntityService service;

    @Autowired
    public UserControllerImpl(UserEntityService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<UserEntityCreatedResponse> createUser(UserEntityCreationRequest request) {
        UserEntityCreatedResponse newUserResponse = this.service.createUserEntity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUserResponse);
    }

    @Override
    public ResponseEntity<UserEntityCreatedResponse> findUserByEmail(String email) {
        UserEntityCreatedResponse userByEmail = this.service.findUserByEmail(email);
        return ResponseEntity.ok(userByEmail);
    }
}
