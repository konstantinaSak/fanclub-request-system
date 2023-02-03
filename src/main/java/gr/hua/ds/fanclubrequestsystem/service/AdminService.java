package gr.hua.ds.fanclubrequestsystem.service;

import gr.hua.ds.fanclubrequestsystem.entity.*;
import gr.hua.ds.fanclubrequestsystem.repository.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final FanclubRepository fanclubRepository;
    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final FanRepository fanRepository;
    private final RequestELASRepository requestELASRepository;
    private final RequestGGARepository requestGGARepository;



    public AdminService(FanclubRepository fanclubRepository, UserRepository userRepository, AuthoritiesRepository authoritiesRepository, FanRepository fanRepository, FanRepository fanRepository1, RequestELASRepository requestELASRepository, RequestGGARepository requestGGARepository) {
        this.fanclubRepository = fanclubRepository;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.fanRepository = fanRepository1;
        this.requestELASRepository = requestELASRepository;
        this.requestGGARepository = requestGGARepository;
    }

    public List<Fanclub> getFanclubs() {

        //Get all fanclubs saved to system
        List<Fanclub> fanclubsList = fanclubRepository.findAll();
        return fanclubsList;
    }

    public void addNewFanclub(Fanclub fanclub) {
        Optional<Fanclub> fanOptional = fanclubRepository.findFanclubByID(fanclub.getID());
        if(fanOptional.isPresent()) {
            throw new IllegalStateException("ID taken");
        }

        //Encrypt password and create a new fanclub
        String hashedPassword = BCrypt.hashpw(fanclub.getPassword(), BCrypt.gensalt(12));
        fanclub.setPassword(hashedPassword);
        fanclubRepository.save(fanclub);

        //Register fanclub as a new sytem User
        User user = new User(fanclub.getUsername(), fanclub.getPassword(), true);
        userRepository.save(user);

        //Set fanclub's role and rights on authorities table
        Authorities authorities = new Authorities(fanclub.getUsername(), "ROLE_FANCLUB");
        authoritiesRepository.save(authorities);
    }

    public void deleteFanclub(int fanclubID) {
        boolean exists = fanclubRepository.existsById(fanclubID);
        if(!exists) {
            throw new IllegalStateException("Fanclub with ID: " + fanclubID + " does not exist");
        }

        //Delete all Fanclub's Members-Fans
        List<Fan> fansList = fanRepository.findFanByFanclubID(fanclubID);
        for (int i = 0; i < fansList.size(); i++) {
            fanRepository.deleteById(fansList.get(i).getID());
        }

        //Delete all Fanclub's Requests to ELAS
        List<RequestELAS> elasRequestsList = requestELASRepository.findRequestELASsByFanclubID(fanclubID);
        for (int i = 0; i < elasRequestsList.size(); i++) {
            requestELASRepository.deleteById(elasRequestsList.get(i).getID());
        }

        //Delete all Fanclub's Requests to GGA
        List<RequestGGA> ggaRequestsList = requestGGARepository.findRequestGGAsByFanclubID(fanclubID);
        for (int i = 0; i < ggaRequestsList.size(); i++) {
            requestGGARepository.deleteById(ggaRequestsList.get(i).getID());
        }

        //Delete Fanclub from User and Authorities tables
        Fanclub fanclub = fanclubRepository.findFanclubByID2(fanclubID);
        userRepository.deleteById(fanclub.getUsername());
        authoritiesRepository.deleteById(fanclub.getUsername());

        //Delete Fanclub
        fanclubRepository.deleteById(fanclubID);
    }

}