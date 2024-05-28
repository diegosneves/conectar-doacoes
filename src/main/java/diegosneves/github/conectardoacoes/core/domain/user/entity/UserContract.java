package diegosneves.github.conectardoacoes.core.domain.user.entity;

import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.UserCreationFailureException;

/**
 * A interface {@link UserContract} define o contrato para o gerenciamento de um usuário no sistema.
 * Ela especifica os métodos que devemos implementar para manipular os detalhes essenciais do usuário,
 * como ID, nome de usuário, email, senha e perfil.
 *
 * <p>A implementação desta interface deve garantir a validação adequada dos dados do usuário antes de
 * prosseguir com qualquer operação, como a mudança de senha ou nome de usuário. Se qualquer dado inválido for
 * fornecido, como uma senha ou username nulo ou em branco, deverá lançar a exceção
 * {@link UserCreationFailureException}.
 *
 * @author diegoneves
 * @version 1.0
 * @see UserCreationFailureException
 * @see RuntimeException
 */
public interface UserContract {

    /**
     * Este método retorna o ID único do usuário.
     *
     * @return ID do usuário como String.
     */
    String getId();

    /**
     * Obtém o nome de usuário do usuário.
     *
     * @return nome de usuário como String.
     */
    String getUsername();

    /**
     * Obtém o email do usuário.
     *
     * @return Email do usuário como String.
     */
    String getEmail();

    /**
     * Recupera a senha do usuário.
     * <p>Em uma implementação segura, esse método deve ser manuseado com cuidado para evitar a exposição de dados sensíveis.</p>
     *
     * @return Senha do usuário como String.
     */
    String getUserPassword();

    /**
     * Retorna o objeto UserProfile associado ao usuário.
     *
     * @return A instância de UserProfile do usuário.
     */
    UserProfile getUserProfile();

    /**
     * Altera a senha do usuário atual.
     * <p>
     * Este método é usado para alterar a senha do usuário. Ele verifica se a senha fornecida é nula ou em branco.
     * Se for esse o caso, ele lançará uma exceção {@link UserCreationFailureException}.
     *
     * @param password Nova senha do usuário como uma String. Ela não pode ser nula ou em branco.
     * @throws UserCreationFailureException se a senha fornecida for nula ou em branco.
     *                                      Isso indica que uma senha adequada não foi fornecida.
     */
    void changeUserPassword(String password) throws UserCreationFailureException;

    /**
     * Este método é responsável por alterar o nome de usuário.
     *
     * <p> Cuidados são tomados para garantir que o nome de usuário não seja uma String vazia ou nula,
     * caso contrário, seria levantada uma exceção {@link UserCreationFailureException} indicando que o nome de usuário é obrigatório. </p>
     *
     * <p> Essa operação é realizada para o perfil de usuário atual associado a este objeto e o valor de {@code 'this.userName'} é alterado no final.
     * O nome de usuário atribuído aqui é usado posteriormente em outras operações relacionadas ao usuário. </p>
     *
     * @param updatedUsername uma String que contém o novo nome de usuário.
     * @throws UserCreationFailureException se o parâmetro updatedUsername é nulo ou uma String vazia.
     */
    void changeUserName(String updatedUsername);


}
