package ru.itis.servlets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import ru.itis.servlets.services.MailService;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendMessage(String subject, String mail,String html) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            messageHelper.setFrom("Nobody");
            messageHelper.setTo(mail);
            messageHelper.setSubject(subject);
            messageHelper.setText(html, true);
        };
        mailSender.send(messagePreparator);
    }
}
