package com.pff.ipchat.chat;

import com.pff.ipchat.model.Interlocutor;
import com.pff.ipchat.model.Message;

import java.time.LocalDate;
import java.util.List;

public interface Channel
{
	//get API=====================================================
	public List<Interlocutor> getInterlocutors(ChannelEntity entity);

	public List<Message> getHistoryMessages(ChannelEntity entity, LocalDate upLimit);

	//set API=====================================================
	public boolean send(ChannelEntity entity, String data);

	//local=======================================================
	public boolean save(ChannelEntity entity);

	public boolean load(ChannelEntity entity);
	
	//build=======================================================
	public ChannelEntity create(String channel_name, String owner_name);

	public ChannelEntity join(String sid, String user_name);
	
	//connection==================================================
	/**
	 * this method will be invoked when you try to connect
	 * to a channel from the channel list (when you open chat)
	*/
	public void connect(ChannelEntity entity);
	
	/**
	 * this method will be called when the disconnection with
	 * the channel (when the chat was closed or when the button is pressed to detach)
	*/
	public void disconnect(ChannelEntity entity);
	
	/**
	 * this method need for getting status of channel from connection,
	 * calling to check status from GUI and other subsystems
	*/
	public boolean isConnection(ChannelEntity entity);

}
