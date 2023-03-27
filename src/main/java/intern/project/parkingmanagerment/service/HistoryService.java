package intern.project.parkingmanagerment.service;

import intern.project.parkingmanagerment.dto.HistoryDto;
import intern.project.parkingmanagerment.model.History;

public interface HistoryService {
    HistoryDto checkHistory(Long cardId);
    History newHistory(History history);
    History checkHistoryEntity(Long cardId);
    History newHistoryByHistoryDto(HistoryDto historyDto);
}
