package diegosneves.github.conectardoacoes.core.utils;

import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * Classe de utilidade para lidar com operações relacionadas ao {@link UUID}.
 * Esta classe fornece métodos estáticos para gerar um novo {@link UUID} e para validar um {@link UUID} existente.
 *
 * @author diegoneves
 * @since 1.0.0
 */
@Slf4j
public class UuidUtils {


    private static final String INVALID_UUID_ERROR = "O UUID [{}] é inválido";
    private static final String EMPTY_UUID = "vazio";
    private static final String NULL_UUID = "nulo";
    public static final String UUID_REQUIRED = "UUID deve ser informado";

    private UuidUtils() {
    }

    /**
     * Gera um novo {@link UUID}.
     *
     * @return A string representando o {@link UUID} gerado.
     */
    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * Valida um {@link UUID}.
     *
     * @param uuid A string que representa o {@link UUID} a ser validado.
     * @return Retorna 'true' se o {@link UUID} for válido.
     * @throws UuidUtilsException Se o {@link UUID} fornecido for inválido.
     */
    public static boolean isValidUUID(String uuid) throws UuidUtilsException {
        if (uuid == null) {
            log.error(INVALID_UUID_ERROR, NULL_UUID);
            throw new UuidUtilsException(UUID_REQUIRED);
        }
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException ignored) {
            log.error(INVALID_UUID_ERROR, uuid.isBlank() ? EMPTY_UUID : uuid);
            throw new UuidUtilsException(uuid);
        }
    }

}
