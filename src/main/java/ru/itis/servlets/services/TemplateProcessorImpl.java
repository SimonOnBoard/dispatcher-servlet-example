package ru.itis.servlets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

public class TemplateProcessorImpl implements TemplateProcessor {
    @Autowired
    private TemplateResolver templateResolver;
    @Autowired
    private Map<String,String> template;

    @Override
    public String getProcessedTemplate(Map<String, String> params, String ftl) {
        template.putAll(params);
        return templateResolver.process(ftl, template);
    }
}
