package com.pff.ipchat;

import com.pff.ipchat.model.channels.DummyChannel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

	private static Main mainApp;
	
    public static void main(String[] args) {
    	//Registration channel versions
    	ChannelVersionsManager.register("Dummy Channel v0.0.1", new DummyChannel());
    	
    	DataManager.load();
        launch(args);
    }
    
    public Main getMainApplication() {
    	return mainApp;
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        StackPane mainForm = FXMLLoader.load(Main.class.getResource("view/fxml/MainForm.fxml"));

        Scene scene = new Scene(mainForm);

        primaryStage.setScene(scene);
        primaryStage.setWidth(mainForm.getWidth());
        primaryStage.setHeight(mainForm.getHeight());
        primaryStage.show();
    }
    
    public Main() {
    	mainApp = this;
    }
}
