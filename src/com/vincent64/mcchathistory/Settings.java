package com.vincent64.mcchathistory;

import java.util.prefs.Preferences;

public class Settings {
    private static final String APPDATA_PATH_WINDOWS = "\\AppData\\Roaming\\";
    private static final String APPDATA_PATH_MACOS = "/Library/Application Support/";
    private static final String PATH_KEY = "mcch_path";
    private static Preferences preferences;
    private static String path;

    static {
        //Load preferences
        preferences = Preferences.userRoot();

        //Detect operating system
        String os = System.getProperty("os.name").toLowerCase();
        //Get default path for Windows
        String defaultPath = System.getProperty("user.home") + APPDATA_PATH_WINDOWS + ".minecraft\\logs";
        //Get default path for macOS
        if(os.contains("mac")) {
            defaultPath = System.getProperty("user.home") + APPDATA_PATH_MACOS + "minecraft/logs";
        }

        //Get preferences
        path = preferences.get(PATH_KEY, defaultPath);
    }

    public static void setPath(String path) {
        Settings.path = path;
        preferences.put(PATH_KEY, path);
    }

    public static String getPath() {
        return path;
    }
}
