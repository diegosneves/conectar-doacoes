package diegosneves.github.conectardoacoes.adapters.rest.controller;

import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressApiResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface para controlar operações de endereço.
 *
 * <p>Esta interface fornece uma abstração para as operações realizadas em um endereço.
 * As operações realizadas por esta interface incluem a recuperação de um endereço
 * com base em seu código postal/zipe code.</p>
 *
 * <p>Esta interface faz uso de um serviço externo para buscar um endereço
 * e retorna uma representação do endereço como um {@code AddressApiResponseDTO}.
 * Cada operação é mapeada para um endpoint específico para fácil uso via REST.</p>
 *
 * <p>Para usar essa interface, um cliente REST precisará acessar o
 * endpoint correspondente e fornecer os parâmetros necessários.</p>
 *
 * @author diegoneves
 * @since 1.2.0
 */
public interface AddressController {

    /**
     * Obtém um dado Endereço, fazendo uso de um serviço externo.<br>
     * <br>
     * Este é um método HTTP GET que é mapeado para o seguinte URL: /{zipcode}.
     * Ele usa a anotação @Operation para fornecer uma descrição de alto nível da operação
     * e a anotação @ApiResponses para descrever as possíveis respostas da operação.
     *
     * @param zipcode O código postal do local para o qual se quer obter a informação do endereço. É um valor obrigatório e é informado na URL.
     * @return {@code ResponseEntity<AddressApiResponseDTO>} O objeto {@link AddressApiResponseDTO} encapsulado em um {@link ResponseEntity}.
     * O objeto AddressApiResponse contém informações detalhadas sobre o endereço.
     * O ResponseEntity é uma extensão do HttpEntity que adiciona um código HttpStatus a ele.
     * A ResponseEntity retorna o endereço obtido com sucesso!
     * @throws diegosneves.github.conectardoacoes.adapters.rest.exception.ExternalApiFailureException Se o endereço com o código postal fornecido não for encontrado.
     * @see AddressApiResponseDTO
     */
    @GetMapping(value = "/{zipcode}")
    @Operation(
            summary = "Obtém um Endereço",
            description = "Esse endpoint tem a função de obter um determinado Endereço fazendo uso de um serviço externo.",
            tags = "Endereços"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Endereço obtido com sucesso!",
                    content = @Content(schema = @Schema(implementation = AddressApiResponseDTO.class))
            )
    })
    ResponseEntity<AddressApiResponseDTO> retrieveAddress(@PathVariable("zipcode") String zipcode);

}
