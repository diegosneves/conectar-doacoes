package diegosneves.github.conectardoacoes.adapters.rest.config.handler;

import diegosneves.github.conectardoacoes.adapters.rest.dto.ExceptionDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.AddressEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ConstructorDefaultUndefinedException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.DonationEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * A classe {@link ControllerExceptionHandler} é um manipulador de exceções global para controladores.
 * Ela lida com as exceções lançadas durante o processamento de solicitações e gera respostas de erro apropriadas.
 * A classe é anotada com {@link RestControllerAdvice} para aplicar o tratamento de exceção globalmente
 * a todas as classes de controlador.
 *
 * @author diegosneves
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    /**
     * Manipula exceções gerais e retorna uma resposta de erro apropriada.
     *
     * @param exception A exceção que ocorreu.
     * @return Uma {@link ResponseEntity} contendo um {@link ExceptionDTO} com a mensagem da exceção e um código de status HTTP
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleFailures(Exception exception) {
        ExceptionDTO dto = new ExceptionDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    /**
     * Esta é uma função que lida com exceções do tipo {@link HttpMessageNotReadableException} em todo o controlador.
     * Um objeto {@link HttpMessageNotReadableException} é lançado quando há um erro de sintaxe no corpo HTTP da solicitação.
     * Este método captura essa exceção, registra uma mensagem de erro, cria um objeto {@link ExceptionDTO}
     * contendo essa mensagem e um valor de retorno HTTP de {@code BAD_REQUEST} (400) e, em seguida, retorna essa entidade.
     *
     * @param exception A exceção {@link HttpMessageNotReadableException} que foi lançada quando ocorreu um erro de sintaxe no corpo HTTP de uma solicitação.
     * @return Uma nova ResponseEntity contendo um objeto ExceptionDTO com a mensagem de erro e o status {@code BAD_REQUEST}.
     * O valor de HttpStatus para {@code BAD_REQUEST} é 400, o que indica que a solicitação era inválida ou não pôde ser entendida pelo servidor.
     * @apiNote {@link HttpMessageNotReadableException} Esta exceção é lançada quando ocorre um erro de sintaxe no corpo HTTP da solicitação, o que significa que a solicitação não pode ser lida.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDTO> handleJSONFailures(HttpMessageNotReadableException exception) {
        String message = "Não foi possível processar o conteúdo da solicitação. Por favor, confira se os dados foram inseridos corretamente.";
        ExceptionDTO dto = new ExceptionDTO(message, HttpStatus.BAD_REQUEST.value());
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    /**
     * Método que manipula as exceções específicas da {@link ConstructorDefaultUndefinedException}
     * que podem ocorrer ao instanciar objetos sem um construtor padrão. Quando essa exceção
     * é lançada, este método a capturará e retornará uma resposta HTTP adequada usando
     * o {@link ExceptionDTO} para transmitir as informações da exceção.
     * <p>
     * Este método é anotado com {@code @ExceptionHandler}, que é uma anotação Springer,
     * significando que o Springer invocará automaticamente este método para tratar as exceções do
     * tipo {@link ConstructorDefaultUndefinedException} lançadas por qualquer método do controlador
     * respectivo.
     *
     * @param exception A exceção específica do {@link ConstructorDefaultUndefinedException} que ocorreu.
     * @return Uma resposta HTTP encapsulada em uma {@link ResponseEntity} que contém o DTO da exceção,
     * composto pela mensagem da exceção e um código de status HTTP. O status HTTP da resposta é
     * o mesmo que o status HTTP da exceção.
     */
    @ExceptionHandler(ConstructorDefaultUndefinedException.class)
    public ResponseEntity<ExceptionDTO> handleMapperFailures(ConstructorDefaultUndefinedException exception) {
        ExceptionDTO dto = new ExceptionDTO(exception.getMessage(), ConstructorDefaultUndefinedException.ERROR.getStatusCodeValue());
        return ResponseEntity.status(ConstructorDefaultUndefinedException.ERROR.getHttpStatusCode()).body(dto);
    }

    /**
     * Manipula as exceções específicas de {@link ShelterEntityFailuresException} que
     * podem ocorrer durante a manipulação dos dados da entidade de abrigo. Quando uma dessas exceções
     * é lançada, este método irá capturá-la e retornar uma resposta adequada usando o {@link ExceptionDTO}
     * para transportar as informações de exceção.
     * <p>
     * O método é anotado com {@code @ExceptionHandler}, o que significa que será invocado automaticamente
     * pelo Spring para tratar exceções do tipo {@link ShelterEntityFailuresException} lançadas
     * por qualquer método do respectivo controlador.
     *
     * @param exception A exceção específica do {@link ShelterEntityFailuresException} que ocorreu.
     * @return Uma resposta HTTP representada como {@link ResponseEntity} contendo o DTO de exceção
     * que contém a mensagem da exceção e um código de status HTTP. O status HTTP da resposta será
     * o mesmo que o status HTTP da exceção.
     */
    @ExceptionHandler(ShelterEntityFailuresException.class)
    public ResponseEntity<ExceptionDTO> handleShelterEntityFailures(ShelterEntityFailuresException exception) {
        ExceptionDTO dto = new ExceptionDTO(exception.getMessage(), ShelterEntityFailuresException.ERROR.getStatusCodeValue());
        return ResponseEntity.status(ShelterEntityFailuresException.ERROR.getHttpStatusCode()).body(dto);
    }

    /**
     * Manipula as exceções específicas de {@link AddressEntityFailuresException} que
     * podem ocorrer durante a manipulação dos dados da entidade de endereço. Quando uma dessas exceções
     * é lançada, esse método irá capturá-la e retornar uma resposta adequada usando o {@link ExceptionDTO}
     * para transportar as informações de exceção.
     * <p>
     * O método é anotado com {@code @ExceptionHandler}, o que significa que será invocado automaticamente
     * pelo Spring para tratar exceções do tipo {@link AddressEntityFailuresException} lançadas
     * por qualquer método do respectivo controlador.
     *
     * @param exception A exceção específica do {@link AddressEntityFailuresException} que ocorreu.
     * @return Uma resposta HTTP representada como {@link ResponseEntity} contendo o DTO de exceção
     * que contém a mensagem da exceção e um código de status HTTP.
     * O status HTTP da resposta será o mesmo que o status HTTP da exceção.
     */
    @ExceptionHandler(AddressEntityFailuresException.class)
    public ResponseEntity<ExceptionDTO> handleAddressEntityFailures(AddressEntityFailuresException exception) {
        ExceptionDTO dto = new ExceptionDTO(exception.getMessage(), AddressEntityFailuresException.ERROR.getStatusCodeValue());
        return ResponseEntity.status(AddressEntityFailuresException.ERROR.getHttpStatusCode()).body(dto);
    }

    /**
     * Manipula a exceção {@link MapperFailureException} e retorna uma resposta de erro adequada.
     * <p>
     * Este método é usado para tratar as exceções específicas do {@link MapperFailureException}
     * que podem ocorrer durante a operação de mapeamento de classes. Quando uma dessas exceções é lançada,
     * esse método irá capturá-la e retornar uma resposta adequada usando o {@link ExceptionDTO} para
     * transportar as informações de exceção.
     * <p>
     * O método é anotado com {@code @ExceptionHandler}, o que significa que será invocado automaticamente
     * pelo Spring para tratar exceções do tipo {@link MapperFailureException} lançadas por qualquer
     * método do controlador.
     *
     * @param exception A exceção específica do {@link MapperFailureException} que ocorreu.
     * @return Uma resposta HTTP representada como {@link ResponseEntity} contendo o DTO de exceção que
     * contém a mensagem da exceção e um código de status HTTP. O status HTTP da resposta será
     * o mesmo que o status HTTP da exceção.
     */
    @ExceptionHandler(MapperFailureException.class)
    public ResponseEntity<ExceptionDTO> handleMapperFailures(MapperFailureException exception) {
        ExceptionDTO dto = new ExceptionDTO(exception.getMessage(), MapperFailureException.ERROR.getStatusCodeValue());
        return ResponseEntity.status(MapperFailureException.ERROR.getHttpStatusCode()).body(dto);
    }

    /**
     * Este método é usado para manipular as exceções específicas de {@link UserEntityFailuresException}
     * que podem ocorrer durante a manipulação dos dados do {@link UserEntity}. Quando uma dessas exceções é lançada,
     * esse método irá capturá-la e retornar uma resposta adequada usando o {@link ExceptionDTO}
     * para transportar as informações de exceção.
     * <p>
     * O método é anotado com {@code @ExceptionHandler}, o que significa que será invocado automaticamente
     * pelo Spring para tratar exceções do tipo {@link UserEntityFailuresException} lançadas por qualquer
     * método do controlador.
     *
     * @param exception A exceção específica do {@link UserEntityFailuresException} que ocorreu.
     * @return Uma resposta HTTP representada como ResponseEntity contendo o DTO de exceção que contém a
     * mensagem da exceção e um código de status HTTP. O status HTTP da resposta será o mesmo que
     * o status HTTP da exceção.
     */
    @ExceptionHandler(UserEntityFailuresException.class)
    public ResponseEntity<ExceptionDTO> handleUserEntityFailures(UserEntityFailuresException exception) {
        ExceptionDTO dto = new ExceptionDTO(exception.getMessage(), UserEntityFailuresException.ERROR.getStatusCodeValue());
        return ResponseEntity.status(UserEntityFailuresException.ERROR.getHttpStatusCode()).body(dto);
    }

    /**
     * Manipula exceções lançadas quando há falhas com as entidades de doação.
     *
     * @param exception A instância da {@link DonationEntityFailuresException} lançada durante operações de entidade de doação.
     *                  Este parâmetro já encapsula a mensagem de erro e os detalhes do erro. Normalmente, esta exceção é lançada quando uma operação realizada numa entidade de doação falha.
     * @return Um objeto {@link ResponseEntity} contendo o corpo personalizado da resposta HTTP e o código de status.
     * O corpo desta resposta é um objeto da classe {@link ExceptionDTO} que envolve a mensagem de erro obtida da exceção e o código de status.
     * O código de status da resposta é derivado do erro associado com a instância do DonationEntityFailuresException.
     * @throws DonationEntityFailuresException Se houver um erro durante o processamento de operações sobre a entidade de doação.
     */
    @ExceptionHandler(DonationEntityFailuresException.class)
    public ResponseEntity<ExceptionDTO> handleDonationEntityFailures(DonationEntityFailuresException exception) {
        ExceptionDTO dto = new ExceptionDTO(exception.getMessage(), DonationEntityFailuresException.ERROR.getStatusCodeValue());
        return ResponseEntity.status(DonationEntityFailuresException.ERROR.getHttpStatusCode()).body(dto);
    }

}
