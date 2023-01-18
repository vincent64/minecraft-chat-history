package com.vincent64.mcchathistory.log;

public enum LogType {
    CHAT("Chat logs"),
    ALL("All logs"),
    RAW("Raw logs");

    private final String value;

    LogType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LogType getType(String typeValue) {
        switch(typeValue) {
            case "Chat logs":
                return CHAT;
            case "All logs":
                return ALL;
            case "Raw logs":
                return RAW;
            default:
                return CHAT;
        }
    }
}
