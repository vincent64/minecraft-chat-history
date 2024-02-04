package com.vincent64.mcchathistory.util;

public static enum OS {
    WINDOWS,
    OSX,
    NIX,
    UNKNOWN;

    public static OS getCurrent() {
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
		if (string.contains("win")) {
			return WINDOWS;
		} else if (string.contains("mac")) {
			return OSX;
		} else if (string.contains("linux") || string.contains("unix") || string.contains("solaris") || string.contains("sunos")) {
			return NIX;
        } else {
            return UNKNOWN;
		}

    }
}