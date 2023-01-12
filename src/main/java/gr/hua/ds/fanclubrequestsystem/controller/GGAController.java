package gr.hua.ds.fanclubrequestsystem.controller;

import com.lowagie.text.DocumentException;
import gr.hua.ds.fanclubrequestsystem.Service.GGAService;
import gr.hua.ds.fanclubrequestsystem.entity.RequestGGA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<RequestGGA> getGGARequests() {
        return ggaService.getGGARequests();
    }

    @PutMapping("/requests/{requestID}/approved")
    public void approvedRequest(HttpServletResponse response, @PathVariable("requestID") int requestID) throws DocumentException, IOException {
        ggaService.approvedRequest(response, requestID);
    }

    @PutMapping(path = "/requests/{requestID}/declined")
    public void declinedRequest(@PathVariable("requestID") int requestID) {
        ggaService.declinedRequest(requestID);
    }

}
