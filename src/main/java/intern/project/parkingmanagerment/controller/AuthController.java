package intern.project.parkingmanagerment.controller;
import intern.project.parkingmanagerment.dto.UserDto;
import intern.project.parkingmanagerment.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AuthController {
    @Autowired
    UserServiceImpl userService;
    @GetMapping("/")
    public String defaultPage(){
        return "index";
    }
    @GetMapping("/login")
    public String LoginPage(){
        return "login";
    }
    @GetMapping("/register")
    public String RegisterPage(Model model){
        model.addAttribute("user",new UserDto());
        return "register";
    }
    @PostMapping("/register")
    public String Register(@ModelAttribute("user") UserDto userDto,
                           BindingResult result){
        /*boolean checkEmail = userService.existsByEmail(userDto.getEmail());
        if(checkEmail){
            result.rejectValue("email",null,"There is already an account registered with the same email");
        }*/

           userService.createUser(userDto);

        return "redirect:/login";
    }
    @GetMapping("/userInfo")
    public String getUserInfo(Principal principal){
        return "userInfo";
    }
}
