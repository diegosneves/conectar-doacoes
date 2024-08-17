package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DepositDTO;
import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.enums.UserProfileType;
import diegosneves.github.conectardoacoes.adapters.rest.exception.DepositProcessingException;
import diegosneves.github.conectardoacoes.adapters.rest.model.DepositEntity;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonorDeposit;
import diegosneves.github.conectardoacoes.adapters.rest.model.UserEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.DonorDepositRepository;
import diegosneves.github.conectardoacoes.adapters.rest.request.DepositDonationRequest;
import diegosneves.github.conectardoacoes.adapters.rest.response.RegisteredDepositResponse;
import diegosneves.github.conectardoacoes.adapters.rest.service.DepositEntityService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DonorDepositServiceImplTest {

    public static final String DONOR_EMAIL = "teste@email.com";
    public static final String DONOR_DEPOSIT_ID = "2f717c50-48ad-4b38-b17c-5048ad4b38d4";

    public static final String USER_UUID = "e744f60b-f691-4735-84f6-0bf691273559";
    public static final String USER_NAME = "Fulano";
    public static final String USER_PASSWORD = "Senha";

    public static final String DEPOSIT_ID = "17087aa2-9dd8-4cc6-887a-a29dd84cc657";
    public static final String DESCRIPTION = "Item 01";
    public static final int AMOUNT = 1;

    public static final String ITEM_TWO = "Item 02";


    @InjectMocks
    private DonorDepositServiceImpl service;

    @Mock
    private DonorDepositRepository repository;

    @Mock
    private DepositEntityService depositEntityService;

    @Captor
    private ArgumentCaptor<DonorDeposit> donorDepositCaptor;

    private DonorDeposit donorDeposit;
    private UserEntity userEntity;
    private DepositEntity depositEntity;
    private DepositDonationRequest request;

    @BeforeEach
    void setUp() {
        this.userEntity = new UserEntity(USER_UUID, USER_NAME, DONOR_EMAIL, UserProfileType.DONOR, USER_PASSWORD);
        this.depositEntity = new DepositEntity(DEPOSIT_ID, DESCRIPTION, AMOUNT);

        this.donorDeposit = DonorDeposit.builder()
                .id(DONOR_DEPOSIT_ID)
                .user(this.userEntity)
                .deposits(List.of(this.depositEntity))
                .build();

        this.request = new DepositDonationRequest(DONOR_EMAIL, List.of(new DepositDTO(ITEM_TWO, AMOUNT)));
    }

    @Test
    void shouldRegisterDonationAndReturnCorrectResponse() {
        when(this.repository.findDonorDepositByUser_Email(DONOR_EMAIL)).thenReturn(Optional.of(this.donorDeposit));
        when(this.depositEntityService.create(any(DepositDTO.class))).thenReturn(this.depositEntity);
        when(this.repository.save(any(DonorDeposit.class))).thenReturn(this.donorDeposit);

        RegisteredDepositResponse response = this.service.registerDonation(this.request);

        verify(this.repository, times(1)).findDonorDepositByUser_Email(DONOR_EMAIL);
        verify(this.depositEntityService, times(1)).create(any(DepositDTO.class));
        verify(this.repository, times(1)).save(this.donorDepositCaptor.capture());

        assertNotNull(response);
        assertEquals(USER_NAME, response.getUserName());
        assertEquals(DONOR_EMAIL, response.getEmail());
        assertNotNull(response.getDeposits());
        assertEquals(2, response.getDeposits().size());
        DonorDeposit captorValue = this.donorDepositCaptor.getValue();
        assertNotNull(captorValue);
        assertTrue(UuidUtils.isValidUUID(captorValue.getId()));
        assertNotNull(captorValue.getUser());
        assertEquals(USER_UUID, captorValue.getUser().getId());
        assertEquals(USER_NAME, captorValue.getUser().getUserName());
        assertEquals(DONOR_EMAIL, captorValue.getUser().getEmail());
        assertEquals(UserProfileType.DONOR, captorValue.getUser().getUserProfile());
        assertNotNull(captorValue.getDeposits());
        assertEquals(2, captorValue.getDeposits().size());
        assertEquals(DESCRIPTION, captorValue.getDeposits().get(0).getDescription());
        assertEquals(AMOUNT, captorValue.getDeposits().get(0).getAmount());
        assertEquals(DESCRIPTION, captorValue.getDeposits().get(1).getDescription());
        assertEquals(AMOUNT, captorValue.getDeposits().get(1).getAmount());
    }

    @Test
    void shouldThrowExceptionWhenDonorEmailNotFound() {
        when(this.repository.findDonorDepositByUser_Email(DONOR_EMAIL)).thenReturn(Optional.empty());

        DepositProcessingException exception = assertThrows(DepositProcessingException.class, () -> this.service.registerDonation(this.request));

        verify(this.repository, times(1)).findDonorDepositByUser_Email(DONOR_EMAIL);
        verify(this.depositEntityService, never()).create(any(DepositDTO.class));
        verify(this.repository, never()).save(any(DonorDeposit.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(DonorDepositServiceImpl.DONOR_EMAIL_NOT_FOUND_ERROR_MESSAGE).formatErrorMessage(DONOR_EMAIL), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenRequestIsNull() {

        DepositProcessingException exception = assertThrows(DepositProcessingException.class, () -> this.service.registerDonation(null));

        verify(this.repository, never()).findDonorDepositByUser_Email(anyString());
        verify(this.depositEntityService, never()).create(any(DepositDTO.class));
        verify(this.repository, never()).save(any(DonorDeposit.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(DonorDepositServiceImpl.DEPOSIT_VALIDATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenResponsibleEmailIsNull() {
        this.request.setResponsibleEmail(null);

        DepositProcessingException exception = assertThrows(DepositProcessingException.class, () -> this.service.registerDonation(this.request));

        verify(this.repository, never()).findDonorDepositByUser_Email(anyString());
        verify(this.depositEntityService, never()).create(any(DepositDTO.class));
        verify(this.repository, never()).save(any(DonorDeposit.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(DonorDepositServiceImpl.DEPOSIT_VALIDATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void shouldThrowExceptionWhenResponsibleEmailIsBlank(String value) {
        this.request.setResponsibleEmail(value);

        DepositProcessingException exception = assertThrows(DepositProcessingException.class, () -> this.service.registerDonation(this.request));

        verify(this.repository, never()).findDonorDepositByUser_Email(anyString());
        verify(this.depositEntityService, never()).create(any(DepositDTO.class));
        verify(this.repository, never()).save(any(DonorDeposit.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(DonorDepositServiceImpl.DEPOSIT_VALIDATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenDepositDTOSIsNull() {
        this.request.setDepositDTOS(null);

        DepositProcessingException exception = assertThrows(DepositProcessingException.class, () -> this.service.registerDonation(this.request));

        verify(this.repository, never()).findDonorDepositByUser_Email(anyString());
        verify(this.depositEntityService, never()).create(any(DepositDTO.class));
        verify(this.repository, never()).save(any(DonorDeposit.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(DonorDepositServiceImpl.DEPOSIT_VALIDATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenDepositDTOSIsEmpty() {
        this.request.setDepositDTOS(new ArrayList<>());

        DepositProcessingException exception = assertThrows(DepositProcessingException.class, () -> this.service.registerDonation(this.request));

        verify(this.repository, never()).findDonorDepositByUser_Email(anyString());
        verify(this.depositEntityService, never()).create(any(DepositDTO.class));
        verify(this.repository, never()).save(any(DonorDeposit.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(DonorDepositServiceImpl.DEPOSIT_VALIDATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldLinkDepositsToDonorWhenUserIsDonor() {

        this.service.linkDepositToDonor(this.userEntity);

        verify(this.repository, times(1)).save(this.donorDepositCaptor.capture());
        verify(this.repository, never()).findDonorDepositByUser_Email(anyString());
        verify(this.depositEntityService, never()).create(any(DepositDTO.class));

        DonorDeposit captorValue = this.donorDepositCaptor.getValue();
        assertNotNull(captorValue);
        assertNotNull(captorValue.getId());
        assertTrue(UuidUtils.isValidUUID(captorValue.getId()));
        assertNotNull(captorValue.getDeposits());
        assertTrue(captorValue.getDeposits().isEmpty());
        assertNotNull(captorValue.getUser());
        assertEquals(DONOR_EMAIL, captorValue.getUser().getEmail());
        assertEquals(USER_NAME, captorValue.getUser().getUserName());
        assertEquals(UserProfileType.DONOR, captorValue.getUser().getUserProfile());
    }

    @Test
    void shouldNotLinkDepositsToDonorWhenUserIsBeneficiary() {
        this.userEntity.setUserProfile(UserProfileType.BENEFICIARY);

        this.service.linkDepositToDonor(this.userEntity);

        verify(this.repository, never()).save(this.donorDepositCaptor.capture());
        verify(this.repository, never()).findDonorDepositByUser_Email(anyString());
        verify(this.depositEntityService, never()).create(any(DepositDTO.class));
    }

    @Test
    void shouldNotLinkDepositsToDonorWhenUserIsNull() {
        this.userEntity.setUserProfile(null);

        this.service.linkDepositToDonor(this.userEntity);

        verify(this.repository, never()).save(this.donorDepositCaptor.capture());
        verify(this.repository, never()).findDonorDepositByUser_Email(anyString());
        verify(this.depositEntityService, never()).create(any(DepositDTO.class));
    }

    @Test
    void shouldThrowExceptionWhen() {

        DepositProcessingException exception = assertThrows(DepositProcessingException.class, () -> this.service.linkDepositToDonor(null));

        verify(this.repository, never()).save(this.donorDepositCaptor.capture());
        verify(this.repository, never()).findDonorDepositByUser_Email(anyString());
        verify(this.depositEntityService, never()).create(any(DepositDTO.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(DonorDepositServiceImpl.REQUIRED_USER_ERROR_MESSAGE).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }


}
