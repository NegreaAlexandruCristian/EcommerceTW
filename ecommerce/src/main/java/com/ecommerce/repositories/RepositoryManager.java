package com.ecommerce.repositories;


import java.util.Optional;

public interface RepositoryManager<T, ID>{

    <S extends T> S save(S entity);
    T findById(ID id);
    boolean existsById(ID id);
    Iterable<T> findAll();
    long count();
    void deleteById(ID id);
    void delete(T entity);
    void deleteAll();
}
