package diegosneves.github.conectardoacoes.adapters.rest.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A enumeração {@link UserProfileType} fornece os tipos de perfis disponíveis para um usuário.
 * Os perfis disponíveis são {@code 'Doador'} e {@code 'Beneficiário'}.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public enum UserProfileType {

    @JsonProperty(value = "Doador")
    DONOR,
    @JsonProperty(value = "Beneficiário")
    BENEFICIARY;

}
