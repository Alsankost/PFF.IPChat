package pff.com.ipchat.chat;

public class ChannelEntity
{
	private String sid = null;
	private String channel_version = null;
	
	public ChannelEntity(String sid, String channel)
	{
		this.sid = sid;
		this.channel_version = channel;
	}
	
	public String getSID()
	{
		return sid;
	}
	
	public String getChannel()
	{
		return channel_version;
	}
}
