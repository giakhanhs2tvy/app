package intern.project.parkingmanagerment.service.impl;
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

    @Override
    public List<Customer> findAll() {
        return cusRepo.findAll();
    }

    public Customer findById(Long id) {
        return cusRepo.findById(id).get();
    }

    public Customer updateCustomer(Customer customer) {
        Customer customer1 = this.findById(customer.getCustomerID());
        customer1.setName(customer.getName());
        customer1.setAddress(customer.getAddress());
        customer1.setEmail(customer.getEmail());
        customer1.setCity(customer.getCity());
        customer1.setPhone(customer.getPhone());
        return cusRepo.save(customer1);
    }
    public void deleteCustomer(Long id){
        cusRepo.deleteById(id);
    }

    @Override
    public Customer findByEmail(String email) {
        return cusRepo.findCustomerByEmail(email);
    }
    /*@Override
    public List<Contract> getAllContractByCustomer(Customer customer) {
        return cusRepo.findAllContractByCustomer(customer);
    }*/
}
