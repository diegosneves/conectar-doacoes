package diegosneves.github.conectardoacoes.adapters.rest.controller;

import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.request.UserEntityCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.UserEntityCreatedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * A interface {@link UserController} age como um controlador REST referente a usuários dentro deste sistema.
 * Seu propósito é fornecer endpoints que lidam com todas as operações relativas aos usuários (como, por exemplo, criação de novo usuário), realizando a ligação entre as requisições HTTP dos clientes e a camada de serviço do sistema.
 *
 * <p>
 * Dentro desta interface, são definidos métodos para lidar com uma série de operações de usuário, como a criação de um usuário novo.
 * Cada método está atrelado a um endpoint específico e é responsável por manipular uma operação distinta.
 * Um exemplo é o método {@link #createUser(UserEntityCreationRequest)}, que está mapeado para o endpoint "create" e é responsável pela criação de um novo usuário no sistema.
 *
 * <p>
 * Como uma interface de controlador REST, processa-se input no formato JSON e devolve-se dados também no formato JSON.
 * A conversão entre este formato de dados e os objetos do domínio do sistema se dá através de classes de solicitação e resposta (como {@link UserEntityCreationRequest} e {@link UserEntityCreatedResponse}), que atuam como DTOs (Data Transfer Objects) que facilitam a serialização e deserialização de dados e validam a estrutura desses dados.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public interface UserController {

    /**
     * Este endpoint pertence à aplicação "Usuários".
     * Responsável por registrar novos usuários no sistema.
     * Aceita uma requisição POST com o objeto {@link UserEntityCreationRequest} no corpo que contém as informações do usuário a ser registrado.
     *
     * <p>
     * Após a criação bem-sucedida do usuário, retorna um objeto {@link ResponseEntity} que encapsula os detalhes da criação do usuário na forma de {@link UserEntityCreatedResponse}.
     * <p>
     * O objeto {@link UserEntityCreatedResponse} é um DTO que contém os detalhes relevantes sobre o usuário que foi criado.
     * Isso inclui o ID de usuário único gerado pelo sistema, o nome de usuário escolhido, o endereço de e-mail fornecido e o tipo de perfil associado a este usuário.
     *
     * <p>
     * Em qualquer caso de violação das validações de integridade do modelo da entidade, um erro HTTP apropriado é retornado junto com os detalhes do erro.
     *
     * @param request um objeto {@link UserEntityCreationRequest} encapsulando os detalhes do novo usuário a ser registrado.
     *                Este parâmetro deve estar presente no corpo da solicitação POST e é deserializado para o tipo {@link UserEntityCreationRequest}.
     * @return {@link ResponseEntity} que encapsula o {@link UserEntityCreatedResponse} se o usuário for criado com sucesso.
     * @see UserEntityCreatedResponse
     * @see UserEntityCreationRequest
     */
    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Criar Usuário",
            description = "Este endpoint é responsável por criar e registrar um novo Usuário no sistema utilizando os dados recebidos por meio de uma requisição POST",
            tags = "Usuários",
            parameters = {
                    @Parameter(
                            name = "Perfil do usuário",
                            description = "O campo 'userProfile' só permite dois valores possíveis: 'Doador' ou 'Beneficiário'.",
                            schema = @Schema(implementation = UserProfileType.class)
                    )
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário criado com sucesso!",
                    content = @Content(schema = @Schema(implementation = UserEntityCreatedResponse.class))
            )
    })
    ResponseEntity<UserEntityCreatedResponse> createUser(@RequestBody UserEntityCreationRequest request);

}
