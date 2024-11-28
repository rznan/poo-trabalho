package com.rznan.boundary;


import com.rznan.controller.IController;
import com.rznan.controller.TremController;
import com.rznan.entity.Onibus;
import com.rznan.entity.Trem;
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

public class TremBoundary implements Tela {

    private final TremController controller;

    private final     Label idDisplay = new Label();
    private final TextField inEmpresa = new TextField();
    private final TextField inQuantidade = new TextField();
    private final TextField inVelocidade = new TextField();
    private final TextField inExtensao = new TextField();

    private final TableView<Trem> tableView = new TableView<>();

    public TremBoundary(IController<Trem> controller) {
        this.controller = (TremController) controller;
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
        addField(pane, this.inEmpresa, 1, "Empresa: ");
        addField(pane, this.inExtensao, 2, "Extensão: ");
        addField(pane, this.inVelocidade, 3, "Velocidade Max: ");
        addField(pane, this.inQuantidade, 4, "Max Passageiros: ");
    }

    private void addField(GridPane gridPane, Node formInput, int rowIndex, String lblText) {
        gridPane.add(new Label(lblText), 0, rowIndex);
        gridPane.add(formInput, 1, rowIndex);
    }

    private void addButtons(GridPane pane) {
        Button saveBtn = new Button("Gravar");
        saveBtn.setOnAction( e -> {
            try {
                controller.save();
            } catch (TransporteException err) {
                new Alert(Alert.AlertType.ERROR, "Erro ao Gravar o TREM", ButtonType.OK).showAndWait();
            }
            tableView.refresh();
        });

        Button searchBtn = new Button("Pesquisar");
        searchBtn.setOnAction( e -> {
            try {
                controller.search();
            } catch (TransporteException err) {
                new Alert(Alert.AlertType.ERROR, "Erro ao Pesquisar o TREM", ButtonType.OK).showAndWait();
            }
            tableView.refresh();
        });

        Button deleteBtn = new Button("Deletar");
        searchBtn.setOnAction( e -> {
            try {
                Trem trem = tableView.getItems().get( tableView.getSelectionModel().getFocusedIndex() );
                controller.delete(trem);
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
        TableColumn<Trem, Integer> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Trem, String> colEmpresa = new TableColumn<>("Empresa Dona");
        colEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresaDona"));

        TableColumn<Trem, Integer> colQuantidade = new TableColumn<>("Max Passageiros");
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeMaxPassageiros"));

        TableColumn<Trem, Double> colVelocidade = new TableColumn<>("Velocidade Max");
        colVelocidade.setCellValueFactory(new PropertyValueFactory<>("velocidadeMaxima"));

        TableColumn<Trem, Double> colExtensao = new TableColumn<>("Extensão");
        colExtensao.setCellValueFactory(new PropertyValueFactory<>("extensaoTotal"));

        tableView.getColumns().clear();
        tableView.getColumns().addAll(colId, colEmpresa, colExtensao, colVelocidade, colQuantidade);
        tableView.setItems(this.controller.getList());
    }

    private void links() {
        controller.idProperty().addListener( (obs, old, newer) -> {
            idDisplay.setText(String.valueOf(newer));
        });

        IntegerStringConverter integerConverter = new IntegerStringConverter();
        DoubleStringConverter doubleStringConverter = new DoubleStringConverter();

        Bindings.bindBidirectional(controller.empresaProperty(), inEmpresa.textProperty());
        Bindings.bindBidirectional(inQuantidade.textProperty(), controller.quantidadeProperty(), (StringConverter) integerConverter);
        Bindings.bindBidirectional(inExtensao.textProperty(), controller.extensaoProperty(), (StringConverter) doubleStringConverter);
        Bindings.bindBidirectional(inVelocidade.textProperty(), controller.velocidadeMaxProperty(), (StringConverter) doubleStringConverter);
    }
}