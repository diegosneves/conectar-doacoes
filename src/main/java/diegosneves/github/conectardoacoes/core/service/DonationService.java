package diegosneves.github.conectardoacoes.core.service;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.shelter.factory.DonationFactory;
import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;

/**
 * Serviço utilizado para gerenciar as operações relacionadas às doações.
 * Implementa a interface {@link DonationServiceContract}, e é o principal ponto de acesso para a manipulação
 * de doações dentro do sistema.
 * <p>
 * A classe se responsabiliza pela criação de novas doações através do método {@link #createDonation(String, Integer)}.
 * Utiliza a classe {@link DonationFactory} para a real criação destes objetos doação.
 * <p>
 * O processo de criação da doação é encapsulado na fabrica de doacoes, assim, a lógica específica de criação de
 * uma doação está isolada para facilidade de manuseio e manutenção do código.
 * <p>
 * Trata a exceção {@link DonationRegisterFailureException} lançada quando ocorre algum problema na criação de uma doação.
 *
 * @author diegoneves
 * @since 1.2.0
 * @see DonationFactory
 * @see Donation
 */
public class DonationService implements DonationServiceContract{

    @Override
    public Donation createDonation(String description, Integer amount) throws DonationRegisterFailureException {
        return DonationFactory.created(description, amount);
    }
}
