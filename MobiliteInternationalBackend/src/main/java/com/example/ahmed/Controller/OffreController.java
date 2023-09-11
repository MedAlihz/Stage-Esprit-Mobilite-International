package com.example.ahmed.Controller;

import com.example.ahmed.Entity.Offre;
import com.example.ahmed.Entity.User;
import com.example.ahmed.Repository.OffreRepository;
import com.example.ahmed.Service.OffreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.File;
import java.io.IOException;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.File;
import java.io.IOException;import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Offre")
@CrossOrigin("*")
@Slf4j
public class OffreController {
    @Autowired
    OffreService offreService;
    @Autowired
    OffreRepository offreRepository;
    @PostMapping("/AddOffre")
    public Offre addOffre(@RequestBody Offre O) {
        Offre offre = offreService.addOffre(O);
        offreService.sendEmailsAjout(offre.getTitre());
        log.info(offre.toString());
        return offre;
    }

    @GetMapping("/retrieve-all-Offre")
    public List<Offre> getOffres() {
        List<Offre> LO = offreService.showOffre();
        return LO;
    }
    @DeleteMapping( "/remove-Offre/{id}")
    public void removeOffre(@PathVariable("id") Integer id) {

        offreService.removeOffre(id);
    }
 /*   @PutMapping("/update-Offre")
    public Offre updateOffre(@RequestBody Offre c) {
        Offre offre = offreService.updateOffre(c);
        return offre;
    }
*/
 @CrossOrigin("http://localhost:4200")
 //@PutMapping("/update-Offre/{idOffre}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
 @PutMapping(value = "/update-Offre/{idOffre}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

 public Offre updateEntity(@PathVariable Integer idOffre, @RequestParam MultipartFile file,
                           @RequestParam String titre,
                           @RequestParam String description,
                           @RequestParam String lieu,
                           @RequestParam("deadline") String dateString,
                           @RequestParam Integer nombrePlaces,
                           @RequestParam String link) {

     //return offreService.updateOffre(idOffre, updatedEntity)
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

     LocalDate deadline = LocalDate.parse(dateString, formatter);
     Offre offre = offreService.updateOffre(idOffre,file, titre, description, lieu,deadline,nombrePlaces,link);

     return offre;

 }

    @GetMapping("/get-Offre/{id}")
    public Offre getOffre(@PathVariable("id") Integer id) {
        Offre offre = offreService.getOffrebyId(id);
        return offre;
    }
    @PostMapping(value = "/AddF", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Offre saveOffre(@RequestParam MultipartFile file,
                           @RequestParam String titre,
                           @RequestParam String description,
                           @RequestParam String lieu,
                           @RequestParam("deadline") String dateString,
                           @RequestParam Integer nombrePlaces,
                           @RequestParam String link)
    {        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate deadline = LocalDate.parse(dateString, formatter);
        Offre offre = offreService.AddOffrewithImage(file, titre, description, lieu,deadline,nombrePlaces,link);
        offreService.sendEmailsAjout(titre);

        return offre;

    }
@PostMapping(value = "/SendEmails")
    public void SendEmails( @RequestParam String text,
                            @RequestParam String Subject)
{
    offreService.SendEmails(text,Subject);
}

}
