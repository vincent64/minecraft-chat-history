package com.vincent64.mcchathistory.log;

import com.vincent64.mcchathistory.list.LogItem;
import com.vincent64.mcchathistory.Settings;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.GZIPInputStream;

public class LogReader {
    private static ArrayList<String> getLogs(LocalDate date) {
        //Create array of logs
        ArrayList<String> logs = new ArrayList<>();

        //Get every files in the logs folder
        File[] logFiles = getLogFiles();
        //Check every files
        for(File logFile : logFiles) {
            String logFileName = logFile.getName();
            //Format date with the same format as the log file name
            String dateFormatted = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            //Decompress and add log files with the right date to the list
            if(logFileName.endsWith(".log.gz") && logFileName.startsWith(dateFormatted)) {
                logs.addAll(getDecompressedLog(logFile));
            }
        }

        return logs;
    }

    private static ArrayList<LogItem> getChatLogs(LocalDate date) {
        //Create array of log items
        ArrayList<LogItem> logItems = new ArrayList<>();

        for(String line : getLogs(date)) {
            if(line.startsWith("[")) {
                //Get log data at the beginning of the line
                String[] logData = line.split(" ");
                //Check if the log is a chat log
                if((logData.length > 2 && logData[2].equals("[CHAT]")) || (logData.length > 3 && logData[3].equals("[CHAT]"))) {
                    //Add log to the list as it is a chat log
                    logItems.add(new LogItem(line.substring(line.indexOf("[CHAT]") + 7), line.substring(1, 9)));
                }
            }
        }
        return logItems;
    }

    private static ArrayList<LogItem> getAllLogs(LocalDate date) {
        //Create array of log items
        ArrayList<LogItem> logItems = new ArrayList<>();

        for(String line : getLogs(date)) {
            if(line.startsWith("[")) {
                //Add log to the list
                logItems.add(new LogItem(line, line.substring(1, 9)));
            }
        }
        return logItems;
    }

    private static File[] getLogFiles() {
        File[] files = new File(Settings.getPath()).listFiles();
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        return files;
    }

    private static ArrayList<String> getDecompressedLog(File file) {
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

    public static ArrayList<LogItem> getLogs(LogType type, LocalDate date) {
        switch(type) {
            case CHAT:
                return getChatLogs(date);
            default:
                return getAllLogs(date);
        }
    }

    public static ArrayList<Integer> search(ArrayList<LogItem> logs, String query) {
        ArrayList<Integer> results = new ArrayList<>();
        for(int i = 0; i < logs.size(); i++) {
            if(logs.get(i).getText().contains(query)) {
                results.add(i);
            }
        }

        return results;
    }
}
