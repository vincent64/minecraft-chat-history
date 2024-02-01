package com.vincent64.mcchatsearch.log;

public class Result {
    private String fileName;
    private int line;
    private long character;
    private long timestamp;

    public Result(String fileName, int line, long character, long timestamp) {
        this.fileName = fileName;
        this.line = line;
        this.character = character;
        this.timestamp = timestamp;
    }

    public String getFileName() {
        return fileName;
    }

    public int getLine() {
        return line;
    }

    public long getCharacter() {
        return character;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
