package gr.hua.ds.fanclubrequestsystem.controller;

import gr.hua.ds.fanclubrequestsystem.entity.Authorities;
import gr.hua.ds.fanclubrequestsystem.repository.AuthoritiesRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final AuthoritiesRepository authoritiesRepository;

    public HomeController(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

    @GetMapping("/")
    public ModelAndView index() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Authorities authorities = authoritiesRepository.findAuthoritiesByUsername(username);
        String Role = authorities.getAuthority();

        if (Role.equals("ROLE_FANCLUB")) {
            ModelAndView fanclubMAV = new ModelAndView();
            fanclubMAV.setViewName("redirect:/api/fan-club/fans");
            return fanclubMAV;
        } else if (Role.equals("ROLE_POLICE")) {
            ModelAndView elasMAV = new ModelAndView();
            elasMAV.setViewName("redirect:/api/elas/requests");
            return elasMAV;
        } else if (Role.equals("ROLE_GGA")) {
            ModelAndView ggaMAV = new ModelAndView();
            ggaMAV.setViewName("redirect:/api/gga/requests");
            return ggaMAV;
        } else {
            ModelAndView adminMAV = new ModelAndView();
            adminMAV.setViewName("redirect:/api/admin/fan-clubs");
            return adminMAV;
        }

    }

}
