package ru.itis.servlets.services;


import org.springframework.stereotype.Service;
import ru.itis.servlets.dto.SignUpDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class ParametrLoaderImpl implements ParametrLoader {

    @Override
    public SignUpDto getRegistrationParams(HttpServletRequest req) {
        return SignUpDto.builder()
                .email(req.getParameter("email"))
                .login(req.getParameter("login"))
                .nick(req.getParameter("user_name"))
                .birthDay(Date.valueOf(req.getParameter("birth")))
                .password( req.getParameter("password"))
                .build();
    }

    @Override
    public Map<String, Object> getLoginParams(HttpServletRequest req) {
        Map<String, Object> params = new HashMap<>();
        params.put("login",req.getParameter("username"));
        params.put("password", req.getParameter("password"));
        return params;
    }

    @Override
    public Map<String, Object> getProductParameters(HttpServletRequest req) {
        Map<String, Object> params = new HashMap<>();
        params.put("name",req.getParameter("name"));
        params.put("cost", req.getParameter("cost"));
        params.put("count", req.getParameter("count"));
        try {
            params.put("photo", req.getPart("photo"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (ServletException e) {
            throw new IllegalStateException(e);
        }
        return params;
    }

    @Override
    public String getRegistratoinKey(String requestURL) {
      String[] strings = requestURL.split("/");
      return strings[strings.length - 1];
    }


}
