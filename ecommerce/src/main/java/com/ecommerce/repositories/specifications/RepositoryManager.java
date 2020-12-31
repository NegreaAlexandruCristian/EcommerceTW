package com.ecommerce.repositories.specifications;

import java.util.List;

public interface RepositoryManager<T, ID>{

    <S extends T> S save(S entity);
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    void delete(T entity);
}
