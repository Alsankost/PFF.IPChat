package com.pff.ipchat;

import java.util.ArrayList;
import java.util.List;

import com.pff.ipchat.chat.ChannelEntity;

public abstract class DataManager {
	
	private final static List<ChannelEntity> CHANNEL_ENTITY_LIST = new ArrayList<ChannelEntity>();
	private static String userName = "NONE";
	
	public static void setUserName(String n) {
		
	}
	
	public static String getUserName() {
		return userName;
	}
	
	public static List<ChannelEntity> getChannelEntityList() {
		return CHANNEL_ENTITY_LIST;
	}
	
	public static void addGroup(String group) {
		
	}
	
	public static void removeGroup(String group) {
		
	}
	
	public static void removeEmptyGroup(String group) {
		
	}
	
	public static void addChannelEntity(String group, ChannelEntity ce) {
		
	}
	
	public static void moveChannelEntity(ChannelEntity ce, String group) {
		
	}
	
	public static void removeChannelEntity(ChannelEntity ce) {
		
	}
	
	public static boolean save() {
		return false;
	}
	
	public static boolean load() {
		return false;
	}
}
