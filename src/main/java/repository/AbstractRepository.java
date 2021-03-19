package repository;

import domain.BaseEntity;
import exception.DuplicateException;
import exception.InexistentEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
// TODO TEAM

public abstract class AbstractRepository<ID, T extends BaseEntity<ID>> implements IRepository<ID, T> {
    protected Map<ID, T> elements;

    public AbstractRepository() {
        this.elements = new HashMap<>();
    }

    protected abstract void readAll();

    protected abstract void writeAll();


    @Override
    public Optional<T> findOne(ID id) {
        readAll();
        return Optional.ofNullable(this.elements.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        readAll();
        return this.elements.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public Optional<T> save(T entity) throws DuplicateException {
        readAll();
        var returnedEntity = Optional.ofNullable(elements.putIfAbsent(entity.getId(), entity));
        if (returnedEntity.isEmpty()) {
            writeAll();
            return returnedEntity;
        } else {
            throw new DuplicateException("There is already an entity with this Id!");
        }
    }

    @Override
    public Optional<T> delete(ID id) {
        readAll();
        var returnedEntity = Optional.of(Optional.ofNullable(this.elements.remove(id)).orElseThrow(
                () -> {
                    System.out.println("im here");
                    throw new InexistentEntity("Entity does not exist");
                }
        ));
        returnedEntity.ifPresent(t -> writeAll());
        return returnedEntity;
    }

    @Override
    public Optional<T> update(T entity) {
        readAll();
        var returnedEntity = Optional.of(Optional.ofNullable(this.elements.computeIfPresent(entity.getId(), (k, v) -> entity))
                .orElseThrow(
                        () -> {
                            throw new InexistentEntity("Entity does not exist");
                        }
                ));
        returnedEntity.ifPresent(t -> writeAll());
        return returnedEntity;
    }

    @Override
    public int length() {
        this.readAll();
        return elements.size();
    }


}
