package intern.project.parkingmanagerment.controller;

import intern.project.parkingmanagerment.model.Customer;
import intern.project.parkingmanagerment.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class CustomerController {
    @Autowired
    CustomerServiceImpl cusService;

    @GetMapping("/getList")
    public String listCustomer(Model model){
        List<Customer> list = cusService.findAll();
        model.addAttribute("listC",list);
        return "list-customer";
    }
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("customer",new Customer());
        return "add-customer";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        Customer customer = cusService.findById(id);
        model.addAttribute("customer1",customer);
        return "edit-customer";
    }
    @PostMapping("/edit/{id}")
    public String update(Customer customer,@PathVariable Long id,Model model){
        customer.setCustomerID(id);
        cusService.updateCustomer(customer);
        model.addAttribute("listC",cusService.findAll());
        return "redirect:/user/getList";
    }
    @PostMapping ("/save")
    public String addCustomer(@Valid Customer customer , BindingResult result){
        if(result.hasErrors()){
            return "add-customer";
        }
        Customer existCustomer = cusService.findByEmail(customer.getEmail());
        if(existCustomer!=null){
            result.rejectValue("email", null, "Email customer  already exists");
            return "add-customer";
        }
        cusService.saveCustomer(customer);

        return "redirect:/user/getList";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,Model model){
        cusService.deleteCustomer(id);
        model.addAttribute("listC",cusService.findAll());
        return  "redirect:/user/getList";
    }

}
