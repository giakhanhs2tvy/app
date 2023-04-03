package intern.project.parkingmanagerment.service;

import intern.project.parkingmanagerment.dto.HistoryDto;
import intern.project.parkingmanagerment.model.History;

import java.util.List;

public interface HistoryService {
    HistoryDto checkHistory(Long cardId);
    History newHistory(History history);
    History checkHistoryEntity(Long cardId);
    History newHistoryByHistoryDto(HistoryDto historyDto);
    HistoryDto newHistoryByHistoryDto2(HistoryDto historyDto);
    List<HistoryDto> listHistoryDto();
    List<HistoryDto> listHistoryDtoByDate();
    List<HistoryDto> loadMoreHistoryDto(Long page);
    List<HistoryDto> listHistoryDtoByVehicleTypeId(Long vehicleTypeId);
}
