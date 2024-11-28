package com.rznan.boundary;


import com.rznan.controller.IController;
import com.rznan.controller.OnibusController;
import com.rznan.entity.Onibus;
import com.rznan.exceptions.TransporteException;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class OnibusBoundary implements Tela {
    private final OnibusController controller;

    private final     Label idDisplay = new Label();
    private final TextField inFabricante = new TextField();
    private final TextField inQuantidade = new TextField();
    private final TextField inVelocidade = new TextField();
    private final TextField inNumeroPneus = new TextField();

    private final TableView<Onibus> tableView = new TableView<>();

    public OnibusBoundary(IController<Onibus> controller) {
        this.controller = (OnibusController) controller;
    }

    @Override
    public Pane render() {
        BorderPane main = new BorderPane();
        GridPane formPane = new GridPane();
        GridPane buttonPane = new GridPane();

        addButtons(buttonPane);
        addForm(formPane);
        links();
        generateColumns();

        main.setTop(formPane);
        main.setCenter(buttonPane);
        main.setBottom(tableView);

        return main;
    }

    private void addForm(GridPane pane) {
        addField(pane, this.idDisplay, 0, "Id: ");
        addField(pane, this.inFabricante, 1, "Fabricante: ");
        addField(pane, this.inNumeroPneus, 2, "N° Pneus: ");
        addField(pane, this.inVelocidade, 3, "Velocidade max: ");
        addField(pane, this.inQuantidade, 4, "Max passageiros: ");
    }

    private void addField(GridPane gridPane, Node formInput, int rowIndex, String lblMessage) {
        gridPane.add(new Label(lblMessage), 0, rowIndex);
        gridPane.add(formInput, 1, rowIndex);
    }

    private void addButtons(GridPane pane) {
        Button saveBtn = new Button("Gravar");
        saveBtn.setOnAction( e -> {
            try {
                controller.save();
            } catch (TransporteException err) {
                new Alert(Alert.AlertType.ERROR, "Erro ao Gravar o ONIBUS", ButtonType.OK).showAndWait();
            }
            tableView.refresh();
        });

        Button searchBtn = new Button("Pesquisar");
        searchBtn.setOnAction( e -> {
            try {
                controller.search();
            } catch (TransporteException err) {
                new Alert(Alert.AlertType.ERROR, "Erro ao Pesquisar o ONIBUS", ButtonType.OK).showAndWait();
            }
            tableView.refresh();
        });

        Button deleteBtn = new Button("Deletar");
        searchBtn.setOnAction( e -> {
            try {
                Onibus onibus = tableView.getItems().get( tableView.getSelectionModel().getFocusedIndex() );
                    controller.delete(onibus);
            } catch (TransporteException err) {
                new Alert(Alert.AlertType.ERROR, "Erro ao Deletar o ONIBUS", ButtonType.OK).showAndWait();
            }
            tableView.refresh();
        });
        Button clearBtn = new Button("Limpar");
        clearBtn.setOnAction(e -> controller.clearScreen());

        pane.add(saveBtn, 0, 0);
        pane.add(searchBtn, 0, 1);
        pane.add(deleteBtn, 0, 2);
        pane.add(clearBtn, 0, 3);
    }

    @SuppressWarnings("unchecked")
    private void generateColumns() {
        TableColumn<Onibus, Integer> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<Onibus, Integer>("id"));

        TableColumn<Onibus, String> colFabricante = new TableColumn<>("Fabricante");
        colFabricante.setCellValueFactory(new PropertyValueFactory<Onibus, String>("marcaFabricante"));

        TableColumn<Onibus, Integer> colQuantidade = new TableColumn<>("Max Passageiros");
        colQuantidade.setCellValueFactory(new PropertyValueFactory<Onibus, Integer>("quantidadeMaxPassageiros"));

        TableColumn<Onibus, Double> colVelocidade = new TableColumn<>("Velocidade Max");
        colVelocidade.setCellValueFactory(new PropertyValueFactory<Onibus, Double>("velocidadeMaxima"));

        TableColumn<Onibus, Integer> colNPneus = new TableColumn<>("Numero Pneus");
        colNPneus.setCellValueFactory(new PropertyValueFactory<Onibus, Integer>("numeroDePneus"));

        tableView.getColumns().clear();
        tableView.getColumns().addAll(colId, colFabricante, colNPneus, colVelocidade, colQuantidade);
        tableView.setItems(this.controller.getList());
    }

    private void links() {
        controller.idProperty().addListener( (obs, old, newer) -> {
            idDisplay.setText(String.valueOf(newer));
        });

        IntegerStringConverter integerConverter = new IntegerStringConverter();
        DoubleStringConverter doubleStringConverter = new DoubleStringConverter();

        Bindings.bindBidirectional(controller.fabricanteProperty(), inFabricante.textProperty());
        Bindings.bindBidirectional(inQuantidade.textProperty(), controller.quantidadeProperty(), (StringConverter) integerConverter);
        Bindings.bindBidirectional(inNumeroPneus.textProperty(), controller.numeroDePneusProperty(), (StringConverter) integerConverter);
        Bindings.bindBidirectional(inVelocidade.textProperty(), controller.velocidadeMaxProperty(), (StringConverter) doubleStringConverter);
    }
}