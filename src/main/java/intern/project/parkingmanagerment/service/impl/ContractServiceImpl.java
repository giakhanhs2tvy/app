package intern.project.parkingmanagerment.service.impl;

import intern.project.parkingmanagerment.model.Contract;
import intern.project.parkingmanagerment.model.Customer;
import intern.project.parkingmanagerment.model.Vehicle;
import intern.project.parkingmanagerment.repositories.ContractRepository;
import intern.project.parkingmanagerment.repositories.CustomerRepository;
import intern.project.parkingmanagerment.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    ContractRepository contractRepo;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ContractTypeServiceImpl contractTypeService;
    @Autowired
    VehicleServiceImpl vehicleService;
    @Autowired
    VehicleTypeServiceImpl vehicleTypeService;
    public List<Contract> getAllContractByCustomer(Customer customer) {
        return contractRepo.findAllContractByCustomer(customer);}
    @Override
    public Contract saveContract(Contract contract) {
        Contract contract1 = new Contract();
        contract1.setCustomer(customerService.findByName(contract.getCustomer().getName()));
        contract1.setAddedBy(userService.findByUserName(userService.getCurrentUserName()));
        contract1.setContractType(contractTypeService.findByContractType(contract.getContractType().getContractTypeID()));
        List<Vehicle> lv = new ArrayList<>(contract.getVehicles());
       /* Vehicle v1 = new Vehicle();
        for (Vehicle v: lv) {
                v1.setLicensePlate(v.getLicensePlate());
                v1.setVehicleType(vehicleTypeService.getVehicleType(v.getVehicleType().getVehicleTypeID()));
                lv.add(v1);

        }*/
        //contract1.setVehicles(lv);
        contract1 = contractRepo.save(contract1);
        for (Vehicle vehicle:lv) {
            vehicle.setContract(contract1);
            if(vehicle.getLicensePlate() != null || !vehicle.getLicensePlate().equals(""))
            vehicleService.saveVehicle(vehicle);
        }

        return contract1;
    }
}
