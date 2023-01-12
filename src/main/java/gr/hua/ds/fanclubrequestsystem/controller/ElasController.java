package gr.hua.ds.fanclubrequestsystem.controller;

import com.lowagie.text.DocumentException;
import gr.hua.ds.fanclubrequestsystem.Service.ElasService;
import gr.hua.ds.fanclubrequestsystem.entity.RequestELAS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/elas")
public class ElasController {

    private final ElasService elasService;

    @Autowired
    public ElasController(ElasService elasService) {
        this.elasService = elasService;
    }

    @GetMapping("/requests")
    public List<RequestELAS> getElasRequests() {
        return elasService.getElasRequests();
    }

    @PutMapping("/requests/{requestID}/approved")
    public void approvedRequest(HttpServletResponse response, @PathVariable("requestID") int requestID) throws DocumentException, IOException {
        elasService.approvedRequest(response, requestID);
    }

    @PutMapping(path = "/requests/{requestID}/declined")
    public void declinedRequest(@PathVariable("requestID") int requestID) {
        elasService.declinedRequest(requestID);
    }

}
