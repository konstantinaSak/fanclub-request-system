package gr.hua.ds.fanclubrequestsystem.controller;

import com.lowagie.text.DocumentException;
import gr.hua.ds.fanclubrequestsystem.service.ElasService;
import gr.hua.ds.fanclubrequestsystem.entity.RequestELAS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "api/elas")
public class ElasController {

    private final ElasService elasService;

    @Autowired
    public ElasController(ElasService elasService) {
        this.elasService = elasService;
    }

    @GetMapping("/requests")
    public ModelAndView getElasRequests(Model model) {
        model.addAttribute("elas", elasService.getElasRequests());

        ModelAndView elasMAV = new ModelAndView();
        elasMAV.setViewName("elas");
        return elasMAV;
    }

    @GetMapping("/requests/approve/{requestID}")
    public ModelAndView downloadRequestPage(@PathVariable("requestID") int requestID, Model model) {
        model.addAttribute("request", elasService.getElasRequestByID(requestID));

        ModelAndView approveMAV = new ModelAndView();
        approveMAV.setViewName("elas_download_request");
        return approveMAV;
    }

    @PostMapping("/requests/approve/{requestID}")
    public void approveRequest(HttpServletResponse response, @PathVariable("requestID") int requestID, @ModelAttribute("request") RequestELAS request) throws DocumentException, IOException {
        elasService.approveRequest(response, requestID);
    }

    @GetMapping("/requests/reject/{requestID}")
    public ModelAndView rejectRequestPage(@PathVariable("requestID") int requestID, Model model) {
        model.addAttribute("request", elasService.getElasRequestByID(requestID));

        ModelAndView rejectMAV = new ModelAndView();
        rejectMAV.setViewName("elas_reject_request");
        return rejectMAV;
    }

    @PostMapping(path = "/requests/reject/{requestID}")
    public ModelAndView rejectRequest(@PathVariable("requestID") int requestID) {
        elasService.declinedRequest(requestID);

        ModelAndView requestsMAV = new ModelAndView();
        requestsMAV.setViewName("redirect:/api/elas/requests");
        return requestsMAV;
    }

}
