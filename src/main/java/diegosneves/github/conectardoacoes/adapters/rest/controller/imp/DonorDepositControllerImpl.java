package diegosneves.github.conectardoacoes.adapters.rest.controller.imp;

import diegosneves.github.conectardoacoes.adapters.rest.controller.DonorDepositController;
import diegosneves.github.conectardoacoes.adapters.rest.request.DepositDonationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.RegisteredDepositResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.DonorDepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST responsável pelo gerenciamento dos depósitos de doações.
 * Esta classe implementa a interface DonorDepositController e expõe endpoints REST
 * para operações relacionadas aos depósitos de doações.
 * <p>
 * A classe utiliza o serviço DonorDepositService para realizar as operações de negócio.
 *
 * <p>Endpoints expostos:</p>
 * <ul>
 *   <li>POST /deposit - Registra uma nova doação de depósito.</li>
 * </ul>
 *
 * @author diegoneves
 * @since 1.3.0
 * @see DonorDepositService
 */
@RestController
@RequestMapping("/deposit")
public class DonorDepositControllerImpl implements DonorDepositController {

    private final DonorDepositService service;

    public DonorDepositControllerImpl(DonorDepositService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<RegisteredDepositResponse> registeredDonation(DepositDonationRequest request) {
        RegisteredDepositResponse response = this.service.registerDonation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
