package com.pff.ipchat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pff.ipchat.chat.ChannelEntity;
import com.pff.ipchat.json.Config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DataManager {
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static Config config;
	
	public static String getUserName() {
		return config.getUsername();
	}

	public static void setUserName(String username) {
		config.setUsername(username);
	}
	
	public static List<ChannelEntity> getChannelEntityList() {
		List<ChannelEntity> buffer = new ArrayList<>();
		config.getGroups().forEach((name, entities) -> buffer.addAll(entities));
		return buffer;
	}
	
	public static List<String> getGroupNames() {
		List<String> buffer = new ArrayList<>();
		config.getGroups().forEach((name, entities) -> buffer.add(name));
		return buffer;
	}
	
	public static List<ChannelEntity> getChannelEntitysFromGroup(String group) {
		List<ChannelEntity> buffer = new ArrayList<>();
		buffer.addAll(config.getGroups().get(group));
		return buffer;	 
	}

	public static void addGroup(String group) {
		if (!config.getGroups().keySet().contains(group)) {
			config.getGroups().put(group, new ArrayList<>());
		}
	}
	
	public static void removeGroup(String group) {
		if (config.getGroups().keySet().contains(group)) {
			config.getGroups().keySet().remove(group);
		}
	}

	public static void addChannelEntity(String group, ChannelEntity channelEntity) {
		if (config.getGroups().keySet().contains(group)) {
			config.getGroups().get(group).add(channelEntity);
		}
	}
	
	public static void moveChannelEntity(ChannelEntity ce, String groupName) {
		if (config.getGroups().keySet().contains(groupName)) {
			removeChannelEntity(ce);
			addChannelEntity(groupName, ce);
		}
	}
	
	public static void removeChannelEntity(ChannelEntity ce) {
		config.getGroups().forEach((name, entities) -> {
			if (entities.contains(ce))
				entities.remove(ce);
		});
	}
	
	public static boolean save() {
		try {
			Writer writer = new PrintWriter("config.json");
			gson.toJson(config, writer);
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean load() {
		try {
			Reader reader = new FileReader("config.json");
			config = gson.fromJson(reader, Config.class);
			reader.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
