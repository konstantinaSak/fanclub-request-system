package gr.hua.ds.fanclubrequestsystem.controller;

import gr.hua.ds.fanclubrequestsystem.Service.FanclubService;
import gr.hua.ds.fanclubrequestsystem.entity.Fan;
import gr.hua.ds.fanclubrequestsystem.entity.RequestELAS;
import gr.hua.ds.fanclubrequestsystem.entity.RequestGGA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/fan-club")
public class FanclubController {

    private final FanclubService fanclubService;

    @Autowired
    public FanclubController(FanclubService fanclubService) {
        this.fanclubService = fanclubService;
    }

    @GetMapping("/fans")
    public List<Fan> getFans() {
        return fanclubService.getFans();
    }

    @PostMapping("/fans")
    public void registerNewFan(@RequestBody Fan fan) {
        fanclubService.addNewFan(fan);
    }

    @PutMapping("/fans/{fanID}")
    public void updateFan(
            @PathVariable("fanID") int fanID,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) int AFM) {
        fanclubService.updateFan(fanID, firstName, lastName, AFM);
    }

    @DeleteMapping(path = "/fans/{fanID}")
    public void deleteFan(@PathVariable("fanID") int fanID) {
        fanclubService.deleteFan(fanID);
    }

    @GetMapping("/elas-requests")
    public List<RequestELAS> getElasRequests() {
        return fanclubService.getElasRequests();
    }

    @PostMapping("/elas-requests")
    public void createNewElasRequest(@RequestBody RequestELAS requestELAS,
                                 @RequestParam(required = false) int ElasID) {
        fanclubService.addNewElasRequest(requestELAS, ElasID);
    }

    @GetMapping("/gga-requests")
    public List<RequestGGA> getGGARequests() {
        return fanclubService.getGGARequests();
    }

    @PostMapping("/gga-requests")
    public void createNewGGARequest(@RequestBody RequestGGA requestGGA) {
        fanclubService.addNewGGARequest(requestGGA);
    }

}
