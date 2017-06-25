package com.pff.ipchat.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import com.pff.ipchat.ChannelVersionsManager;
import com.pff.ipchat.chat.Channel;
import com.pff.ipchat.chat.ChannelEntity;
import com.pff.ipchat.model.Interlocutor;
import com.pff.ipchat.model.Message;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    @FXML
    private JFXTreeView channelTreeView;

    @FXML
    private JFXComboBox versionsComboBox;

    @FXML
    private JFXTextField channelTextField;

    @FXML
    private JFXButton channelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TEST
        ChannelVersionsManager.register("Hello", new Channel() {
            @Override
            public List<Interlocutor> getInterlocutors(ChannelEntity entity) {
                return null;
            }

            @Override
            public List<Message> getHistoryMessages(ChannelEntity entity, LocalDate upLimit) {
                return null;
            }

            @Override
            public boolean send(ChannelEntity entity, String data) {
                return false;
            }

            @Override
            public boolean save(ChannelEntity entity) {
                return false;
            }

            @Override
            public boolean load(ChannelEntity entity) {
                return false;
            }

            @Override
            public ChannelEntity create(String channel_name) {
                return null;
            }

            @Override
            public ChannelEntity join(String sid) {
                return null;
            }
        });
        ChannelVersionsManager.register("Byte", new Channel() {
            @Override
            public List<Interlocutor> getInterlocutors(ChannelEntity entity) {
                return null;
            }

            @Override
            public List<Message> getHistoryMessages(ChannelEntity entity, LocalDate upLimit) {
                return null;
            }

            @Override
            public boolean send(ChannelEntity entity, String data) {
                return false;
            }

            @Override
            public boolean save(ChannelEntity entity) {
                return false;
            }

            @Override
            public boolean load(ChannelEntity entity) {
                return false;
            }

            @Override
            public ChannelEntity create(String channel_name) {
                return null;
            }

            @Override
            public ChannelEntity join(String sid) {
                return null;
            }
        });
        //TEST

        //Get versions
        //The first element need, if you want connect to channel
        versionsComboBox.getItems().add("");
        versionsComboBox.getItems().addAll(ChannelVersionsManager.getVersions());

    }

    public void onChannelButtonAction() {
        boolean isTextEmpty = channelTextField.getText().isEmpty();
        Object item = versionsComboBox.getSelectionModel().getSelectedItem();
        boolean isItemSelected = (item != null) && !item.equals("");

        if ((isTextEmpty && !isItemSelected) || (isTextEmpty && isItemSelected)) {
            //You should choose channel version to create and enter channel name or enter channel ID to connect
        } else if (!isTextEmpty && isItemSelected) {
            //Create new channel
            ChannelEntity channelEntity = ChannelVersionsManager.get(versionsComboBox
                    .getSelectionModel()
                    .getSelectedItem()
                    .toString())
                    .create(channelTextField.getText());
        } else {
            //Connect to channel
            ChannelEntity channelEntity = ChannelVersionsManager
                    .get("Hello") //*when get info about channel*
                    .join(channelTextField.getText());
        }
    }
}
