package ru.itis.servlets.services;


import ru.itis.servlets.dto.SignUpDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ParametrLoader {
    public SignUpDto getRegistrationParams(HttpServletRequest req);
    public Map<String, Object> getLoginParams(HttpServletRequest req);

    Map<String, Object> getProductParameters(HttpServletRequest req);

    String getRegistratoinKey(String requestURL);
}
