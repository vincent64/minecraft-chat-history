package com.vincent64.mcchathistory.util;

import java.util.prefs.Preferences;

public class Settings {
    private static Preferences preferences;
    private static String path;

    static {
        //Load preferences
        preferences = Preferences.userRoot();

        //Get default path for Windows and MacOS
        String os = System.getProperty("os").toLowerCase();
        String defaultPath = System.getProperty("user.home") + "\\AppData\\Roaming\\.minecraft\\logs";
        if(os.contains("mac")) {
            defaultPath = System.getProperty("user.home") + "/Library/Application Support/minecraft/logs";
        }

        //Get preferences
        path = preferences.get("path", defaultPath);
    }

    public static void setPath(String path) {
        Settings.path = path;
        preferences.put("path", path);
    }

    public static String getPath() {
        return path;
    }
}
