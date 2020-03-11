package ru.itis.servlets.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.servlets.models.FileInfo;
import ru.itis.servlets.models.State;
import ru.itis.servlets.models.User;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class FilesRepositoryImpl implements FilesRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT_BY_NAME =
            "SELECT * FROM files where storage_file_name = ?";

    private RowMapper<FileInfo> filesRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .storageFileName(row.getString("storage_file_name"))
                    .originalFileName(row.getString("original_file_name"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .url(row.getString("url"))
                    .ownerId(row.getLong("owner_id"))
                    .build();
    @Override
    public Optional<FileInfo> getFileByName(String name) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, new Object[]{name}, filesRowMapper);
            return Optional.ofNullable(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    private static final String SQL_SELECT_BY_ORIGINAL_NAME =
            "SELECT * FROM files where original_file_name = ?";
    @Override
    public Optional<FileInfo> getFileByOriginalName(String name) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_ORIGINAL_NAME, new Object[]{name}, filesRowMapper);
            return Optional.ofNullable(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<FileInfo> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<FileInfo> findAll() {
        return null;
    }

    //language=sql
    private static final String SQL_INSERT =
            "INSERT INTO files (storage_file_name, original_file_name, size, type, url,owner_id)" +
                    " values (?,?,?,?,?,?)";

    @Override
    public void save(FileInfo entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getStorageFileName());
            statement.setString(2, entity.getOriginalFileName());
            statement.setLong(3, entity.getSize());
            statement.setString(4, entity.getType());
            statement.setString(5, entity.getUrl());
            statement.setLong(6, entity.getOwnerId());
            return statement;
        }, keyHolder);
        System.out.println("" + keyHolder.getKey());
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(FileInfo entity) {

    }
}
