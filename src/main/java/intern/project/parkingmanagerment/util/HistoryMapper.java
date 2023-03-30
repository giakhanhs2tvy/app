package intern.project.parkingmanagerment.util;

import intern.project.parkingmanagerment.dto.HistoryDto;
import intern.project.parkingmanagerment.model.History;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryMapper {

    public static HistoryDto toDTO(History history) {
        HistoryDto historyDto = new HistoryDto();
        if (history.getInvoice() != null ) {
            historyDto.setPrice(history.getInvoice().getPrice());
        }

//        historyDto.setHasContract();
        historyDto.setLicensePlate(history.getVehicle().getLicensePlate());
        historyDto.setCardId(history.getCard().getCardId());
        historyDto.setTimeIn(history.getTimeIn());
        historyDto.setTimeOut(history.getTimeOut());
        historyDto.setVehicleTypeId(history.getVehicle().getVehicleType().getVehicleTypeId());
        historyDto.setVehicleType(history.getVehicle().getVehicleType().getName());
        return historyDto;
    }

    public static List<HistoryDto> toDTOList(List<History> historyList) {
        return historyList.stream().map(HistoryMapper::toDTO).collect(Collectors.toList());
    }
}
