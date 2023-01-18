package com.vincent64.mcchathistory.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SettingsWindow {
    private Stage stage;
    private boolean isOpen;

    public SettingsWindow() throws Exception {
        //Create new stage
        stage = new Stage();

        //Load stage
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com/vincent64/mcchathistory/view/settings_window.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        //Load Minecraft font used in the stage
        Font.loadFont(getClass().getResourceAsStream("/resources/minecraft_font.ttf"), 10);

        //Set stage informations
        stage.setTitle("Settings");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.png")));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setWidth(640);
        stage.setHeight(480);

        //Load stage controller
        ((SettingsWindowController) loader.getController()).initialize(stage);

        stage.setOnHidden(windowEvent -> {
            isOpen = false;
        });
    }

    public void run() {
        //Show the stage
        stage.show();
        stage.toFront();
        isOpen = true;
    }

    public void close() {
        //Close the stage
        isOpen = false;
        stage.close();
    }

    public boolean isOpen() {
        return isOpen;
    }
}
