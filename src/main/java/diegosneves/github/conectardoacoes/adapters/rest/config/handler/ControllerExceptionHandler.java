package diegosneves.github.conectardoacoes.adapters.rest.config.handler;

import diegosneves.github.conectardoacoes.adapters.rest.dto.ExceptionDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.CustomException;
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
     * Trata as exceções do tipo {@link CustomException}. Este método atua como um tratador de exceptions
     * para a {@link CustomException} usada na camada de serviço. Quando uma {@link CustomException} é lançada,
     * este método captura a exceção e a processa criando uma {@link ExceptionDTO}, e um
     * {@link ResponseEntity} com os detalhes de erro da exceção.
     *
     * @param exception a instância de CustomException que foi lançada e precisa ser tratada. Este
     *                  parâmetro carrega detalhes essenciais sobre o que deu errado
     *                  durante a execução do programa para o cliente.
     * @return um objeto ResponseEntity contendo o código de status e corpo que deve ser
     * enviado como resposta após capturar uma {@link CustomException}. O corpo da resposta
     * inclui uma mensagem e o valor do código de status da CustomException
     * enquanto o código de status da resposta é configurado como o código de status HTTP
     * dos detalhes do erro da exceção.
     * @throws CustomException processado por este método. Geralmente contém
     *                         informações necessárias para o cliente ou os desenvolvedores identificarem o que deu errado durante a execução.
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionDTO> handleDonationEntityFailures(CustomException exception) {
        ExceptionDTO dto = new ExceptionDTO(exception.getMessage(), exception.getErrorDetails().getStatusCodeValue());
        return ResponseEntity.status(exception.getErrorDetails().getHttpStatusCode()).body(dto);
    }

}
