package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DonationDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.DonationEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.DonationEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.DonationRepository;
import diegosneves.github.conectardoacoes.adapters.rest.service.DonationEntityService;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.shelter.factory.DonationFactory;
import diegosneves.github.conectardoacoes.core.service.DonationService;
import diegosneves.github.conectardoacoes.core.service.DonationServiceContract;
import diegosneves.github.conectardoacoes.core.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável pela lógica de negócios relacionada à gestão de doações.
 * <p>
 * Essa classe manipula principalmente operações de conversão e persistência de doações, além de garantir que todas as doações criadas sejam válidas.
 * <p>
 * O fluxo de trabalho principal desta classe envolve a conversão de objetos de transferência de dados (DTOs) para Entidades de doação e a posterior salvamento dos mesmos no repositório.
 * O processo de conversão é realizado por métodos específicos da classe, que transformam um {@link DonationDTO} em uma Entidade de doação ({@link DonationEntity}) e então em uma doação ({@link Donation}).
 * Durante esse processo, os dados da doação são validados por meio do utilitário {@link ValidationUtils}.
 * <p>
 * Além disso, a classe também gerencia exceções que podem surgir durante o procedimento de criação de uma doação. Caso qualquer anormalidade ocorra, um registro é adicionado ao log do sistema e uma {@link DonationEntityFailuresException} é lançada.
 *
 * @author diegoneves
 * @since 1.1.0
 */
@Service
@Slf4j
public class DonationEntityServiceImpl implements DonationEntityService {

    public static final String INVALID_DONATION_INFO_ERROR = "Por favor, insira informações válidas para realizar a doação.";
    public static final String DONATION_CREATION_FAILURE = "Desculpe, encontramos um problema durante a criação da doação. Por favor, verifique as informações e tente novamente.";

    private static final String DONATION_CREATION_ERROR_LOG = "A criação da doação não foi bem-sucedida devido ao seguinte erro: {}";

    private final DonationRepository repository;
    private final DonationServiceContract donationServiceContract;

    @Autowired
    public DonationEntityServiceImpl(DonationRepository repository) {
        this.repository = repository;
        this.donationServiceContract = new DonationService();
    }


    @Override
    public DonationEntity convertAndSaveDonationDTO(DonationDTO donationDTO) {
        ValidationUtils.validateNotNullOrEmpty(donationDTO, 5, INVALID_DONATION_INFO_ERROR, DonationEntityFailuresException.class);
        return this.convertDonationDTOToDonationEntity(donationDTO);
    }

    /**
     * Converte um objeto {@link DonationDTO} em um objeto {@link DonationEntity}.
     * <p>
     * Este método primeiro cria um objeto {@link Donation} a partir do DTO do objeto de doação fornecido.
     * Em seguida, ele mapeia o objeto {@link Donation} para um objeto {@link DonationEntity} usando o mapeador de entities de doação.
     *
     * @param donationDTO O objeto de doação na forma de um Data Transfer Object (DTO) a ser convertido.
     * @return A entity de doação resultante que foi convertida e mapeada a partir do objeto DTO de doação fornecido.
     * @throws DonationEntityFailuresException se não foi possível criar uma instância da entity {@link Donation}.
     */
    private DonationEntity convertDonationDTOToDonationEntity(DonationDTO donationDTO) {
        Donation newDonation = this.createDonation(donationDTO);
        DonationEntity donationEntityOutput = BuilderMapper.mapTo(this.getDonationEntityMapperInstance(), newDonation);
        return this.repository.save(donationEntityOutput);
    }

    /**
     * Cria uma entidade de Doação a partir de um objeto {@link DonationDTO} usando a Factory de Doação.
     *
     * <p>Este método leva um objeto {@link DonationDTO} como argumento.
     * Usando os dados do objeto {@link DonationDTO}, isto irá criar uma nova entidade de Doação através da {@link DonationFactory}.
     * A nova entidade de Doação é então retornada.
     *
     * @param donationDTO objeto que contém as informações para a criação da entidade de Doação
     * @return uma entidade de Doação
     * @throws DonationEntityFailuresException se a criação da entidade de Doação falhar,
     *                                         a exceção {@link DonationEntityFailuresException} é lançada
     *                                         e uma mensagem de erro é registrada com detalhes do erro.
     */
    private Donation createDonation(DonationDTO donationDTO) throws DonationEntityFailuresException {
        Donation created;
        try {
            created = this.donationServiceContract.createDonation(donationDTO.getDescription(), donationDTO.getAmount());
        } catch (RuntimeException e) {
            log.error(DONATION_CREATION_ERROR_LOG, e.getMessage(), e);
            throw new DonationEntityFailuresException(5, DONATION_CREATION_FAILURE, e);
        }
        return created;
    }

    /**
     * Esta função é responsável por instanciar e retornar uma nova instância de {@link DonationEntityMapper}.
     *
     * <p>O {@link DonationEntityMapper} é uma classe utilizada para mapear uma entidade de doação em outra representação ou formato.
     * Esta função é utilizada quando uma nova instância dessa classe mapeadora é necessária.
     *
     * @return Uma nova instância de {@link DonationEntityMapper}. Cada chamada a esta função irá gerar uma nova instância.
     * @see diegosneves.github.conectardoacoes.adapters.rest.mapper.MapperStrategy
     */
    private DonationEntityMapper getDonationEntityMapperInstance() {
        return new DonationEntityMapper();
    }

}
