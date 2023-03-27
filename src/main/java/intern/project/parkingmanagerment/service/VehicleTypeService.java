package intern.project.parkingmanagerment.service;

import intern.project.parkingmanagerment.model.VehicleType;

import java.util.List;

public interface VehicleTypeService {
    List<VehicleType> getAllVehicleType();

    VehicleType getVehicleType(Long vehicleTypeId);
}
