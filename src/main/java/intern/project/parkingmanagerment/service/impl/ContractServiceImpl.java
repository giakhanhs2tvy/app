package intern.project.parkingmanagerment.service.impl;
import intern.project.parkingmanagerment.model.Contract;
import intern.project.parkingmanagerment.model.Customer;
import intern.project.parkingmanagerment.model.Vehicle;
import intern.project.parkingmanagerment.repositories.ContractRepository;
import intern.project.parkingmanagerment.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

        contract1 = contractRepo.save(contract1);
        List<Vehicle> lv = new ArrayList<>(contract.getVehicles());

        for (Vehicle vehicle:lv) {
            vehicle.setContract(contract1);
            if(vehicle.getLicensePlate() != null && !vehicle.getLicensePlate().equals("")){
                vehicleService.saveVehicle(vehicle);
            }

        }

        return contract1;
    }
    public  void updateContract(Contract contract){
        Contract newContract = this.findById(contract.getContractID());
        newContract.setContractType(contract.getContractType());
        List<Vehicle> vehicleList = contract.getVehicles();
        // false
        List<Vehicle> vehicleListDb = newContract.getVehicles();
        for (Vehicle vehicle : vehicleListDb) {
            vehicle.setContract(null);
        }
        vehicleService.saveAllVehicle(vehicleListDb);

        for (Vehicle vehicle:vehicleList) {
            Vehicle vehicle1 = vehicleService.findByLicensePlate(vehicle.getLicensePlate());
            if( vehicle1 == null){
                if(vehicle.getLicensePlate() != null && !vehicle.getLicensePlate().equals("")){
                    vehicle1 = new Vehicle();
                    vehicle1.setLicensePlate(vehicle.getLicensePlate());
                    vehicle1.setVehicleType(vehicle.getVehicleType());
//                    vehicleList.add(vehicle1);
                    vehicle1.setContract(newContract);
                    vehicleService.saveVehicle(vehicle1);
                }

            } else {
                if(vehicle1.getContract() == null ||  vehicle1.getContract().getTimeExpired().before(new Date())){
                    vehicle1.setVehicleType(vehicle.getVehicleType());
                    vehicle1.setContract(newContract);
                    vehicleService.saveVehicle(vehicle1);
                }

            }


        }
        contractRepo.save(newContract);
    }
        @Override
        public List<Contract> getAllContract(){
            return contractRepo.findAll();
        }

    public Contract findById(Long id) {
        return contractRepo.findByContractID(id);
    }

    public void deleteContract(Long id) {
        Contract newContract = this.findById(id);
        List<Vehicle> vehicleListDb = newContract.getVehicles();
        for (Vehicle vehicle : vehicleListDb) {
            vehicle.setContract(null);
        }
        vehicleService.saveAllVehicle(vehicleListDb);
        contractRepo.deleteById(id);
    }
}
