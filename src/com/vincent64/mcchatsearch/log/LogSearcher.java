package com.vincent64.mcchatsearch.log;

import com.vincent64.mcchatsearch.Settings;
import com.vincent64.mcchatsearch.prompt.LoadingBar;
import com.vincent64.mcchatsearch.util.LogUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.zip.GZIPInputStream;

public class LogSearcher {
    private ArrayList<Result> searchResults;
    private String query;
    private long lineSearched;
    private long startDate;
    private long endDate;

    public LogSearcher(String query, long startDate, long endDate) {
        this.query = query;
        this.startDate = startDate;
        this.endDate = endDate;

        //Initialize search results arraylist
        searchResults = new ArrayList<>();
    }

    public void startSearch() {
        //Get every files in the logs folder
        File[] logFiles = getLogFiles();

        lineSearched = 0;
        int i = 1;
        //Check every files
        for(File logFile : logFiles) {
            String logFileName = logFile.getName();

            //Decompress and add log files with the right date to the list
            if(LogUtil.isValidFormat(logFileName)) {
                //Get the log file's date
                long logDate = LogUtil.getLogDateMilliseconds(logFileName);

                //Check if the log file is within the selected date range
                if((startDate == 0 && endDate == 0) || isWithinDateRange(startDate, endDate, logDate)) {
                    searchLogFile(logFile);
                }
            }

            System.out.print(LoadingBar.getLoadingBar((float) i / (float) logFiles.length));
            i++;
        }
    }

    private void searchLogFile(File file) {
        try {
            //Get file and decompress it in a buffered reader
            GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(file));
            InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream, Charset.forName("windows-1252"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            int j = 0;
            int lineNumber = 1;
            long characterNumber = 1;
            //Search through every lines
            String line;
            while((line = bufferedReader.readLine()) != null) {
                for(int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) == query.charAt(j)) {
                        if(j == query.length() - 1) {
                            //Last character of the query is matching, add result to search results
                            searchResults.add(new Result(file.getName(), lineNumber, characterNumber, 0));
                            //Set character index back to zero
                            j = 0;
                        } else {
                            //Increase character index
                            j++;
                        }
                    } else {
                        //Set character index back to zero
                        j = 0;
                    }

                    characterNumber++;
                }

                lineNumber++;
                lineSearched++;
            }
        } catch(IOException exception) {
            //System.out.println("Error while decoding file: " + file.getName());
        }
    }

    public ArrayList<Result> getSearchResults() {
        return searchResults;
    }

    private static File[] getLogFiles() {
        //Get a list of every file in the log folder
        File[] files = new File(Settings.getPath()).listFiles();
        //Sort the list by date from most recent
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        Arrays.sort(files, Collections.reverseOrder());
        return files;
    }

    public static long getEstimatedTime(long startDate, long endDate) {
        //Get every files in the logs folder
        File[] logFiles = getLogFiles();

        //Calculate total size of the log files in Kb
        long totalSize = 0;
        for(File logFile : logFiles) {
            //Check if the file is a log file
            if(LogUtil.isValidFormat(logFile.getName())) {
                //Get the log file's date
                long logDate = LogUtil.getLogDateMilliseconds(logFile.getName());
                //Check if the log file is within the selected date range
                if((startDate == 0 && endDate == 0) || isWithinDateRange(startDate, endDate, logDate)) {
                    totalSize += logFile.length() / 1000;
                }
            }
        }

        //Return estimated time in second
        return (long) (totalSize * 0.0003d);
    }

    public static int getFileAmount(long startDate, long endDate) {
        //Get every files in the logs folder
        File[] logFiles = getLogFiles();

        //Get the amount of files
        long fileAmount = 0;
        for(File logFile : logFiles) {
            //Check if the file is a log file
            if(LogUtil.isValidFormat(logFile.getName())) {
                //Get the log file's date
                long logDate = LogUtil.getLogDateMilliseconds(logFile.getName());
                //Check if the log file is within the selected date range
                if((startDate == 0 && endDate == 0) || isWithinDateRange(startDate, endDate, logDate)) {
                    fileAmount++;
                }
            }
        }

        return (int) fileAmount;
    }

    private static boolean isWithinDateRange(long startDate, long endDate, long logDate) {
        return logDate >= startDate && logDate <= endDate;
    }
}
