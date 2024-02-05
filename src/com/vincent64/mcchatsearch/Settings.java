package com.vincent64.mcchatsearch;

import com.vincent64.mcchatsearch.util.PathHelper;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.prefs.Preferences;

public class Settings {
    private static final String PATH_KEY = "mccs_path";
    private static Preferences preferences;
    private static String path;
    
    static {
        //Load preferences
        preferences = Preferences.userRoot();

        //Get preferences
        Path logsPath = PathHelper.getDefaultMinecraftPath().resolve("logs");
        path = preferences.get(PATH_KEY, logsPath.toString());
    }

    public static void setPath(String path) {
        Settings.path = path;
        preferences.put(PATH_KEY, path);
    }

    public static String getPath() {
        return path;
    }

    public static String getMCCHPath() {
        return PathHelper.getMCCHRootDirectory().resolve("MinecraftChatHistory").resolve("MCCH.jar").toString();
    }

    public static String getExtractedLogsPath() throws IOException {
        Path path = PathHelper.getMCCHRootDirectory().resolve("MinecraftChatSearch").resolve("ExtractedLogs");
        Files.createDirectories(path);
        return path.toString();
    }

    public static String getSavedResultsPath() throws IOException {
        Path path = PathHelper.getMCCHRootDirectory().resolve("MinecraftChatSearch").resolve("SavedResults");
        Files.createDirectories(path);
        return path.toString();
    }
}
