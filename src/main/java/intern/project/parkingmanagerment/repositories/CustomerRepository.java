package intern.project.parkingmanagerment.repositories;

import intern.project.parkingmanagerment.model.Contract;
import intern.project.parkingmanagerment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
    Customer findCustomerByEmail(String email);
    //List<Contract> findAllContractByCustomer(Customer customer);
}