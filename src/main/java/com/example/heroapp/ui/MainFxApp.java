package com.example.heroapp.ui;

import com.example.heroapp.services.CharacterService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainFxApp extends Application {
    private static CharacterService service;

    @Autowired
    public void setService(CharacterService s) {
        service = s;
    }

    @Override
    public void start(Stage stage) {
        TextField nameField = new TextField();
        ComboBox<String> typebox = new ComboBox<>();
        typebox.getItems().addAll("hero", "villain");

        typebox.setStyle("""
                -fx-background-color: #3a3a50;
                -fx-mark-color: white;
                """);

        typebox.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
                setTextFill(javafx.scene.paint.Color.WHITE);
            }
        });


        Button addButton = new Button("Add");

        ListView<String> list = new ListView<>();

        addButton.setOnAction(e -> {
            service.add(nameField.getText(), typebox.getValue());
            refresh(list);
        });

        addButton.setStyle("""
                -fx-background-color: #ff6f61;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-background-radius: 8;
                """);

        nameField.setStyle("""
                -fx-background-radius: 8;
                -fx-background-color: #3a3a50;
                -fx-text-fill: #ffffff;
                -fx-padding: 5;
                """);

        typebox.setStyle("""
                -fx-background-radius: 8;
                -fx-background-color: #3a3a50;
                -fx-text-fill: #ffffff;
                """);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #1e1e2f;"); // dark background

        VBox inputBox = new VBox(10, nameField, typebox, addButton);
        inputBox.setPadding(new Insets(10));
        inputBox.setStyle("-fx-background-color: #2e2e44; -fx-background-radius: 10;");
        list.setStyle("-fx-background-color: #252538; -fx-text-fill: #ffffff;");
        root.setTop(inputBox);
        root.setCenter(list);

        stage.setScene(new Scene(root, 300, 400));
        stage.show();

        refresh(list);
    }

    private void refresh(ListView<String> list) {
        list.getItems().setAll(
                service.getAll().stream()
                        .map(c -> c.getId() + " - " + c.getName() + " (" + c.getType() + ")")
                        .toList()
        );
    }
}
