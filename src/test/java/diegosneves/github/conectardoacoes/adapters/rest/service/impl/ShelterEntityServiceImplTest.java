package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.AddressDTO;
import diegosneves.github.conectardoacoes.adapters.rest.dto.DonationDTO;
import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.AddressEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.UserEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.BuilderMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.ShelterEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.ShelterRepository;
import diegosneves.github.conectardoacoes.adapters.rest.request.ReceiveDonationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.request.ShelterCreationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.ReceiveDonationResponse;
import diegosneves.github.conectardoacoes.adapters.rest.response.ShelterCreatedResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.AddressEntityService;
import diegosneves.github.conectardoacoes.adapters.rest.service.DonationEntityService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static final String DONATION_ID = "b6d38f1a-22a2-4d49-938f-1a22a22d4966";
    public static final String DESCRIPTION = "Mochila";
    public static final int AMOUNT = 2;


    @InjectMocks
    private ShelterEntityServiceImpl service;

    @Mock
    private ShelterRepository repository;

    @Mock
    private UserEntityService userEntityService;

    @Mock
    private AddressEntityService addressService;

    @Mock
    private DonationEntityService donationEntityService;

    @Captor
    private ArgumentCaptor<ShelterContract> shelterCaptor;


    private ShelterCreationRequest request;
    private User user;
    private Shelter shelter;
    private Address address;
    private DonationEntity donation;

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

        this.donation = new DonationEntity(DONATION_ID, DESCRIPTION, AMOUNT);
        this.address = new Address(ADDRESS_ID, STREET, NUMBER, NEIGHBORHOOD, CITY, STATE, ZIP);
        this.user = new User(USER_ID, USER_NAME, USER_EMAIL, UserProfile.BENEFICIARY, USER_PASSWORD);
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
        assertEquals(UserProfileType.BENEFICIARY, response.getResponsibleUser().getUserProfile());
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
        assertEquals(UserProfile.BENEFICIARY, this.shelterCaptor.getValue().getUser().getUserProfile());
        assertEquals(USER_PASSWORD, this.shelterCaptor.getValue().getUser().getUserPassword());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void shouldThrowExceptionWhenShelterNameIsBlank(String shelterName) {
        this.request.setShelterName(shelterName);
        when(this.userEntityService.searchUserByEmail(USER_EMAIL)).thenReturn(this.user);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.userEntityService, times(1)).searchUserByEmail(USER_EMAIL);

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.SHELTER_CREATION_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(ShelterCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowExceptionWhenShelterNameIsNull() {
        this.request.setShelterName(null);
        when(this.userEntityService.searchUserByEmail(USER_EMAIL)).thenReturn(this.user);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.userEntityService, times(1)).searchUserByEmail(USER_EMAIL);

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.SHELTER_CREATION_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
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
        assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.USER_RESPONSIBLE_EMAIL_NOT_FOUND_ERROR).formatErrorMessage(), exception.getMessage());
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
        assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.USER_RESPONSIBLE_EMAIL_NOT_FOUND_ERROR).formatErrorMessage(), exception.getMessage());
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
        assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.SHELTER_CREATION_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(AddressEntityFailuresException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowShelterEntityFailuresExceptionWhenCreatingShelterWithNullArgument() {

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(null));

        verify(this.addressService, never()).createAndSaveAddressFromDto(any(AddressDTO.class));
        verify(this.repository, never()).persist(any(ShelterContract.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.REQUEST_VALIDATION_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
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
            assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.SHELTER_CREATION_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
            assertNotNull(exception.getCause());
            assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
        }
    }

    @Test
    void shouldThrowExceptionWhenCreatingShelterWithDonorProfileUser() {
        this.user = new User(USER_ID, USER_NAME, USER_EMAIL, UserProfile.DONOR, USER_PASSWORD);
        when(this.userEntityService.searchUserByEmail(USER_EMAIL)).thenReturn(this.user);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.addressService, never()).createAndSaveAddressFromDto(any(AddressDTO.class));
        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.userEntityService, times(1)).searchUserByEmail(USER_EMAIL);

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.RESPONSIBLE_USER_PROFILE_INVALID).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenCreatingShelterWithSameResponsibleEmail() {

        when(this.userEntityService.searchUserByEmail(USER_EMAIL)).thenReturn(this.user);
        when(this.repository.findShelterEntitiesByResponsibleUser_Email(USER_EMAIL)).thenReturn(Optional.of(new ShelterEntity()));

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.createShelter(this.request));

        verify(this.addressService, never()).createAndSaveAddressFromDto(any(AddressDTO.class));
        verify(this.repository, never()).persist(any(ShelterContract.class));
        verify(this.userEntityService, times(1)).searchUserByEmail(USER_EMAIL);
        verify(this.repository, times(1)).findShelterEntitiesByResponsibleUser_Email(USER_EMAIL);

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.RESPONSIBLE_USER_ALREADY_IN_USE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldReceiveDonationForSpecificShelter() {
        ShelterEntity shelterEntity = generateShelterEntity();
        ReceiveDonationRequest donationRequest = generateReceiveDonationRequest();

        when(this.donationEntityService.convertAndSaveDonationDTO(any(DonationDTO.class))).thenReturn(this.donation);
        when(this.repository.findShelterEntitiesByResponsibleUser_Email(USER_EMAIL)).thenReturn(Optional.of(shelterEntity));
        when(this.repository.save(any(ShelterEntity.class))).thenReturn(shelterEntity);

        ReceiveDonationResponse response = this.service.receiveDonation(donationRequest);

        verify(this.donationEntityService, times(1)).convertAndSaveDonationDTO(any(DonationDTO.class));
        verify(this.repository, times(1)).findShelterEntitiesByResponsibleUser_Email(USER_EMAIL);
        verify(this.repository, times(1)).save(any(ShelterEntity.class));

        assertNotNull(response);
        assertEquals(SHELTER_NAME, response.getShelterName());
        assertEquals(USER_NAME, response.getResponsibleName());
        assertEquals(USER_EMAIL, response.getResponsibleEmail());
        assertNotNull(response.getDonationDTOS());
        assertEquals(1, response.getDonationDTOS().size());
        assertEquals(DESCRIPTION, response.getDonationDTOS().get(0).getDescription());
        assertEquals(AMOUNT, response.getDonationDTOS().get(0).getAmount());
    }


    private static UserEntity generateUserEntity() {
        return UserEntity.builder()
                .userName(USER_NAME)
                .email(USER_EMAIL)
                .userProfile(UserProfileType.BENEFICIARY)
                .build();
    }

    private static ShelterEntity generateShelterEntity() {
        return ShelterEntity.builder()
                .shelterName(SHELTER_NAME)
                .responsibleUser(generateUserEntity())
                .donations(new ArrayList<>())
                .build();
    }

    private static DonationDTO generateDonationDTO() {
        return new DonationDTO(DESCRIPTION, AMOUNT);
    }

    private static ReceiveDonationRequest generateReceiveDonationRequest() {
        return ReceiveDonationRequest.builder()
                .responsibleEmail(USER_EMAIL)
                .donationDTOS(List.of(generateDonationDTO()))
                .build();
    }

    @Test
    void shouldThrowExceptionWhenReceiveDonationRequestIsNull() {

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.receiveDonation(null));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.DONATION_VALIDATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenReceiveDonationDTOListIsNull() {
        ShelterEntity shelterEntity = generateShelterEntity();
        ReceiveDonationRequest donationRequest = generateReceiveDonationRequest();
        donationRequest.setDonationDTOS(null);

        when(this.repository.findShelterEntitiesByResponsibleUser_Email(USER_EMAIL)).thenReturn(Optional.of(shelterEntity));

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.receiveDonation(donationRequest));

        verify(this.repository, never()).save(any(ShelterEntity.class));
        verify(this.donationEntityService, never()).convertAndSaveDonationDTO(any(DonationDTO.class));
        verify(this.repository, times(1)).findShelterEntitiesByResponsibleUser_Email(USER_EMAIL);

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.EMPTY_DONATION_LIST).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenReceiveDonationDTOListIsEmpty() {
        ShelterEntity shelterEntity = generateShelterEntity();
        ReceiveDonationRequest donationRequest = generateReceiveDonationRequest();
        donationRequest.setDonationDTOS(new ArrayList<>());

        when(this.repository.findShelterEntitiesByResponsibleUser_Email(USER_EMAIL)).thenReturn(Optional.of(shelterEntity));

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.receiveDonation(donationRequest));

        verify(this.repository, never()).save(any(ShelterEntity.class));
        verify(this.donationEntityService, never()).convertAndSaveDonationDTO(any(DonationDTO.class));
        verify(this.repository, times(1)).findShelterEntitiesByResponsibleUser_Email(USER_EMAIL);

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.EMPTY_DONATION_LIST).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhen() {
        ReceiveDonationRequest donationRequest = generateReceiveDonationRequest();

        when(this.repository.findShelterEntitiesByResponsibleUser_Email(USER_EMAIL)).thenReturn(Optional.empty());

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.service.receiveDonation(donationRequest));

        verify(this.repository, never()).save(any(ShelterEntity.class));
        verify(this.donationEntityService, never()).convertAndSaveDonationDTO(any(DonationDTO.class));
        verify(this.repository, times(1)).findShelterEntitiesByResponsibleUser_Email(USER_EMAIL);

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(ShelterEntityServiceImpl.RESPONSIBLE_EMAIL_NOT_ASSOCIATED_WITH_SHELTER).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

}
