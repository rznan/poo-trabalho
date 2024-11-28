package com.rznan.controller;

import com.rznan.exceptions.TransporteException;

public abstract class ControllerFactory<T> {
    public abstract IController<T> createController() throws TransporteException;
}
