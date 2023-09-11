package com.example.ahmed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@RestController
public class AhmedApplication extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/displayBarGraph").setViewName("barGraph");
    }

//    @GetMapping("/google")
//    public String welcome() {
//        return "Welcome to Google !!";
//    }
//
//    @GetMapping("/user")
//    public Principal user(Principal principal) {
//        System.out.println("username : " + principal.getName());
//        return principal;
//    }

    public static void main(String[] args) {
        SpringApplication.run(AhmedApplication.class, args);

    }
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail(){
//
//        emailService.sendMail2( regsitrationService.getM(),
//                "click link to Confirm!!!!",
//                regsitrationService.getM()
//
//        );
//
//    }


}
