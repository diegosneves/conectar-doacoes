package diegosneves.github.conectardoacoes.adapters.rest.service;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DepositDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.DepositProcessingException;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.adapters.rest.request.DepositDonationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.RegisteredDepositResponse;

/**
 * Interface para o serviço de depósitos de doadores.
 * <p>
 * Esta interface fornece o contrato para o registro de doações feitas por doadores.
 * Implementações desta interface deverão definir a lógica específica de como
 * registrar essas doações e retornar a resposta adequada.
 *
 * @author diegoneves
 * @since 1.3.0
 */
public interface DonorDepositService {

    /**
     * Registra uma nova doação com base nas informações fornecidas no {@link DepositDonationRequest}.
     *
     * <p>Este método é responsável por processar uma solicitação de doação de depósito,
     * validar os dados da solicitação e registrar a doação no sistema. Ele retorna um
     * {@link RegisteredDepositResponse} contendo as informações da doação registrada,
     * como confirmação de que a doação foi processada com sucesso.
     *
     * @param request O objeto {@link DepositDonationRequest} contendo os detalhes da doação a ser registrada.
     *                Deve incluir o email do responsável pela doação e uma lista de detalhes dos depósitos
     *                ({@link DepositDTO}).
     * @return Um objeto {@link RegisteredDepositResponse} contendo os detalhes da doação registrada,
     * incluindo o nome do usuário, email e a lista de depósitos associados.
     * @throws DepositProcessingException Se a solicitação fornecida for inválida ou faltarem dados obrigatórios, ou, se ocorrer um erro durante o processamento do depósito.
     */
    RegisteredDepositResponse registerDonation(DepositDonationRequest request);

    /**
     * Vincula depósitos a um doador baseado nas informações do usuário fornecidas.
     * <p>
     * Este método valida o usuário dado e, se o perfil do usuário for do tipo "DONOR",
     * cria um novo registro de depósito doador e salva no repositório apropriado.
     * </p>
     *
     * @param user Um objeto {@link UserEntity} que representa o usuário cujos depósitos serão vinculados.
     *             O usuário deve ter um perfil do tipo "DONOR" para prosseguir com o vinculo.
     * @throws DepositProcessingException Se o objeto de usuário fornecido for nulo, estiver vazio,
     *                                    ou se ocorrer algum erro durante o processamento da validação.
     */
    void linkDepositToDonor(UserEntity user);

}
