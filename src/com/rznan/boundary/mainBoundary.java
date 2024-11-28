package com.rznan.boundary;

import com.rznan.controller.ControllerFactory;
import com.rznan.controller.OnibusControllerFactory;
import com.rznan.controller.TremControllerFactory;
import com.rznan.exceptions.TransporteException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class mainBoundary extends Application {

    private final Map<String, Tela> telaMap = new HashMap<>();

    @Override
    public void start(Stage stage) throws Exception {
        String[] telas = { "Trem", "Onibus" };
        for(String tela : telas) {
            telaMap.put(tela, makeTela(tela));
        }
        BorderPane main = new BorderPane();

        MenuBar menuBar = new MenuBar();

        addMenu(main, menuBar);

        Scene scn = new Scene(main, 800, 600);
        stage.setScene(scn);
        stage.setTitle("Veículos de Transporte público");
        stage.show();
    }

    private void addMenu(BorderPane main, MenuBar menuBar) {
        Menu menu = new Menu("Cadastro");
        MenuItem menuItemTrem = new MenuItem("Trem");
        menuItemTrem.setOnAction(e -> main.setCenter(telaMap.get("Trem").render()));

        MenuItem menuItemOnibus = new MenuItem("Onibus");
        menuItemOnibus.setOnAction(e -> main.setCenter(telaMap.get("Onibus").render()));

        menu.getItems().addAll(menuItemTrem, menuItemOnibus);

        Menu menuAjuda = new Menu("Ajuda");
        MenuItem menuItemCreditos = new MenuItem("Creditos");

        menuAjuda.getItems().addAll(menuItemCreditos);

        menuBar.getMenus().addAll(menu, menuAjuda);
        main.setTop(menuBar);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Tela makeTela(String tela) {
        ControllerFactory factory;
        try {
            if(tela.equalsIgnoreCase("Trem")) {
                factory = new TremControllerFactory();
                return new TremBoundary(factory.createController());
            }
            else if(tela.equalsIgnoreCase("Onibus")) {
                factory = new OnibusControllerFactory();
                return new OnibusBoundary(factory.createController());
            }
            throw new TransporteException();
        } catch (TransporteException e ) {
            new Alert(Alert.AlertType.ERROR, "Erro ao iniciar o sistema", ButtonType.OK).showAndWait();
        }
        return null;
    }

    public static void main(String[] args) {
        Application.launch(mainBoundary.class, args);
    }
}
