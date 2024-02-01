package com.vincent64.mcchatsearch.log;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

public class LogReader {
    public static ArrayList<String> getChatLogs(File file) {
        try {
            //Get file and decompress it in a buffered reader
            GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(file));
            InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream, Charset.forName("windows-1252"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //Write log buffer to string array
            ArrayList<String> logString = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.startsWith("[")) {
                    //Get log data at the beginning of the line
                    String[] logData = line.split(" ");
                    //Check if the log is a chat log
                    if((logData.length > 2 && logData[2].equals("[CHAT]")) || (logData.length > 3 && logData[3].equals("[CHAT]"))) {
                        //Add log to the list as it is a chat log
                        logString.add(line);
                    }
                }
            }
            return logString;
        } catch(IOException exception) {
            //Return empty log list
            return new ArrayList<>();
        }
    }

    public static ArrayList<String> getRawLogs(File file) {
        try {
            //Get file and decompress it in a buffered reader
            GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(file));
            InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream, Charset.forName("windows-1252"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //Write log buffer to string array
            ArrayList<String> logString = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logString.add(line);
            }
            return logString;
        } catch(IOException exception) {
            //Return empty log list
            return new ArrayList<>();
        }
    }

    public static String logToString(ArrayList<String> logs) {
        String logString = "";
        for(String log : logs) {
            logString += log + "\n";
        }
        return logString;
    }
}
