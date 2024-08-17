package diegosneves.github.conectardoacoes.adapters.rest.service.impl;

import diegosneves.github.conectardoacoes.adapters.rest.dto.DepositDTO;
import diegosneves.github.conectardoacoes.adapters.rest.enums.ExceptionDetails;
import diegosneves.github.conectardoacoes.adapters.rest.exception.DepositEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.model.DepositEntity;
import diegosneves.github.conectardoacoes.adapters.rest.repository.DepositRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DepositEntityServiceImplTest {

    public static final String DEPOSIT_UUID = "0f46f5b9-ab37-478b-86f5-b9ab37878b33";
    public static final String DESCRIPTION = "Item 01";
    public static final int AMOUNT = 1;


    @InjectMocks
    private DepositEntityServiceImpl service;

    @Mock
    private DepositRepository repository;

    @Captor
    private ArgumentCaptor<DepositEntity> depositCaptor;

    private DepositDTO depositDTO;
    private DepositEntity depositEntity;

    @BeforeEach
    void setUp() {
        this.depositDTO = new DepositDTO(DESCRIPTION, AMOUNT);
        this.depositEntity = new DepositEntity(DEPOSIT_UUID, DESCRIPTION, AMOUNT);
    }

    @Test
    void shouldCreateAndSaveDepositEntitySuccessfully() {
        when(this.repository.save(any(DepositEntity.class))).thenReturn(this.depositEntity);

        DepositEntity actual = this.service.create(this.depositDTO);

        verify(this.repository, times(1)).save(this.depositCaptor.capture());

        assertNotNull(actual);
        assertEquals(DEPOSIT_UUID, actual.getId());
        assertEquals(DESCRIPTION, actual.getDescription());
        assertEquals(AMOUNT, actual.getAmount());
        DepositEntity capturedDeposit = this.depositCaptor.getValue();
        assertNotNull(capturedDeposit);
        assertNotNull(capturedDeposit.getId());
        assertTrue(UuidUtils.isValidUUID(capturedDeposit.getId()));
        assertEquals(DESCRIPTION, capturedDeposit.getDescription());
        assertEquals(AMOUNT, capturedDeposit.getAmount());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -3, -58})
    void shouldSetMinimumAmountAndSaveDepositEntityWhenInvalidAmountProvided(Integer value) {
        this.depositDTO.setAmount(value);
        when(this.repository.save(any(DepositEntity.class))).thenReturn(this.depositEntity);

        DepositEntity actual = this.service.create(this.depositDTO);

        verify(this.repository, times(1)).save(this.depositCaptor.capture());

        assertNotNull(actual);
        assertEquals(DEPOSIT_UUID, actual.getId());
        assertEquals(DESCRIPTION, actual.getDescription());
        assertEquals(AMOUNT, actual.getAmount());
        DepositEntity capturedDeposit = this.depositCaptor.getValue();
        assertNotNull(capturedDeposit);
        assertNotNull(capturedDeposit.getId());
        assertTrue(UuidUtils.isValidUUID(capturedDeposit.getId()));
        assertEquals(DESCRIPTION, capturedDeposit.getDescription());
        assertEquals(AMOUNT, capturedDeposit.getAmount());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull(){
        this.depositDTO.setDescription(null);

        DepositEntityFailuresException exception = assertThrows(DepositEntityFailuresException.class, () -> this.service.create(this.depositDTO));

        verify(repository, never()).save(any(DepositEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(DepositEntityServiceImpl.DEPOSIT_VALIDATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void shouldThrowExceptionWhenDescriptionIsEmptyOrBlank(String value){
        this.depositDTO.setDescription(value);

        DepositEntityFailuresException exception = assertThrows(DepositEntityFailuresException.class, () -> this.service.create(this.depositDTO));

        verify(repository, never()).save(any(DepositEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(DepositEntityServiceImpl.DEPOSIT_VALIDATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNull(){
        this.depositDTO.setAmount(null);

        DepositEntityFailuresException exception = assertThrows(DepositEntityFailuresException.class, () -> this.service.create(this.depositDTO));

        verify(repository, never()).save(any(DepositEntity.class));

        assertNotNull(exception);
        assertEquals(ExceptionDetails.getExceptionDetails(DepositEntityServiceImpl.DEPOSIT_VALIDATION_ERROR).formatErrorMessage(), exception.getMessage());
        assertNull(exception.getCause());
    }


}
