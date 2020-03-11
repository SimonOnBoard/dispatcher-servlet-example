package ru.itis.servlets.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.servlets.dto.SignUpDto;
import ru.itis.servlets.dto.UserDto;
import ru.itis.servlets.services.ParametrLoader;
import ru.itis.servlets.services.RegistrationService;
import ru.itis.servlets.services.TemplateProcessor;
import ru.itis.servlets.services.TemplateResolver;

import javax.xml.crypto.dsig.SignedInfo;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView completeRegistrationProcess(SignUpDto signUpDto) {
        UserDto userDto = registrationService.loadUserFromParameters(signUpDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pls_confirm");
        modelAndView.addObject("user",userDto.toString());
        return modelAndView;
    }
}
