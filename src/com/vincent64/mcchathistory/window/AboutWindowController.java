package com.vincent64.mcchathistory.window;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;

public class AboutWindowController {
    @FXML private ImageView close;
    @FXML private Label credits;
    @FXML private Label linkGithub;
    @FXML private Label linkDeveloper64;
    private final String LINK_GITHUB = "https://github.com/vincent64/minecraft-chat-history";
    private final String LINK_DEVELOPER64 = "https://developer64.com";

    public void initialize(Stage stage) {
        //Set close button event
        close.setOnMouseClicked(mouseEvent -> stage.close());

        //Set credits text
        credits.setText("Minecraft Chat History v1.0\nView your Minecraft chat history with color and font formatting.\nThis app was developed by Developer64.");

        //Set link button event
        linkGithub.setOnMouseClicked(mouseEvent -> {
            try {
                //Open link in browser
                Desktop.getDesktop().browse(new URL(LINK_GITHUB).toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        linkDeveloper64.setOnMouseClicked(mouseEvent -> {
            try {
                //Open link in browser
                Desktop.getDesktop().browse(new URL(LINK_DEVELOPER64).toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
