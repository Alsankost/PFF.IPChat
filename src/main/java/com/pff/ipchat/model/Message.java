package com.pff.ipchat.model;

import java.time.LocalDate;

public class Message
{
	private final String interlocutor_sid;
	private final String data;
	private final LocalDate date;
	
	public Message(String isid, String data, LocalDate date)
	{
		this.interlocutor_sid = isid;
		this.data = data;
		this.date = date;
	}
	
	public String getInterlocutorSID()
	{
		return this.interlocutor_sid;
	}
	
	public String getData()
	{
		return data;
	}
	
	public LocalDate getDate()
	{
		return date;
	}
}
