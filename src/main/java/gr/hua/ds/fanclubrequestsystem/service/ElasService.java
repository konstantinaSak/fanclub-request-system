package gr.hua.ds.fanclubrequestsystem.service;

import gr.hua.ds.fanclubrequestsystem.PDFexporter.ElasRequestPDFExporter;
import gr.hua.ds.fanclubrequestsystem.entity.ELAS;
import gr.hua.ds.fanclubrequestsystem.entity.RequestELAS;
import gr.hua.ds.fanclubrequestsystem.repository.ELASRepository;
import gr.hua.ds.fanclubrequestsystem.repository.RequestELASRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ElasService {

    private final ELASRepository elasRepository;
    private final RequestELASRepository requestElasRepository;

    public ElasService(ELASRepository elasRepository, RequestELASRepository elasRequestRepository) {
        this.elasRepository = elasRepository;
        this.requestElasRepository = elasRequestRepository;
    }

    public List<RequestELAS> getElasRequests() {

        //Find all requests belonging to the ELAS user currently logged in
        String elasName = SecurityContextHolder.getContext().getAuthentication().getName();
        ELAS elas = elasRepository.findElasByUsername(elasName);
        List<RequestELAS> elasRequestsList = requestElasRepository.findRequestElasByElasID(elas.getID());

        //Remove Approved or Declined requests from list
        List<RequestELAS> elasRequestsRemoveList = new ArrayList<>();
        for (int i = 0; i < elasRequestsList.size(); i++) {
            if(elasRequestsList.get(i).getState().equals("Declined")) {
                elasRequestsRemoveList.add(elasRequestsList.get(i));
            }
            if(elasRequestsList.get(i).getState().equals("Approved")) {
                elasRequestsRemoveList.add(elasRequestsList.get(i));
            }
        }
        elasRequestsList.removeAll(elasRequestsRemoveList);

        //Return list with only Pending requests
        return elasRequestsList;
    }

    public RequestELAS getElasRequestByID(int elasRequest) {
        return requestElasRepository.findById(elasRequest).get();
    }

    @Transactional
    public void approveRequest(HttpServletResponse response, int requestID) throws IOException {

        //Check if ELAS request exists
        boolean exists = requestElasRepository.existsById(requestID);
        if(!exists) {
            throw new IllegalStateException("GGA Request with ID: " + requestID + " does not exist");
        }

        //PDF
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename= ELAS_request_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        RequestELAS request = requestElasRepository.findById(requestID).orElseThrow(() -> new IllegalStateException("Request with ID " + requestID + " does not exist"));

        ElasRequestPDFExporter exporter = new ElasRequestPDFExporter(request);
        exporter.export(response);

        //Request is now showing as Approved
        request.setState("Approved");
    }

    @Transactional
    public void declinedRequest(int requestID) {

        //Check if ELAS request exists
        boolean exists = requestElasRepository.existsById(requestID);
        if(!exists) {
            throw new IllegalStateException("GGA Request with ID: " + requestID + " does not exist");
        }

        //Request is now showing as Declined
        RequestELAS request = requestElasRepository.findById(requestID).orElseThrow(() -> new IllegalStateException("Request with ID " + requestID + " does not exist"));
        request.setState("Declined");
    }

}
