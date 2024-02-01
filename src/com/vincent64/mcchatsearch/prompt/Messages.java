package com.vincent64.mcchatsearch.prompt;

public class Messages {
    public static String title =
            "┌─────────────────────────────────────────────────────────────────────────────────────────┐\n" +
            "│  ███╗   ███╗██╗███╗   ██╗███████╗ ██████╗██████╗  █████╗ ███████╗████████╗              │\n" +
            "│  ████╗ ████║██║████╗  ██║██╔════╝██╔════╝██╔══██╗██╔══██╗██╔════╝╚══██╔══╝              │\n" +
            "│  ██╔████╔██║██║██╔██╗ ██║█████╗  ██║     ██████╔╝███████║█████╗     ██║                 │\n" +
            "│  ██║╚██╔╝██║██║██║╚██╗██║██╔══╝  ██║     ██╔══██╗██╔══██║██╔══╝     ██║                 │\n" +
            "│  ██║ ╚═╝ ██║██║██║ ╚████║███████╗╚██████╗██║  ██║██║  ██║██║        ██║                 │\n" +
            "│  ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚══════╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝        ╚═╝                 │\n" +
            "│                                                                                         │\n" +
            "│   ██████╗██╗  ██╗ █████╗ ████████╗    ███████╗███████╗ █████╗ ██████╗  ██████╗██╗  ██╗  │\n" +
            "│  ██╔════╝██║  ██║██╔══██╗╚══██╔══╝    ██╔════╝██╔════╝██╔══██╗██╔══██╗██╔════╝██║  ██║  │\n" +
            "│  ██║     ███████║███████║   ██║       ███████╗█████╗  ███████║██████╔╝██║     ███████║  │\n" +
            "│  ██║     ██╔══██║██╔══██║   ██║       ╚════██║██╔══╝  ██╔══██║██╔══██╗██║     ██╔══██║  │\n" +
            "│  ╚██████╗██║  ██║██║  ██║   ██║       ███████║███████╗██║  ██║██║  ██║╚██████╗██║  ██║  │\n" +
            "│   ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝       ╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝  │\n" +
            "└─────────────────────────────────────────────────────────────────────────────────────────┘\n";

    public static String horizontalLine = "───────────────────────────────────────────────────────────────────────────────────────────";

    public static String welcome =
            "Welcome on Minecraft Chat Search (MCCS) !\n" +
            "This tool is used to search a specific keyword/query/content within ALL of your Minecraft logs.\n" +
            "If you are not used to make use of the command line, don't worry. You will be guided through all the steps.";

