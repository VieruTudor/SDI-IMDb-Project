package database.adapters;

import domain.BaseEntity;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

public interface TableAdapter<ID, T extends BaseEntity> {

    void insert(T entity);

    List<T> readAll();

    Optional<T> read(ID id);

    void update(T entity);

    void delete(ID id);

}
