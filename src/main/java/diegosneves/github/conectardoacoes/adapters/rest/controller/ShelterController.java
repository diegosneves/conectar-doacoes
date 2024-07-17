package diegosneves.github.conectardoacoes.adapters.rest.controller;

import diegosneves.github.conectardoacoes.adapters.rest.request.ReceiveDonationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.request.ShelterCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.ReceiveDonationResponse;
import diegosneves.github.conectardoacoes.adapters.rest.response.ShelterCreatedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

/**
 * Interface que define as operações de gerenciamento de abrigos.
 * <p>
 * Esta interface representa o controlador que manipula todas as operações relacionadas aos abrigos
 * em nosso sistema. Define como o cliente (geralmente um front-end da web)
 * deve interfacear com os serviços de back-end para criar e administrar abrigos.
 * <p>
 * As operações principais que ela define incluem a criação de novos abrigos,
 * através de solicitações HTTP POST para a rota "/ create".
 * A especificação completa dessas rotas e do códigos de status HTTP que eles podem retornar,
 * encontra-se nos Javadoc dos métodos individuais.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public interface ShelterController {

    /**
     * Método POST para a criação de um Abrigo.
     * <p>
     * Este método recebe um objeto JSON que representa uma solicitação de criação de abrigo,
     * a criação do abrigo é realizada no back-end, e retorna uma resposta que inclui os
     * detalhes do abrigo criado.
     * <p>
     * O objeto de solicitação deve ser fornecido no corpo da solicitação, com os seguintes campos:
     * <ul>
     * <li> {@code shelterName}: Nome do abrigo a ser criado.</li>
     * <li> {@code address}: Um objeto que representa o endereço do abrigo. Deve incluir rua, número, bairro,
     * cidade, estado e CEP.</li>
     * <li> {@code responsibleUserEmail}: O e-mail do usuário responsável pelo abrigo.</li>
     * </ul>
     * <p>
     * O método retornará um objeto JSON com os seguintes campos:
     * <ul>
     *      <li> {@code id}: O ID gerado para o novo abrigo criado.</li>
     *      <li> {@code shelterName}: O nome do abrigo criado.</li>
     *      <li> {@code address}: Um objeto representando o endereço do abrigo criado.</li>
     *      <li> {@code responsibleUser}: Um objeto representando o usuário responsável pelo abrigo.</li>
     * </ul>
     *
     * @param request objeto {@link ShelterCreationRequest} que representa a solicitação de criação de
     *                um abrigo que é mapeada do corpo da solicitação JSON.
     * @return Retorna um {@link ResponseEntity} que encapsula a resposta da criação do abrigo. Esta
     * resposta inclui o status HTTP da operação, bem como um corpo que é uma representação JSON
     * do abrigo criado.
     * @throws ResponseStatusException será lançada se a criação do abrigo falhar devido a
     *                                 problemas de validação ou problemas no servidor. A mensagem de erro será incluída na
     *                                 exceção.
     */
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Criar Abrigo",
            description = "Este endpoint é responsável por criar e registrar um novo Abrigo no sistema utilizando os dados recebidos por meio de uma requisição POST",
            tags = "Abrigos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Abrigo criado com sucesso!",
                    content = @Content(schema = @Schema(implementation = ShelterCreatedResponse.class))
            )
    })
    ResponseEntity<ShelterCreatedResponse> createShelter(@RequestBody ShelterCreationRequest request);

    /**
     * Método POST para o recebimento de doações.
     * <p>
     * Este método recebe um objeto JSON que representa uma solicitação de recebimento de doação,
     * o registro da doação é realizado no back-end, e retorna uma resposta que inclui os detalhes da doação recebida.
     * </p>
     * <p>
     * O objeto de solicitação deve ser fornecido no corpo da solicitação, com os seguintes campos:
     * <ul>
     * <li> {@code responsibleEmail}: O e-mail do usuário responsável pelo recebimento da doação.</li>
     * <li> {@code donationDTOS}: Uma lista de doações que o abrigo está recebendo.</li>
     * </ul>
     * <p>
     * O método retornará um objeto JSON com os seguintes campos:
     * <ul>
     *      <li> {@code shelterName}: O nome do abrigo que recebeu a doação.</li>
     *      <li> {@code responsibleName}: O nome da pessoa que está gerenciando as doações no abrigo.</li>
     *      <li> {@code responsibleEmail}: O e-mail do responsável pelo recebimento das doações.</li>
     *      <li> {@code donationDTOS}: Uma lista de objetos representando as doações recebidas.</li>
     * </ul>
     *
     * @param request objeto {@link ReceiveDonationRequest} que representa a solicitação de recebimento de
     *                uma doação que é mapeada do corpo da solicitação JSON.
     * @return Retorna um {@link ResponseEntity} que encapsula a resposta do recebimento da doação. Esta
     * resposta inclui o status HTTP da operação, bem como um corpo que é uma representação JSON
     * das doações recebidas.
     * @throws ResponseStatusException será lançada se o recebimento da doação falhar devido a
     *                                 problemas de validação ou problemas no servidor. A mensagem de erro será incluída na
     *                                 exceção.
     */
    @PostMapping(value = "/donation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Registrar doações",
            description = "Este endpoint recebe e registra novas doações no sistema por meio de uma requisição POST",
            tags = "Abrigos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Registro de doações foi bem-sucedido!",
                    content = @Content(schema = @Schema(implementation = ReceiveDonationResponse.class))
            )
    })
    ResponseEntity<ReceiveDonationResponse> receiveDonation(@RequestBody ReceiveDonationRequest request);

}
