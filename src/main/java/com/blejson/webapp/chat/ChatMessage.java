package com.blejson.webapp.chat;

/**
 * Created by Blejson on 24.08.2020
 */
public class ChatMessage {
    private String value;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ChatMessage() {
    }

    public ChatMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
