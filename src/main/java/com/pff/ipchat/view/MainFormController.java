package com.pff.ipchat.view;

import com.jfoenix.controls.*;
import com.pff.ipchat.ChannelVersionsManager;
import com.pff.ipchat.DataManager;
import com.pff.ipchat.Main;
import com.pff.ipchat.chat.Channel;
import com.pff.ipchat.chat.ChannelEntity;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
	@FXML
	private Label labelName;
	
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

        //TreeItem<Object> root = new TreeItem<Object>("Channels");
        //root.getChildren().add(new TreeItem<Object>("default"));
        
        this.channelTreeView.setRoot(new TreeItem<Object>("Channels"));
        this.channelTreeView.showRootProperty().set(false);
        this.channelTreeView.setEditable(true);
        
        updateChannelsTree();
        
        
    }

    private void updateChannelsTree() {
    	List<String> groups = DataManager.getGroupNames();
    	TreeItem<Object> root = this.channelTreeView.getRoot();
    	
    	for (int i = 0; i < groups.size(); i++) {
    		TreeItem<Object> groupItem = new TreeItem<>(groups.get(i));
    		List<ChannelEntity> channelEntitys = DataManager.getChannelEntitysFromGroup(groups.get(i));
    		
    		for (int j = 0; j < channelEntitys.size(); j++) {
    			TreeItem<Object> channelEntityItem = new TreeItem<>(channelEntitys.get(j));
    			groupItem.getChildren().add(channelEntityItem);
    		}
    		root.getChildren().add(groupItem);
    	}
    	
    	if (getTreeItemGroupFromName("default") == null) {
    		root.getChildren().add(new TreeItem<Object>("default"));
    	}
    }
    
    private TreeItem<Object> getTreeItemGroupFromName(String name) {
    	ObservableList<TreeItem<Object>> list = this.channelTreeView.getRoot().getChildren();
    	for (int i = 0; i < list.size(); i++) {
    		TreeItem<Object> temp = list.get(i);
    		if (((String)temp.getValue()).compareTo(name) == 0) {
    			return temp;
    		}
    	}
    	return null;
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
    
    	TreeItem<Object> selected = (TreeItem<Object>) this.channelTreeView.getSelectionModel().getSelectedItem();
    	if (selected == null) return;
    	Object value = (selected == null) ? "default" : selected.getValue();
    	String group = (value instanceof ChannelEntity) ? "default" : (String) value;
    	this.getTreeItemGroupFromName(group).getChildren().add(new TreeItem<>(ce));
    	
    	System.out.println(value + " : " + ce.toString());
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
