package gr.hua.ds.fanclubrequestsystem.service;

import gr.hua.ds.fanclubrequestsystem.PDFexporter.GGARequestPDFExporter;
import gr.hua.ds.fanclubrequestsystem.entity.GGA;
import gr.hua.ds.fanclubrequestsystem.entity.RequestGGA;
import gr.hua.ds.fanclubrequestsystem.repository.GGARepository;
import gr.hua.ds.fanclubrequestsystem.repository.RequestGGARepository;
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
public class GGAService {

    private final GGARepository GGARepository;
    private final RequestGGARepository requestGGARepository;

    public GGAService(GGARepository ggaRepository, RequestGGARepository requestGGARepository) {
        this.GGARepository = ggaRepository;
        this.requestGGARepository = requestGGARepository;

    }


    public List<RequestGGA> getGGARequests() {

        //Find all requests belonging to the GGA user currently logged in
        String ggaName = SecurityContextHolder.getContext().getAuthentication().getName();
        GGA gga = GGARepository.findGGAByUsername(ggaName);
        List<RequestGGA> ggaRequestsList = requestGGARepository.findRequestGGAByGGAID(gga.getID());

        //Remove Approved or Declined requests from list
        List<RequestGGA> ggaRequestsRemoveList = new ArrayList<>();
        for (int i = 0; i < ggaRequestsList.size(); i++) {
            if(ggaRequestsList.get(i).getState().equals("Declined")) {
                ggaRequestsRemoveList.add(ggaRequestsList.get(i));
            }
            if(ggaRequestsList.get(i).getState().equals("Approved")) {
                ggaRequestsRemoveList.add(ggaRequestsList.get(i));
            }
        }
        ggaRequestsList.removeAll(ggaRequestsRemoveList);

        //Return list with only Pending requests
        return ggaRequestsList;
    }

    public RequestGGA getGGARequestByID(int ggaRequest) {
        return requestGGARepository.findById(ggaRequest).get();
    }

    @Transactional
    public void approvedRequest(HttpServletResponse response, int requestID) throws IOException {

        //Check if GGA request exists
        boolean exists = requestGGARepository.existsById(requestID);
        if(!exists) {
            throw new IllegalStateException("GGA Request with ID: " + requestID + " does not exist");
        }

        //PDF
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename= GGA_request_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        RequestGGA request = requestGGARepository.findById(requestID).orElseThrow(() -> new IllegalStateException("Request with ID " + requestID + " does not exist"));

        GGARequestPDFExporter exporter = new GGARequestPDFExporter(request);
        exporter.export(response);

        //Request is now showing as Approved
        request.setState("Approved");
    }

    @Transactional
    public void declinedRequest(int requestID) {

        //Check if GGA request exists
        boolean exists = requestGGARepository.existsById(requestID);
        if(!exists) {
            throw new IllegalStateException("GGA Request with ID: " + requestID + " does not exist");
        }

        //Request is now showing as Declined
        RequestGGA request = requestGGARepository.findById(requestID).orElseThrow(() -> new IllegalStateException("Request with ID " + requestID + " does not exist"));
        request.setState("Declined");
    }

}
