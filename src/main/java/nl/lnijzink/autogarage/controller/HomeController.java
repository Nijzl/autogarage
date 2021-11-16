package nl.lnijzink.autogarage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    // Checks if somebody is logged in
    @GetMapping("/")
    public @ResponseBody String loginMessage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getPrincipal() instanceof UserDetails){
            return "";}
        else{
            return "You are not logged in. Please try again.";}
    }

    // Display Index
    @GetMapping("/home")
    public String showOverview(){
        return "Index";
    }

}
