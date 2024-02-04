package com.vincent64.mcchathistory;

import com.vincent64.mcchathistory.util.OS;
import com.vincent64.mcchathistory.util.PathHelper;
import java.nio.file.Path;
import java.util.prefs.Preferences;

public class Settings {
    private static final String PATH_KEY = "mcch_path";
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
}
