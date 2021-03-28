package repository;

import domain.BaseEntity;

public class MemoryRepository<ID, T extends BaseEntity<ID>> extends AbstractRepository<ID, T> {
    @Override
    protected void readAll() {

    }

    @Override
    protected void writeAll() {

    }
}
