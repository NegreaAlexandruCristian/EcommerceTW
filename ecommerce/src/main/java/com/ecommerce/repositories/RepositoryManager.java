package com.ecommerce.repositories;


import java.util.List;

public interface RepositoryManager<T, ID>{

    <S extends T> S save(S entity);
    T findById(ID id);
    boolean existsById(ID id);
    List<T> findAll();
    int count();
    void deleteById(ID id);
    void delete(T entity);
}
