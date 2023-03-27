package intern.project.parkingmanagerment.service.impl;

import intern.project.parkingmanagerment.dto.VehicleDto;
import intern.project.parkingmanagerment.model.Contract;
import intern.project.parkingmanagerment.model.Vehicle;
import intern.project.parkingmanagerment.repositories.VehicleRepository;
import intern.project.parkingmanagerment.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

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
}
