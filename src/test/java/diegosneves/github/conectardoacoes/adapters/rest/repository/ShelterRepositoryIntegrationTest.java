package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.exception.MapperFailureException;
import diegosneves.github.conectardoacoes.adapters.rest.exception.ShelterEntityFailuresException;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.AddressEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.DonationEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.MapperStrategy;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.ShelterEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.mapper.UserEntityMapper;
import diegosneves.github.conectardoacoes.adapters.rest.model.ShelterEntity;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.Shelter;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.ShelterContract;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Address;
import diegosneves.github.conectardoacoes.core.domain.shelter.entity.value.Donation;
import diegosneves.github.conectardoacoes.core.domain.user.entity.User;
import diegosneves.github.conectardoacoes.core.domain.user.entity.value.UserProfile;
import diegosneves.github.conectardoacoes.core.exception.ShelterCreationFailureException;
import diegosneves.github.conectardoacoes.core.exception.UuidUtilsException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@ExtendWith(SpringExtension.class)
class ShelterRepositoryIntegrationTest {

    public static final String ADDRESS_ID = "9f3928bc-3985-4035-b928-bc3985f035c9";
    public static final String STREET = "Rua";
    public static final String NUMBER = "123";
    public static final String NEIGHBORHOOD = "Bairro";
    public static final String CITY = "Cidade";
    public static final String STATE = "Estado";
    public static final String ZIP = "92123456";

    public static final String SHELTER_ID = "1229ed8f-76d3-4fbd-a9ed-8f76d3ffbdf5";
    public static final String SHELTER_NAME = "Abrigo";

    public static final String USER_ID = "574371b9-ae17-4f07-8371-b9ae175f0721";
    public static final String USER_NAME = "Fulano";
    public static final String USER_EMAIL = "email@teste.com";
    public static final String USER_PASSWORD = "Senha";

    public static final String DONATION_ID = "bf9b8d38-c6b3-4fd6-9b8d-38c6b3bfd69f";
    public static final String DONATION_DESCRIPTION = "Descrição";
    public static final int AMOUNT = 1;

    public static final String ENTITY_ID = "b7a89acb-c03f-4f87-a89a-cbc03fef8755";


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShelterRepository shelterRepository;

    private Shelter shelter;
    private Address address;
    private User user;
    private Donation donation;

    private ShelterEntity shelterEntity;

    @BeforeEach
    void setUp() {
        this.address = new Address(ADDRESS_ID, STREET, NUMBER, NEIGHBORHOOD, CITY, STATE, ZIP);
        this.user = new User(USER_ID, USER_NAME, USER_EMAIL, UserProfile.DONOR, USER_PASSWORD);
        this.donation = new Donation(DONATION_ID, DONATION_DESCRIPTION, AMOUNT);
        this.shelter = new Shelter(SHELTER_ID, SHELTER_NAME, this.address, this.user);
    }

    private <T, E> void persistEntity(MapperStrategy<T, E> mapper, E entity) {
        this.entityManager.persist(mapper.mapFrom(entity));
        this.entityManager.flush();
    }

    private <T> void persistEntity(T entity) {
        this.entityManager.persist(entity);
        this.entityManager.flush();
    }

    @Test
    void shouldRetrieveAndValidateShelterWithAssociationsIncludingAddressUserAndEmptyDonations() {

        persistEntity(new AddressEntityMapper(), this.address);
        persistEntity(new UserEntityMapper(), this.user);
        persistEntity(new DonationEntityMapper(), this.donation);
        persistEntity(new ShelterEntityMapper(), this.shelter);

        ShelterContract foundShelter = this.shelterRepository.findEntityById(SHELTER_ID);

        assertNotNull(foundShelter);
        assertEquals(SHELTER_ID, foundShelter.getId());
        assertEquals(SHELTER_NAME, foundShelter.getShelterName());
        assertNotNull(foundShelter.getAddress());
        assertEquals(ADDRESS_ID, foundShelter.getAddress().getId());
        assertEquals(STREET, foundShelter.getAddress().getStreet());
        assertEquals(NUMBER, foundShelter.getAddress().getNumber());
        assertEquals(NEIGHBORHOOD, foundShelter.getAddress().getNeighborhood());
        assertEquals(CITY, foundShelter.getAddress().getCity());
        assertEquals(STATE, foundShelter.getAddress().getState());
        assertEquals(ZIP, foundShelter.getAddress().getZip());
        assertNotNull(foundShelter.getUser());
        assertEquals(USER_ID, foundShelter.getUser().getId());
        assertEquals(USER_NAME, foundShelter.getUser().getUsername());
        assertEquals(USER_EMAIL, foundShelter.getUser().getEmail());
        assertEquals(UserProfile.DONOR, foundShelter.getUser().getUserProfile());
        assertEquals(USER_PASSWORD, foundShelter.getUser().getUserPassword());
        assertNotNull(foundShelter.getDonations());
        assertTrue(foundShelter.getDonations().isEmpty());
    }

