package intern.project.parkingmanagerment.controller;

import intern.project.parkingmanagerment.dto.ContractDto;
import intern.project.parkingmanagerment.model.*;
import intern.project.parkingmanagerment.service.ContractTypeService;
import intern.project.parkingmanagerment.service.VehicleTypeService;
import intern.project.parkingmanagerment.service.impl.ContractServiceImpl;
import intern.project.parkingmanagerment.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    CustomerServiceImpl customerService;
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
    public String addContract(@ModelAttribute("contract") Contract contract, BindingResult result ){
        Customer customer = customerService.findByName(contract.getCustomer().getName());
        if(customer==null){
            result.rejectValue("customer", null, " customer not exists");
            return "contract";
        }
        contractService.saveContract(contract);
        return "redirect:/list";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id,Model model){
        Contract contract = contractService.findById(id);
        model.addAttribute("contract" ,contract);
        return "edit-contract";
    }
    @PostMapping("/editContract/{id}")
    public String editContract(@PathVariable Long id,Contract contract,Model model){
        contract.setContractID(id);
        contractService.updateContract(contract);
        model.addAttribute("listContract",contractService.getAllContract());
        return "list-contract";
    }
    @GetMapping("/list")
    public String getList(Model model){
        List<Contract> contractlist= contractService.getAllContract();
        model.addAttribute("listContract",contractlist);
        return "list-contract";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id,Model model){
        contractService.deleteContract(id);
        model.addAttribute("listContract",contractService.getAllContract());
        return "list-contract";
    }
}
