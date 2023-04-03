package intern.project.parkingmanagerment.repositories;

import intern.project.parkingmanagerment.model.Contract;
import intern.project.parkingmanagerment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    Contract findByContractID(Long id);
    List<Contract> findAllContractByCustomer(Customer customer);
}