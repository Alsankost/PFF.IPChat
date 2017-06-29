package com.pff.ipchat.chat;

public class ChannelEntity {
	private final String sid; //Specific id
	private final String name;
	private final String channel_version; //String for version channel in CVM
	private String other_data = null;

    public ChannelEntity(Builder builder) {
        this.sid = builder.sid;
        this.channel_version = builder.channel_version;
        this.name = builder.name;
    }

    public String getSID() {
        return sid;
	}

    public static class Builder {
        private String sid;
        private String channel_version;
        private String name;

        public Builder setSid(String sid) {
            this.sid = sid;
            return this;
        }

        public Builder setChannelVersion(String channelVersion) {
            this.channel_version = channelVersion;
            return this;
        }
        
        public Builder setName(String n) {
        	this.name = n;
        	return this;
        }

        public ChannelEntity build() {
            return new ChannelEntity(this);
        }
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
	
	public String getName() {
    	return name;
    }
}
