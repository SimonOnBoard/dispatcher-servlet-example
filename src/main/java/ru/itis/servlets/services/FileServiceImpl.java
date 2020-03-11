package ru.itis.servlets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.servlets.dto.FileDto;
import ru.itis.servlets.models.FileInfo;
import ru.itis.servlets.models.MyUserDetails;
import ru.itis.servlets.repositories.FilesRepository;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class FileServiceImpl implements FileService {
    @Autowired
    private FilesRepository filesRepository;

    private String path;

    public FileServiceImpl(String path) {
        this.path = path;
    }

    @Override
    public FileDto saveFile(MultipartFile multipartFile, MyUserDetails user) {
        String name = getNewFilename(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileInfo info = FileInfo.builder().originalFileName(multipartFile.getOriginalFilename())
                .ownerId(user.user.getId()).size(multipartFile.getSize())
                .type(multipartFile.getContentType()).storageFileName(name)
                .url("http://localhost:8000/files/" + name).build();
        File file = new File(path + File.separator + name);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        filesRepository.save(info);

        return FileDto.builder().userLogin(user.user.getLogin()).userName(user.user.getNick()).originalName(info.getOriginalFileName()).size(info.getSize()).url(info.getUrl()).build();
    }

    private String getNewFilename(String originalFilename) {
        String[] strings = originalFilename.split("\\.");
        return UUID.randomUUID().toString() + "." + strings[strings.length - 1];
    }

    @Override
    public String[] getFile(String fileName, MyUserDetails user) {
        Optional<FileInfo> fileInfo = filesRepository.getFileByName(fileName);
        if (fileInfo.isPresent()) {
            FileInfo fileInfo1 = fileInfo.get();
            if (fileInfo1.getOwnerId().equals(user.user.getId())) {
                return new String[]{path + File.separator + fileName, fileInfo1.getType(), fileInfo1.getOriginalFileName()};
            } else {
                return new String[]{"Error", "Illegal Access"};
            }
        } else {
            return new String[]{"Error", "File not found"};
        }
    }
}
