package pff.com.ipchat;

import java.util.HashMap;
import java.util.Map;

import pff.com.ipchat.chat.Channel;

public abstract class ChannelVersionsManager
{
	private static final Map <String,Channel> MAP = new HashMap<String,Channel>();
	
	public static boolean register(String version, Channel channel)
	{
		if (channel == null || version == null || version.length() == 0 || MAP.containsKey(version)) return false;
		MAP.put(version, channel);
		return true;
	}
	
	public static Channel get(String version)
	{
		return MAP.get(version);
	}
	
	public static Channel removeFromVersion(String version)
	{
		return MAP.put(version, null);
	}
	
	public static Channel removeFromObject(Channel channel)
	{
		return MAP.remove(channel);
	}
}
