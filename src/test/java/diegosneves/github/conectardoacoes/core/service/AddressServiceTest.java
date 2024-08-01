package diegosneves.github.conectardoacoes.core.service;

import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class AddressServiceTest {

    public static final String STREET = "Rua";
    public static final String NUMBER = "54";
    public static final String NEIGHBORHOOD = "Bairro";
    public static final String CITY = "Canoas";
    public static final String STATE_ABBREVIATION = "RS";
    public static final String ZIPCODE = "95000000";

    @InjectMocks
    private AddressService addressService;


    @Test
    void shouldCreateAddress() {
        Address address = this.addressService.createAddress(STREET, NUMBER, NEIGHBORHOOD, CITY, STATE_ABBREVIATION, ZIPCODE);

        assertNotNull(address);
        assertNotNull(address.getId());
        assertTrue(UuidUtils.isValidUUID(address.getId()));
        assertEquals(STREET, address.getStreet());
        assertEquals(NUMBER, address.getNumber());
        assertEquals(NEIGHBORHOOD, address.getNeighborhood());
        assertEquals(CITY, address.getCity());
        assertEquals(STATE_ABBREVIATION, address.getState());
        assertEquals(ZIPCODE, address.getZip());
    }

}
