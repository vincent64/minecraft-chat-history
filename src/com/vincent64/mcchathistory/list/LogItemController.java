package com.vincent64.mcchathistory.list;

import com.vincent64.mcchathistory.util.TextFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class LogItemController {
    @FXML private TextFlow logText;
    @FXML private Label timestampText;
    @FXML private HBox logPane;

    public void initialize() {
        //Load Minecraft font
        timestampText.setFont(Font.font("Minecraft Regular", 8));

        //Show timestamp on hover
        logPane.setOnMouseEntered(mouseEvent -> {
            timestampText.setVisible(true);
        });

        //Hide timestamp when the mouse stop hovering
        logPane.setOnMouseExited(mouseEvent -> {
            timestampText.setVisible(false);
        });
    }

    public void setLog(LogItem logItem, boolean formatted) {
        if(formatted) {
            //Set text to formatted log
            logText.getChildren().addAll(TextFormat.getText(logItem.getText()));
        } else {
            //Set text to unformatted logs
            Text unformattedText = new Text(logItem.getText());
            unformattedText.setFont(Font.font("Minecraft Regular", 16));
            unformattedText.setFill(Color.WHITE);
            logText.getChildren().add(unformattedText);
        }

        //Set timestamp text
        timestampText.setText(logItem.getTimestamp());
    }
}
