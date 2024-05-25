package diegosneves.github.conectardoacoes.core.domain.user.entity.value;

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
