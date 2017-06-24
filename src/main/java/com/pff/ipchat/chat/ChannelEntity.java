package com.pff.ipchat.chat;

/**
 * Created by Good_Pudge(Senya) on 24.06.2017.
 * From com.pff.ipchat.chat
 */
public class ChannelEntity {
	private String sid = null; //Specific id
	private String channel_version = null; //String for version channel in CVM
	private String other_data = null;
	
	public ChannelEntity(String sid, String cv) {
		this.sid = sid;
		this.channel_version = cv;
	}
	
	public String getSID() {
		return sid;
	}
	
	public String getChannelVersion() {
		return channel_version;
	}
	
	public String getData()
	{
		return other_data;
	}
	
	public void setData(String data)
	{
		this.other_data = data;
	}
}
