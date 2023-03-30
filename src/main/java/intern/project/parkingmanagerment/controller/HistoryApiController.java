package intern.project.parkingmanagerment.controller;

import intern.project.parkingmanagerment.dto.HistoryDto;
import intern.project.parkingmanagerment.dto.VehicleDto;
import intern.project.parkingmanagerment.model.Card;
import intern.project.parkingmanagerment.model.History;
import intern.project.parkingmanagerment.model.Vehicle;
import intern.project.parkingmanagerment.model.VehicleType;
import intern.project.parkingmanagerment.repositories.HistoryRepository;
import intern.project.parkingmanagerment.service.CardService;
import intern.project.parkingmanagerment.service.HistoryService;
import intern.project.parkingmanagerment.service.VehicleService;
import intern.project.parkingmanagerment.service.VehicleTypeService;
import intern.project.parkingmanagerment.util.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class HistoryApiController {

    private final Logger logger = LogFactory.getLogger();
    @Autowired
    VehicleTypeService vehicleTypeService;
    @Autowired
    CardService cardService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    HistoryService historyService;


    @GetMapping("/vehicleType")
    public ResponseEntity<?> getAllVehicleType() {
        try {
            return ResponseEntity.ok().body(vehicleTypeService.getAllVehicleType());
        } catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }
    }


    @GetMapping("/history")
    public ResponseEntity<?> getHistory(@RequestParam Long cardId) {
        try {
//            logger.info("cardId: " + cardId + "\n card: " + cardService.findById(cardId));
            Card card = cardService.findById(cardId);
            if(card == null) {
                logger.info("cardId: " + cardId);
                return ResponseEntity.ok().body(null);
            }
            HistoryDto historyCheck = historyService.checkHistory(cardId);
            if (historyCheck == null) {
                historyCheck = new HistoryDto();
                if(card.getLicensePlate() != null) {
                    Vehicle vehicle = vehicleService.findByLicensePlate(card.getLicensePlate());
                    historyCheck.setVehicleTypeId(vehicle.getVehicleType().getVehicleTypeId());
                    historyCheck.setLicensePlate(vehicle.getLicensePlate());
                }
                historyCheck.setCardId(cardId);
                historyCheck.setTimeIn(new Date());
            }
            return ResponseEntity.ok().body(historyCheck);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }
    }

    @GetMapping("/check-vehicle")
    public ResponseEntity<?> getVehicle(@RequestParam String licensePlate) {
        try {
            VehicleDto vehicleDto = vehicleService.findVehicleDtoByLicensePlate(licensePlate);
            return ResponseEntity.ok().body(vehicleDto);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }
    }

    @PostMapping("/new-history")
    public ResponseEntity<?> getVehicle(@RequestBody HistoryDto historyDto) {
        try {
            logger.info("historyDto " + historyDto);
            historyDto = historyService.newHistoryByHistoryDto2(historyDto);
            return ResponseEntity.ok().body(historyDto);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }
    }

    @GetMapping("/list-history")
    public ResponseEntity<?> getAllHistory() {
        try {
            Vehicle vehicle = new Vehicle();

            VehicleType vehicleType = vehicleTypeService.getVehicleType(1L);
            logger.info(vehicleType);
            vehicle.setVehicleType(vehicleType);

            vehicle = vehicleService.saveVehicle(vehicle);

            logger.info("vehicle " + vehicle);

            return ResponseEntity.ok().body(vehicle);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }
    }



    @GetMapping("/load-more")
    public ResponseEntity<?> getHistory2(@RequestParam Long page) {
        try {
            page = page * 6;
            return ResponseEntity.ok().body(historyService.loadMoreHistoryDto(page));
        } catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }
    }


}
