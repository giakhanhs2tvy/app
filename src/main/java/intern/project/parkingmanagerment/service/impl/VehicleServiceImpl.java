package intern.project.parkingmanagerment.service.impl;

import intern.project.parkingmanagerment.dto.VehicleDto;
import intern.project.parkingmanagerment.exceptions.ExistException;
import intern.project.parkingmanagerment.exceptions.NotFoundException;
import intern.project.parkingmanagerment.model.Contract;
import intern.project.parkingmanagerment.model.Vehicle;
import intern.project.parkingmanagerment.repositories.ContractRepository;
import intern.project.parkingmanagerment.repositories.VehicleRepository;
import intern.project.parkingmanagerment.repositories.VehicleTypeRepository;
import intern.project.parkingmanagerment.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    VehicleTypeRepository vehicleTypeRepo;
    @Autowired
    ContractRepository contractRepo;
    @Override
    public Vehicle findByLicensePlate(String licensePlate) {
        return vehicleRepository.getVehicleByLicensePlate(licensePlate);
    }

    @Override
    public List<Vehicle> findAllByContract(Contract contract) {
        return vehicleRepository.findAllVehicleByContract(contract);
    }

    @Override
    public VehicleDto findVehicleDtoByLicensePlate(String licensePlate) {
        Vehicle vehicle = vehicleRepository.getVehicleByLicensePlate(licensePlate);
        if(vehicle == null) {
            return null;
        }
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleType(vehicle.getVehicleType());
        vehicleDto.setLicensePlate(vehicle.getLicensePlate());
        return vehicleDto;
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void saveAllVehicle(List<Vehicle> vehicleList) {
        vehicleRepository.saveAll(vehicleList);
    }

    //@Override
    /*public Vehicle createVehicle(Vehicle vehicle) {
        *//*Vehicle vehicle1 = vehicleRepository.getVehicleByLicensePlate(vehicle.getLicensePlate());
        if(vehicle1 !=null){
            throw new ExistException("Vehicle is exist");
        }
        else
            vehicle1.setVehicleType(vehicleTypeRepo.findByVehicleTypeID(vehicle.getVehicleType().getVehicleTypeID()));
            vehicle1.setContract(contractRepo.findByContractID(vehicle.getContract().getContractID()));
        return vehicle1;*//*
    }*/
}
