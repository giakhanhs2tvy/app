package intern.project.parkingmanagerment.service.impl;

import intern.project.parkingmanagerment.model.Contract;
import intern.project.parkingmanagerment.model.Customer;
import intern.project.parkingmanagerment.repositories.CustomerRepository;
import intern.project.parkingmanagerment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository cusRepo;
    @Override
    public Customer saveCustomer(Customer customer) {
        return cusRepo.save(customer);
    }

    @Override
    public Customer findByName(String name) {
        return cusRepo.findByName(name);
    }

    /*@Override
    public List<Contract> getAllContractByCustomer(Customer customer) {
        return cusRepo.findAllContractByCustomer(customer);
    }*/
}
