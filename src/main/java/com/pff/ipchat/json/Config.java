package com.pff.ipchat.json;

import com.pff.ipchat.chat.ChannelEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Good_Pudge(Senya) on 02.07.2017.
 * From com.pff.ipchat.json
 */
public class Config {
    private String username;
    private Map<String, List<ChannelEntity>> groups;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, List<ChannelEntity>> getGroups() {
        return groups;
    }
}
