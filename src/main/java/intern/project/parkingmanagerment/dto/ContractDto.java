package intern.project.parkingmanagerment.dto;

import intern.project.parkingmanagerment.model.ContractType;
import intern.project.parkingmanagerment.model.Customer;
import intern.project.parkingmanagerment.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContractDto {
    private Customer customer;
    private ContractType contractType;
    private List<Vehicle> vehicleList;
    public void addVehicle(Vehicle vehicle){
        this.vehicleList.add(vehicle);
    }
}
