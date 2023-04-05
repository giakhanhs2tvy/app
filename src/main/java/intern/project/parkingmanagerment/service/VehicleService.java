package intern.project.parkingmanagerment.service;

import intern.project.parkingmanagerment.dto.VehicleDto;
import intern.project.parkingmanagerment.model.Contract;
import intern.project.parkingmanagerment.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle findByLicensePlate(String licensePlate);
    List<Vehicle> findAllByContract(Contract contract);

    VehicleDto findVehicleDtoByLicensePlate(String licensePlate);

    Vehicle saveVehicle(Vehicle vehicle);
   // Vehicle createVehicle(Vehicle vehicle);
}
