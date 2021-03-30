package repository;

import config.JdbcConfig;
import database.Database;
import database.adapters.TableAdapter;
import database.serializers.IDBTranslator;
import domain.BaseEntity;
import exception.InexistentEntity;
import exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class DBRepository<ID, T extends BaseEntity<ID>> implements IRepository<ID, T> {


    private TableAdapter<ID, T> tableAdapter;

    public DBRepository(TableAdapter<ID, T> tableAdapter){
        this.tableAdapter = tableAdapter;
    }
    @Override
    public Optional<T> findOne(ID id) {
        Objects.requireNonNull(id, "id must not be null");
        return tableAdapter.read(id);
    }

    @Override
    public Iterable<T> findAll() {
        return tableAdapter.readAll();
    }

    @Override
    public Optional<T> save(T entity) throws ValidException {
        Objects.requireNonNull(entity, "Entity can't be null");
        Optional<T> readEntity = tableAdapter.read(entity.getId());
        if(readEntity.isEmpty()){
            tableAdapter.insert(entity);
        }
        return readEntity;
    }

    @Override
    public Optional<T> delete(ID id) {
        Objects.requireNonNull(id, "Id can't be null");
        return tableAdapter.read(id).map(
                readEntity -> {
                    tableAdapter.delete(id);
                    return readEntity;
                }
        );
    }

    @Override
    public Optional<T> update(T entity) throws ValidException {
        Objects.requireNonNull(entity, "Entity can't be null");
        return tableAdapter.read(entity.getId()).map(
                readEntity -> {
                    tableAdapter.update(entity);
                    return entity;
                }
        );
    }

    @Override
    public int length() {
        return tableAdapter.readAll().size();
    }
}
