package diegosneves.github.conectardoacoes.core.domain.shelter.factory;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.user.entity.UserContract;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;

public class ShelterFactory {

    private ShelterFactory() {}

    public static Shelter create(String shelterName, Address address, UserContract responsibleUser) {
        return new Shelter(UuidUtils.generateUuid(), shelterName, address, responsibleUser);
    }

}
