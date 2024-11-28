package com.rznan.controller;

import com.rznan.entity.Onibus;
import com.rznan.exceptions.TransporteException;
import com.rznan.persistence.IDao;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OnibusController implements IController<Onibus> {

    private final ObservableList<Onibus> list = FXCollections.observableArrayList();
    private int counter;

    private final IntegerProperty id = new SimpleIntegerProperty(0);
    private final StringProperty fabricante = new SimpleStringProperty("");
    private final IntegerProperty quantidade = new SimpleIntegerProperty(0);
    private final DoubleProperty velocidadeMax = new SimpleDoubleProperty(0);
    private final IntegerProperty numeroDePneus = new SimpleIntegerProperty(0);

    private final IDao<Onibus> DAO;

    public OnibusController(IDao<Onibus> dao) throws TransporteException {
        this.DAO = dao;
        searchAll();
        counter = list.size() + 1;
    }

    @Override
    public void toScreen(Onibus onibus) {
        if(onibus != null) {
            id.set(onibus.getId());
            fabricante.set(onibus.getMarcaFabricante());
            quantidade.set(onibus.getQuantidadeMaxPassageiros());
            velocidadeMax.set(onibus.getVelocidadeMaxima());
            numeroDePneus.set(onibus.getNumeroDePneus());
        }
    }

    @Override
    public Onibus toEntity() {
        Onibus o = new Onibus();
        o.setId(id.get());
        o.setMarcaFabricante(fabricante.get());
        o.setQuantidadeMaxPassageiros(quantidade.get());
        o.setVelocidadeMaxima(velocidadeMax.get());
        o.setNumeroDePneus(numeroDePneus.get());
        return o;
    }

    @Override
    public void clearScreen() {
        id.set(0);
        fabricante.set("");
        quantidade.set(0);
        velocidadeMax.set(0);
        numeroDePneus.set(0);
    }

    @Override
    public ObservableList<Onibus> getList() {
        return this.list;
    }

    @Override
    public void delete(Onibus onibus) throws TransporteException {
        DAO.remove(onibus);
        searchAll();
    }

    @Override
    public void save() throws TransporteException {
        Onibus o = toEntity();
        if(o.getId() == 0) {
            this.counter++;
            o.setId(this.counter);
            DAO.insert(o);
        } else {
            DAO.update(o);
        }
        searchAll();
    }

    @Override
    public void search() throws TransporteException {
        list.clear();
        list.addAll(DAO.search(fabricante.get()));
        if(!list.isEmpty()) {
            toScreen(list.get(0));
        }
    }

    @Override
    public void searchAll() throws TransporteException {
        list.clear();
        list.addAll(DAO.search(""));
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty fabricanteProperty() {
        return fabricante;
    }

    public IntegerProperty quantidadeProperty() {
        return quantidade;
    }

    public DoubleProperty velocidadeMaxProperty() {
        return velocidadeMax;
    }

    public IntegerProperty numeroDePneusProperty() {
        return numeroDePneus;
    }
}
