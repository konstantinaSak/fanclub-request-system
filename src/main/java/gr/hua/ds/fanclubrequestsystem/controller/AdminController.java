package gr.hua.ds.fanclubrequestsystem.controller;

import gr.hua.ds.fanclubrequestsystem.entity.Fan;
import gr.hua.ds.fanclubrequestsystem.service.AdminService;
import gr.hua.ds.fanclubrequestsystem.entity.Fanclub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/fan-clubs")
    public ModelAndView getFanclubs(Model model) {
        model.addAttribute("fanclubs", adminService.getFanclubs());

        ModelAndView fanclubsMAV = new ModelAndView();
        fanclubsMAV.setViewName("admin");
        return fanclubsMAV;
    }

    @GetMapping("/fan-clubs/new")
    public ModelAndView addNewFanClubForm(Model model) {
        //create Fan object to hold fan form data
        Fanclub fanclub = new Fanclub();

        model.addAttribute("fanclub", fanclub);

        ModelAndView newFanclubMAV = new ModelAndView();
        newFanclubMAV.setViewName("admin_add_fanclub");
        return newFanclubMAV;
    }

    @PostMapping("/fan-clubs")
    public ModelAndView registerNewFanclub(@ModelAttribute("fanclub") Fanclub fanclub) {
        adminService.addNewFanclub(fanclub);

        ModelAndView fanclubMAV = new ModelAndView();
        fanclubMAV.setViewName("redirect:/api/admin/fan-clubs");
        return fanclubMAV;
    }

    @GetMapping(path = "/fan-clubs/{fanclubID}")
    public ModelAndView deleteFanclub(@PathVariable("fanclubID") int fanclubID) {
        adminService.deleteFanclub(fanclubID);

        ModelAndView fanclubMAV = new ModelAndView();
        fanclubMAV.setViewName("redirect:/api/admin/fan-clubs");
        return fanclubMAV;
    }

}