    @Test
    void shouldRetrieveAndValidateShelterWithAssociationsIncludingAddressUserAndNonEmptyDonations() {
        this.shelter.addDonation(this.donation);

        persistEntity(new AddressEntityMapper(), this.address);
        persistEntity(new UserEntityMapper(), this.user);
        persistEntity(new DonationEntityMapper(), this.donation);
        persistEntity(new ShelterEntityMapper(), this.shelter);

        ShelterContract foundShelter = this.shelterRepository.findEntityById(SHELTER_ID);

        assertNotNull(foundShelter);
        assertEquals(SHELTER_ID, foundShelter.getId());
        assertEquals(SHELTER_NAME, foundShelter.getShelterName());
        assertNotNull(foundShelter.getAddress());
        assertEquals(ADDRESS_ID, foundShelter.getAddress().getId());
        assertEquals(STREET, foundShelter.getAddress().getStreet());
        assertEquals(NUMBER, foundShelter.getAddress().getNumber());
        assertEquals(NEIGHBORHOOD, foundShelter.getAddress().getNeighborhood());
        assertEquals(CITY, foundShelter.getAddress().getCity());
        assertEquals(STATE, foundShelter.getAddress().getState());
        assertEquals(ZIP, foundShelter.getAddress().getZip());
        assertNotNull(foundShelter.getUser());
        assertEquals(USER_ID, foundShelter.getUser().getId());
        assertEquals(USER_NAME, foundShelter.getUser().getUsername());
        assertEquals(USER_EMAIL, foundShelter.getUser().getEmail());
        assertEquals(UserProfile.DONOR, foundShelter.getUser().getUserProfile());
        assertEquals(USER_PASSWORD, foundShelter.getUser().getUserPassword());
        assertNotNull(foundShelter.getDonations());
        assertFalse(foundShelter.getDonations().isEmpty());
        assertEquals(1, foundShelter.getDonations().size());
        assertEquals(DONATION_ID, foundShelter.getDonations().get(0).getId());
        assertEquals(DONATION_DESCRIPTION, foundShelter.getDonations().get(0).getDescription());
        assertEquals(AMOUNT, foundShelter.getDonations().get(0).getAmount());
    }

    @Test
    void shouldRetrieveShelterNull() {
        this.shelter.addDonation(this.donation);

        persistEntity(new AddressEntityMapper(), this.address);
        persistEntity(new UserEntityMapper(), this.user);
        persistEntity(new DonationEntityMapper(), this.donation);
        persistEntity(new ShelterEntityMapper(), this.shelter);

        ShelterContract foundShelter = this.shelterRepository.findEntityById(ENTITY_ID);

        assertNull(foundShelter);
    }

