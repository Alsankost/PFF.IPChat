package com.pff.ipchat.chat;

import com.pff.ipchat.model.Interlocutor;
import com.pff.ipchat.model.Message;

import java.time.LocalDate;
import java.util.List;

public interface Channel
{
	//get API
	List<Interlocutor> getInterlocutors(ChannelEntity entity);
	List<Message>      getHistoryMessages(ChannelEntity entity, LocalDate upLimit);

	//set API
	boolean send(ChannelEntity entity, String data);
	
	//local
	boolean save(ChannelEntity entity);
	boolean load(ChannelEntity entity);
}
