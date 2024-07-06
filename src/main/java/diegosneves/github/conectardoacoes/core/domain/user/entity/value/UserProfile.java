package diegosneves.github.conectardoacoes.core.domain.user.entity.value;

/**
 * A enumeração {@link UserProfile} fornece os tipos de perfis disponíveis para um usuário.
 * Os perfis disponíveis são {@code 'Doador'} e {@code 'Beneficiário'}.
 *
 * @author diegoneves
 * @since 1.0.0
 */
public enum UserProfile {

    DONOR("Doador"),
    BENEFICIARY("Beneficiário");

    private final String name;

    UserProfile(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
