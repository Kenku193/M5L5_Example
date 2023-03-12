package org.example.repository;

public interface Repo<E> {
    E getBiId(Long id);

    void update(E entity);
}
