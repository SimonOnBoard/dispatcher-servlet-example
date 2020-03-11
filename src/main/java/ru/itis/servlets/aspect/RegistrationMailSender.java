package ru.itis.servlets.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.servlets.dto.UserDto;
import ru.itis.servlets.services.MailService;
import ru.itis.servlets.services.TemplateProcessor;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class RegistrationMailSender {
    @Autowired
    private TemplateProcessor templateProcessor;
    @Autowired
    private MailService mailService;

    @AfterReturning(pointcut = "execution(* ru.itis.servlets.services.RegistrationService.loadUserFromParameters(..))", returning = "resultDto")
    public void logAfterReturning(JoinPoint joinPoint, UserDto resultDto) {
        System.out.println("hijacked : " + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("******");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", resultDto.getName());
        parameters.put("link", "http://localhost:8000/confirm/" + resultDto.getCode());
        String html = templateProcessor.getProcessedTemplate(parameters, "mail.ftl");
        mailService.sendMessage("Confirm", resultDto.getEmail(), html);
    }
}
