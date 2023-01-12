package gr.hua.ds.fanclubrequestsystem.controller;

import gr.hua.ds.fanclubrequestsystem.Service.AdminService;
import gr.hua.ds.fanclubrequestsystem.entity.Fanclub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Fanclub> getFanclubs() {
        return adminService.getFanclubs();
    }

    @PostMapping("/fan-clubs")
    public void registerNewFanclub(@RequestBody Fanclub fanclub) {
        adminService.addNewFanclub(fanclub);
    }

    @DeleteMapping(path = "/fan-clubs/{fanclubID}")
    public void deleteFanclub(@PathVariable("fanclubID") int fanclubID) {
        adminService.deleteFanclub(fanclubID);
    }

}
