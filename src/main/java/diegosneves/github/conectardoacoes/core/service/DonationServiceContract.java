package diegosneves.github.conectardoacoes.core.service;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;

/**
 * Interface {@link DonationServiceContract} define um contrato para a criação de doações.
 * <p>
 * Uma doação é caracterizada por sua descrição e a quantidade (amount).
 * <p>
 * As classes que implementam essa interface são responsáveis por definir a lógica de negócios para
 * atender aos requisitos deste contrato.
 * <p>
 * As classes que implementam essa interface devem gerenciar eventuais exceções, garantindo que as doações
 * sejam criadas somente quando os dados estiverem de acordo com as regras definidas.
 *
 * @author diegoneves
 * @since 1.2.0
 * @see Donation
 */
public interface DonationServiceContract {

    /**
     * Cria um objeto de doação com uma descrição e quantidade dada.
     * <p>
     * Este método tenta criar um objeto {@link Donation} com base nos parâmetros de descrição e quantidade fornecidos pelo usuário.
     * A operação pode falhar e, consequentemente, lançar uma exceção em dois cenários:
     * <ul>
     * <li>
     * Descrição: quando a descrição fornecida é nula ou uma string vazia.
     * </li>
     * <li>
     * Quantidade: quando a quantidade fornecida é nula.
     * </li>
     * </ul>
     *
     * @param description Deve ser uma string contendo a descrição da doação. Não pode ser nula ou vazia.
     * @param amount      Deve ser um número inteiro maior que zero representando a quantidade de doação. Não pode ser nulo.
     * @return Uma instância do objeto Donation se a operação for bem-sucedida.
     * @throws DonationRegisterFailureException Se a criação da doação falhar devido a argumentos inválidos (veja os detalhes acima).
     */
    Donation createDonation(String description, Integer amount) throws DonationRegisterFailureException;

}
