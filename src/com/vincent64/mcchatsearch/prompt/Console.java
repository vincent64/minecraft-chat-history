package com.vincent64.mcchatsearch.prompt;

import com.vincent64.mcchatsearch.Settings;
import com.vincent64.mcchatsearch.log.LogReader;
import com.vincent64.mcchatsearch.log.LogSearcher;
import com.vincent64.mcchatsearch.log.Result;
import com.vincent64.mcchatsearch.util.DateUtil;
import com.vincent64.mcchatsearch.util.PrintUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static com.vincent64.mcchatsearch.prompt.CommandValidator.*;

public class Console {
    private Scanner scanner;
    private ArrayList<Result> previousResults;
    private String previousQuery;
    private boolean hasPreviousResults;
    private boolean shouldClose = false;

    public Console() {
        //Display welcome and title content
        println(Messages.title);
        println(Messages.welcome);
        println(Messages.horizontalLine);
        println(Messages.commandsList);

        //Initialize console scanner
        scanner = new Scanner(System.in);
        hasPreviousResults = false;

        //Wait for user input
        input();
    }

    private void input() {
        //Wait for the user to input something
        String line = scanner.nextLine();
        //Transform the input into a command
        Command command = new Command(line);
        //Execute the command
        execute(command);
    }

    private void execute(Command command) {
        //Detect which command it is and process it
        switch(command.getCommand()) {
            case "search":
                if(isValidSearchCommand(command)) search(command);
                break;
            case "path":
                if(isValidPathCommand(command)) path(command);
                break;
            case "open":
                if(isValidOpenCommand(command)) open(command);
                break;
            case "extract":
                if(isValidExtractCommand(command)) extract(command);
                break;
            case "save":
                if(isValidSaveCommand(command)) {
                    if(hasPreviousResults) save(command);
                    else println("There is no previous search result stored.\n");
                }
                break;
            case "exit":
                println(Messages.exit);
                shouldClose = true;
                break;
            case "help":
                println(Messages.commandsList);
                break;
            default:
                println("Command not recognized. Type help for help.");
                break;
        }

        //Once the command has been executed, wait back for user input if the console shouldn't close
        if(!shouldClose) input();
    }

    private void search(Command command) {
        //Get start and end date
        long startDate = 0, endDate = 0;
        if(command.getArgumentAmount() == 3) {
            String startDateString = command.getArgument(1);
            String endDateString = command.getArgument(2);
            startDate = DateUtil.getDateMilliseconds(startDateString);
            endDate = DateUtil.getDateMilliseconds(endDateString);
        }

        //Give information about the search
        println("Search query:       " + command.getArgument(0));
        if(command.getArgumentAmount() == 3) {
            println("Search through:     logs between the " + command.getArgument(1) + " and the " + command.getArgument(2));
        } else {
            println("Search through:     every logs");
        }

        //Get the amount of files to search through
        println("Files amount:       " + LogSearcher.getFileAmount(startDate, endDate) + " files");
        //Calculate the estimated time
        long estimatedTime = LogSearcher.getEstimatedTime(startDate, endDate);
        println("Estimated time:     " + DateUtil.secondsToHMS(estimatedTime));

        //Ask confirmation to run the searching
        print("Start searching? [Y/N]  ");
        boolean confirmation = askConfirmation();

        //Check if confirmation is given
        if(confirmation) {
            //Start searching process if the confirmation is positive
            println(Messages.horizontalLine);

            //Start searching
            LogSearcher logSearcher = new LogSearcher(command.getArgument(0), startDate, endDate);
            logSearcher.startSearch();
            println("\n");

            //Get results once the search has ended
            ArrayList<Result> searchResults = logSearcher.getSearchResults();
            String printResult = PrintUtil.getFormattedResults(searchResults);
            println(printResult);

            //Set as previous results
            previousResults = searchResults;
            previousQuery = command.getArgument(0);
            hasPreviousResults = true;
        } else {
            //Interrupt the searching process if the confirmation is negative
            println("Searching interrupted.");
        }
    }

    private void path(Command command) {
        switch(command.getArgument(0)) {
            case "view":
                //Display the currently used path
                String pathPrint = Settings.getPath();
                pathPrint += PrintUtil.getSpacing(87 - pathPrint.length());
                println(String.format(Messages.pathView, pathPrint));
                break;
            case "set":
                //Set the new path
                Settings.setPath(command.getArgument(1));
                println("The new path has been successfully set!\n");
                break;
        }
    }

    private void open(Command command) {
        println("Opening in MCCH...");

        try {
            //Launch MCCH with argument date
            String date = command.getArgument(0);
            Runtime.getRuntime().exec("java -jar " + Settings.getMCCHPath() + " " + date);
            shouldClose = true;
        } catch (IOException e) {
            println("Failed to open the logs in MCCH.\n");
        }
    }

    private void extract(Command command) {
        println("Extracting file...");

        //Get log type and filename
        String type = command.getArgument(0);
        String fileName = command.getArgument(1);

        //Get log file
        File file = new File(Settings.getPath() + "\\" + fileName + ".log.gz");

        //Read log file content
        String fileContent = "";
        switch(type) {
            case "all":
                fileContent = LogReader.logToString(LogReader.getRawLogs(file));
                break;
            case "chat":
                fileContent = LogReader.logToString(LogReader.getChatLogs(file));
                break;
        }

        println("Saving log file...");

        //Write log file content in a TXT file
        try {
            //Initialize the buffered writer
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Settings.getExtractedLogsPath() + fileName + ".txt"));
            //Write the file content in the buffer and close it
            bufferedWriter.write(fileContent);
            bufferedWriter.close();
            println("File successfully saved!\n");
        } catch(IOException e) {
            println("Failed to save file.\n");
            throw new RuntimeException(e);
        }
    }

    private void save(Command command) {
        println("Saving search results...");
        try {
            //Initialize the buffered writer
            String fileName = command.getArgument(0);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Settings.getSavedResultsPath() + fileName + ".txt"));

            //Create the content of the file
            String fileContent = "MCCS SEARCH RESULTS   (" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + ")\n";
            fileContent += "Query: " + previousQuery + "\n";
            fileContent += "Results found: " + previousResults.size() + "\n\n";
            fileContent += "The query was found in the following log files:\n";
            for(Result result : previousResults) {
                fileContent += "  - " + result.getFileName() + " (at line " + result.getLine() + ")\n";
            }

            //Write the file content in the buffer and close it
            bufferedWriter.write(fileContent);
            bufferedWriter.close();
            println("File successfully saved!\n");
        } catch(IOException e) {
            println("Failed to save file.\n");
            throw new RuntimeException(e);
        }
    }

    private boolean askConfirmation() {
        String confirmation = scanner.nextLine().toUpperCase();
        return confirmation.equals("Y");
    }

    private void println(String text) {
        System.out.println(text);
    }

    private void print(String text) {
        System.out.print(text);
    }
}
