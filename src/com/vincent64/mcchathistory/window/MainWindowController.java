package com.vincent64.mcchathistory.window;

import com.vincent64.mcchathistory.list.LogItem;
import com.vincent64.mcchathistory.list.LogItemController;
import com.vincent64.mcchathistory.log.LogReader;
import com.vincent64.mcchathistory.log.LogType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainWindowController {
    @FXML private AnchorPane root;
    @FXML private Pane topbar;
    @FXML private ImageView close, maximize, minimize, settings, about;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox scrollContent;
    @FXML private Label bottomInfo;
    @FXML private TextField logSearch;
    @FXML private ImageView logSearchIcon;
    @FXML private ChoiceBox<String> logTypeChoice;
    @FXML private DatePicker logDatePicker;
    private SettingsWindow settingsWindow;
    private AboutWindow aboutWindow;
    private ArrayList<LogItem> logItems;
    private double x, y;

    public void initialize(Stage stage) {
        //Set close, maximize and minimize button event
        close.setOnMouseClicked(mouseEvent -> stage.close());
        maximize.setOnMouseClicked(mouseEvent -> stage.setMaximized(true));
        minimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

        //Set window drag events
        topbar.setOnMousePressed(mouseEvent -> {
            x = stage.getX() - mouseEvent.getScreenX();
            y = stage.getY() - mouseEvent.getScreenY();
        });

        topbar.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() + x);
            stage.setY(mouseEvent.getScreenY() + y);
        });

        //Set datepicker listener
        logDatePicker.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER) {
                //Get selected date and type
                LocalDate date = logDatePicker.getValue();
                LogType type = LogType.getType(logTypeChoice.getValue());
                //Display logs in the list
                displayLogs(date, type);
            }
        });

        logDatePicker.valueProperty().addListener((observableValue, previousDate, currentDate) -> {
            if(previousDate == null || !previousDate.equals(currentDate)) {
                //Get selected date and type
                LocalDate date = logDatePicker.getValue();
                LogType type = LogType.getType(logTypeChoice.getValue());
                //Display logs in the list
                displayLogs(date, type);
            }
        });

        //Set choicebox choice listener
        logTypeChoice.valueProperty().addListener((observableValue, previousItem, currentItem) -> {
            if(previousItem == null || !previousItem.equals(currentItem)) {
                //Get selected date and type
                LocalDate date = logDatePicker.getValue();
                LogType type = LogType.getType(logTypeChoice.getValue());
                //Display logs in the list
                displayLogs(date, type);
            }
        });

        //Set search box listener
        logSearch.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER) {
                if(!logSearch.getText().equals("")) {
                    //Search in the logs if query is not empty
                    searchLogs(logSearch.getText(), LogType.getType(logTypeChoice.getValue()));
                } else {
                    //Remove highlights in list if query is empty
                    clearSearchLogs();
                }
            }
        });

        logSearch.textProperty().addListener((observableValue, oldText, newText) -> {
            if(!newText.equals("")) {
                logSearchIcon.setImage(new Image(getClass().getResourceAsStream("/resources/close_scaled.png")));
            } else {
                logSearchIcon.setImage(new Image(getClass().getResourceAsStream("/resources/search_scaled.png")));
            }
        });

        logSearchIcon.setOnMouseClicked(mouseEvent -> {
            if(!logSearch.getText().equals("")) {
                logSearch.setText("");
                clearSearchLogs();
            }
        });

        //Create choicebox values
        String[] logTypes = new String[] {LogType.CHAT.getValue(), LogType.ALL.getValue(), LogType.RAW.getValue()};
        logTypeChoice.setItems(FXCollections.observableArrayList(logTypes));
        //Set default choicebox value
        logTypeChoice.setValue("Chat logs");

        //Set datepicker current date
        logDatePicker.setValue(LocalDate.now());

        //Initialize settings and about window
        try {
            settingsWindow = new SettingsWindow();
            aboutWindow = new AboutWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Set settings button event
        settings.setOnMouseClicked(mouseEvent -> {
            if(!settingsWindow.isOpen()) {
                settingsWindow.run();
            }
        });

        //Set about button event
        about.setOnMouseClicked(mouseEvent -> {
            if(!aboutWindow.isOpen()) {
                aboutWindow.run();
            }
        });

        //Close settings and about window when main window is closed
        stage.setOnHiding(windowEvent -> {
            if(settingsWindow.isOpen()) settingsWindow.close();
            if(aboutWindow.isOpen()) aboutWindow.close();
        });
    }

    private void displayLogs(LocalDate date, LogType type) {
        //Get logs at the selected date
        logItems = LogReader.getLogs(type, date);

        //Clear previous logs in the list
        scrollContent.getChildren().clear();
        //Scroll back to the top of the list
        scrollPane.setVvalue(0);

        //Measure loading time
        long timeStart = System.currentTimeMillis();

        //Make log list with nodes
        Node[] nodes = new Node[logItems.size()];
        for(int i = 0; i < nodes.length; i++) {
            try {
                //Load log item view and controller
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com/vincent64/mcchathistory/view/log_item.fxml"));
                nodes[i] = loader.load();
                LogItemController logItemController = loader.getController();
                logItemController.initialize();

                //Set if the logs should be formatted
                boolean formatted = type != LogType.RAW;
                logItemController.setLog(logItems.get(i), formatted);
                //Add log item node to the list view
                scrollContent.getChildren().add(nodes[i]);
            } catch(IOException exception) {
                exception.printStackTrace();
            }
        }

        //Calculate loading time
        long timeElapsed = System.currentTimeMillis() - timeStart;
        float timeElapsedSecond = timeElapsed / 1000f;
        String timeElapsedText = new DecimalFormat("#.#").format(timeElapsedSecond);

        //Show line amount and loading time at the bottom of the window
        bottomInfo.setText(logItems.size() + " lines (loaded in " + timeElapsedText + "s)");

        //Display message if no logs available for selected parameters
        if(logItems.isEmpty()) {
            try {
                //Load no log view
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com/vincent64/mcchathistory/view/log_item_none.fxml"));
                Node node = loader.load();
                scrollContent.getChildren().add(node);

                //Show zero line at the bottom of the window
                bottomInfo.setText("0 line");
            } catch(IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void searchLogs(String query, LogType type) {
        //Get logs that contains the query
        ArrayList<Integer> results = LogReader.search(logItems, query);

        //Set transparent background for every logs in the list
        for(int i = 0; i < scrollContent.getChildren().size(); i++) {
            scrollContent.getChildren().get(i).setStyle(".logItem { -fx-background-color: transparent; }");
        }

        //Highlight logs that contains the search query in yellow
        for(Integer result : results) {
            scrollContent.getChildren().get(result).setStyle("-fx-background-color: rgba(255, 255, 0, 0.1);");
        }
    }

    private void clearSearchLogs() {
        //Set transparent background for every logs in the list
        for(int i = 0; i < scrollContent.getChildren().size(); i++) {
            scrollContent.getChildren().get(i).setStyle(".logItem { -fx-background-color: transparent; }");
        }
    }
}
