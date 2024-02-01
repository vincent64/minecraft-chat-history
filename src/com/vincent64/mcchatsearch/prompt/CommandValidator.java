package com.vincent64.mcchatsearch.prompt;

import com.vincent64.mcchatsearch.Settings;
import com.vincent64.mcchatsearch.util.DateUtil;
import com.vincent64.mcchatsearch.util.FileUtil;

public class CommandValidator {
    public static boolean isValidSearchCommand(Command command) {
        if(command.getArgumentAmount() == 3) {
            String startDate = command.getArgument(1);
            String endDate = command.getArgument(2);
            if(DateUtil.isValidFormat(startDate) && DateUtil.isValidFormat(endDate)) {
                if(DateUtil.isValidDate(startDate) && DateUtil.isValidDate(endDate)) {
                    return true;
                } else {
                    println(Messages.invalidDateWrong);
                    return false;
                }
            } else {
                println(Messages.invalidDateFormat);
                return false;
            }
        } else if(command.getArgumentAmount() == 1) {
            return true;
        } else {
            println(Messages.invalidSearchCommand);
            return false;
        }
    }

    public static boolean isValidPathCommand(Command command) {
        if(command.getArgumentAmount() == 2) {
            if(command.getArgument(0).equals("set")) {
                if(FileUtil.isValidPath(command.getArgument(1))) {
                    return true;
                } else {
                    println("This path does not exist on your device.\n");
                    return false;
                }
            } else {
                println(Messages.invalidPathCommand);
                return false;
            }
        } else if(command.getArgumentAmount() == 1) {
            if(command.getArgument(0).equals("view")) {
                return true;
            } else {
                println(Messages.invalidPathCommand);
                return false;
            }
        } else {
            println(Messages.invalidPathCommand);
            return false;
        }
    }

    public static boolean isValidOpenCommand(Command command) {
        if(command.getArgumentAmount() == 1) {
            if(DateUtil.isValidFormat(command.getArgument(0))) {
                if(DateUtil.isValidDate(command.getArgument(0))) {
                    return true;
                } else {
                    println(Messages.invalidDateWrong);
                    return false;
                }
            } else {
                println(Messages.invalidDateFormat);
                return false;
            }
        } else {
            println(Messages.invalidOpenCommand);
            return false;
        }
    }

    public static boolean isValidExtractCommand(Command command) {
        if(command.getArgumentAmount() == 2) {
            if(command.getArgument(0).equals("all") || command.getArgument(0).equals("chat")) {
                if(FileUtil.isValidPath(Settings.getPath() + "\\" + command.getArgument(1) + ".log.gz")) {
                    return true;
                } else {
                    println("This file does not exist in your log folder.\n");
                    return false;
                }
            } else {
                println(Messages.invalidExtractCommand);
                return false;
            }
        } else {
            println(Messages.invalidExtractCommand);
            return false;
        }
    }

    public static boolean isValidSaveCommand(Command command) {
        if(command.getArgumentAmount() == 1) {
            if(FileUtil.isValidFileName(command.getArgument(0))) {
                return true;
            } else {
                println("Invalid file name.\n");
                return false;
            }
        } else {
            println(Messages.invalidSaveCommand);
            return false;
        }
    }

    private static void println(String text) {
        System.out.println(text);
    }
}
