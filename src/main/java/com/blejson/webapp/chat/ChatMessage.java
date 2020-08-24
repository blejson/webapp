package com.blejson.webapp.chat;

/**
 * Created by Blejson on 24.08.2020
 */
public class ChatMessage {
    private String value;

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
