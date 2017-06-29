package com.pff.ipchat.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import com.pff.ipchat.ChannelVersionsManager;
import com.pff.ipchat.chat.Channel;
import com.pff.ipchat.chat.ChannelEntity;
import com.pff.ipchat.model.Interlocutor;
import com.pff.ipchat.model.Message;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

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
    private BorderPane rootPane;

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
			public void connect(ChannelEntity entity) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disconnect(ChannelEntity entity) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean isConnection(ChannelEntity entity) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public ChannelEntity create(String channel_name, String owner_name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ChannelEntity join(String sid, String user_name) {
				// TODO Auto-generated method stub
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
			public void connect(ChannelEntity entity) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disconnect(ChannelEntity entity) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean isConnection(ChannelEntity entity) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public ChannelEntity create(String channel_name, String owner_name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ChannelEntity join(String sid, String user_name) {
				// TODO Auto-generated method stub
				return null;
			}
        });
        //TEST

        versionsComboBox.getItems().addAll(ChannelVersionsManager.getVersions());
    }

    public void onCreateChannelAction() {
        Channel channel = checkAndReturn();
        if (channel != null) {
            ChannelEntity channelEntity = channel.create(channelTextField.getText(), "test");
        }
    }

    public void onConnectChannelAction() {
        Channel channel = checkAndReturn();
        if (channel != null) {
            ChannelEntity channelEntity = channel.join(channelTextField.getText(), "test");
        }
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
