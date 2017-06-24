package com.pff.ipchat.chat;

import com.pff.ipchat.model.Interlocutor;
import com.pff.ipchat.model.Message;

import java.time.LocalDate;
import java.util.List;

public interface Channel
{
	List<Interlocutor> getInterlocutors(ChannelEntity entity);

	List<Message> getMessages(ChannelEntity entity, LocalDate upLimit);

	boolean send(ChannelEntity entity, String data);
}
