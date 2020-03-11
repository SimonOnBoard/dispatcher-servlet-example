package ru.itis.servlets.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.servlets.dto.FileDto;
import ru.itis.servlets.models.MyUserDetails;

public interface FileService {
    FileDto saveFile(MultipartFile multipartFile, MyUserDetails user);
    String[] getFile(String fileName, MyUserDetails user);
}
