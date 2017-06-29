package com.pff.ipchat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import javax.swing.JFrame;

public class Main extends Application {

	private static Main mainApp;
	
    public static void main(String[] args) {
        launch(args);
    }
    
    public Main getMainApplication() {
    	return mainApp;
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane mainForm = FXMLLoader.load(Main.class.getResource("view/fxml/MainForm.fxml"));

        Scene scene = new Scene(mainForm);

        primaryStage.setScene(scene);
        primaryStage.setWidth(mainForm.getWidth());
        primaryStage.setHeight(mainForm.getHeight());
        primaryStage.show();
        
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(200,200);
		frame.setVisible(true);
    }
    
    public Main() {
    	mainApp = this;
    }
}
