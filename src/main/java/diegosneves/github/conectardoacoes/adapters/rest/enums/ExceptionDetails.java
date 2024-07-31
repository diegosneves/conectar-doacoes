package diegosneves.github.conectardoacoes.adapters.rest.enums;

import diegosneves.github.conectardoacoes.adapters.rest.exception.DetailsFailureException;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Objects;

/**
 * A classe {@link ExceptionDetails} é uma enumeração que define várias mensagens de exceções.
 * Cada mensagem corresponde a uma condição específica de validação ou erro
 * que pode ocorrer durante as operações.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public enum ExceptionDetails {

    TERM_NOT_FOUND(1, "Desconhecido", HttpStatus.NOT_IMPLEMENTED),
    CONSTRUCTOR_DEFAULT_UNDEFINED(2, "Classe [ %s ] deve declarar um construtor padrão.", HttpStatus.NOT_IMPLEMENTED),
    REQUEST_VALIDATION_ERROR_MESSAGE(3, "Por favor, forneça uma requisição de criação de Abrigo preenchida corretamente.", HttpStatus.BAD_REQUEST),
    CLASS_MAPPING_FAILURE(4, "Falha ao tentar mapear a classe [ %s ].", HttpStatus.BAD_REQUEST),
    SHELTER_CREATION_ERROR_MESSAGE(5, "Erro na criação de um Abrigo. Por favor,Confirme se todos os campos do Abrigo estão corretamente preenchidos e tente novamente.", HttpStatus.BAD_REQUEST),
    INVALID_UUID_FORMAT_MESSAGE(6, "O ID %s precisa estar no formato UUID", HttpStatus.BAD_REQUEST),
    RESPONSIBLE_USER_PROFILE_INVALID(7, "O usuário deve possuir o perfil de responsável.", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_FOUND_ERROR_MESSAGE(8, "Não foi possível encontrar um usuário com o email %s .", HttpStatus.NOT_FOUND),
    RESPONSIBLE_USER_ALREADY_IN_USE(9, "Este usuário já possui responsabilidade sobre outro abrigo.", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_IN_USE(10, "Desculpe, o endereço de email %s , já está associado a uma conta existente. Por favor,tente com um email diferente.", HttpStatus.BAD_REQUEST),
    USER_RESPONSIBLE_EMAIL_NOT_FOUND_ERROR(11, "Ops! Não conseguimos encontrar o e-mail do usuário responsável. Por gentileza, tente novamente.", HttpStatus.NOT_FOUND),
    DONATION_VALIDATION_ERROR(13, "Para o cadastro de doações, é indispensável o fornecimento de informações válidas e completas.", HttpStatus.BAD_REQUEST),
    EMPTY_DONATION_LIST(15, "Até o momento, não há doações listadas.", HttpStatus.BAD_REQUEST),
    RESPONSIBLE_EMAIL_NOT_ASSOCIATED_WITH_SHELTER(17, "Por favor, verifique se o e-mail do usuário responsável está correto e associado a um abrigo. Caso contrário, certifique-se de que o usuário responsável tenha um e-mail válido em nosso sistema.", HttpStatus.BAD_REQUEST),
    INVALID_ID_MESSAGE(19, "Deve ser fornecido um ID válido!", HttpStatus.BAD_REQUEST),
    SHELTER_ERROR_MESSAGE(21, "Um objeto Abrigo válido deve ser fornecido para persistência!", HttpStatus.BAD_REQUEST),
    ADDRESS_CREATION_ERROR(23, "Erro na criação do endereço. Confirme se todos os campos do endereço estão corretos e tente novamente.", HttpStatus.BAD_REQUEST),
    ERROR_MAPPING_ADDRESS(25, "Erro durante o mapeamento do endereço para persistência", HttpStatus.BAD_REQUEST),
    MISSING_USER_ENTITY_REQUEST_ERROR_MESSAGE(27, "Por favor, forneça uma requisição de criação de usuário preenchida corretamente.", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL_ERROR_MESSAGE(29, "Não foi informado nenhum email. Por favor, forneça um email válido.", HttpStatus.BAD_REQUEST),
    USER_CREATION_FAILURE_MESSAGE(31, "Ops! A criação do novo usuário não foi bem-sucedida. Por favor, certifique-se de que seus dados estão corretos e tente novamente.", HttpStatus.BAD_REQUEST),
    USER_PROFILE_VALIDATION_FAILURE(33, "A validação do Perfil do usuário fornecido falhou.", HttpStatus.BAD_REQUEST),
    REQUIRED_USER_ERROR_MESSAGE(35, "Um usuário válido é requerido para efetuar a persistência.", HttpStatus.BAD_REQUEST);


    public static final String EXCEPTION_PREFIX = "T%03dF - ";

    private final Integer term;
    private final String message;
    private final HttpStatus httpStatus;

    ExceptionDetails(Integer term, String message, HttpStatus httpStatus) {
        this.term = term;
        this.message = message;
        this.httpStatus = httpStatus;
    }


    /**
     * Formata uma mensagem com a entrada fornecida e retorna a mensagem formatada.
     *
     * @param message A mensagem de entrada que será formatada.
     * @return A mensagem após a formatação.
     */
    public String formatErrorMessage(String message) {
        return String.format(EXCEPTION_PREFIX, this.term) + String.format(this.message, message);
    }

    /**
     * Método responsável por formatar uma mensagem de erro.
     * <p>
     * Esse método forma uma mensagem de erro por prefixar a string de termo com um prefixo de exceção definido e
     * em seguida, concatenando a mensagem. Isso é típico de cenários onde você precisa gerar mensagens de erro
     * personalizadas para exceções.
     *
     * @return a mensagem de erro formatada como uma string. A mensagem de erro retornada consiste no termo prefixado
     * com o EXCEPTION_PREFIX seguido pela mensagem de erro original.
     */
    public String formatErrorMessage() {
        return String.format(EXCEPTION_PREFIX, this.term) + this.message;
    }


    /**
     * Retorna o código de status HTTP associado ao erro.
     *
     * @return O código numérico do status HTTP relacionado com o erro.
     */
    public int getStatusCodeValue() {
        return this.httpStatus.value();
    }

    /**
     * Obtém o status HTTP associado ao erro.
     *
     * @return O código de status HTTP relacionado ao erro.
     */
    public HttpStatus getHttpStatusCode() {
        return this.httpStatus;
    }

    /**
     * Obtem os detalhes da exceção pelo código correspondente.
     *
     * <p> Este método percorre o enum ExceptionDetails, procura um elemento cujo campo 'term' seja igual
     * ao código de entrada e retorna esse elemento. Se nenhum elemento for encontrado com o código
     * fornecido, uma nova exceção DetailsFailureException é lançada com código de erro 1.
     *
     * <h2> Exemplo de Uso </h2>
     * {@code ExceptionDetails detail = getExceptionDetails(1001);}
     *
     * <h2> Exceções Lançadas </h2>
     * {@code DetailsFailureException} - Se o código fornecido não corresponder a nenhum 'term'
     * do enum ExceptionDetails
     *
     * @param code é um Integer representando o código do term correspondente à exceção a ser buscada.
     * @return Retorna o detalhe da exceção correspondente ao código.
     * @throws DetailsFailureException se nenhum detalhe de exceção correspondente for encontrado.
     */
    public static ExceptionDetails getExceptionDetails(Integer code) {
        return Arrays.stream(ExceptionDetails.values())
                .filter(detail -> Objects.equals(detail.term, code))
                .findFirst()
                .orElseThrow(() -> new DetailsFailureException(1));
    }

}
