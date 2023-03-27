package intern.project.parkingmanagerment.dto;

import intern.project.parkingmanagerment.model.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDto {
    private String licensePlate;
    private VehicleType vehicleType;
}
