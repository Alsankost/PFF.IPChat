package com.pff.ipchat;

import com.pff.ipchat.chat.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ChannelVersionsManager {
	private static final Map<String, Channel> MAP = new HashMap<>();

    public static boolean register(String version, Channel channel) {
        if (channel == null || version == null || version.length() == 0 || MAP.containsKey(version)) return false;
		MAP.put(version, channel);
		return true;
	}
	
	public static Channel get(String version) {
		return MAP.get(version);
	}

	public static boolean removeByVersion(String version) {
		return MAP.keySet().remove(version);
	}

	public static boolean removeByChannel(Channel channel) {
		return MAP.values().remove(channel);
	}

    public static List<String> getVersions() {
        List<String> temp = new ArrayList<>();
		temp.addAll(MAP.keySet());
		return (temp.size() > 0) ? temp : null;
 	}
}
