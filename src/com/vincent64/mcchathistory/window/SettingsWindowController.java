package com.vincent64.mcchathistory.window;

import com.vincent64.mcchathistory.Settings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SettingsWindowController {
    @FXML private ImageView close;
    @FXML private TextField pathField;
    @FXML private Button save;
    private String path;

    public void initialize(Stage stage) {
        //Set close button event
        close.setOnMouseClicked(mouseEvent -> stage.close());

        //Set path from preferences in field
        path = Settings.getPath();
        pathField.setText(path);

        //Update path when the textfield changes
        pathField.textProperty().addListener((observableValue, oldText, newText) -> {
            if(!oldText.equals(newText)) {
                path = newText;
            }
        });

        //Save new settings when clicking on save button
        save.setOnMouseClicked(mouseEvent -> {
            Settings.setPath(path);
            stage.close();
        });
    }
}
