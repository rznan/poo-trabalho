package com.rznan.persistence;

import com.rznan.exceptions.TransporteException;

import java.util.List;

public interface IDao<T> {
    void insert(T t)          throws TransporteException;
    void update(T t)          throws TransporteException;
    void remove(T t)          throws TransporteException;
    List<T> search(String name) throws TransporteException;
}
