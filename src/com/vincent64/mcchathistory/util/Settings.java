package com.vincent64.mcchathistory.util;

import java.util.prefs.Preferences;

public class Settings {
    private static Preferences preferences;
    private static String path;

    static {
        //Load preferences
        preferences = Preferences.userRoot();

        //Get preferences
        path = preferences.get("path", System.getProperty("user.home") + "\\AppData\\Roaming\\.minecraft\\logs");
    }

    public static void setPath(String path) {
        Settings.path = path;
        preferences.put("path", path);
    }

    public static String getPath() {
        return path;
    }
}
