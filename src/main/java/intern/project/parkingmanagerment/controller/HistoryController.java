package intern.project.parkingmanagerment.controller;

import intern.project.parkingmanagerment.dto.HistoryDto;
import intern.project.parkingmanagerment.model.History;
import intern.project.parkingmanagerment.service.HistoryService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @GetMapping("/check")
    public String getVehicle(Model model, @RequestParam String vehiclePlace, @RequestParam String cardId) {

        // check: history has timeOut null and cardId = cardId

        // if null create new history base cardId, vehiclePlace

        // if not null fill timeOut and check vehicle has contract

            // if it has the contract check time expired

                // not expired -> do nothing

                // expired -> delete all contract in vehicle has this contract and create new invoice

            // if it hasn't the contract create new invoice base type vehicle and time(day)
        model.addAttribute("type", "adadad");

        return "home";
    }

    @RequestMapping("/new-history")
    public String createHistory(Model model, @ModelAttribute("historyDto")HistoryDto historyDto) {
        if(historyDto.getTimeOut() == null) {

        }
        History history = historyService.newHistoryByHistoryDto(historyDto);
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String getAllHistory(Model model) {

        List<HistoryDto> historyDtoList = historyService.loadMoreHistoryDto(0L);
        model.addAttribute("historyList", historyDtoList);

        return "list-history";
    }


}
