package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.adapter.RetrieveAddressAdapter;
import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressApiResponseDTO;
import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.exception.AddressEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ExternalApiFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.AddressEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.AddressEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.AddressRepository;
import diegosneves.github.conectardoacoes.adapters.rest.response.AddressApiResponse;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.exception.AddressCreationFailureException;
import diegosneves.github.conectardoacoes.core.utils.UuidUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AddressEntityServiceImplTest {

    public static final String STREET = "Rua";
    public static final String NUMBER = "298";
    public static final String NEIGHBORHOOD = "Bairro";
    public static final String CITY = "Cidade";
    public static final String STATE = "Estado";
    public static final String ZIP = "98456123";

    @InjectMocks
    private AddressEntityServiceImpl service;

    @Mock
    private AddressRepository repository;

    @Mock
    private RetrieveAddressAdapter addressAdapter;

    @Captor
    private ArgumentCaptor<AddressEntity> addressCaptor;

    private AddressDTO addressDTO;
    private AddressApiResponse addressApiResponse;

    @BeforeEach
    void setUp() {
        this.addressApiResponse = AddressApiResponse.builder()
                .street(STREET)
                .neighborhood(NEIGHBORHOOD)
                .city(CITY)
                .state(STATE)
                .zip(ZIP)
                .build();

        this.addressDTO = AddressDTO.builder()
                .street(STREET)
                .number(NUMBER)
                .neighborhood(NEIGHBORHOOD)
                .city(CITY)
                .state(STATE)
                .zip(ZIP)
                .build();
    }

    @Test
    void shouldSaveAddressEntityAndReturnAddress() {

        Address address = this.service.createAndSaveAddressFromDto(this.addressDTO);

        verify(this.repository, times(1)).save(this.addressCaptor.capture());

        assertNotNull(address);
        AddressEntity addressEntity = this.addressCaptor.getValue();
        assertNotNull(addressEntity);
        assertTrue(UuidUtils.isValidUUID(addressEntity.getId()));
        assertTrue(UuidUtils.isValidUUID(address.getId()));
        assertEquals(STREET, addressEntity.getStreet());
        assertEquals(NUMBER, addressEntity.getNumber());
        assertEquals(NEIGHBORHOOD, addressEntity.getNeighborhood());
        assertEquals(CITY, addressEntity.getCity());
        assertEquals(STATE, addressEntity.getState());
        assertEquals(ZIP, addressEntity.getZip());
        assertEquals(addressEntity.getId(), address.getId());
        assertEquals(address.getStreet(), addressEntity.getStreet());
        assertEquals(address.getNumber(), addressEntity.getNumber());
        assertEquals(address.getNeighborhood(), addressEntity.getNeighborhood());
        assertEquals(address.getCity(), addressEntity.getCity());
        assertEquals(address.getState(), addressEntity.getState());
        assertEquals(address.getZip(), addressEntity.getZip());
    }

    @Test
    void mapAddressAndSaveToRepositoryThrowsExceptionWhenAddressIsNull() {

        try (MockedStatic<BuilderMapper> mockedBuilder = mockStatic(BuilderMapper.class)) {

            mockedBuilder.when(() -> BuilderMapper.mapTo(any(AddressEntityMapper.class), any(Address.class))).thenThrow(IllegalArgumentException.class);

            AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

            verify(this.repository, never()).save(any(AddressEntity.class));

            assertNotNull(exception);
            assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ERROR_MAPPING_ADDRESS).formatErrorMessage(), exception.getMessage());
            assertNotNull(exception.getCause());
            assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
        }
    }


    @Test
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoIsNull() {

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(null));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoStreetNameIsNull() {
        this.addressDTO.setStreet(null);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoStreetNameIsBlank(String addressStreet) {
        this.addressDTO.setStreet(addressStreet);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoNumberIsNull() {
        this.addressDTO.setNumber(null);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoNumberIsBlank(String value) {
        this.addressDTO.setNumber(value);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoNeighborhoodIsNull() {
        this.addressDTO.setNeighborhood(null);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoNeighborhoodIsBlank(String value) {
        this.addressDTO.setNeighborhood(value);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoCityIsNull() {
        this.addressDTO.setCity(null);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoCityIsBlank(String value) {
        this.addressDTO.setCity(value);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoStateIsNullInCreateShelter() {
        this.addressDTO.setState(null);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoStateIsBlankInCreateShelter(String value) {
        this.addressDTO.setState(value);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoZipCodeIsNull() {
        this.addressDTO.setZip(null);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowAddressEntityFailuresExceptionWhenAddressDtoZipCodeIsBlank(String value) {
        this.addressDTO.setZip(value);

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.createAndSaveAddressFromDto(this.addressDTO));

        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ADDRESS_CREATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldRetrieveAddressFromAPIAndNotStoreInRepository(){
        when(this.addressAdapter.retrieveAddress(ZIP)).thenReturn(this.addressApiResponse);

        AddressApiResponseDTO addressApiResponseDTO = this.service.restrieveAddress(ZIP);

        verify(this.addressAdapter, times(1)).retrieveAddress(ZIP);
        verify(this.repository, never()).save(any(AddressEntity.class));

        assertNotNull(addressApiResponseDTO);
        assertEquals(STREET, addressApiResponseDTO.getStreet());
        assertEquals(NEIGHBORHOOD, addressApiResponseDTO.getNeighborhood());
        assertEquals(CITY, addressApiResponseDTO.getCity());
        assertEquals(STATE, addressApiResponseDTO.getState());
        assertEquals(ZIP, addressApiResponseDTO.getZip());

    }

    @Test
    void shouldThrowExceptionWhenRetrievingAddressWithNullZipcode() {

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.restrieveAddress(null));

        verify(this.addressAdapter, never()).retrieveAddress(ZIP);

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ZIPCODE_INVALID_FAILURE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void shouldThrowExceptionWhenRetrievingAddressWithEmptyOrWhitespaceZipcode(String value) {

        AddressEntityFailuresException exception = assertThrows(AddressEntityFailuresException.class, () -> this.service.restrieveAddress(value));

        verify(this.addressAdapter, never()).retrieveAddress(ZIP);

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(AddressEntityServiceImpl.ZIPCODE_INVALID_FAILURE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenRetrievingAddressWithInvalidZipcode() {
        String invalidZipcode = "invalid";
        int term = 12;
        when(this.addressAdapter.retrieveAddress(invalidZipcode)).thenThrow(new ExternalApiFailureException(term, invalidZipcode, new Exception()));

        ExternalApiFailureException exception = assertThrows(ExternalApiFailureException.class, () -> this.service.restrieveAddress(invalidZipcode));

        verify(this.addressAdapter, never()).retrieveAddress(ZIP);

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(term).formatErrorMessage(invalidZipcode), exception.getMessage());
        assertNotNull(exception.getCause());
    }

}
