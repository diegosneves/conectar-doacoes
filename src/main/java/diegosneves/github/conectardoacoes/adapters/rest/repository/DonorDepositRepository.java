package diegosneves.github.conectardoacoes.adapters.rest.repository;

import diegosneves.github.conectardoacoes.adapters.rest.model.DonorDeposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonorDepositRepository extends CrudRepository<DonorDeposit, String> {

    Optional<DonorDeposit> findDonorDepositByUser_Email(String email);

}
