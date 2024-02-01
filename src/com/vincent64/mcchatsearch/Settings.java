package com.vincent64.mcchatsearch;

import java.io.File;
import java.util.prefs.Preferences;

public class Settings {
    private static final String APPDATA_PATH_WINDOWS = "\\AppData\\Roaming\\";
    private static final String APPDATA_PATH_MACOS = "/Library/Application Support/";
    private static final String PATH_MCCH_WINDOWS =
            APPDATA_PATH_WINDOWS + "MinecraftChatHistory\\MinecraftChatHistory\\MCCH.jar";
    private static final String PATH_MCCH_MACOS =
            APPDATA_PATH_MACOS + "MinecraftChatHistory/MinecraftChatHistory/MCCH.jar";
    private static final String PATH_EXTRACTED_LOGS_WINDOWS =
            APPDATA_PATH_WINDOWS + "MinecraftChatHistory\\MinecraftChatSearch\\ExtractedLogs\\";
    private static final String PATH_EXTRACTED_LOGS_MACOS =
            APPDATA_PATH_MACOS + "MinecraftChatHistory/MinecraftChatSearch/ExtractedLogs/";
    private static final String PATH_SAVED_RESULTS_WINDOWS =
            APPDATA_PATH_WINDOWS + "MinecraftChatHistory\\MinecraftChatSearch\\SavedResults\\";
    private static final String PATH_SAVED_RESULTS_MACOS =
            APPDATA_PATH_MACOS + "MinecraftChatHistory/MinecraftChatSearch/SavedResults/";
    private static final String PATH_KEY = "mccs_path";
    private static Preferences preferences;
    private static String path;
    private static String os;

    static {
        //Load preferences
        preferences = Preferences.userRoot();

        //Detect operating system
        os = System.getProperty("os.name").toLowerCase();
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

    public static String getMCCHPath() {
        //Get MCCH path for Windows
        String path = System.getProperty("user.home") + PATH_MCCH_WINDOWS;
        //Get MCCH path for macOS
        if(os.contains("mac")) {
            path = System.getProperty("user.home") + PATH_MCCH_MACOS;
        }

        return path;
    }

    public static String getExtractedLogsPath() {
        //Get extracted log path for Windows
        String path = System.getProperty("user.home") + PATH_EXTRACTED_LOGS_WINDOWS;
        //Get extracted log path for macOS
        if(os.contains("mac")) {
            path = System.getProperty("user.home") + PATH_EXTRACTED_LOGS_MACOS;
        }

        File file = new File(path);
        //Create folder if they don't exist
        if(!file.exists()) file.mkdirs();
        return path;
    }

    public static String getSavedResultsPath() {
        //Get saved results path for Windows
        String path = System.getProperty("user.home") + PATH_SAVED_RESULTS_WINDOWS;
        //Get saved results path for macOS
        if(os.contains("mac")) {
            path = System.getProperty("user.home") + PATH_SAVED_RESULTS_MACOS;
        }

        File file = new File(path);
        //Create folder if they don't exist
        if(!file.exists()) file.mkdirs();
        return path;
    }
}
