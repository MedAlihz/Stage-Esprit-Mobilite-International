package com.example.ahmed.Controller;

import com.example.ahmed.Email.EmailService;
import com.example.ahmed.Entity.Candidature;
import com.example.ahmed.Interface.ICandidatureService;
import com.example.ahmed.Service.CandidatureService;
import com.example.ahmed.Service.PdfGenerationService;
import com.example.ahmed.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Candidature")
@Slf4j
@CrossOrigin("*")
public class CandidatureController {
    @Autowired
    CandidatureService candidatureService;

    @Autowired
    EmailService emailService;
    @Autowired
    PdfGenerationService pdfGenerationService;

    @PostMapping("/AddCandidature/{idOffre}")

    public Candidature AddandAssignCandidature(@RequestBody Candidature candidature, @PathVariable("idOffre") Integer idOffre) {
        Candidature C = candidatureService.AddAndAssignCandidatureToOffre(candidature, idOffre);
        return C;
    }

    @PostMapping("/AddCandidature/")

    public Candidature AddCandidature(@RequestBody Candidature candidature) {
        Candidature C = candidatureService.addCandidature(candidature);
        return C;
    }

    @PutMapping("/update-Candidature")
    public Candidature updateCandidature(@RequestBody Candidature c) {
        Candidature O = candidatureService.updateCandidature(c);
        return O;
    }

    @DeleteMapping("/remove-Candidature/{idCandidature}")
    public void removeCandidature(@PathVariable("idCandidature") Integer idCandidature) {

        candidatureService.removeCandidature(idCandidature);
    }

    @PostMapping("/assign-idCandidature-to-user/{idCandidature}/{IdUser}")
    public void assignCandidatureToUser(@PathVariable("idCandidature") Integer idCandidature,
                                        @PathVariable("IdUser") Integer IdUser) {
        candidatureService.assignCandidatureToUser(idCandidature, IdUser);

    }

    @GetMapping("/get-candidature/{idCandidature}")
    public Candidature getTicket(@PathVariable("idCandidature") Integer idCandidature) {

        return candidatureService.retreviveCandidature(idCandidature);
    }

    @GetMapping("retrevive-candiddatures")
    public List<Candidature> retrevive() {
        List<Candidature> l = candidatureService.retrieveAllCandidatures();
        return l;
    }

    @GetMapping("Show-All")
    public List<Candidature> showAll() {
        return candidatureService.ListCandidatures();
    }

    @GetMapping("Show-Top/{idOffre}")
    public List<Candidature> TopCandidature(@PathVariable("idOffre") Integer idOffre) {
        return candidatureService.TopCandidature(idOffre);
    }

    @GetMapping("Show-TopEmails/{idOffre}")
    public List<String> TopCandidatureemails(@PathVariable("idOffre") Integer idOffre) {
        return candidatureService.getEmailsFromCandidaturesLimitedByAvailablePlaces(idOffre);
    }

    @GetMapping("Show-UserCandidature/{Iduser}")
    public List<Candidature> CandidaturesByUserId(@PathVariable("Iduser") Integer Iduser) {
        return candidatureService.getCandidatureByUserId(Iduser);
    }

    @GetMapping("Show-topnames/{idOffre}")
    public List<String> TopCandidaturenames(@PathVariable("idOffre") Integer idOffre) {
        return candidatureService.getNamesFromCandidaturesLimitedByAvailablePlaces(idOffre);
    }

    @PostMapping("/send/{idOffre}")
    public void sendEmailsToCandidatures(@PathVariable Integer idOffre) {
        try {
            candidatureService.sendEmailsCondida(idOffre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/generate/{idCandidature}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Integer idCandidature) {
        Candidature candidature = candidatureService.retreviveCandidature(idCandidature);
        byte[] pdfBytes = pdfGenerationService.generateCandidaturePdf(candidature);

        if (pdfBytes != null) {
            // Define the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "candidature.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } else {
            // Handle the case where PDF generation failed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/validEmail/{email}")
    public Boolean isEmailValid( @PathVariable String email) {
        Boolean isValid = candidatureService.ValidEmail(email);
        return isValid;

    }
}



