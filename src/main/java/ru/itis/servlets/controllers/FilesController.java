package ru.itis.servlets.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.servlets.dto.FileDto;
import ru.itis.servlets.models.MyUserDetails;
import ru.itis.servlets.services.FileLoaderThread;
import ru.itis.servlets.services.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FilesController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    @ResponseBody
    public FileDto uploadFile(@RequestParam("file") MultipartFile multipartFile, @AuthenticationPrincipal MyUserDetails user) {
        FileDto message = fileService.saveFile(multipartFile, user);
        return message;
    }

    @RequestMapping(value = "/files/{file-name:.+}", method = RequestMethod.GET)
    public ModelAndView getFile(@PathVariable("file-name") String fileName, @AuthenticationPrincipal MyUserDetails user, HttpServletResponse httpServletResponse) throws IOException {
        String[] arguments = fileService.getFile(fileName, user);
        ModelAndView modelAndView = new ModelAndView();
        if (arguments[0].equals("Error")) {
            modelAndView.setViewName("file_upload");
            modelAndView.addObject("message", arguments[1]);
        } else {
            FileLoaderThread fileLoaderThread = new FileLoaderThread(arguments, httpServletResponse);
            fileLoaderThread.start();
            modelAndView.setViewName("file_upload");
            modelAndView.addObject("message", "Delivering started");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public ModelAndView uploadFile(@AuthenticationPrincipal UserDetails user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("file_upload");
        return modelAndView;
    }

}
