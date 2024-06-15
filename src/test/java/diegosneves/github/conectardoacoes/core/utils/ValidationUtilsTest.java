package diegosneves.github.conectardoacoes.core.utils;

import diegosneves.github.conectardoacoes.core.exception.ValidationUtilsException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    public static final String NULL_VALUE_ERROR_MESSAGE = "Valor Nulo";
    public static final String EMPTY_STRING = " ";
    public static final String EMPTY_VALUE_ERROR = "Valor vazio";
    public static final String UNEXPECTED_EXCEPTION = "Exceção não deveria ter sido lançada";

    private String value;

    @BeforeEach
    void setUp() {
        this.value = EMPTY_STRING;
    }

    @Test
    void isNullOrEmpty() {
        this.value = "Teste";
        try {
            ValidationUtils.checkNotNullAndNotEmptyOrThrowException(this.value, EMPTY_VALUE_ERROR, IllegalArgumentException.class);
        } catch (Exception e) {
            fail(UNEXPECTED_EXCEPTION);
        }
    }

    @Test
    void checkStringNotNull() {
        this.value = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ValidationUtils.checkNotNullAndNotEmptyOrThrowException(this.value, NULL_VALUE_ERROR_MESSAGE, IllegalArgumentException.class));

        assertNotNull(exception);
        assertEquals(NULL_VALUE_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void checkStringNotNullWithValidateUtilsException() {
        this.value = null;

        ValidationUtilsException exception = assertThrows(ValidationUtilsException.class,
                () -> ValidationUtils.checkNotNullAndNotEmptyOrThrowException(this.value, NULL_VALUE_ERROR_MESSAGE, NoMethodFoundException.class));

        assertNotNull(exception);
        assertEquals(ValidationUtilsException.ERROR.buildMessage(NoMethodFoundException.class.getSimpleName()), exception.getMessage());
    }

    @Test
    void checkStringNotEmptyWithValidateUtilsException() {

        ValidationUtilsException exception = assertThrows(ValidationUtilsException.class,
                () -> ValidationUtils.checkNotNullAndNotEmptyOrThrowException(this.value, EMPTY_VALUE_ERROR, NoMethodFoundException.class));

        assertNotNull(exception);
        assertEquals(ValidationUtilsException.ERROR.buildMessage(NoMethodFoundException.class.getSimpleName()), exception.getMessage());
    }

    @Test
    void checkStringNotEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ValidationUtils.checkNotNullAndNotEmptyOrThrowException(this.value, EMPTY_VALUE_ERROR, IllegalArgumentException.class));

        assertNotNull(exception);
        assertEquals(EMPTY_VALUE_ERROR, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowNoSuchMethodException() {
        Method method = ValidationUtils.class.getDeclaredMethod("throwException", String.class, Class.class);
        method.setAccessible(true);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class,
                () -> method.invoke(ValidationUtils.class, this.value, NoMethodFoundException.class));

        assertNotNull(exception);
        assertInstanceOf(NoSuchMethodException.class, exception.getCause().getCause());
        assertEquals(ValidationUtilsException.ERROR.buildMessage(NoMethodFoundException.class.getSimpleName()), exception.getTargetException().getMessage());
    }

    static class NoMethodFoundException extends RuntimeException {

        public NoMethodFoundException(Throwable cause) {
            super(cause);
        }
    }

}
