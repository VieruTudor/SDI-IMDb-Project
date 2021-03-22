package repository;

import database.Database;
import database.serializers.IDBTranslator;
import domain.BaseEntity;
import exception.InexistentEntity;
import exception.ValidException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class DBRepository<ID, T extends BaseEntity<ID>> implements IRepository<ID, T> {

    private final IDBTranslator<T> serializer;

    public DBRepository(IDBTranslator<T> serializer) {
        this.serializer = serializer;
    }

    /**
     * function to collect entities from db given sql command
     *
     * @param sqlString - string , sql command
     */
    private List<T> collect(String sqlString) {
        List<T> all = new ArrayList<>();

        try (var connection = Database.newConnection();
             var ps = connection.prepareStatement(sqlString);
             var rs = ps.executeQuery()) {
            while (rs.next()) {
                var entity = serializer.deserializer(rs);
                all.add(entity);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return all;
    }

    /**
     * function that execute the sqlCommand on the db
     *
     * @param sqlString - string , sql command
     */
    public void executeSqlCommand(String sqlString) {
        try (var connection = Database.newConnection();
             var ps = connection.prepareStatement(sqlString)) {
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public Optional<T> findOne(ID id) {
        String sqlString = "Select * from " + serializer.getTable() + " " + serializer.searchById() + id;
        var all = collect(sqlString);
        all.stream().findAny()
                .orElseThrow(()->{ throw new InexistentEntity("No entity with matching id"); });
        return Optional.ofNullable(all.get(0));
    }

    @Override
    public Iterable<T> findAll() {
        String sqlString = "Select * from " + serializer.getTable();

        return collect(sqlString);
    }

    @Override
    public Optional<T> save(T entity) throws ValidException {
        String sqlString = "insert into " + this.serializer.getTable() +
                this.serializer.getFields() + " values " + this.serializer.serializer(entity);
        this.executeSqlCommand(sqlString);
        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<T> delete(ID id) {
        var found = this.findOne(id);
        String sqlString = "Delete from " + this.serializer.getTable() + " " + this.serializer.searchById() + id;
        this.executeSqlCommand(sqlString);
        return found;
    }

    @Override
    public Optional<T> update(T entity) throws ValidException {
        // try catch here
        var found = this.findOne(entity.getId());
        this.delete(entity.getId());
        this.save(entity);
        return Optional.of(entity);
    }

    @Override
    public int length() {
        return (int) StreamSupport.stream(this.findAll().spliterator(), false).count();
    }
}
