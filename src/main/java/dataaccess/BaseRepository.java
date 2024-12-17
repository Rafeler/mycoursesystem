package dataaccess;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    Optional<T> getById(Long id);
    List<T> getAll();
    Optional<T> insert(T entity);
    Optional<T> update(T entity);
    void deleteById(Long id);
}
