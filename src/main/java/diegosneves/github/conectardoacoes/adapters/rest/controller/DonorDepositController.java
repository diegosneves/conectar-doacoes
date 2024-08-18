package diegosneves.github.conectardoacoes.adapters.rest.controller;

import diegosneves.github.conectardoacoes.adapters.rest.dto.ExceptionDTO;
import diegosneves.github.conectardoacoes.adapters.rest.request.DepositDonationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.RegisteredDepositResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface DonorDepositController
 * <p>
 * Esta interface define os endpoints relacionados ao registro de itens de doação
 * no depósito do doador.
 *
 * <p>Através desta interface, é possível fazer o registro de novos itens de doação,
 * fornecendo os dados necessários via requisição. O registro é realizado utilizando
 * anotações do Spring MVC para definir a rota e o método HTTP, além de anotações do
 * Swagger para documentação e definição das possíveis respostas da API.</p>
 *
 * <p>Principais funcionalidades:</p>
 * <ul>
 *     <li>Registrar um item de doação no depósito do doador utilizando uma requisição
 *     do tipo POST, contendo um objeto JSON com as informações do item.</li>
 *     <li>A rota associada à operação de registro é definida pela anotação {@code @PostMapping},
 *     que especifica também os tipos de mídia de consumo e produção (ambos JSON).</li>
 *     <li>Utilização da anotação {@code @Operation} para adicionar metadados à documentação
 *     Swagger sobre a operação de registro.</li>
 *     <li>Especificação das possíveis respostas através da anotação {@code @ApiResponses}, que
 *     define diferentes cenários como sucesso no registro, requisição inválida, e doador não encontrado.</li>
 * </ul>
 *
 * @see org.springframework.web.bind.annotation.PostMapping
 * @see io.swagger.v3.oas.annotations.Operation
 * @see io.swagger.v3.oas.annotations.responses.ApiResponse
 * @see io.swagger.v3.oas.annotations.responses.ApiResponses
 * @author diegoneves
 * @since 1.3.0
 */
public interface DonorDepositController {


    /**
     * Endpoint para registrar um item de doação no depósito do Doador.
     *
     * <p>Este endpoint recebe uma requisição POST contendo dados do item a ser registrado
     * e armazena essa informação no depósito associado ao doador. A requisição deve
     * conter um JSON com as informações definidas na classe {@link DepositDonationRequest}.</p>
     *
     * <p>Notas sobre o funcionamento:</p>
     * <ul>
     *     <li>Anotações {@code @PostMapping} definem a rota e especificam o tipo de mídia de consumo e produção
     *     ({@code MediaType.APPLICATION_JSON_VALUE} para ambos).</li>
     *     <li>Anotação {@code @Operation} fornece metadados Swagger para documentar a operação de registro do item de doação.</li>
     *     <li>Anotação {@code @ApiResponses} define as respostas possíveis para esta requisição.</li>
     * </ul>
     *
     * @param request objeto do tipo {@link DepositDonationRequest}, contendo as informações do item de doação a ser registrado.
     * @return um {@link ResponseEntity} contendo uma instância de {@link RegisteredDepositResponse} retornada com sucesso,
     * ou um {@link ExceptionDTO} em caso de erro.
     * @apiNote As respostas possíveis são:
     * <ul>
     *     <li>{@code 201 Created} - Item registrado no depósito com sucesso. Retorna um objeto {@link RegisteredDepositResponse} contendo os detalhes do registro.</li>
     *     <li>{@code 400 Bad Request} - Solicitação inválida, pode ser devido a erros de validação ou dados incorretos no objeto {@link DepositDonationRequest}. Retorna um objeto {@link ExceptionDTO} contendo a mensagem de erro e o código de status.</li>
     *     <li>{@code 404 Not Found} - Doador não encontrado, o e-mail fornecido não está cadastrado ou o usuário não tem perfil de doador. Retorna um objeto {@link ExceptionDTO} contendo a mensagem de erro e o código de status.</li>
     * </ul>
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Registrar um item de doação no depósito",
            description = "Este endpoint é responsável por registrar um item de doação no depósito do Doador utilizando os dados recebidos por meio de uma requisição POST",
            tags = "Depósitos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Item registrado no depósito com sucesso!",
                    content = @Content(schema = @Schema(implementation = RegisteredDepositResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitação inválida – erros de validação ou dados incorretos.",
                    content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Doador não encontrado – O e-mail fornecido não está cadastrado ou o usuário não tem perfil de doador.",
                    content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            )
    })
    ResponseEntity<RegisteredDepositResponse> registeredDonation(@RequestBody DepositDonationRequest request);

}
