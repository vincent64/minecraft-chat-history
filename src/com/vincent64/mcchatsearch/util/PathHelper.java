package com.vincent64.mcchatsearch.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public static class PathHelper {
    
    private static final OS os = OS.getCurrent();

    public static Path getDefaultMinecraftPath() {
        switch (os) {
            case WINDOWS:
			    return Paths.get(System.getenv("APPDATA"), ".minecraft").toAbsolutePath();
            case OSX:
                return Paths.get(System.getProperty("user.home"), "Library", "Application Support", "minecraft").toAbsolutePath();
            case NIX:
                return Paths.get(System.getProperty("user.home"), ".minecraft").toAbsolutePath();
            default:
                // Use current working directory by default on unknown operating systems
                return Paths.get("minecraft").toAbsolutePath();
        }
    }

    public static Path getMCCHRootDirectory() {
        switch (os) {
            case WINDOWS:
			    return Paths.get(System.getenv("APPDATA"), "MinecraftChatHistory").toAbsolutePath();
            case OSX:
                return Paths.get(System.getProperty("user.home"), "Library", "Application Support", "MinecraftChatHistory").toAbsolutePath();
            case NIX: {
                // For *nix systems, we should follow XDG
                String dataHome = System.getenv("XDG_DATA_HOME");
                if (dataHome != null) {
                    return Paths.get(dataHome, "MinecraftChatHistory").toAbsolutePath();
                }
                return Paths.get(System.getProperty("user.home"), ".local", "share", "MinecraftChatHistory").toAbsolutePath();
            }
            default:
                // Use current working directory by default on unknown operating systems
                return Paths.get("MinecraftChatHistory").toAbsolutePath();
        }
    }
}