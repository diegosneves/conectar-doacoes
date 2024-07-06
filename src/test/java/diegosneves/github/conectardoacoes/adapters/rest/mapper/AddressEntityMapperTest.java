package diegosneves.github.conectardoacoes.adapters.rest.mapper;

import diegosneves.github.conectardoacoes.adapters.rest.exception.AddressEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.model.AddressEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddressEntityMapperTest {

    public static final String ADDRESS_ID = "4cdc1890-6c94-4d8d-9c18-906c944d8da9";
    public static final String STREET = "Rua";
    public static final String NUMBER = "123";
    public static final String NEIGHBORHOOD = "Bairro";
    public static final String CITY = "Cidade";
    public static final String STATE = "Estado";
    public static final String ZIP = "91234567";


    private AddressEntityMapper addressEntityMapper;

    private Address address;

    @BeforeEach
    void setUp() {
        this.addressEntityMapper = new AddressEntityMapper();
        this.address = new Address(ADDRESS_ID, STREET, NUMBER, NEIGHBORHOOD, CITY, STATE, ZIP);
    }

    @Test
    void shouldMapAddressEntityToAddress() {
        AddressEntity addressEntity = this.addressEntityMapper.mapFrom(this.address);

        assertNotNull(addressEntity);
        assertEquals(ADDRESS_ID, addressEntity.getId());
        assertEquals(STREET, addressEntity.getStreet());
        assertEquals(NUMBER, addressEntity.getNumber());
        assertEquals(NEIGHBORHOOD, addressEntity.getNeighborhood());
        assertEquals(CITY, addressEntity.getCity());
        assertEquals(STATE, addressEntity.getState());
        assertEquals(ZIP, addressEntity.getZip());
    }

    @Test
    void shouldThrowExceptionOnMapFromNullAddress() {
        this.address = null;

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressEntityMapper.mapFrom(this.address));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressEntityMapper.ADDRESS_CLASS.getSimpleName())), exception.getMessage());
    }

}
