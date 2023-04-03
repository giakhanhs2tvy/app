package intern.project.parkingmanagerment.controller;

import intern.project.parkingmanagerment.service.impl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VehicleController {
    @Autowired
    VehicleServiceImpl vehicleService;

}
