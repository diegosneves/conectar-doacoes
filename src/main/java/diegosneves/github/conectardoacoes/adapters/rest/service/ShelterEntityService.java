package diegosneves.github.conectardoacoes.adapters.rest.service;

import diegosneves.github.conectardoacoes.adapters.rest.request.ShelterCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.ShelterCreatedResponse;

public interface ShelterEntityService {

    ShelterCreatedResponse createShelter(ShelterCreationRequest request);

}
