package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.AddressEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.AddressEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.AddressRepository;
import diegosneves.github.conectardoacoes.adapters.rest.repository.DonationRepository;
import diegosneves.github.conectardoacoes.adapters.rest.repository.ShelterRepository;
import diegosneves.github.conectardoacoes.adapters.rest.request.ShelterCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.ShelterCreatedResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.UserEntityService;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.ShelterContract;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.AddressCreationFailureException;
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

    public static final String DONATION_ID = "bf9b8d38-c6b3-4fd6-9b8d-38c6b3bfd69f";
    public static final String DONATION_DESCRIPTION = "Descrição";
    public static final int AMOUNT = 1;

    public static final String ENTITY_ID = "b7a89acb-c03f-4f87-a89a-cbc03fef8755";


    @InjectMocks
    private ShelterEntityServiceImpl service;

    @Mock
    private ShelterRepository repository;

    @Mock
    private UserEntityService userEntityService;

    @Mock
    private DonationRepository donationRepository;

    @Mock
    private AddressRepository addressRepository;

    @Captor
    private ArgumentCaptor<ShelterContract> shelterCaptor;

    @Captor
    private ArgumentCaptor<AddressEntity> addressCaptor;

    private ShelterCreationRequest request;
    private AddressDTO addressDTO;
    private User user;
    private Shelter shelter;
    private Address address;

    @BeforeEach
    void setUp() {
        this.addressDTO = AddressDTO.builder()
                .street(STREET)
                .number(NUMBER)
                .neighborhood(NEIGHBORHOOD)
                .city(CITY)
                .state(STATE)
                .zip(ZIP)
                .build();

        this.request = ShelterCreationRequest.builder()
                .shelterName(SHELTER_NAME)
                .address(this.addressDTO)
                .responsibleUserEmail(USER_EMAIL)
                .build();

        this.address = new Address(ADDRESS_ID, STREET, NUMBER, NEIGHBORHOOD, CITY, STATE, ZIP);
        this.user = new User(USER_ID, USER_NAME, USER_EMAIL, UserProfile.DONOR, USER_PASSWORD);
        this.shelter = new Shelter(SHELTER_ID, SHELTER_NAME, this.address, this.user);
    }

    @Test
    void shouldCreateShelter() {
        AddressEntity addressEntity = new AddressEntityMapper().mapFrom(this.address);
        when(this.userEntityService.searchUserByEmail(USER_EMAIL)).thenReturn(this.user);
        when(this.addressRepository.save(any(AddressEntity.class))).thenReturn(addressEntity);
        when(this.repository.persist(any(ShelterContract.class))).thenReturn(this.shelter);

        ShelterCreatedResponse response = this.service.createShelter(this.request);

        verify(this.userEntityService, times(1)).searchUserByEmail(USER_EMAIL);
        verify(this.addressRepository, times(1)).save(addressCaptor.capture());
        verify(this.repository, times(1)).persist(shelterCaptor.capture());
        verify(this.donationRepository, never()).save(any(DonationEntity.class));

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
        assertNotNull(shelterCaptor.getValue());
        assertTrue(UuidUtils.isValidUUID(shelterCaptor.getValue().getId()));
        assertEquals(SHELTER_NAME, shelterCaptor.getValue().getShelterName());
        assertNotNull(shelterCaptor.getValue().getDonations());
        assertTrue(shelterCaptor.getValue().getDonations().isEmpty());
        assertNotNull(shelterCaptor.getValue().getAddress());
        assertNotNull(addressCaptor.getValue());
        assertTrue(UuidUtils.isValidUUID(addressCaptor.getValue().getId()));
        assertEquals(STREET, shelterCaptor.getValue().getAddress().getStreet());
        assertEquals(NUMBER, shelterCaptor.getValue().getAddress().getNumber());
        assertEquals(NEIGHBORHOOD, shelterCaptor.getValue().getAddress().getNeighborhood());
        assertEquals(CITY, shelterCaptor.getValue().getAddress().getCity());
        assertEquals(STATE, shelterCaptor.getValue().getAddress().getState());
        assertEquals(ZIP, shelterCaptor.getValue().getAddress().getZip());
        assertNotNull(shelterCaptor.getValue().getUser());
        assertTrue(UuidUtils.isValidUUID(shelterCaptor.getValue().getUser().getId()));
        assertEquals(USER_NAME, shelterCaptor.getValue().getUser().getUsername());
        assertEquals(USER_EMAIL, shelterCaptor.getValue().getUser().getEmail());
        assertEquals(UserProfile.DONOR, shelterCaptor.getValue().getUser().getUserProfile());
        assertEquals(USER_PASSWORD, shelterCaptor.getValue().getUser().getUserPassword());
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

    @Test
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoIsNullInCreateShelter() {
        this.request.setAddress(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoStreetNameIsNullInCreateShelter() {
        this.addressDTO.setStreet(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoStreetNameIsBlankInCreateShelter(String addressStreet) {
        this.addressDTO.setStreet(addressStreet);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoNumberIsNullInCreateShelter() {
        this.addressDTO.setNumber(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoNumberIsBlankInCreateShelter(String value) {
        this.addressDTO.setNumber(value);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoNeighborhoodIsNullInCreateShelter() {
        this.addressDTO.setNeighborhood(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoNeighborhoodIsBlankInCreateShelter(String value) {
        this.addressDTO.setNeighborhood(value);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoCityIsNullInCreateShelter() {
        this.addressDTO.setCity(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoCityIsBlankInCreateShelter(String value) {
        this.addressDTO.setCity(value);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoStateIsNullInCreateShelter() {
        this.addressDTO.setState(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoStateIsBlankInCreateShelter(String value) {
        this.addressDTO.setState(value);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoZipCodeIsNullInCreateShelter() {
        this.addressDTO.setZip(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    void shouldThrowShelterEntityFailuresExceptionWhenAddressDtoZipCodeIsBlankInCreateShelter(String value) {
        this.addressDTO.setZip(value);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.addressRepository, never()).save(any(AddressEntity.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ADDRESS_CREATION_ERROR), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressCreationFailureException.class, exception.getCause().getClass());
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
    void mapAddressAndSaveToRepositoryThrowsExceptionWhenAddressIsNull() {

        try (MockedStatic<BuilderMapper> mockedBuilder = mockStatic(BuilderMapper.class)) {

            mockedBuilder.when(() -> BuilderMapper.mapTo(any(AddressEntityMapper.class), any(Address.class))).thenThrow(IllegalArgumentException.class);

            ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

            verify(this.addressRepository, never()).save(any(AddressEntity.class));
            verify(this.repository, never()).persist(any(ShelterContract.class));

            assertNotNull(exception);
            assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.ERROR_MAPPING_ADDRESS), exception.getMessage());
            assertNotNull(exception.getCause());
            assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
        }
    }

    @Test
    void mapShelterAndSaveToRepositoryThrowsExceptionWhenAddressIsNull() {

        when(this.userEntityService.searchUserByEmail(anyString())).thenReturn(this.user);
        when(this.repository.persist(any(ShelterContract.class))).thenReturn(null);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.addressRepository, times(1)).save(any(AddressEntity.class));
        verify(this.repository, times(1)).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterEntityServiceImpl.SHELTER_CREATION_ERROR_MESSAGE), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
    }

}
