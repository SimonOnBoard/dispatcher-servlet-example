package ru.itis.servlets.repositories;

import ru.itis.servlets.models.FileInfo;

import java.io.File;
import java.util.Optional;

public interface FilesRepository extends CrudRepository<Long, FileInfo>{
    Optional<FileInfo> getFileByName(String name);
    Optional<FileInfo> getFileByOriginalName(String name);
}
