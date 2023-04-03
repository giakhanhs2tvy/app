package intern.project.parkingmanagerment.service.impl;

import intern.project.parkingmanagerment.model.VehicleType;
import intern.project.parkingmanagerment.repositories.VehicleTypeRepository;
import intern.project.parkingmanagerment.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @Override
    public List<VehicleType> getAllVehicleType() {
        return vehicleTypeRepository.findAll();
    }

    @Override
    public VehicleType getVehicleType(Long vehicleTypeId) {
        return vehicleTypeRepository.findByVehicleTypeId(vehicleTypeId);
    }
}
