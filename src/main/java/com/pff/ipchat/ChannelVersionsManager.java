package com.pff.ipchat;

import com.pff.ipchat.chat.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ChannelVersionsManager {
	private static final Map <String,Channel> MAP = new HashMap<String,Channel>();
	
	public static boolean register(String version, Channel channel) {
		if (channel == null || version == null || version.length() == 0 || MAP.containsKey(version)) return false;
		MAP.put(version, channel);
		return true;
	}
	
	public static Channel get(String version) {
		return MAP.get(version);
	}
	
	public static Channel removeFromVersion(String version) {
		return MAP.put(version, null);
	}
	
	public static Channel removeFromObject(Channel channel) {
		return MAP.remove(channel);
	}
	
	public static List<String> getVersions() {
		List<String> temp = new ArrayList<String>();
		for (Entry<String, Channel> entry : MAP.entrySet()) {
			if (entry.getKey() != null) {
				temp.add(entry.getKey());
			}
		}
		
		return (temp.size() > 0)?temp:null;
 	}
}
