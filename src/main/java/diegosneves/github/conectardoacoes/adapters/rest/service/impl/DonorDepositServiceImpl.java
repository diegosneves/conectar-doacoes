package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DepositDTO;
import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.DepositProcessingException;
import diegosneves.github.conectardoacoes.adapters.rest.factory.DonorDepositFactory;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.RegisteredDepositResponseFromDonorDepositMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.DepositEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonorDeposit;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.DonorDepositRepository;
import diegosneves.github.conectardoacoes.adapters.rest.request.DepositDonationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.RegisteredDepositResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.DepositEntityService;
import diegosneves.github.conectardoacoes.adapters.rest.service.DonorDepositService;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço de depósitos de doadores.
 * <p>
 * Esta classe fornece a implementação dos métodos definidos na interface {@link DonorDepositService},
 * responsável por gerenciar depósitos feitos por doadores.
 * <p>
 * A classe utiliza os repositórios {@link DonorDepositRepository} e {@link DepositEntityService} para
 * persistência e operações relacionadas aos depósitos.
 *
 * <h3>Constantes de Erro</h3>
 * <ul>
 *   <li>{@link #DONOR_EMAIL_NOT_FOUND_ERROR_MESSAGE} - Código de erro para e-mail de doador não encontrado.</li>
 *   <li>{@link #DEPOSIT_VALIDATION_ERROR} - Código de erro para falha na validação de depósito.</li>
 *   <li>{@link #REQUIRED_USER_ERROR_MESSAGE} - Código de erro para usuário obrigatório.</li>
 * </ul>
 *
 * <p>Também utiliza o utilitário {@link ValidationUtils} para validações comuns.</p>
 *
 * <h3>Exemplo de Uso</h3>
 * <pre>
 * {@code
 * @Autowired
 * private DonorDepositServiceImpl donorDepositService;
 *
 * DepositDonationRequest request = new DepositDonationRequest();
 * // configuração do request
 * RegisteredDepositResponse response = donorDepositService.registerDonation(request);
 * }
 * </pre>
 *
 * @see DonorDepositService
 * @see ValidationUtils
 * @author diegoneves
 * @since 1.3.0
 */
@Service
public class DonorDepositServiceImpl implements DonorDepositService {

    public static final Integer DONOR_EMAIL_NOT_FOUND_ERROR_MESSAGE = 14;
    public static final Integer DEPOSIT_VALIDATION_ERROR = 39;
    public static final Integer REQUIRED_USER_ERROR_MESSAGE = 35;

    private final DonorDepositRepository donorDepositRepository;
    private final DepositEntityService depositEntityService;

    public DonorDepositServiceImpl(DonorDepositRepository donorDepositRepository, DepositEntityService depositEntityService) {
        this.donorDepositRepository = donorDepositRepository;
        this.depositEntityService = depositEntityService;
    }

    @Override
    public RegisteredDepositResponse registerDonation(DepositDonationRequest request) {
        registerDonationValidate(request);
        Optional<DonorDeposit> matchingDonorDeposit = this.donorDepositRepository.findDonorDepositByUser_Email(request.getResponsibleEmail());
        if (matchingDonorDeposit.isPresent()) {
            DonorDeposit donorDeposit = matchingDonorDeposit.get();
            List<DepositEntity> newDepositEntities = this.mergeDeposits(request, donorDeposit);
            donorDeposit.setDeposits(newDepositEntities);
            return BuilderMapper.mapTo(getResponseMapper(), this.donorDepositRepository.save(donorDeposit));
        }
        throw new DepositProcessingException(DONOR_EMAIL_NOT_FOUND_ERROR_MESSAGE, request.getResponsibleEmail());
    }

    /**
     * Valida uma solicitação de depósito.
     * <p>
     * Este método verifica se a solicitação de depósito de doação, o e-mail do responsável pelo depósito,
     * e a lista de objetos de depósito não são nulos ou vazios, lançando exceções em casos de validação falha.
     * <p>
     * Todas as validações utilizam o utilitário {@link ValidationUtils} para garantir que os dados obrigatórios
     * estejam presentes antes de prosseguir com o processamento de depósito.
     *
     * @param request A solicitação de depósito que está sendo validada.
     *                O objeto {@link DepositDonationRequest} deve incluir um e-mail do responsável
     *                e uma lista de objetos do tipo {@link DepositDTO}.
     * @throws DepositProcessingException Se qualquer um dos seguintes critérios de validação falhar:
     *                                    <ul>
     *                                        <li>A solicitação de depósito ({@code request}) é nula ou está vazia.</li>
     *                                        <li>O e-mail do responsável pela doação ({@code request.getResponsibleEmail()}) é nulo ou está vazio.</li>
     *                                        <li>A lista de objetos de depósito ({@code request.getDepositDTOS()}) é nula ou está vazia.</li>
     *                                    </ul>
     * @see ValidationUtils#validateNotNullOrEmpty(Object, String, Class)
     * @see ValidationUtils#ensureListIsNotNullOrEmpty(List, String, Class)
     */
    private static void registerDonationValidate(DepositDonationRequest request) {
        ValidationUtils.validateNotNullOrEmpty(request, DEPOSIT_VALIDATION_ERROR, DepositProcessingException.class);
        ValidationUtils.validateNotNullOrEmpty(request.getResponsibleEmail(), DEPOSIT_VALIDATION_ERROR, DepositProcessingException.class);
        ValidationUtils.ensureListIsNotNullOrEmpty(request.getDepositDTOS(), DEPOSIT_VALIDATION_ERROR, DepositProcessingException.class);
    }

    /**
     * Obtém uma instância de {@link RegisteredDepositResponseFromDonorDepositMapper} que é utilizada
     * para mapear objetos do tipo {@link DonorDeposit} para o tipo {@link RegisteredDepositResponse}.
     *
     * @return Uma nova instância de {@link RegisteredDepositResponseFromDonorDepositMapper}, que será usada para
     * realizar o mapeamento dos dados de doações de doadores registrados.
     */
    private static RegisteredDepositResponseFromDonorDepositMapper getResponseMapper() {
        return new RegisteredDepositResponseFromDonorDepositMapper();
    }

    /**
     * Combina os depósitos existentes de um doador com novos depósitos fornecidos em uma solicitação de doação.
     * <p>
     * Este método une a lista de {@link DepositEntity} existente associada a um {@link DonorDeposit}
     * com uma nova lista de {@link DepositEntity} que é criada a partir do {@link DepositDonationRequest}.
     *
     * @param request      A solicitação de doação contendo detalhes dos novos depósitos.
     *                     O objeto {@link DepositDonationRequest} deve incluir uma lista de {@link DepositDTO}.
     *                     {@link DepositDonationRequest#getDepositDTOS()} é utilizada para obter esta lista.
     * @param donorDeposit A entidade {@link DonorDeposit} existente associada ao doador.
     *                     {@link DonorDeposit#getDeposits()} deve retornar a lista atual de depósitos do doador.
     * @return Uma lista combinada de {@link DepositEntity} contendo os depósitos existentes e os novos.
     */
    private List<DepositEntity> mergeDeposits(DepositDonationRequest request, DonorDeposit donorDeposit) {
        List<DepositEntity> newDepositEntities = new ArrayList<>();
        newDepositEntities.addAll(donorDeposit.getDeposits());
        newDepositEntities.addAll(request.getDepositDTOS().stream().map(this.depositEntityService::create).toList());
        return newDepositEntities;
    }

    @Override
    public void linkDepositToDonor(UserEntity user) {
        ValidationUtils.validateNotNullOrEmpty(user, REQUIRED_USER_ERROR_MESSAGE, DepositProcessingException.class);
        if (UserProfileType.DONOR.equals(user.getUserProfile())) {
            DonorDeposit newDeposit = DonorDepositFactory.create(user);
            this.donorDepositRepository.save(newDeposit);
        }
    }
}
