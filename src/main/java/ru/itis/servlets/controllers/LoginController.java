package ru.itis.servlets.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.servlets.security.defails.UserDetailsImpl;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam(value = "error",required = false) String error){
        if(userDetails != null){
            return new ModelAndView("redirect:/profile");
        }else{
            Map<String,Object> parameters = new HashMap<String,Object>();
            parameters.put("error",error);
            return new ModelAndView("login",parameters);
        }
    }
}
