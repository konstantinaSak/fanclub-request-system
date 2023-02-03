package gr.hua.ds.fanclubrequestsystem.controller;

import com.lowagie.text.DocumentException;
import gr.hua.ds.fanclubrequestsystem.service.GGAService;
import gr.hua.ds.fanclubrequestsystem.entity.RequestGGA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/gga")
public class GGAController {

    private final GGAService ggaService;

    @Autowired
    public GGAController(GGAService ggaService) {
        this.ggaService = ggaService;
    }

    @GetMapping("/requests")
    public ModelAndView getGGARequests(Model model) {
        model.addAttribute("gga", ggaService.getGGARequests());

        ModelAndView ggaMAV = new ModelAndView();
        ggaMAV.setViewName("gga");
        return ggaMAV;
    }

    @GetMapping("/requests/approve/{requestID}")
    public ModelAndView downloadRequestPage(@PathVariable("requestID") int requestID, Model model) {
        model.addAttribute("request", ggaService.getGGARequestByID(requestID));

        ModelAndView approveMAV = new ModelAndView();
        approveMAV.setViewName("gga_download_request");
        return approveMAV;
    }

    @PostMapping("/requests/approve/{requestID}")
    public void approvedRequest(HttpServletResponse response, @PathVariable("requestID") int requestID) throws DocumentException, IOException {
        ggaService.approvedRequest(response, requestID);
    }

    @GetMapping("/requests/reject/{requestID}")
    public ModelAndView rejectRequestPage(@PathVariable("requestID") int requestID, Model model) {
        model.addAttribute("request", ggaService.getGGARequestByID(requestID));

        ModelAndView rejectMAV = new ModelAndView();
        rejectMAV.setViewName("gga_reject_request");
        return rejectMAV;
    }

    @PostMapping(path = "/requests/reject/{requestID}")
    public ModelAndView rejectRequest(@PathVariable("requestID") int requestID) {
        ggaService.declinedRequest(requestID);

        ModelAndView requestsMAV = new ModelAndView();
        requestsMAV.setViewName("redirect:/api/gga/requests");
        return requestsMAV;
    }

}