    @Test
    void shouldThrowExceptionWhenFindingEntityByIdGivenNullId() {
        persistEntity(new AddressEntityMapper(), this.address);
        persistEntity(new UserEntityMapper(), this.user);
        persistEntity(new DonationEntityMapper(), this.donation);
        persistEntity(new ShelterEntityMapper(), this.shelter);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.shelterRepository.findEntityById(null));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterRepository.INVALID_ID_MESSAGE), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenFindingEntityByIdGivenEmptyStringId() {
        persistEntity(new AddressEntityMapper(), this.address);
        persistEntity(new UserEntityMapper(), this.user);
        persistEntity(new DonationEntityMapper(), this.donation);
        persistEntity(new ShelterEntityMapper(), this.shelter);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.shelterRepository.findEntityById(""));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterRepository.INVALID_ID_MESSAGE), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionAndHaveCauseWhenFindingEntityByIdGivenInvalidId() {
        persistEntity(new AddressEntityMapper(), this.address);
        persistEntity(new UserEntityMapper(), this.user);
        persistEntity(new DonationEntityMapper(), this.donation);
        persistEntity(new ShelterEntityMapper(), this.shelter);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.shelterRepository.findEntityById("null"));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterRepository.INVALID_ID_MESSAGE), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(UuidUtilsException.class, exception.getCause().getClass());
    }

    @Test
    void shouldRetrieveAllShelterContracts() {
        persistEntity(new AddressEntityMapper(), this.address);
        persistEntity(new UserEntityMapper(), this.user);
        persistEntity(new DonationEntityMapper(), this.donation);
        persistEntity(new ShelterEntityMapper(), this.shelter);

        List<ShelterContract> retrievedAll = this.shelterRepository.retrieveAll();

        assertNotNull(retrievedAll);
        assertFalse(retrievedAll.isEmpty());
        assertEquals(1, retrievedAll.size());
    }

    @Test
    void shouldRetrieveEmptyShelterContractsList() {

        List<ShelterContract> retrievedAll = this.shelterRepository.retrieveAll();

        assertNotNull(retrievedAll);
        assertTrue(retrievedAll.isEmpty());
    }

    @Test
    @SneakyThrows
    void shouldReturnEmptyListWhenMapEntityListInvokedWithNull() {
        Method method = ShelterRepository.class.getDeclaredMethod("mapEntityList", List.class);
        method.setAccessible(true);

        List<ShelterContract> result = (List<ShelterContract>) method.invoke(this.shelterRepository, new Object[]{null});
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionOnRetrieveAllWithInvalidShelterEntity() {
        this.shelterEntity = new ShelterEntityMapper().mapFrom(this.shelter);
        this.shelterEntity.setShelterName("");

        persistEntity(new AddressEntityMapper(), this.address);
        persistEntity(new UserEntityMapper(), this.user);
        persistEntity(new DonationEntityMapper(), this.donation);
        persistEntity(this.shelterEntity);

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.shelterRepository.retrieveAll());

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(MapperFailureException.ERROR.formatErrorMessage(ShelterEntity.class.getSimpleName())), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(ShelterCreationFailureException.class, exception.getCause().getClass());
    }

    @Test
    void shouldPersistAndValidateShelterIncludingAssociationsWithoutDonations() {

        persistEntity(new AddressEntityMapper(), this.address);
        persistEntity(new UserEntityMapper(), this.user);
        persistEntity(new DonationEntityMapper(), this.donation);

        ShelterContract persisted = this.shelterRepository.persist(this.shelter);

        assertNotNull(persisted);
        assertEquals(SHELTER_ID, persisted.getId());
        assertEquals(SHELTER_NAME, persisted.getShelterName());
        assertNotNull(persisted.getAddress());
        assertEquals(ADDRESS_ID, persisted.getAddress().getId());
        assertEquals(STREET, persisted.getAddress().getStreet());
        assertEquals(NUMBER, persisted.getAddress().getNumber());
        assertEquals(NEIGHBORHOOD, persisted.getAddress().getNeighborhood());
        assertEquals(CITY, persisted.getAddress().getCity());
        assertEquals(STATE, persisted.getAddress().getState());
        assertEquals(ZIP, persisted.getAddress().getZip());
        assertNotNull(persisted.getUser());
        assertEquals(USER_ID, persisted.getUser().getId());
        assertEquals(USER_NAME, persisted.getUser().getUsername());
        assertEquals(USER_EMAIL, persisted.getUser().getEmail());
        assertEquals(UserProfile.DONOR, persisted.getUser().getUserProfile());
        assertEquals(USER_PASSWORD, persisted.getUser().getUserPassword());
        assertNotNull(persisted.getDonations());
        assertTrue(persisted.getDonations().isEmpty());
    }

    @Test
    void shouldPersistAndValidateShelterIncludingAssociationsWithDonations() {
        this.shelterEntity = new ShelterEntityMapper().mapFrom(this.shelter);

        persistEntity(new AddressEntityMapper(), this.address);
        persistEntity(new UserEntityMapper(), this.user);
        persistEntity(new DonationEntityMapper(), this.donation);
        persistEntity(this.shelterEntity);

        ShelterContract found = this.shelterRepository.findEntityById(SHELTER_ID);

        found.addDonation(this.donation);

        ShelterContract persisted = this.shelterRepository.persist(found);

        assertNotNull(persisted);
        assertEquals(SHELTER_ID, persisted.getId());
        assertEquals(SHELTER_NAME, persisted.getShelterName());
        assertNotNull(persisted.getAddress());
        assertEquals(ADDRESS_ID, persisted.getAddress().getId());
        assertEquals(STREET, persisted.getAddress().getStreet());
        assertEquals(NUMBER, persisted.getAddress().getNumber());
        assertEquals(NEIGHBORHOOD, persisted.getAddress().getNeighborhood());
        assertEquals(CITY, persisted.getAddress().getCity());
        assertEquals(STATE, persisted.getAddress().getState());
        assertEquals(ZIP, persisted.getAddress().getZip());
        assertNotNull(persisted.getUser());
        assertEquals(USER_ID, persisted.getUser().getId());
        assertEquals(USER_NAME, persisted.getUser().getUsername());
        assertEquals(USER_EMAIL, persisted.getUser().getEmail());
        assertEquals(UserProfile.DONOR, persisted.getUser().getUserProfile());
        assertEquals(USER_PASSWORD, persisted.getUser().getUserPassword());
        assertNotNull(persisted.getDonations());
        assertFalse(persisted.getDonations().isEmpty());
        assertEquals(1, persisted.getDonations().size());
        assertEquals(DONATION_ID, persisted.getDonations().get(0).getId());
        assertEquals(DONATION_DESCRIPTION, persisted.getDonations().get(0).getDescription());
        assertEquals(AMOUNT, persisted.getDonations().get(0).getAmount());
    }

    @Test
    void shouldThrowShelterEntityFailuresExceptionWhenPersistNullEntity() {

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.shelterRepository.persist(null));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterRepository.SHELTER_ERROR_MESSAGE), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldDeleteShelterEntityAndConfirmItsDeletion() {

        persistEntity(new AddressEntityMapper(), this.address);
        persistEntity(new UserEntityMapper(), this.user);
        persistEntity(new DonationEntityMapper(), this.donation);
        persistEntity(new ShelterEntityMapper(), this.shelter);

        this.shelterRepository.deleteEntityById(SHELTER_ID);

        assertNull(this.shelterRepository.findById(SHELTER_ID).orElse(null));
    }

    @Test
    void shouldHandleNonExistentEntityDeletionAttemptGracefully() {

        persistEntity(new AddressEntityMapper(), this.address);
        persistEntity(new UserEntityMapper(), this.user);
        persistEntity(new DonationEntityMapper(), this.donation);
        persistEntity(new ShelterEntityMapper(), this.shelter);

        this.shelterRepository.deleteEntityById(ENTITY_ID);

        assertNull(this.shelterRepository.findById(ENTITY_ID).orElse(null));
    }

    @Test
    void shouldThrowExceptionWhenIdIsEmpty() {

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.shelterRepository.deleteEntityById(""));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterRepository.INVALID_ID_MESSAGE), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.shelterRepository.deleteEntityById(null));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterRepository.INVALID_ID_MESSAGE), exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenIdIsInvalid() {

        ShelterEntityFailuresException exception = assertThrows(ShelterEntityFailuresException.class, () -> this.shelterRepository.deleteEntityById("null"));

        assertNotNull(exception);
        assertEquals(ShelterEntityFailuresException.ERROR.formatErrorMessage(ShelterRepository.INVALID_ID_MESSAGE), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(UuidUtilsException.class, exception.getCause().getClass());
    }

}
