package diegosneves.github.conectardoacoes.adapters.rest.controller.imp;


import diegosneves.github.conectardoacoes.adapters.rest.controller.ShelterController;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.request.ReceiveDonationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.request.ShelterCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.ReceiveDonationResponse;
import diegosneves.github.conectardoacoes.adapters.rest.response.ShelterCreatedResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.ShelterEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A classe {@link ShelterControllerImpl} implementa a interface {@link ShelterController} e serve como
 * o ponto de entrada da API para o gerenciamento dos abrigos.
 * <p/>
 * Esta classe é decorada com as anotações {@code @RestController} e {@code @RequestMapping("/shelter")},
 * indicando que é um controlador REST e que irá responder a requisições feitas para /shelter.
 *
 * @author diegoneves
 * @see ShelterController
 * @see ShelterEntityService
 * @see ShelterCreationRequest
 * @see ShelterCreatedResponse
 * @see ShelterEntityFailuresException
 * @since 1.0.0
 */
@RestController
@RequestMapping("/shelter")
public class ShelterControllerImpl implements ShelterController {

    private final ShelterEntityService shelterEntityService;

    public ShelterControllerImpl(ShelterEntityService shelterEntityService) {
        this.shelterEntityService = shelterEntityService;
    }

    @Override
    public ResponseEntity<ShelterCreatedResponse> createShelter(ShelterCreationRequest request) {
        ShelterCreatedResponse response = this.shelterEntityService.createShelter(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<ReceiveDonationResponse> receiveDonation(ReceiveDonationRequest request) {
        ReceiveDonationResponse response = this.shelterEntityService.receiveDonation(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
