package com.ua.project.crud;

import java.util.List;

public interface CRUDService<T> {
    List<T> listAll();
    T add(T element);
    void deleteById(long id);
    void update(T element);
    T getById(long id);
}
