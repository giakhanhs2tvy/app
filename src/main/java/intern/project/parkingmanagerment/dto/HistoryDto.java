package intern.project.parkingmanagerment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HistoryDto {

    Long cardId;
    String licensePlate;
    Long vehicleTypeId;
    Date timeIn;
    Date timeOut;

}
