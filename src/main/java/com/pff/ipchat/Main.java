package com.pff.ipchat;
/**
 * Created by Good_Pudge(Senya) on 24.06.2017.
 * From com.pff.ipchat
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane mainForm = FXMLLoader.load(Main.class.getResource("view/fxml/MainForm.fxml"));

        Scene scene = new Scene(mainForm);

        primaryStage.setScene(scene);
        primaryStage.setWidth(mainForm.getWidth());
        primaryStage.setHeight(mainForm.getHeight());
        primaryStage.show();
    }
}
