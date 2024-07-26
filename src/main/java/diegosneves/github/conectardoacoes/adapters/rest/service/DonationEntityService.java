package diegosneves.github.conectardoacoes.adapters.rest.service;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DonationDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.DonationEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;

/**
 * Interface para o serviço de manipulação de doações {@link DonationEntityService}.
 * Fornece métodos para realizar operações como conversão e persistência de doações em nossa base de dados.
 * <p>
 * Além disso, garante que todo o código relacionado a operações específicas de doação estejam centralizados em um só lugar.
 * <p>
 * Esta interface define um contrato para a implementação do serviço, garantindo que todas as classes que implementam
 * {@link DonationEntityService} devem fornecer a implementação para os métodos definidos aqui.
 *
 * @author diegoneves
 * @since 1.1.0
 */
public interface DonationEntityService {

    /**
     * Este método é responsável por converter uma instância de {@link DonationDTO} para a entidade {@link Donation}
     * e persistir essa informação no banco de dados.
     *
     * <p>
     * Primeiro, ele valida se a instância de {@link DonationDTO} dada não é nula e contém todas as informações necessárias.
     * <p>
     * Após a conversão, o método persiste a instância de {@link Donation} convertida no banco de dados usando o
     * repositório e, em seguida, retorna a instância persistida.
     * </p>
     *
     * @param donationDTO Uma instância de {@link DonationDTO} que precisa ser convertida e persistida.
     * @return A instância da entidade {@link DonationEntity} que foi convertida do {@link DonationDTO} e persistida no banco de dados.
     * @throws DonationEntityFailuresException Se o {@link DonationDTO} fornecido for nulo ou não contiver as informações necessárias.
     */
    DonationEntity convertAndSaveDonationDTO(DonationDTO donationDTO) throws DonationEntityFailuresException;

}
