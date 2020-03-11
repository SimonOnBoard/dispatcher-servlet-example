package ru.itis.servlets.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.servlets.models.MyUserDetails;
import ru.itis.servlets.services.ConfirmationService;
import ru.itis.servlets.services.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
@Controller
public class ConfirmationController {
    @Autowired
    private ConfirmationService confirmationService;
    @RequestMapping(value = "/confirm/{key}", method = RequestMethod.GET)
    public ModelAndView getFile(@PathVariable("key") String key, @AuthenticationPrincipal MyUserDetails user, HttpServletResponse httpServletResponse) throws IOException {
        String answer = confirmationService.checkConfirmation(key);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("confirm");
        modelAndView.addObject("result", answer);
        return modelAndView;
    }
}
