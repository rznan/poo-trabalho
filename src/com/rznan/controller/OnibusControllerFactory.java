package com.rznan.controller;

import com.rznan.entity.Onibus;
import com.rznan.exceptions.TransporteException;
import com.rznan.persistence.OnibusDAO;

public class OnibusControllerFactory extends ControllerFactory<Onibus> {
    @Override
    public IController<Onibus> createController() throws TransporteException {
        OnibusDAO onibusDAO = new OnibusDAO();
        return new OnibusController(onibusDAO);
    }
}
