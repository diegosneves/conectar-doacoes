package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DepositDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.DepositEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.factory.DepositFactory;
import diegosneves.github.conectardoacoes.adapters.rest.model.DepositEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.DepositRepository;
import diegosneves.github.conectardoacoes.adapters.rest.service.DepositEntityService;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import org.springframework.stereotype.Service;

/**
 * Classe de implementação do serviço de entidade de depósito.
 *
 * <p>
 * Esta classe fornece a implementação das operações de criação relacionadas à entidade
 * de depósito. Ela utiliza o repositório de depósitos ({@link DepositRepository})
 * para salvar a nova entidade de depósito criada.
 * </p>
 *
 * <p>
 * A constante {@code DEPOSIT_VALIDATION_ERROR} representa um código específico de erro
 * de validação dos depósitos.
 * </p>
 *
 * <p>
 * As operações de criação de depósitos utilizam a fábrica de depósitos
 * ({@link DepositFactory}) para instanciar novos objetos de depósito a partir dos dados
 * de transferência (DTO - Data Transfer Object).
 * </p>
 *
 * <p>
 * Esta classe é anotada com {@code @Service} para indicar que é um componente de serviço
 * Spring, tornando-a detectável para a injeção de dependência.
 * </p>
 *
 * @author diegoneves
 * @see DepositEntityService
 * @see DepositRepository
 * @see DepositFactory
 * @since 1.3.0
 */
@Service
public class DepositEntityServiceImpl implements DepositEntityService {

    private static final int DEFAULT_AMOUNT = 1;
    public static final Integer DEPOSIT_VALIDATION_ERROR = 39;

    private final DepositRepository depositRepository;

    public DepositEntityServiceImpl(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public DepositEntity create(DepositDTO dto) {
        depositValidate(dto);
        DepositEntity newDeposit = DepositFactory.createDepositEntity(dto.getDescription(), dto.getAmount());
        return this.depositRepository.save(newDeposit);
    }

    /**
     * Valida o objeto {@link DepositDTO} para garantir que está devidamente populado
     * e contém dados válidos.
     * <p>
     * A validação inclui a verificação se o DTO e seus campos de descrição e valor
     * não são nulos ou vazios. Também garante que o valor não seja menor que o valor
     * padrão, atualizando-o se necessário.
     * </p>
     *
     * @param dto o objeto {@link DepositDTO} a ser validado.
     *            Não deve ser nulo e deve conter uma descrição e um valor não nulos/não vazios.
     * @throws DepositEntityFailuresException se algum dos campos (dto, descrição, valor)
     *                                        forem nulos ou vazios.
     * @implNote O método {@link ValidationUtils#validateNotNullOrEmpty(Object, Integer, Class)} é utilizado
     * para realizar as verificações de validação, lançando a exceção {@link DepositEntityFailuresException}
     * com o código de erro específico {@code DEPOSIT_VALIDATION_ERROR} se alguma regra de validação for violada.
     */
    private static void depositValidate(DepositDTO dto) {
        ValidationUtils.validateNotNullOrEmpty(dto, DEPOSIT_VALIDATION_ERROR, DepositEntityFailuresException.class);
        ValidationUtils.validateNotNullOrEmpty(dto.getDescription(), DEPOSIT_VALIDATION_ERROR, DepositEntityFailuresException.class);
        ValidationUtils.validateNotNullOrEmpty(dto.getAmount(), DEPOSIT_VALIDATION_ERROR, DepositEntityFailuresException.class);
        if (dto.getAmount() < DEFAULT_AMOUNT) {
            dto.setAmount(DEFAULT_AMOUNT);
        }
    }


}
