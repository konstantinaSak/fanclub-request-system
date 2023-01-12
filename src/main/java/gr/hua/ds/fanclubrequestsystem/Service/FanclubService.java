package gr.hua.ds.fanclubrequestsystem.Service;

import gr.hua.ds.fanclubrequestsystem.entity.*;
import gr.hua.ds.fanclubrequestsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class FanclubService {

    private final FanRepository fanRepository;
    private final FanclubRepository fanclubRepository;
    private final RequestELASRepository requestELASRepository;
    private final ELASRepository ELASRepository;
    private final RequestGGARepository requestGGARepository;
    private final GGARepository GGARepository;

    @Autowired
    public FanclubService(FanRepository fanRepository, FanclubRepository fanclubRepository, RequestELASRepository requestELASRepository, ELASRepository elasRepository, RequestGGARepository requestGGARepository, GGARepository ggaRepository) {
        this.fanRepository = fanRepository;
        this.fanclubRepository = fanclubRepository;
        this.requestELASRepository = requestELASRepository;
        this.ELASRepository = elasRepository;
        this.requestGGARepository = requestGGARepository;
        this.GGARepository = ggaRepository;
    }

    public List<Fan> getFans() {
        //Find all members-fans of fanclub currently logged in
        String fanclubName = SecurityContextHolder.getContext().getAuthentication().getName();
        Fanclub fanclub = fanclubRepository.findFanclubByUsername(fanclubName);
        List<Fan> fansList = fanRepository.findFanByFanclubID(fanclub.getID());
        return fansList;
    }

    public void addNewFan(Fan fan) {
        Optional<Fan> fanOptional = fanRepository.findFanByID(fan.getID());
        if(fanOptional.isPresent()) {
            throw new IllegalStateException("ID taken");
        }

        //Add a new member-fan to the fanclub
        String fanclubName = SecurityContextHolder.getContext().getAuthentication().getName();
        Fanclub fanclub = fanclubRepository.findFanclubByUsername(fanclubName);
        fan.setFanclub(fanclub);

        fanRepository.save(fan);
    }

    @Transactional
    public void updateFan(int fanID, String firstName, String lastName, int AFM) {
        Fan fan = fanRepository.findById(fanID).orElseThrow(() -> new IllegalStateException("Fan with ID: " + fanID + " does not exist"));

        //Check for valid inputs, if pass change attributes value
        if(firstName!=null && firstName.length()>0 && !Objects.equals(fan.getFirstName(), firstName)) {
            fan.setFirstName(firstName);
        }

        if(lastName!=null && lastName.length()>0 && !Objects.equals(fan.getLastName(), lastName)) {
            fan.setLastName(lastName);
        }

        int length = String.valueOf(AFM).length();
        if(length == 9){
            fan.setAFM(AFM);
        }

    }

    public void deleteFan(int fanID) {
        boolean exists = fanRepository.existsById(fanID);
        if(!exists) {
            throw new IllegalStateException("Fan with ID: " + fanID + " does not exist");
        }

        //Delete a member-fan from fanclub
        fanRepository.deleteById(fanID);
    }

    public List<RequestELAS> getElasRequests() {

        //Get all requests tha the currently logged in fanclub has sent to ELAS
        String fanclubName = SecurityContextHolder.getContext().getAuthentication().getName();
        Fanclub fanclub = fanclubRepository.findFanclubByUsername(fanclubName);
        List<RequestELAS> elasRequestsList = requestELASRepository.findRequestELASsByFanclubID(fanclub.getID());
        return elasRequestsList;
    }

    public void addNewElasRequest(RequestELAS requestELAS, int ElasID) {
        Optional<RequestELAS> requestElasOptional = requestELASRepository.findRequestELASByID(requestELAS.getID());
        if(requestElasOptional.isPresent()) {
            throw new IllegalStateException("ID taken");
        }

        //Find the fanclub that is sending the request
        String fanclubName = SecurityContextHolder.getContext().getAuthentication().getName();
        Fanclub fanclub = fanclubRepository.findFanclubByUsername(fanclubName);
        requestELAS.setFanclub(fanclub);

        //Find ELAS user to send the request
        ELAS ELAS = ELASRepository.findElasByID(ElasID).orElseThrow(() -> new IllegalStateException("ELAS with ID: " + ElasID + " does not exist"));
        requestELAS.setELAS(ELAS);

        //Set request's date as the current date
        Date date = new Date();
        requestELAS.setDate(date);

        //Request is shown as Pending
        requestELAS.setState("Pending");

        //Request created
        requestELASRepository.save(requestELAS);
    }

    public List<RequestGGA> getGGARequests() {

        //Get all requests tha the currently logged in fanclub has sent to GGA
        String fanclubName = SecurityContextHolder.getContext().getAuthentication().getName();
        Fanclub fanclub = fanclubRepository.findFanclubByUsername(fanclubName);
        List<RequestGGA> ggaRequestList = requestGGARepository.findRequestGGAsByFanclubID(fanclub.getID());
        return ggaRequestList;
    }

    public void addNewGGARequest(RequestGGA requestGGA) {
        Optional<RequestGGA> requestGGAOptional = requestGGARepository.findRequestGGAByID(requestGGA.getID());
        if(requestGGAOptional.isPresent()) {
            throw new IllegalStateException("ID taken");
        }

        //Find the fanclub that is sending the request
        String fanclubName = SecurityContextHolder.getContext().getAuthentication().getName();
        Fanclub fanclub = fanclubRepository.findFanclubByUsername(fanclubName);
        requestGGA.setFanclub(fanclub);

        //Find GGA user to send the request
        GGA GGA = GGARepository.findGGAByID(771).orElseThrow(() -> new IllegalStateException("GGA with ID: " + 771 + " does not exist"));
        requestGGA.setGGA(GGA);

        //Set request's date as the current date
        Date date = new Date();
        requestGGA.setDate(date);

        //Request is shown as Pending
        requestGGA.setState("Pending");

        //Request created
        requestGGARepository.save(requestGGA);
    }


}
