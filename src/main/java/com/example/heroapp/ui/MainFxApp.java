package com.example.heroapp.ui;

import com.example.heroapp.services.CharacterService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

        Button addButton = new Button("Add");

        ListView<String> list = new ListView<>();

        addButton.setOnAction(e -> {
            service.add(nameField.getText(), typebox.getValue());
            refresh(list);
        });

        VBox root = new VBox(10, nameField, typebox, addButton, list);
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
