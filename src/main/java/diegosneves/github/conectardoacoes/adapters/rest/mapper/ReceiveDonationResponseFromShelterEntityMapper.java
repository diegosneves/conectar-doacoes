package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DonationDTO;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.adapters.rest.response.ReceiveDonationResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe é responsável pela conversão de objetos do tipo {@link ShelterEntity} para {@link ReceiveDonationResponse}.
 * Esta conversão é especialmente útil na camada de serviço, quando obtemos dados da entidade Shelter (Abrigo) do banco de dados
 * e precisamos converter esses dados para um formato de resposta (DTO) que será enviado ao cliente.
 * <p>
 * A classe implementa a estratégia de mapeamento, que é um padrão de design comportamental que permite que você defina uma família
 * de algoritmos, coloque cada um deles em uma classe separada, e torna as instâncias desses algoritmos intercambiáveis.
 * <p>
 * Esta estratégia específica é utilizada quando se deseja converter um {@link ShelterEntity} (que contém dados do Abrigo e Doações),
 * para uma {@link ReceiveDonationResponse} que é uma resposta padrão para o cliente que contém os detalhes de um abrigo e suas doações.
 *
 * @author diegoneves
 * @see MapperStrategy
 * @see <a href="https://refactoring.guru/design-patterns/strategy">Padrão Strategy</a> para mais detalhes sobre a estratégia de mapeamento utilizada.
 * @since 1.1.0
 */
public class ReceiveDonationResponseFromShelterEntityMapper implements MapperStrategy<ReceiveDonationResponse, ShelterEntity> {


    @Override
    public ReceiveDonationResponse mapFrom(ShelterEntity source) {
        return ReceiveDonationResponse.builder()
                .shelterName(source.getShelterName())
                .responsibleName(source.getResponsibleUser().getUserName())
                .responsibleEmail(source.getResponsibleUser().getEmail())
                .donationDTOS(getDonationDTOs(source.getDonations()))
                .build();
    }

    /**
     * Este método privado tem como função auxiliar a converter uma lista de objetos do tipo {@link DonationEntity}
     * para uma lista do tipo {@link DonationDTO}.
     *
     * <p>
     * Este é realizado ao mapear cada entidade de doação na lista de entidades de doação fornecida como argumento para um DTO de doação.
     * Consequentemente, uma nova lista de objetos {@link DonationDTO} é gerada.
     * </p>
     *
     * <p>
     * A operação de mapeamento é facilitada com a ajuda da classe {@link BuilderMapper}.
     * A classe {@link BuilderMapper} executa a operação de mapeamento de um objeto-fonte para um objeto-alvo de um tipo-fonte para um tipo-alvo.
     * </p>
     *
     * <p>
     * Se a lista fornecida for nula ou vazia, o método retorna uma nova lista vazia.
     * </p>
     *
     * @param donations a lista de entidades de doação a serem convertidas para DTOs
     * @return uma lista de {@link DonationDTO}, onde cada DTO é o resultado da conversão de uma entidade de doação
     */
    private List<DonationDTO> getDonationDTOs(List<DonationEntity> donations) {
        if (donations == null || donations.isEmpty()) {
            return new ArrayList<>();
        }
        return donations.stream().map(d -> BuilderMapper.mapTo(DonationDTO.class, d)).toList();
    }

}
