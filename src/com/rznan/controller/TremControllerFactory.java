package com.rznan.controller;

import com.rznan.entity.Trem;
import com.rznan.exceptions.TransporteException;
import com.rznan.persistence.TremDAO;

public class TremControllerFactory extends ControllerFactory<Trem> {
    @Override
    public IController<Trem> createController() throws TransporteException {
        TremDAO tremDAO = new TremDAO();
        return new TremController(tremDAO);
    }
}
