package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DonationDTO;
import diegosneves.github.conectardoacoes.adapters.rest.exception.DonationEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.DonationEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.DonationRepository;
import diegosneves.github.conectardoacoes.core.exception.DonationRegisterFailureException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DonationEntityServiceImplTest {

    public static final String DONATION_UUID = "ca73cd2e-7e50-42ea-b3cd-2e7e5022ea72";
    public static final String DESCRIPTION = "Mochila";
    public static final int AMOUNT = 30;


    @InjectMocks
    private DonationEntityServiceImpl service;

    @Mock
    private DonationRepository repository;

    @Captor
    private ArgumentCaptor<DonationEntity> donationCaptor;

    private DonationEntity entity;
    private DonationDTO donationDTO;

    @BeforeEach
    void setUp() {
        this.entity = new DonationEntity(DONATION_UUID, DESCRIPTION, AMOUNT);
        this.donationDTO = DonationDTO.builder()
                .description(DESCRIPTION)
                .amount(AMOUNT)
                .build();
    }

    @Test
    void shouldConvertDonationDtoToEntitySaveAndValidate() {
        when(this.repository.save(any(DonationEntity.class))).thenReturn(this.entity);

        DonationEntity actual = this.service.convertAndSaveDonationDTO(this.donationDTO);

        verify(this.repository, times(1)).save(this.donationCaptor.capture());

        assertNotNull(actual);
        assertEquals(DESCRIPTION, actual.getDescription());
        assertEquals(AMOUNT, actual.getAmount());
        assertEquals(this.entity.getId(), actual.getId());
        assertNotNull(this.donationCaptor.getValue());
        assertTrue(UuidUtils.isValidUUID(this.donationCaptor.getValue().getId()));
        assertEquals(DESCRIPTION, this.donationCaptor.getValue().getDescription());
        assertEquals(AMOUNT, donationCaptor.getValue().getAmount());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2})
    void shouldConvertDonationDtoWithZeroAmountToEntitySaveAndValidateAsOne(Integer amount) {
        this.donationDTO.setAmount(amount);
        when(this.repository.save(any(DonationEntity.class))).thenReturn(this.entity);

        DonationEntity actual = this.service.convertAndSaveDonationDTO(this.donationDTO);

        verify(this.repository, times(1)).save(this.donationCaptor.capture());

        assertNotNull(actual);
        assertEquals(DESCRIPTION, actual.getDescription());
        assertEquals(AMOUNT, actual.getAmount());
        assertEquals(this.entity.getId(), actual.getId());
        assertNotNull(this.donationCaptor.getValue());
        assertTrue(UuidUtils.isValidUUID(this.donationCaptor.getValue().getId()));
        assertEquals(DESCRIPTION, this.donationCaptor.getValue().getDescription());
        assertEquals(1, donationCaptor.getValue().getAmount());
    }

    @Test
    void shouldThrowDonationEntityFailuresExceptionWhenDonationDTOIsNull() {

        DonationEntityFailuresException exception = assertThrows(DonationEntityFailuresException.class,
                () -> this.service.convertAndSaveDonationDTO(null));

        verify(this.repository, never()).save(any(DonationEntity.class));

        assertNotNull(exception);
        assertEquals(DonationEntityFailuresException.ERROR.formatErrorMessage(DonationEntityServiceImpl.INVALID_DONATION_INFO_ERROR), exception.getMessage());
        assertNull(exception.getCause());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void shouldThrowDonationEntityFailuresExceptionOnEmptyOrWhitespaceDescription(String value) {
        this.donationDTO.setDescription(value);

        DonationEntityFailuresException exception = assertThrows(DonationEntityFailuresException.class,
                () -> this.service.convertAndSaveDonationDTO(this.donationDTO));

        verify(this.repository, never()).save(any(DonationEntity.class));

        assertNotNull(exception);
        assertEquals(DonationEntityFailuresException.ERROR.formatErrorMessage(DonationEntityServiceImpl.DONATION_CREATION_FAILURE), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(DonationRegisterFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldThrowDonationEntityFailuresExceptionOnNullAmmountValue() {
        this.donationDTO.setAmount(null);

        DonationEntityFailuresException exception = assertThrows(DonationEntityFailuresException.class,
                () -> this.service.convertAndSaveDonationDTO(this.donationDTO));

        verify(this.repository, never()).save(any(DonationEntity.class));

        assertNotNull(exception);
        assertEquals(DonationEntityFailuresException.ERROR.formatErrorMessage(DonationEntityServiceImpl.DONATION_CREATION_FAILURE), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(DonationRegisterFailureException.class, exception.getCause().getClass());
    }

}
