package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DepositDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.DepositProcessingException;
import diegosneves.github.conectardoacoes.adapters.rest.model.DepositEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonorDeposit;
import diegosneves.github.conectardoacoes.adapters.rest.response.RegisteredDepositResponse;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapeador para converter um objeto do tipo {@link DonorDeposit} em um objeto do tipo {@link RegisteredDepositResponse}.
 * <p>
 * Esta classe implementa a interface {@link MapperStrategy} específica para o mapeamento de doações de doadores registradas.
 * A principal responsabilidade desta classe é validar o objeto de origem e, em seguida, mapear suas propriedades para o objeto de resposta correspondente.
 * </p>
 * <p>
 * Utiliza a utilidade {@link ValidationUtils} para certificar-se de que o objeto de origem não seja nulo ou vazio antes de proceder com o mapeamento.
 * Se ocorrer qualquer falha na validação, uma exceção {@link DepositProcessingException} será lançada.
 * </p>
 * <p>
 * A execução principal do mapeamento acontece no método {@link #mapFrom(DonorDeposit)}, onde os atributos do objeto {@link DonorDeposit}
 * são transformados e ajustados para preencher um novo objeto {@link RegisteredDepositResponse}.
 * </p>
 * <p>
 * Além disso, a classe contém um método auxiliar {@link #getDepositDTOs(List)} que converte listas de entidades de depósito
 * ({@link DepositEntity}) em listas de objetos de transferência de dados ({@link DepositDTO}).
 * </p>
 *
 * @see MapperStrategy
 * @see ValidationUtils
 * @see BuilderMapper
 * @see RegisteredDepositResponse
 * @see DonorDeposit
 * @see DepositDTO
 * @author diegoneves
 * @since 1.3.0
 *
 */
public class RegisteredDepositResponseFromDonorDepositMapper implements MapperStrategy<RegisteredDepositResponse, DonorDeposit> {

    public static final Class<DonorDeposit> SOURCE_CLASS = DonorDeposit.class;

    @Override
    public RegisteredDepositResponse mapFrom(DonorDeposit source) {
        ValidationUtils.validateNotNullOrEmpty(source, CLASS_MAPPING_FAILURE, SOURCE_CLASS.getSimpleName(), DepositProcessingException.class);
        return RegisteredDepositResponse.builder()
                .userName(source.getUser().getUserName())
                .email(source.getUser().getEmail())
                .deposits(getDepositDTOs(source.getDeposits()))
                .build();
    }

    /**
     * Converte uma lista de entidades de depósito {@link DepositEntity} em uma lista de objetos {@link DepositDTO}.
     * <p>
     * Este método verifica se a lista de depósitos é nula ou vazia. Se for, retorna uma nova lista vazia.
     * Caso contrário, mapeia cada entidade {@link DepositEntity} para um objeto {@link DepositDTO} usando o {@link BuilderMapper}.
     *
     * @param deposits a lista de entidades de depósito {@link DepositEntity} a ser convertida.
     * @return uma lista de {@link DepositDTO} que representa os depósitos fornecidos. Se a lista de entrada for nula ou vazia, uma lista vazia será retornada.
     */
    private static List<DepositDTO> getDepositDTOs(List<DepositEntity> deposits) {
        if (deposits == null || deposits.isEmpty()) {
            return new ArrayList<>();
        }
        return deposits.stream().map(d -> BuilderMapper.mapTo(DepositDTO.class, d)).toList();
    }
}
