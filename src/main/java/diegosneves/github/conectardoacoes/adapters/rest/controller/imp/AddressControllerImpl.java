package diegosneves.github.conectardoacoes.adapters.rest.controller.imp;

import diegosneves.github.conectardoacoes.adapters.rest.controller.AddressController;
import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressApiResponseDTO;
import diegosneves.github.conectardoacoes.adapters.rest.service.AddressEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controladora REST para gerenciar o endereçamento. Esta classe roteia as requisições
 * HTTP relacionadas a endereço que chegam em "/address" para o serviço correspondente.
 * Implementa a interface {@link AddressController}.
 * <p>
 * Esta classe contém:
 * uma instância de AddressEntityService que é usada para acessar o serviço correspondente à entidade de endereços.
 *
 * @author diegoneves
 * @since 1.2.0
 * @see AddressEntityService
 */
@RestController
@RequestMapping("/address")
public class AddressControllerImpl implements AddressController {

    private final AddressEntityService addressEntityService;

    @Autowired
    public AddressControllerImpl(AddressEntityService addressEntityService) {
        this.addressEntityService = addressEntityService;
    }

    @Override
    public ResponseEntity<AddressApiResponseDTO> retrieveAddress(String zipcode) {
        AddressApiResponseDTO addressApiResponseDTO = this.addressEntityService.restrieveAddress(zipcode);
        return ResponseEntity.ok(addressApiResponseDTO);
    }
}
