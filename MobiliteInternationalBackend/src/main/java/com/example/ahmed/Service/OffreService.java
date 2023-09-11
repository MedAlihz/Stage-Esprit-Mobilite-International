package com.example.ahmed.Service;

import com.example.ahmed.Email.EmailService;
import com.example.ahmed.Entity.Offre;
import com.example.ahmed.Entity.User;
import com.example.ahmed.Interface.IOffreService;
import com.example.ahmed.Repository.OffreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class OffreService implements IOffreService {
    OffreRepository offreRepository;
    public final EmailService Sender;

    UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Offre addOffre(Offre o) {
        return offreRepository.save(o);

    }

    @Override
    public void removeOffre(Integer id) {
        offreRepository.deleteById(id);
    }

    @Override
    public List<Offre> showOffre() {
        return offreRepository.findAll();
    }

    /*@Override
    public Offre updateOffre(Offre f) {
        return offreRepository.save(f);
    }*/
    public Offre updateOffre(Integer idOffre, MultipartFile file,String titre, String description, String lieu, LocalDate deadline, Integer nombrePlaces, String link) {
        // Check if the entity with the specified ID exists
        if (offreRepository.existsById(idOffre)) {
            Offre updatedEntity=new Offre();
            // Set the ID of the updated entity to match the path variable
            updatedEntity.setIdOffre(idOffre);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if(fileName.contains(".."))
            {
                System.out.println("not a a valid file");
            }

            try {
                updatedEntity.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

            } catch (IOException e) {
                e.printStackTrace();
            }

            updatedEntity.setTitre(titre);
            updatedEntity.setDescription(description);
            updatedEntity.setLieu(lieu);
            updatedEntity.setDeadline(deadline);
            updatedEntity.setNombreplaces(nombrePlaces);
            updatedEntity.setLink(link);

            return offreRepository.save(updatedEntity);
        } else {
            // Handle the case where the entity with the given ID doesn't exist
            // You can throw an exception or return a specific response
            throw new EntityNotFoundException("Entity with ID " + idOffre + " not found.");
        }
    }
    /*public Offre addOffreandimage (String titre, String description, String lieu, LocalDate deadline, Integer nombrePlaces, String link, MultipartFile image) throws IOException {
        Offre offre = objectMapper.readValue(titre, Offre.class);

        offre.setTitre(titre);
        offre.setDescription(description);
        offre.setLieu(lieu);
        offre.setDeadline(deadline);
        offre.setNombreplaces(nombrePlaces);
        offre.setLink(link);
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        if(filename.contains("..")){
            System.out.println("!!! Not a valid File");
        }
        offre.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        return offreRepository.save(offre);
    }*/
   /* public Offre addOffreWithImage(String titre, String description, String lieu, LocalDate deadline, Integer nombrePlaces, String link, MultipartFile imageFile) {
        try {
            Offre offre = new Offre();
            offre.setTitre(titre);
            offre.setDescription(description);
            offre.setLieu(lieu);
            offre.setDeadline(deadline);
            offre.setNombreplaces(nombrePlaces);
            offre.setLink(link);

            String filename = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename()));
            if (filename.contains("..")) {
                throw new IllegalArgumentException("Invalid file path detected.");
            }

            byte[] imageBytes = imageFile.getBytes();
            offre.setImage(Base64.getEncoder().encodeToString(imageBytes));

            // Save the offre object with the image to the database
            return offreRepository.save(offre);
        } catch (IOException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }

        return null; // Return null if an exception occurs
    }
*/
   /* public void AddOffreAndImage(Offre offre, MultipartFile imageFile) {
        try {
            // Convert the image file to a byte array
            byte[] imageData = imageFile.getBytes();

            // Encode the byte array as a Base64 string
            String encodedImage = Base64.getEncoder().encodeToString(imageData);

            // Set the encoded image string in the Offre object
            offre.setImage(encodedImage);

            // Save the Offre object in the repository
            offreRepository.save(offre);
        } catch (IOException e) {
            // Handle any exception that occurred during file processing
            throw new RuntimeException("Failed to process image file.", e);
        }
    }*/
    /*
    public Offre AddOffreImage(Offre offre, MultipartFile imageFile) {
        String imageData = saveImage(imageFile);
        offre.setImage(imageData);
        return offreRepository.save(offre);
    }

    private String saveImage(MultipartFile imageFile) {
        try {
            byte[] imageData = imageFile.getBytes();
            return Base64.getEncoder().encodeToString(imageData);
        } catch (IOException e) {
            throw new RuntimeException("Failed to process image file.", e);
        }
    }
*/

   public Offre  AddOffrewithImage(MultipartFile file,String titre, String description, String lieu, LocalDate deadline, Integer nombrePlaces, String link)
    {
        Offre p = new Offre();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        p.setTitre(titre);
        p.setDescription(description);
        p.setLieu(lieu);
        p.setDeadline(deadline);
        p.setNombreplaces(nombrePlaces);
        p.setLink(link);

       return offreRepository.save(p);
    }
   /*public Offre AddOffrewithImage(MultipartFile file, String titre, String description, String lieu, LocalDate deadline, Integer nombrePlaces, String link) {
        Offre p = new Offre();
        p.setTitre(titre);
        p.setDescription(description);
        p.setLieu(lieu);
        p.setDeadline(deadline);
        p.setNombreplaces(nombrePlaces);
        p.setLink(link);

        // Get the original filename
        String originalFilename = file.getOriginalFilename();

        // Create the image path based on the original filename
        String imagePath = "src/assets/img" + originalFilename;

        // Set the image path in the entity
        p.setImage(imagePath);

        return offreRepository.save(p);
    }
*/
    //   SAVE NAME FILE IN DATABASE
 /*  public Offre AddOffrewithImage(MultipartFile file, String titre, String description, String lieu, LocalDate deadline, Integer nombrePlaces, String link) {
        Offre p = new Offre();
        p.setTitre(titre);
        p.setDescription(description);
        p.setLieu(lieu);
        p.setDeadline(deadline);
        p.setNombreplaces(nombrePlaces);
        p.setLink(link);

        // Get the original path of the file
        String originalPath = file.getOriginalFilename();

        // Set the image path in the entity
        p.setImage(originalPath);

        return offreRepository.save(p);
    }
*/
   public Offre getOffrebyId(Integer id){
       Offre f = offreRepository.findById(id).get();
       return f;
   }
    @NotNull
    @Contract(pure = true)
    private String buildEmail(String OffreName) {
       /*return "chers étudiants,\n" +
               "nous vous informons qu'un nouveau Offre de mobilité  a été ajouté :" +OffreName+
               ". n'hésitez pas à postuler sur notre  site." +
               "Bien cordialement.\n ";*/
        return"<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#red;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"red\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"red\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Nouveau Offre de Mobilité Internationale</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"red\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">chers étudiants, " + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> nous vous informons qu'un nouveau Offre de mobilité  a été ajouté : </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> "+OffreName+ "\n"+"</blockquote>"+
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> n'hésitez pas à postuler sur notre  site. " + "</p>"+
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Bien cordialement." + "</p>"+

                "\n "+

                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\">" +

                "<br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +

                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +

                "</div></div>";
    }
    public  void sendEmailsAjout(String OffreName) {
        List<String> emails =userService.FindEtudiants();
        try {
            for (String email :emails) {
                //sendEmailsTopCandidatures(email);
                Sender.sendAjoutOffre(email, buildEmail(OffreName ));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void SendEmails(String text,String sub) {
        List<String> emails =userService.FindEtudiants();
        try {
            for (String email :emails) {
                //sendEmailsTopCandidatures(email);
                Sender.SendEmails(email, text,sub);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}