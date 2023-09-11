package com.example.ahmed.Registration;

import com.example.ahmed.Email.EmailService;
import com.example.ahmed.Entity.Offre;
import com.example.ahmed.Entity.Role;
import com.example.ahmed.Entity.User;
import com.example.ahmed.Repository.UserRep;
import com.example.ahmed.Service.UserService;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.aspectj.weaver.bcel.Utility;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/Auth")
@AllArgsConstructor
@CrossOrigin("*")
public class RegistrationController {
    private RegsitrationService registrationService;
    private final UserRep userRep;
    private final UserService userService;
    @CrossOrigin("http://localhost:4200")
    @PostMapping("/register")
    private String register(@RequestBody RegistrationRequest request) throws MessagingException {
        return registrationService.register(request);
    }

@CrossOrigin("http://localhost:4200")
@GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

//***********************************SEARCH*******************
//*********************NORMAL ID
@CrossOrigin("http://localhost:4200")
    @GetMapping("/Search")
public String SearchUserById(@RequestParam("id") int id){
   User u = registrationService.SearchUser(id);
   return "YOUR USER:\n\n\n"+
           "FIRSTNAME: "+ u.getFirstName()+"\n\n"+
        "LASTNAME: "+ u.getLastName()+"\n\n"+
           "EMAIL: "+ u.getEmail()+"\n\n"+
           "ROLE: " + u.getRole()+"\n\n";

}
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/SearchRole/")
    public String SearchUserRoleByEmail(@RequestParam("Email") String email){
        String u = registrationService.SearchUserRole(email);
        return u;

    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/SearchUserByEmail/{Email}")
    public User SearchUserEmail(@RequestParam("Email") String email){
         User u = registrationService.SearchUserByEmail(email);
        return u;

    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/SearchId/")
    public String SearchUserIdByEmail(@RequestParam("Email") String email){
        String u = registrationService.SearchUserId(email);
        return u;

    } //*********BYROLE****************
                    @GetMapping("/UsersWithSameRole")
                    public List<User> GetThemAllR(@RequestParam("role") Role role){

                        return userRep.findUserByRole(role);


                    }
                    ///********************ENABLEDACCOUNT
                    @GetMapping("/TheEnabledOnes")
                    public List<User> TheEnabledOnes(){

                        return userRep.TheEnabledOnes();
                    }
                    ////******************THE NON ENABLED ONES
                    @GetMapping("/TheNonEnabledOnes")
                    public List<User> TheNONEnabledOnes(){

                        return userRep.TheNonEnabledOnes();
                    }
//*********************************GETALL****************
@CrossOrigin("http://localhost:4200")
    @GetMapping("/AllUsers")
    public List<User> GetThemAll(){

        return userRep.findAll();
//    return "YOUR USER:\n\n\n"+
//            "FIRSTNAME: "+ u.getFirstname()+"\n\n"+
//            "LASTNAME: "+ u.getLastname()+"\n\n"+
//            "EMAIL: "+ u.getEmail()+"\n\n"+
//            "ROLE: " + u.getRole()+"\n\n";

    }
///////////RESET**************************************************


    //DELETE ACCOUNT****************************************************************
    @CrossOrigin("http://localhost:4200")
    @DeleteMapping ("/DeleteUser/")
    public void DeleteUser(@RequestParam("id") int id){

       userRep.deleteById(id);

    }
///////////////////////////////////////RESETPASSWORD*****************************
    @CrossOrigin("http://localhost:4200")
    @PutMapping("/ResetPass/InsetYourEmailHere/")
    public String SendPassToken(@RequestParam("email") String email){
      return   userService.SendPassToken(email);

    }
    @CrossOrigin("http://localhost:4200")
    @PutMapping("/ResetPass")
    public String ResetPass(Integer idUser,String token,String neoPass ){
        return userService.ResetPass(idUser,token,neoPass);
    }
    //*****************************MODIFYROLE*******************************************

@GetMapping("/current")
public User currentUser(){
        return userService.isCurrent();
}
    @GetMapping("/current/{id}")
    public boolean currentUser(@PathVariable("id")int id){return userService.isCurrentUser(id);
    }

  /////////////////////////////////////////////////////////////////////////


//    @GetMapping("/searchRole")
//    public Role searchRoleByEmail(String email){
//        return  registrationService.searchRoleByEmail(email);
//    }
@CrossOrigin("http://localhost:4200")
    @PutMapping("/Enable/{id}")
    public String Enable(@PathVariable("id")int id ) {
    User user = userRep.findById(id).get();
    if (user.getEnabled() == false) {
        return registrationService.Activate(id);
    } else {
        return registrationService.Deactivate(id);
    }
}
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/FindEtudiant")
    public List<String> FindEtudiants(){

        return userService.FindEtudiants();
    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/FindAllUsers")
    public List<User> FindAllUsers(){

        return userService.getAllUsers();
    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/RemoveUser/{id}")
    public void RemoveUser(Integer id){

         userService.removeUser(id);
    }
    @PutMapping("/update-User/{idUser}")
    public User updateEntity(@PathVariable Integer idUser, @RequestBody User updatedEntity) {

            return userService.updateUser(idUser, updatedEntity);

    }

}
