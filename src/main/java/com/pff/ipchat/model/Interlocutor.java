package com.pff.ipchat.model;

public class Interlocutor
{
	private final String sid;
	private final String name;
	
	public Interlocutor(String sid, String name)
	{
		this.sid  = sid;
		this.name = name;
	}
	
	public String getSID()
	{
		return sid;
	}
	
	public String getName()
	{
		return name;
	}
}
