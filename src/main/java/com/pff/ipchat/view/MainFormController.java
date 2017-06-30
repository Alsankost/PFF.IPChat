package com.pff.ipchat.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import com.pff.ipchat.ChannelVersionsManager;
import com.pff.ipchat.DataManager;
import com.pff.ipchat.chat.Channel;
import com.pff.ipchat.chat.ChannelEntity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
	@FXML
	private Label labelName;
	
	private TreeItem<String> rootTreeItem; //temp
	
    @FXML
    private JFXTreeView channelTreeView;

    @FXML
    private JFXComboBox versionsComboBox;

    @FXML
    private JFXTextField channelTextField;

    @FXML
    private BorderPane rootPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        versionsComboBox.setItems(ChannelVersionsManager.getVersions());
        
        this.labelName.setText(DataManager.getUserName());
        
        /**
         * set entity channel list on TreeView
         * use DataManager.getChannelEntityList();
         */
        
        this.rootTreeItem = new TreeItem<String>("Channels");
        this.channelTreeView.setRoot(rootTreeItem);
    }

    @FXML
    private void onChangeName() {
    	//System.out.print("asd");
    	Optional<String> result = dialogChangeName.showAndWait();
        if (result.isPresent()) {
        	if (result.get().length() > 0)
        	{
        		DataManager.setUserName(result.get());
        		this.labelName.setText(result.get());
        	}
        	else
        	{
        		/**
        		 * - To inform the user
        		 */
        	}
        }
    }
    
    public void onCreateChannelAction() {
        Channel channel = checkAndReturn();
        if (channel != null) {
            ChannelEntity channelEntity = channel.create(channelTextField.getText(), this.labelName.getText() /** <Get user name form ROM> */);
            addEntityChannelToViewTree(channelEntity);
        }
    }

    public void onConnectChannelAction() {
        Channel channel = checkAndReturn();
        if (channel != null) {
            ChannelEntity channelEntity = channel.join(channelTextField.getText(), this.labelName.getText() /** <Get user name form ROM> */);
            addEntityChannelToViewTree(channelEntity);
        }
    }

    TextInputDialog dialogChangeName = new TextInputDialog("anonymous");
    
    private ObservableList<ChannelEntity> channelEntityList;
    
    private void addEntityChannelToViewTree(ChannelEntity ce) {
    	/**
    	 * - Working with hierarchy
    	 * - Add to list
    	 */
    
        rootTreeItem.getChildren().add(new TreeItem<>(ce.toString()));
    }
    
    private Channel checkAndReturn() {
        final boolean isTextEmpty = channelTextField.getText().isEmpty();
        final Object item = versionsComboBox.getSelectionModel().getSelectedItem();
        final boolean isItemSelected = item != null;

        if (!isTextEmpty && isItemSelected) {
            return ChannelVersionsManager.get(item.toString());
        }

        final JFXSnackbar snackbar = new JFXSnackbar(rootPane);
        snackbar.show("You need choose channel version. Enter channel SID to connect or channel name to create",
                "OK", 5000, event -> snackbar.close());

        return null;
    }
    
    public MainFormController() {
    	channelEntityList = FXCollections.observableArrayList();
    	
        dialogChangeName.setTitle("Chane name");
        dialogChangeName.setHeaderText(null);
        dialogChangeName.setContentText("Please enter your name:");
    }
}
