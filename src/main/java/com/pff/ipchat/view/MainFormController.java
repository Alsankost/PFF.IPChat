package com.pff.ipchat.view;

import com.jfoenix.controls.*;
import com.pff.ipchat.ChannelVersionsManager;
import com.pff.ipchat.DataManager;
import com.pff.ipchat.Main;
import com.pff.ipchat.chat.Channel;
import com.pff.ipchat.chat.ChannelEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
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
    private StackPane rootPane;

    @FXML
    private JFXDialog changeNicknameDialog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComponentManager.labelName = labelName;
        ComponentManager.changeNicknameDialog = changeNicknameDialog;

        try {
            StackPane stackChangeNicknamePane = FXMLLoader.load(
                    Main.class.getResource("view/fxml/ChangeNameForm.fxml"));
            changeNicknameDialog.setContent(stackChangeNicknamePane);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    public void onChangeNicknameAction() {
        changeNicknameDialog.show(rootPane);
    }

    @FXML
    public void onCreateChannelAction() {
        Channel channel = checkAndReturn();
        if (channel != null) {
            ChannelEntity channelEntity = channel.create(channelTextField.getText(), this.labelName.getText() /** <Get user name form ROM> */);
            addEntityChannelToViewTree(channelEntity);
        }
    }

    @FXML
    public void onConnectChannelAction() {
        Channel channel = checkAndReturn();
        if (channel != null) {
            ChannelEntity channelEntity = channel.join(channelTextField.getText(), this.labelName.getText() /** <Get user name form ROM> */);
            addEntityChannelToViewTree(channelEntity);
        }
    }

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
}
