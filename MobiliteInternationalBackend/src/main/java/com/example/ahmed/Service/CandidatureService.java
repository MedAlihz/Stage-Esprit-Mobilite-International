package com.example.ahmed.Service;

import com.example.ahmed.Email.EmailService;
import com.example.ahmed.Entity.Candidature;
import com.example.ahmed.Entity.Niveau;
import com.example.ahmed.Entity.Offre;
import com.example.ahmed.Entity.User;
import com.example.ahmed.Interface.ICandidatureService;
import com.example.ahmed.Repository.CandidatureRepository;
import com.example.ahmed.Repository.OffreRepository;
import com.example.ahmed.Repository.UserRep;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class CandidatureService implements ICandidatureService {
    @Autowired
    CandidatureRepository candidatureRepository;
    @Autowired

    UserRep userRepository;
    @Autowired

    OffreRepository offreRepository;
    public final  EmailService Sender;
    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    @Override
    public Candidature AddAndAssignCandidatureToOffre(Candidature f, Integer idOffre) {
        Optional<Offre> O = offreRepository.findById(idOffre);
        if (O.isPresent()) {
            Offre U = O.get();

            f.setOffre(U);

            double s = calculateScore(f.getMoy1(), f.getMoy2(), f.getMoy3(),f.getFrancais(),f.getAnglais());
            f.setScore(s);
            User u =userRepository.findUserByEmail(f.getEmail());
            f.setUserCandidature(u);
            //f.setUserCandidature();
            return candidatureRepository.save(f);
        }
        else {
            log.info("not found");
            return null;
        }
    }

    @Override
    public Candidature updateCandidature(Candidature f) {
        return candidatureRepository.save(f);    }


    @Override
    public List<Candidature> retrieveAllCandidatures() {
        return candidatureRepository.findAll();
    }

    @Override
    public List<Candidature> ListCandidatures() {
        return candidatureRepository.findAll();
    }

    public List<Candidature> TopCandidature(Integer idOffre) {
        return candidatureRepository.getCandidaturesLimitedByAvailablePlaces(idOffre);
    }

    public String getNameOffre(Integer idOffre){
        Offre O = offreRepository.findById(idOffre).get();
return O.getTitre();
    }
        public  List<String> getEmailsFromCandidaturesLimitedByAvailablePlaces(Integer idOffre) {
        List<Candidature> candidatures = candidatureRepository.getCandidaturesLimitedByAvailablePlaces(idOffre);

        // Use Java Stream API to extract the emails from the list of candidatures
        return candidatures.stream()
                .map(Candidature::getEmail)
                .collect(Collectors.toList());
    }
    public  List<String> getEmailsFromRemainCandidatures(Integer idOffre) {
        List<Candidature> candidatures = candidatureRepository.getRemainingCandidatures(idOffre);

        // Use Java Stream API to extract the emails from the list of candidatures
        return candidatures.stream()
                .map(Candidature::getEmail)
                .collect(Collectors.toList());
    }
    public  List<Candidature> getCandidatureByUserId(Integer idUser) {
        List<Candidature> candidatures = candidatureRepository.findCandidaturesByUserId(idUser);
return  candidatures;
    }
    public  List<String> getNamesFromCandidaturesLimitedByAvailablePlaces(Integer idOffre) {
        List<Candidature> candidatures = candidatureRepository.getCandidaturesLimitedByAvailablePlaces(idOffre);

        // Use Java Stream API to extract the emails from the list of candidatures
        return candidatures.stream()
                .map(Candidature::getNomComplet)
                .collect(Collectors.toList());
    }
    public  List<String> getNamesFromRemainingCandidatures(Integer idOffre) {
        List<Candidature> candidatures = candidatureRepository.getCandidaturesLimitedByAvailablePlaces(idOffre);

        // Use Java Stream API to extract the emails from the list of candidatures
        return candidatures.stream()
                .map(Candidature::getNomComplet)
                .collect(Collectors.toList());
    }
   public void sendEmailsTopCandidatures(String to) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText("Congrats");
            helper.setTo(to);
            helper.setSubject("Accepté en Mobilité Internationale");
            helper.setFrom("hrizi.mohamedali@esprit.tn");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }

    }
    @NotNull
    @Contract(pure = true)
    private String buildEmail(String name, String OffreName) {
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
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Candidature En Mobilité Internationale</span>\n" +
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
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Félicitations " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Vous êtes selectionné dans l'offre de mobilité: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> "+OffreName+
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
    @NotNull
    @Contract(pure = true)
    private String buildEmail2(String name, String OffreName) {
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
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Candidature En Mobilité Internationale</span>\n" +
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
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Bonjour " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Nous vous remercions pour votre candidature dans l'offre de mobilité: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> "+OffreName+"\n"+"</blockquote>"+
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Malheureusement, nous ne pouvons pas retenir votre candidature pour le moment. " + "</p>"+
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Nous apprécions vos compétences et votre expérience, et nous vous encourageons à continuer à rechercher des opportunités qui correspondent à votre profil. " + "</p>"+
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
    public  void sendEmailsCondida(Integer idOffre) {
        List<String> emails =getEmailsFromCandidaturesLimitedByAvailablePlaces(idOffre);
        List<String> names =getNamesFromCandidaturesLimitedByAvailablePlaces(idOffre);
        List<String> remainNames=getNamesFromRemainingCandidatures(idOffre);
        List<String> RemainEmails=getEmailsFromRemainCandidatures(idOffre);
        String OffreName=getNameOffre(idOffre);
        try {
            int size = Math.min(emails.size(), names.size());
            for (int i = 0; i < size; i++) {
                String email = emails.get(i);
                String name = names.get(i);
                //sendEmailsTopCandidatures(email);
                Sender.sendAcceptance(email, buildEmail(name,OffreName ));
            }
            int s = Math.min(RemainEmails.size(), remainNames.size());
            for (int i = 0; i < s; i++) {
                String email = RemainEmails.get(i);
                String name = remainNames.get(i);
                //sendEmailsToRestofCandidatures
                Sender.sendRefuse(email, buildEmail2(name,OffreName ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Candidature addCandidature(Candidature f) {
        double s = calculateScore(f.getMoy1(), f.getMoy2(), f.getMoy3(),f.getFrancais(),f.getAnglais());

        f.setScore(s)   ;
        return candidatureRepository.save(f);
    }


    @Override
    public void removeCandidature(Integer IdCandidature) {
         candidatureRepository.deleteById(IdCandidature);

    }

    @Override
    public void assignCandidatureToUser(Integer IdCandidature, Integer IdUser) {
        Candidature T=candidatureRepository.findById(IdCandidature).orElse(null);
        User U=userRepository.findById(IdUser).orElse(null);
        T.setUserCandidature(U);
        candidatureRepository.save(T);
    }
@Override
public int PointLangue(Niveau L){
        switch (L){
            case A1:return 1;
            case A2:return 2;
            case B1:return 3;
            case B2:return 4;
            case C1:return 5;
            case C2:return 6;
            default:return 0;
        }

    }
    @Override
    public double calculateScore(double M1, double M2,double M3,Niveau F,Niveau A) {
     int n1=PointLangue(F);
     int n2=PointLangue(A);
        double S= M1+M2+M3+n2+n1;
        return S;
    }

    @Override
    public Candidature retreviveCandidature(Integer IdCandidature) {
        return candidatureRepository.findById(IdCandidature).get();
    }

    @Override
    public Candidature retreviveCandidature2(Integer IdCandidature, HttpServletResponse response) {
        return candidatureRepository.findById(IdCandidature).get();
    }
        public  Boolean ValidEmail(String email){
    Boolean V=userRepository.validEmail(email);
return V;
}


}
