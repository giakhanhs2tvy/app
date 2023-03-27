package intern.project.parkingmanagerment.controller;

import intern.project.parkingmanagerment.dto.HistoryDto;
import intern.project.parkingmanagerment.dto.VehicleDto;
import intern.project.parkingmanagerment.service.HistoryService;
import intern.project.parkingmanagerment.service.VehicleService;
import intern.project.parkingmanagerment.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class VehicleApiController {

    @Autowired
    VehicleTypeService vehicleTypeService;
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
            HistoryDto historyCheck = historyService.checkHistory(cardId);
            if (historyCheck == null) {
                historyCheck = new HistoryDto();
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


}
