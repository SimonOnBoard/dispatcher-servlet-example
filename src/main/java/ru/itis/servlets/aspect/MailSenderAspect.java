package ru.itis.servlets.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.servlets.dto.FileDto;
import ru.itis.servlets.services.MailService;
import ru.itis.servlets.services.TemplateProcessor;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class MailSenderAspect {
    @Autowired
    private TemplateProcessor templateProcessor;
    @Autowired
    private MailService mailService;

    @AfterReturning(pointcut = "execution(* ru.itis.servlets.services.FileService.saveFile(..))",
            returning = "resultDto")
    public void logAfterReturning(JoinPoint joinPoint, FileDto resultDto) {
        System.out.println("hijacked : " + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("******");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("nick",resultDto.getUserName());
        parameters.put("link",resultDto.getUrl());
        parameters.put("fileName",resultDto.getOriginalName());
        String html = templateProcessor.getProcessedTemplate(parameters, "mail_2.ftl");
        mailService.sendMessage("File notification", resultDto.getUserLogin(), html);

    }
}
