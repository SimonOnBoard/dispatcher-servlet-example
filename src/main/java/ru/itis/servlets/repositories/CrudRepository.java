package ru.itis.servlets.repositories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID, V> {
    Optional<V> find(ID id);
    List<V> findAll();
    void save(V entity);
    void delete(ID id);
    void update(V entity);
}