    public static String commandsList =
            "┌───────────────────────────────────────────────────────┐ ┌───────────────────────────────┐\n" +
            "│ COMMANDS LIST                                 (help)  │ │ INFORMATIONS                  │\n" +
            "│                                                       │ │                               │\n" +
            "│ search [query] [startDate] [endDate]                  │ │ If this is your first time    │\n" +
            "│    └ Search the the query within your log files.      │ │ using this program, first     │\n" +
            "│      You can specify a start date or end date with    │ │ make sure that the path to    │\n" +
            "│      the following format: DD/MM/YYYY.                │ │ your logs is correct, using   │\n" +
            "│      If no date is specified, the query will be       │ │ the path command explained    │\n" +
            "│      searched within ALL of your log files.           │ │ on the left side.             │\n" +
            "│      NOTE: If your query is composed of multiple      │ │ Once it is correct, you can   │\n" +
            "│      words or contains spaces, make sure to add       │ │ use the main feature of this  │\n" +
            "│      a \" at the beginning and end of the query!       │ │ program: the search command.  │\n" +
            "│                                                       │ │                               │\n" +
            "│ path [view/set] [path]                                │ │ If you notice any bugs or     │\n" +
            "│    └ View/edit the path to the log files.             │ │ problems with this program,   │\n" +
            "│      If the view argument is specified, the path      │ │ don't hesitate to raise an    │\n" +
            "│      currently used for the program will be shown.    │ │ issue in the ISSUE tab on     │\n" +
            "│      If the set argument is specified, the path will  │ │ the GitHub repository.        │\n" +
            "│      be modified.                                     │ │ Thanks for your help!         │\n" +
            "│      NOTE: If your path contains spaces, make sure to │ │                               │\n" +
            "│      add a \" at the beginning and end of the path!    │ │                               │\n" +
            "│                                                       │ │                               │\n" +
            "│ open [date]                                           │ │                               │\n" +
            "│    └ Open the chat history for this date in           │ │                               │\n" +
            "│      the MCCH app.                                    │ │                               │\n" +
            "│      The format of the date must: DD/MM/YYYY.         │ │                               │\n" +
            "│      NOTE: The MCCH app must be installed and         │ │                               │\n" +
            "│      in the same folder as this program for this      │ │                               │\n" +
            "│      command to work!                                 │ │                               │\n" +
            "│                                                       │ │                               │\n" +
            "│ extract [all/chat] [fileName]                         │ │                               │\n" +
            "│    └ Uncompress and extract the log file into a TXT.  │ │                               │\n" +
            "│      If the all argument is specified, the entirety   │ │                               │\n" +
            "│      of the log content will be extracted.            │ │                               │\n" +
            "│      If the chat argument is specified, only the      │ │                               │\n" +
            "│      chat content will be extracted.                  │ │                               │\n" +
            "│      NOTE: The file name must not contains the        │ │                               │\n" +
            "│      .log.gz format, only the name.                   │ │                               │\n" +
            "│                                                       │ │                               │\n" +
            "│ save [fileName]                                       │ └───────────────────────────────┘\n" +
            "│    └ Save the search results in a TXT file.           │ ┌───────────────────────────────┐\n" +
            "│      The search features must have been used          │ │ MINECRAFT CHAT SEARCH (MCCS)  │\n" +
            "│      previously to make use of this command.          │ │                               │\n" +
            "│                                                       │ │ Version: 1.0                  │\n" +
            "│ exit                                                  │ │ Author: vincent64             │\n" +
            "│    └ Exit the program and close the terminal.         │ │ Github: github.com/vincent64/ │\n" +
            "│                                                       │ │        minecraft-chat-history │\n" +
            "└───────────────────────────────────────────────────────┘ └───────────────────────────────┘\n";

    public static String exit = "Exiting the program.";

    public static String pathView =
            "┌─────────────────────────────────────────────────────────────────────────────────────────┐\n" +
            "│ PATH                                                                                    │\n" +
            "│ The currently saved logs folder path is:                                                │\n" +
            "│                                                                                         │\n" +
            "│ %s │\n" +
            "│                                                                                         │\n" +
            "└─────────────────────────────────────────────────────────────────────────────────────────┘\n";

    public static String loadingBlockFull = "█";
    public static String loadingBlockEmpty = "▒";

    public static String boxTop = "┌─────────────────────────────────────────────────────────────────────────────────────────┐\n";
    public static String boxBottom = "└─────────────────────────────────────────────────────────────────────────────────────────┘\n";

    public static String resultsTop =
            "┌─────────────────────────────────────────────────────────────────────────────────────────┐\n" +
            "│ SEARCH RESULTS                                                                          │\n" +
            "│                                                                                         │\n" +
            "│ Results found: %s │\n" +
            "│ The query was found in the following log files:                                         │\n" +
            "│                                                                                         │\n";

    public static String resultsNone =
            "┌─────────────────────────────────────────────────────────────────────────────────────────┐\n" +
            "│ SEARCH RESULTS                                                                          │\n" +
            "│                                                                                         │\n" +
            "│ Results found: 0                                                                        │\n" +
            "│ The query was found in no logs files. :(                                                │\n" +
            "└─────────────────────────────────────────────────────────────────────────────────────────┘\n";

    public static String resultsTip =
            "│                                                                                         │\n" +
            "│ WANT TO VIEW THE CONTENT OF THE LOG?                                                    │\n" +
            "│ Use the open command to open the content of the log in MCCH.                            │\n" +
            "│                                                                                         │\n";

    public static String invalidSearchCommand = "Invalid search command.\n";
    public static String invalidPathCommand = "Invalid path command.\n";
    public static String invalidOpenCommand = "Invalid open command.\n";
    public static String invalidExtractCommand = "Invalid extract command.\n";
    public static String invalidSaveCommand = "Invalid save command.\n";
    public static String invalidDateFormat = "Invalid date format.\n";
    public static String invalidDateWrong = "This date does not exist.\n";
}