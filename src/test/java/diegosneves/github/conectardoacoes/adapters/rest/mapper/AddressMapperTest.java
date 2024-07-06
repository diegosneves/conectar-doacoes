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
class AddressMapperTest {

    public static final String ADDRESS_ID = "5e4c5ca9-46d2-42c3-8c5c-a946d242c3dd";
    public static final String ADDRESS_STREET = "Rua";
    public static final String ADDRESS_NUMBER = "123";
    public static final String ADDRESS_NEIGHBORHOOD = "Bairro";
    public static final String ADDRESS_CITY = "Cidade";
    public static final String ADDRESS_STATE = "Estado";
    public static final String ZIP_CODE = "91234567";

    private AddressMapper addressMapper;

    private AddressEntity addressEntity;

    @BeforeEach
    void setUp() {
        this.addressMapper = new AddressMapper();
        this.addressEntity = AddressEntity.builder()
                .id(ADDRESS_ID)
                .street(ADDRESS_STREET)
                .number(ADDRESS_NUMBER)
                .neighborhood(ADDRESS_NEIGHBORHOOD)
                .city(ADDRESS_CITY)
                .state(ADDRESS_STATE)
                .zip(ZIP_CODE)
                .build();
    }

    @Test
    void shouldCorrectlyMapAddressEntityToAddressModel() {
        Address address = this.addressMapper.mapFrom(this.addressEntity);

        assertNotNull(address);
        assertEquals(ADDRESS_STREET, address.getStreet());
        assertEquals(ADDRESS_NUMBER, address.getNumber());
        assertEquals(ADDRESS_NEIGHBORHOOD, address.getNeighborhood());
        assertEquals(ADDRESS_CITY, address.getCity());
        assertEquals(ADDRESS_STATE, address.getState());
        assertEquals(ZIP_CODE, address.getZip());
    }

    @Test
    void shouldThrowExceptionWhenMappingNullAddressEntity() {
        this.addressEntity = null;

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMappingNullAddressStreet() {
        this.addressEntity.setStreet(null);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMappingEmptyAddressStreet() {
        this.addressEntity.setStreet("   ");

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMappingNullAddressNumber() {
        this.addressEntity.setNumber(null);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMappingEmptyAddressNumber() {
        this.addressEntity.setNumber("");

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMappingNullAddressNeighborhood() {
        this.addressEntity.setNeighborhood(null);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMappingEmptyAddressNeighborhood() {
        this.addressEntity.setNeighborhood("");

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMappingNullAddressCity() {
        this.addressEntity.setCity(null);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMappingEmptyAddressCity() {
        this.addressEntity.setCity("");

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMappingNullAddressState() {
        this.addressEntity.setState(null);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMappingEmptyAddressState() {
        this.addressEntity.setState("");

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMappingNullAddressZipCode() {
        this.addressEntity.setZip(null);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMappingEmptyAddressZipCode() {
        this.addressEntity.setZip("");

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.addressMapper.mapFrom(this.addressEntity));

        assertNotNull(exception);
        assertEquals(AddressEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(AddressMapper.ADDRESS_ENTITY_TYPE.getSimpleName())), exception.getMessage());
    }

}
