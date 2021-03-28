package repository;

import domain.BaseEntity;
import exception.ValidException;

import java.util.Optional;

public interface IRepository<ID, T extends BaseEntity<ID>> {

    /**
     * Finds the entity with the given ID.
     *
     * @param id not null
     * @return an {@code Optional} encapsulating the entity with the given ID.
     */
    Optional<T> findOne(ID id);

    /**
     * @return all entities
     */
    Iterable<T> findAll();

    /**
     * Adds an entity to the list
     *
     * @param entity not null
     * @return an {@code Optional} - the entity if it was saved successfully
     * - null if the entity was already in the repository.
     * @throws ValidException if the entity is not valid.
     */
    Optional<T> save(T entity) throws ValidException;

    /**
     * Deletes the entity with the given ID.
     *
     * @param id must not be null
     * @return an {@code Optional} - the entity if it was removed successfully
     * - null if there was no entity with that ID.
     */
    Optional<T> delete(ID id);

    /**
     * Updates an entity matching the ID of the given entity.
     *
     * @param entity not null
     * @return an {@code Optional} - the entity if it was updated successfully
     * - null if there was no entity with that ID.
     * @throws ValidException
     */
    Optional<T> update(T entity) throws ValidException;

    /**
     * @return the length of the repository
     */
    public int length();
}