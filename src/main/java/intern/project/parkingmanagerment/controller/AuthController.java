package intern.project.parkingmanagerment.controller;
import intern.project.parkingmanagerment.dto.UserDto;
import intern.project.parkingmanagerment.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public String getUserInfo(Principal principal,Model model){

        model.addAttribute("currentuser1",principal.getName());
        return "userInfo";
    }

    @GetMapping("/403")
    public String accessDenied(Principal user,Model model){
        if (user != null) {
            model.addAttribute("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addAttribute("msg",
                    "You do not have permission to access this page!");
        }
        return "403";
    }

    @GetMapping("/car")
    public String getCar(){
        return "car";

    }
}
