package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.AddressEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.ShelterEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.repository.ShelterRepository;
import diegosneves.github.conectardoacoes.adapters.rest.request.ShelterCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.ShelterCreatedResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.AddressEntityService;
import diegosneves.github.conectardoacoes.adapters.rest.service.UserEntityService;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.ShelterContract;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ShelterEntityServiceImplTest {

    public static final String SHELTER_ID = "1229ed8f-76d3-4fbd-a9ed-8f76d3ffbdf5";
    public static final String SHELTER_NAME = "Abrigo";

    public static final String USER_ID = "574371b9-ae17-4f07-8371-b9ae175f0721";
    public static final String USER_NAME = "Fulano";
    public static final String USER_EMAIL = "email@email.com";
    public static final String USER_PASSWORD = "Senha";

    public static final String ADDRESS_ID = "9f3928bc-3985-4035-b928-bc3985f035c9";
    public static final String STREET = "Rua";
    public static final String NUMBER = "298";
    public static final String NEIGHBORHOOD = "Bairro";
    public static final String CITY = "Cidade";
    public static final String STATE = "Estado";
    public static final String ZIP = "98456123";



    @InjectMocks
    private ShelterEntityServiceImpl service;

    @Mock
    private ShelterRepository repository;

    @Mock
    private UserEntityService userEntityService;

    @Mock
    private AddressEntityService addressService;

    @Captor
    private ArgumentCaptor<ShelterContract> shelterCaptor;


    private ShelterCreationRequest request;
    private User user;
    private Shelter shelter;
    private Address address;

    @BeforeEach
    void setUp() {
        AddressDTO addressDTO = AddressDTO.builder()
                .street(STREET)
                .number(NUMBER)
                .neighborhood(NEIGHBORHOOD)
                .city(CITY)
                .state(STATE)
                .zip(ZIP)
                .build();

        this.request = ShelterCreationRequest.builder()
                .shelterName(SHELTER_NAME)
                .address(addressDTO)
                .responsibleUserEmail(USER_EMAIL)
                .build();

        this.address = new Address(ADDRESS_ID, STREET, NUMBER, NEIGHBORHOOD, CITY, STATE, ZIP);
        this.user = new User(USER_ID, USER_NAME, USER_EMAIL, UserProfile.DONOR, USER_PASSWORD);
        this.shelter = new Shelter(SHELTER_ID, SHELTER_NAME, this.address, this.user);
    }

    @Test
    void shouldCreateShelter() {
        when(this.userEntityService.searchUserByEmail(USER_EMAIL)).thenReturn(this.user);
        when(this.addressService.createAndSaveAddressFromDto(any(AddressDTO.class))).thenReturn(this.address);
        when(this.repository.persist(any(ShelterContract.class))).thenReturn(this.shelter);

        ShelterCreatedResponse response = this.service.createShelter(this.request);

        verify(this.userEntityService, times(1)).searchUserByEmail(USER_EMAIL);
        verify(this.addressService, times(1)).createAndSaveAddressFromDto(any(AddressDTO.class));
        verify(this.repository, times(1)).persist(this.shelterCaptor.capture());

        assertNotNull(response);
        assertEquals(SHELTER_ID, response.getId());
        assertEquals(SHELTER_NAME, response.getShelterName());
        assertEquals(STREET, response.getAddress().getStreet());
        assertEquals(NUMBER, response.getAddress().getNumber());
        assertEquals(NEIGHBORHOOD, response.getAddress().getNeighborhood());
        assertEquals(CITY, response.getAddress().getCity());
        assertEquals(STATE, response.getAddress().getState());
        assertEquals(ZIP, response.getAddress().getZip());
        assertEquals(USER_ID, response.getResponsibleUser().getId());
        assertEquals(USER_NAME, response.getResponsibleUser().getUserName());
        assertEquals(USER_EMAIL, response.getResponsibleUser().getEmail());
        assertNotNull(response.getResponsibleUser().getUserProfile());
        assertEquals(UserProfileType.DONOR, response.getResponsibleUser().getUserProfile());
        assertNotNull(this.shelterCaptor.getValue());
        assertTrue(UuidUtils.isValidUUID(this.shelterCaptor.getValue().getId()));
        assertEquals(SHELTER_NAME, this.shelterCaptor.getValue().getShelterName());
        assertNotNull(this.shelterCaptor.getValue().getDonations());
        assertTrue(this.shelterCaptor.getValue().getDonations().isEmpty());
        assertNotNull(this.shelterCaptor.getValue().getAddress());
        assertEquals(STREET, this.shelterCaptor.getValue().getAddress().getStreet());
        assertEquals(NUMBER, this.shelterCaptor.getValue().getAddress().getNumber());
        assertEquals(NEIGHBORHOOD, this.shelterCaptor.getValue().getAddress().getNeighborhood());
        assertEquals(CITY, this.shelterCaptor.getValue().getAddress().getCity());
        assertEquals(STATE, this.shelterCaptor.getValue().getAddress().getState());
        assertEquals(ZIP, this.shelterCaptor.getValue().getAddress().getZip());
        assertNotNull(this.shelterCaptor.getValue().getUser());
        assertTrue(UuidUtils.isValidUUID(this.shelterCaptor.getValue().getUser().getId()));
        assertEquals(USER_NAME, this.shelterCaptor.getValue().getUser().getUsername());
        assertEquals(USER_EMAIL, this.shelterCaptor.getValue().getUser().getEmail());
        assertEquals(UserProfile.DONOR, this.shelterCaptor.getValue().getUser().getUserProfile());
        assertEquals(USER_PASSWORD, this.shelterCaptor.getValue().getUser().getUserPassword());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void shouldThrowExceptionWhenShelterNameIsBlank(String shelterName) {
        this.request.setShelterName(shelterName);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.SHELTER_CREATION_ERROR_MESSAGE), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(ShelterCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenShelterNameIsNull() {
        this.request.setShelterName(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.SHELTER_CREATION_ERROR_MESSAGE), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(ShelterCreationFailureException.class, exception.getCause().getClass());
    }


    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void shouldThrowExceptionWhenResponsibleUserEmailIsBlank(String value) {
        this.request.setResponsibleUserEmail(value);
        when(this.userEntityService.searchUserByEmail(anyString())).thenThrow(UserEntityFailuresException.class);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.USER_RESPONSIBLE_EMAIL_NOT_FOUND_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(UserEntityFailuresException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenResponsibleUserEmailIsNull() {
        this.request.setResponsibleUserEmail(null);
        when(this.userEntityService.searchUserByEmail(null)).thenThrow(UserEntityFailuresException.class);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.USER_RESPONSIBLE_EMAIL_NOT_FOUND_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(UserEntityFailuresException.class, exception.getCause().getClass());
    }




    @Test
    void mapShelterAndSaveToRepositoryThrowsExceptionWhenAddressIsNull() {
        this.request.setAddress(null);

        when(this.userEntityService.searchUserByEmail(anyString())).thenReturn(this.user);
        when(this.addressService.createAndSaveAddressFromDto(null)).thenThrow(AddressEntityFailuresException.class);
        when(this.repository.persist(any(ShelterContract.class))).thenReturn(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.addressService, times(1)).createAndSaveAddressFromDto(null);
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.SHELTER_CREATION_ERROR_MESSAGE), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressEntityFailuresException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowShelterEntityFailuresExceptionWhenCreatingShelterWithNullArgument() {

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(null));

        verify(this.addressService, never()).createAndSaveAddressFromDto(any(AddressDTO.class));
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.REQUEST_VALIDATION_ERROR_MESSAGE), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void mapShelterEntityFromShelterContractThrowsExceptionWhenShelterContractIsNull() {
        when(this.userEntityService.searchUserByEmail(USER_EMAIL)).thenReturn(this.user);
        when(this.addressService.createAndSaveAddressFromDto(any(AddressDTO.class))).thenReturn(this.address);
        when(this.repository.persist(any(ShelterContract.class))).thenReturn(null);

        try (MockedStatic<BuilderMapper> mockedBuilder = mockStatic(BuilderMapper.class)) {

            mockedBuilder.when(() -> BuilderMapper.mapTo(any(ShelterEntityMapper.class), eq(null))).thenThrow(IllegalArgumentException.class);

            ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

            verify(this.addressService, times(1)).createAndSaveAddressFromDto(any(AddressDTO.class));
            verify(this.repository, times(1)).persist(any(ShelterContract.class));

            assertNotNull(exception);
            assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.SHELTER_CREATION_ERROR_MESSAGE), exception.getMessage());
            assertNotNull(exception.getCause());
            assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
        }
    }

}
