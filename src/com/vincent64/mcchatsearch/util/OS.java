package com.vincent64.mcchatsearch.util;

import java.util.Locale;

public enum OS {
    WINDOWS,
    OSX,
    NIX,
    UNKNOWN;

    public static OS getCurrent() {
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
		if(os.contains("win")) {
			return WINDOWS;
		} else if(os.contains("mac")) {
			return OSX;
		} else if(os.contains("linux") || os.contains("unix") || os.contains("solaris") || os.contains("sunos")) {
			return NIX;
        } else {
            return UNKNOWN;
		}
    }
}