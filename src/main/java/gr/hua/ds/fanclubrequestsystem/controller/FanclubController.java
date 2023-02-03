package gr.hua.ds.fanclubrequestsystem.controller;

import gr.hua.ds.fanclubrequestsystem.service.FanclubService;
import gr.hua.ds.fanclubrequestsystem.entity.Fan;
import gr.hua.ds.fanclubrequestsystem.entity.RequestELAS;
import gr.hua.ds.fanclubrequestsystem.entity.RequestGGA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "api/fan-club")
public class FanclubController {

    private final FanclubService fanclubService;

    @Autowired
    public FanclubController(FanclubService fanclubService) {
        this.fanclubService = fanclubService;
    }

    @GetMapping("/fans")
    public ModelAndView getFans(Model model) {
        model.addAttribute("fans", fanclubService.getFans());

        ModelAndView fansMAV = new ModelAndView();
        fansMAV.setViewName("fanclub_fans");
        return fansMAV;
    }

    @GetMapping("/fans/new")
    public ModelAndView registerNewFanForm(Model model) {
        //create Fan object to hold fan form data
        Fan fan = new Fan();

        model.addAttribute("fan", fan);

        ModelAndView newFanMAV = new ModelAndView();
        newFanMAV.setViewName("fanclub_register_fan");
        return newFanMAV;
    }


    @PostMapping("/fans")
    public ModelAndView registerNewFan(@ModelAttribute("fan") Fan fan) {
        fanclubService.addNewFan(fan);

        ModelAndView fanMAV = new ModelAndView();
        fanMAV.setViewName("redirect:/api/fan-club/fans");
        return fanMAV;
    }

    @GetMapping("/fans/edit/{fanID}")
    public ModelAndView updateFanForm(@PathVariable("fanID") int fanID, Model model) {
        model.addAttribute("fan", fanclubService.getFanByID(fanID));

        ModelAndView editFanMAV = new ModelAndView();
        editFanMAV.setViewName("fanclub_edit_fan");
        return editFanMAV;
    }

    @PostMapping("/fans/{fanID}")
    public ModelAndView updateFan(@PathVariable("fanID") int fanID, @ModelAttribute("fan") Fan fan, Model model) {
        fanclubService.updateFan(fanID, fan);

        ModelAndView fanMAV = new ModelAndView();
        fanMAV.setViewName("redirect:/api/fan-club/fans");
        return fanMAV;
    }

    @GetMapping(path = "/fans/{fanID}")
    public ModelAndView deleteFan(@PathVariable("fanID") int fanID) {
        fanclubService.deleteFan(fanID);

        ModelAndView fanMAV = new ModelAndView();
        fanMAV.setViewName("redirect:/api/fan-club/fans");
        return fanMAV;
    }

    @GetMapping("/elas-requests")
    public ModelAndView getElasRequests(Model model) {
        model.addAttribute("elas_requests", fanclubService.getElasRequests());

        ModelAndView elasMAV = new ModelAndView();
        elasMAV.setViewName("fanclub_elas_requests");
        return elasMAV;
    }

    @GetMapping("/elas-requests/new")
    public ModelAndView createNewElasRequestForm(Model model) {
        //create Fan object to hold fan form data
        RequestELAS elasRequest = new RequestELAS();

        model.addAttribute("elasRequest", elasRequest);

        ModelAndView newElasRequest = new ModelAndView();
        newElasRequest.setViewName("fanclub_create_elas_request");
        return newElasRequest;
    }

    @PostMapping("/elas-requests")
    public ModelAndView createNewElasRequest(@ModelAttribute("elasRequest") RequestELAS elasRequest) {
        fanclubService.addNewElasRequest(elasRequest);

        ModelAndView elasRequestMAV = new ModelAndView();
        elasRequestMAV.setViewName("redirect:/api/fan-club/elas-requests");
        return elasRequestMAV;
    }

    @GetMapping("/gga-requests")
    public ModelAndView getGGARequests(Model model) {
        model.addAttribute("gga_requests", fanclubService.getGGARequests());

        ModelAndView ggaMAV = new ModelAndView();
        ggaMAV.setViewName("fanclub_gga_requests");
        return ggaMAV;
    }

    @GetMapping("/gga-requests/new")
    public ModelAndView createNewGGARequestForm(Model model) {
        //create Fan object to hold fan form data
        RequestGGA ggaRequest = new RequestGGA();

        model.addAttribute("ggaRequest", ggaRequest);

        ModelAndView newGGARequest = new ModelAndView();
        newGGARequest.setViewName("fanclub_create_gga_request");
        return newGGARequest;
    }

    @PostMapping("/gga-requests")
    public ModelAndView createNewGGARequest(@ModelAttribute("ggaRequest") RequestGGA ggaRequest) {
        fanclubService.addNewGGARequest(ggaRequest);

        ModelAndView ggaRequestMAV = new ModelAndView();
        ggaRequestMAV.setViewName("redirect:/api/fan-club/gga-requests");
        return ggaRequestMAV;
    }

}
