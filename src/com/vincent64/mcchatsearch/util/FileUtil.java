package com.vincent64.mcchatsearch.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class FileUtil {
    public static boolean isValidPath(String path) {
        try {
            //Check if the path is valid
            Paths.get(path);
            //Check if the path exists
            return new File(path).exists();
        } catch(InvalidPathException | NullPointerException ex) {
            return false;
        }
    }

    public static boolean isValidFileName(String fileName) {
        try {
            //Check if filename is valid
            File file = new File(fileName);
            file.getCanonicalPath();
            return true;
        } catch(IOException e) {
            return false;
        }
    }
}
