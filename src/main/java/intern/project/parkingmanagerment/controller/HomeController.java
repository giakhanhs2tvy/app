package intern.project.parkingmanagerment.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class HomeController {
 
	@GetMapping("/")
	public String home(Model model) {
 
		model.addAttribute("text", "Spring2222  2222 Boot huhuhuh DevTools example");
 
		return "home";
	}
}