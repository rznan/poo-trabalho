package com.rznan.controller;

import com.rznan.entity.Trem;
import com.rznan.exceptions.TransporteException;
import com.rznan.persistence.IDao;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TremController implements IController<Trem> {

    private final ObservableList<Trem> list = FXCollections.observableArrayList();
    private int counter;

    private final IntegerProperty id = new SimpleIntegerProperty(0);
    private final StringProperty empresa = new SimpleStringProperty("");
    private final IntegerProperty quantidade = new SimpleIntegerProperty(0);
    private final DoubleProperty velocidadeMax = new SimpleDoubleProperty(0);
    private final DoubleProperty extensao = new SimpleDoubleProperty(0);

    private final IDao<Trem> DAO;

    public TremController(IDao<Trem> dao) throws TransporteException {
        this.DAO = dao;
        searchAll();
        counter = list.size() + 1;
    }

    @Override
    public void toScreen(Trem trem) {
        if(trem != null) {
            id.set(trem.getId());
            empresa.set(empresa.get());
            quantidade.set(trem.getQuantidadeMaxPassageiros());
            velocidadeMax.set(trem.getVelocidadeMaxima());
            extensao.set(trem.getExtensaoTotal());
        }
    }

    @Override
    public Trem toEntity() {
        Trem t = new Trem();
        t.setId(id.get());
        t.setEmpresaDona(empresa.get());
        t.setQuantidadeMaxPassageiros(quantidade.get());
        t.setVelocidadeMaxima(velocidadeMax.get());
        t.setExtensaoTotal(extensao.get());
        return t;
    }

    @Override
    public void clearScreen() {
        id.set(0);
        empresa.set("");
        quantidade.set(0);
        velocidadeMax.set(0);
        extensao.set(0);
    }

    @Override
    public ObservableList<Trem> getList() {
        return this.list;
    }

    @Override
    public void delete(Trem trem) throws TransporteException {
        DAO.remove(trem);
        searchAll();
    }

    @Override
    public void save() throws TransporteException {
        Trem t = toEntity();
        if(t.getId() == 0) {
            this.counter++;
            t.setId(this.counter);
            DAO.insert(t);
        } else {
            DAO.update(t);
        }
        searchAll();
    }

    @Override
    public void search() throws TransporteException {
        list.clear();
        list.addAll(DAO.search(empresa.get()));
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

    public StringProperty empresaProperty() {
        return empresa;
    }

    public IntegerProperty quantidadeProperty() {
        return quantidade;
    }

    public DoubleProperty velocidadeMaxProperty() {
        return velocidadeMax;
    }

    public DoubleProperty extensaoProperty() {
        return extensao;
    }
}
