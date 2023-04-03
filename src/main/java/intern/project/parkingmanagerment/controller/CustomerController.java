package intern.project.parkingmanagerment.controller;

import intern.project.parkingmanagerment.model.Customer;
import intern.project.parkingmanagerment.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    @Autowired
    CustomerServiceImpl cusService;

    @GetMapping("/user/addCustomer")
    public String addForm(Model model){
        model.addAttribute("customer",new Customer());
        return "add-customer";
    }

    @PostMapping ("/user/addCustomer")
    public String addCustomer(@ModelAttribute("customer") Customer customer ){
        cusService.saveCustomer(customer);
        return "redirect:/";
    }

}
