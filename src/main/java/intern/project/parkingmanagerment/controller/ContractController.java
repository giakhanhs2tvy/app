package intern.project.parkingmanagerment.controller;

import intern.project.parkingmanagerment.dto.ContractDto;
import intern.project.parkingmanagerment.model.*;
import intern.project.parkingmanagerment.service.ContractTypeService;
import intern.project.parkingmanagerment.service.VehicleTypeService;
import intern.project.parkingmanagerment.service.impl.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContractController {

    @Autowired
    ContractServiceImpl contractService;
    @Autowired
    ContractTypeService contractTypeService;
    @Autowired
    VehicleTypeService vehicleTypeService;
    @ModelAttribute("contractType")
    public Iterable<ContractType> getAllType(){
        return contractTypeService.getAllContract();
    }
    @ModelAttribute("vehicleType")
    public Iterable<VehicleType> getAllVType(){
        return vehicleTypeService.getAllVehicleType();
    }
    @GetMapping("/addContract")
    public String addForm(Model model){
        List<Vehicle> vehicleList = new ArrayList<>();
        Contract contractForm = new Contract();
        for(int i=0;i<5;i++){
            vehicleList.add(new Vehicle());
        }
        contractForm.setVehicles(vehicleList);
        model.addAttribute("contract",contractForm);
        return "contract";
    }

    @PostMapping("/addContract")
    public String addContract(@ModelAttribute("contract") Contract contract ){

        contractService.saveContract(contract);
        return "redirect:/home";
    }
}
