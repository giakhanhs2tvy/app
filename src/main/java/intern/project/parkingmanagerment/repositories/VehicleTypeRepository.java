package intern.project.parkingmanagerment.repositories;

import intern.project.parkingmanagerment.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
    VehicleType findByVehicleTypeID(Long vehicleTypeID);
}
