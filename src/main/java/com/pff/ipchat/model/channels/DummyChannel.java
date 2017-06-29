package com.pff.ipchat.model.channels;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.pff.ipchat.chat.Channel;
import com.pff.ipchat.chat.ChannelEntity;
import com.pff.ipchat.model.Interlocutor;
import com.pff.ipchat.model.Message;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class is implementation of dummy channel for front-end testing
 */

public class DummyChannel implements Channel {

	private static int max_id = 0;
	
	private Map<String, Proxy> entity_map;
	
	class Proxy {
		private final ObservableList<Interlocutor> interlocutor_list;
		private final ObservableList<Message> message_list;
		private final ChannelEntity channel_entity;
		
		private final Interlocutor curr_interlocutor;
		
		private final TextField tf_user_name;
		private final ListView<Interlocutor> lw_users;
		
		public ObservableList<Interlocutor> getInterlocatorList() {
			return interlocutor_list;
		}
		
		public ObservableList<Message> getMessageList() {
			return message_list;
		}
		
		public ChannelEntity getChannelEntity() {
			return channel_entity;
		}
		
		public Interlocutor getCurrentInterlocator()
		{
			return curr_interlocutor;
		}
		
		public Proxy(ChannelEntity ce, String un) {	
			channel_entity = ce;
			this.interlocutor_list = FXCollections.observableArrayList();
			this.message_list      = FXCollections.observableArrayList();
			
			curr_interlocutor = new Interlocutor(un, un);
			
			Stage stage = new Stage();
			stage.setTitle(channel_entity.getName());
			
			BorderPane pane = new BorderPane();
			stage.setScene(new Scene(pane, 400, 90));
			
			lw_users = new ListView<Interlocutor>();
			lw_users.setMaxWidth(150);
			lw_users.setItems(interlocutor_list);
			
			pane.setLeft(lw_users);
			
			AnchorPane center_pane = new AnchorPane();
			pane.setCenter(center_pane);
			
			tf_user_name = new TextField();
			tf_user_name.setLayoutX(48);
			tf_user_name.setLayoutY(10);
			tf_user_name.setPrefWidth(144);
			center_pane.getChildren().add(tf_user_name);
			
			Button b_add = new Button("<");
			b_add.setLayoutX(10);
			b_add.setLayoutY(10);
			b_add.setOnAction(val -> {
				String temp = tf_user_name.getText();
				if (temp.length() == 0 || interlocutor_list.contains(temp) || curr_interlocutor.getSID().compareTo(temp) == 0) return;
				interlocutor_list.add(new Interlocutor(temp, temp));
			});
			center_pane.getChildren().add(b_add);
			
			Button b_del = new Button("DU");
			b_del.setLayoutX(201);
			b_del.setLayoutY(10);
			b_del.setOnAction(val -> {
				int ind = lw_users.getSelectionModel().getSelectedIndex();
				if (ind < 0 || ind >= interlocutor_list.size()) return;
				interlocutor_list.remove(ind);
			});
			center_pane.getChildren().add(b_del);
			
			Separator sp = new Separator();
			sp.setLayoutX(10);
			sp.setLayoutY(45);
			sp.setPrefWidth(230);
			center_pane.getChildren().add(sp);
			
			TextField tf_message = new TextField();
			tf_message.setLayoutX(10);
			tf_message.setLayoutY(55);
			center_pane.getChildren().add(tf_message);
			
			Button b_send = new Button("send");
			b_send.setLayoutX(190);
			b_send.setLayoutY(55);
			center_pane.getChildren().add(b_send);
			
			stage.show();
		}
	}
	
	public DummyChannel() {
		entity_map = new HashMap<String, Proxy>();
	}
	
	@Override
	public List<Interlocutor> getInterlocutors(ChannelEntity entity) {
		if (entity == null) return null;
		return entity_map.get(entity.getSID()).getInterlocatorList();
	}

	@Override
	public List<Message> getHistoryMessages(ChannelEntity entity, LocalDate upLimit) {
		if (entity == null) return null;
		return entity_map.get(entity.getSID()).getMessageList();
	}

	@Override
	public boolean send(ChannelEntity entity, String data) {
		if (entity == null) return false;
		if (data.length() > 0) {
			Proxy temp = entity_map.get(entity.getSID());
			temp.message_list.add(new Message(temp.getCurrentInterlocator().getSID(), data, LocalDate.now()));
			return true;
		}
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
		
	}

	@Override
	public void disconnect(ChannelEntity entity) {
		
	}

	@Override
	public boolean isConnection(ChannelEntity entity) {
		return false;
	}

	@Override
	public ChannelEntity create(String channel_name, String owner_name) {
		if (entity_map.keySet().contains(channel_name)) return null;
		
		ChannelEntity temp = new ChannelEntity.Builder()
			.setSid(channel_name)
			.setName(channel_name)
			.build();
		
		Proxy p = new Proxy(temp, owner_name);
		entity_map.put(channel_name, p);
		
		return temp;
	}

	@Override
	public ChannelEntity join(String sid, String user_name) {
		if (!entity_map.keySet().contains(sid)) return null;
		return entity_map.get(sid).getChannelEntity();
	}
	
}
