package pff.com.ipchat.chat;

import java.time.LocalDate;
import java.util.List;

import pff.com.ipchat.model.Interlocutor;
import pff.com.ipchat.model.Message;

public interface Channel
{
	public List<Interlocutor> getInterlocutors(ChannelEntity entity);
	
	public List<Message> getMessages(ChannelEntity entity, LocalDate upLimit);
	
	public boolean send(ChannelEntity entity, String data);
}
