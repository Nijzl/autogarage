package nl.lnijzink.autogarage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {
    @GetMapping("/")
    public @ResponseBody String loginMessage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getPrincipal() instanceof UserDetails){
            String userName = ((UserDetails)(auth.getPrincipal())).getUsername();
            return "Hello " + userName;
        }
        else{
            return "You are not logged in. Please try again.";
        }

    }

    @GetMapping("/home")
    public @ResponseBody String showOverview(){
        return "this page will display an overview of options";
    }

/*    @GetMapping("/customers")
    public @ResponseBody String showCustomers(){
        return "this page will display customer information";
    }

    @GetMapping("/cars")
    public @ResponseBody String showCars(){
        return "this page will display car information";
    }*/

    @GetMapping("/appointments")
    public @ResponseBody String showAgenda(){
        return "here go the appointments";
    }



}
