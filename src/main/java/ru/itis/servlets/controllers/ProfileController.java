package ru.itis.servlets.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.servlets.security.defails.UserDetailsImpl;

import java.util.HashMap;
@Controller
public class ProfileController {
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getUsersProfile(@AuthenticationPrincipal UserDetailsImpl user){
        HashMap<String, Object> params = new HashMap<>();
        params.put("user",user.getUser());
        return new ModelAndView("profile",params);
    }

}
