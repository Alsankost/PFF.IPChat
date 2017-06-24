package com.pff.ipchat.chat;

/**
 * Created by Good_Pudge(Senya) on 24.06.2017.
 * From com.pff.ipchat.chat
 */
public class ChannelEntity {
	private String sid = null; //Specific id
	private String channel_version; //String for version channel in CVM
	
	public ChannelEntity(String sid, String cv)
	{
		this.sid = sid;
		this.channel_version = cv;
	}
	
	public String getSID()
	{
		return sid;
	}
	
	public String getChannelVersion()
	{
		return channel_version;
	}
}
