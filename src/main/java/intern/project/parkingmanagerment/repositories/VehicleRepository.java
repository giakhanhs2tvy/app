package intern.project.parkingmanagerment.repositories;

import intern.project.parkingmanagerment.model.Contract;
import intern.project.parkingmanagerment.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Vehicle getVehicleByLicensePlate(String licensePlate);

    List<Vehicle> findAllVehicleByContract(Contract contract);

}
