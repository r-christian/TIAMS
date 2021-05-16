package com.christian.TIAMS.dao.base;

import java.util.List;

public interface IGenericDAO<T> {
    public List<T> findAll();

    public T update(T object);

    public T get(Long id);

    public void deleteObject(T object);

    public void delete(Long id);

    public T insert(T object);

    public boolean exists(Long id);
}
