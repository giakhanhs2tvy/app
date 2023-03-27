package intern.project.parkingmanagerment.service.impl;

import intern.project.parkingmanagerment.dto.HistoryDto;
import intern.project.parkingmanagerment.model.History;
import intern.project.parkingmanagerment.model.Vehicle;
import intern.project.parkingmanagerment.repositories.HistoryRepository;
import intern.project.parkingmanagerment.service.CardService;
import intern.project.parkingmanagerment.service.HistoryService;
import intern.project.parkingmanagerment.service.VehicleService;
import intern.project.parkingmanagerment.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    VehicleTypeService vehicleTypeService;

    @Autowired
    CardService cardService;




    @Override
    public HistoryDto checkHistory(Long cardId) {
        History history = historyRepository.findHistoriesByCardIdAndTimeOutNull(cardId);
        if(history == null) {
            return null;
        }
        HistoryDto historyDto = new HistoryDto();
        historyDto.setLicensePlate(history.getVehicle().getLicensePlate());
        historyDto.setCardId(cardId);
        historyDto.setVehicleTypeId(history.getVehicle().getVehicleType().getVehicleTypeID());
        historyDto.setTimeIn(history.getTimeIn());
        historyDto.setTimeOut(new Date());
        return historyDto;
    }

    @Override
    public History checkHistoryEntity(Long cardId) {
        return historyRepository.findHistoriesByCardIdAndTimeOutNull(cardId);
    }

    @Override
    public History newHistory(History history) {

        return historyRepository.save(history);
    }

    @Override
    public History newHistoryByHistoryDto(HistoryDto historyDto) {
        History historyCheck = checkHistoryEntity(historyDto.getCardId());
        if (historyCheck == null) {
            Vehicle vehicle = vehicleService.findByLicensePlate(historyDto.getLicensePlate());
            if(vehicle == null) {
                vehicle = new Vehicle();
                vehicle.setVehicleType(vehicleTypeService.getVehicleType(historyDto.getVehicleTypeId()));
                vehicle = vehicleService.saveVehicle(vehicle);
            }
            historyCheck.setCard(cardService.findById(historyDto.getCardId()));
            historyCheck.setVehicle(vehicle);
            historyCheck.setTimeIn(historyDto.getTimeIn());
        }
        historyCheck.setTimeOut(historyDto.getTimeOut());
        return historyRepository.save(historyCheck);
    }
}
