package com.vincent64.mcchathistory.window;

import com.vincent64.mcchathistory.util.DateUtil;
import com.vincent64.mcchathistory.util.ResizeHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;


public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Load stage
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com/vincent64/mcchathistory/view/main_window.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        //Load Minecraft font used in the stage
        Font.loadFont(getClass().getResourceAsStream("/resources/minecraft_font.ttf"), 10);

        //Set stage informations
        stage.setTitle("Minecraft Chat History");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.png")));
        stage.setResizable(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setWidth(1024);
        stage.setHeight(640);

        //Set minimum stage size
        stage.setMinWidth(1024);
        stage.setMinHeight(640);

        //Add ResizeHelper to resize undecorated stage
        ResizeHelper.addResizeListener(stage);

        //Set default date on launch as today
        LocalDate launchDate = LocalDate.now();
        //Get program parameters
        Parameters params = getParameters();
        //Check if the parameters' length is 1
        if(params.getRaw().size() == 1) {
            String param = params.getRaw().get(0);
            //Check if the parameter is a date
            if (!param.isEmpty() && DateUtil.isValidFormat(param) && DateUtil.isValidDate(param)) {
                launchDate = DateUtil.toLocalDate(param);
            }
        }

        //Load stage controller
        ((MainWindowController) loader.getController()).initialize(stage, launchDate);

        //Show the stage
        stage.show();
    }

    public void run() {
        launch();
    }
}
