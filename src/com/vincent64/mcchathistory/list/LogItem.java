package com.vincent64.mcchathistory.list;

public class LogItem {
    private final String text;
    private final String timestamp;

    public LogItem(String text, String timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
