package intern.project.parkingmanagerment.service;

import intern.project.parkingmanagerment.model.Contract;
import intern.project.parkingmanagerment.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer findByName(String name);
    //List<Contract> getAllContractByCustomer(Customer customer);
}
