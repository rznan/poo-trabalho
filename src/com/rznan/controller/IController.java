package com.rznan.controller;

import com.rznan.exceptions.TransporteException;
import javafx.collections.ObservableList;

public interface IController<T> {
    void toScreen(T t);
    T    toEntity();
    void clearScreen();
    ObservableList<T> getList();

    void delete(T t) throws TransporteException;
    void save() throws TransporteException;
    void search() throws TransporteException;
    void searchAll() throws TransporteException;

}
