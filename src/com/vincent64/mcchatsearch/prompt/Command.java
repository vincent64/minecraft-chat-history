package com.vincent64.mcchatsearch.prompt;

import java.util.ArrayList;

public class Command {
    private String command;
    private String[] arguments;

    public Command(String text) {
        //Create text splits arraylist
        ArrayList<String> tokens = new ArrayList<>();

        //Remove leading and trailing spaces in the text
        text = text.trim();

        int tokenStartIndex = 0;
        boolean isQuotationEscaped = false;
        boolean wasPreviousSpace = true;
        //Go through the command text
        for(int i = 0; i < text.length(); i++) {
            //Get current character
            char currentChar = text.charAt(i);

            if(currentChar == ' ' && !isQuotationEscaped) {
                if(!wasPreviousSpace) {
                    //Add the token to the list
                    tokens.add(text.substring(tokenStartIndex, i));
                    //Set previous character as space
                    wasPreviousSpace = true;
                }
                //Increase token start index to next character
                tokenStartIndex = i + 1;
            } else if(currentChar == '"') {
                //Enable/disable quotation mark escaping
                isQuotationEscaped = !isQuotationEscaped;
                //Set previous character as not space
                wasPreviousSpace = false;
            } else if(currentChar != ' ') {
                //Set previous character as not space
                wasPreviousSpace = false;
            }
        }
        //Add the last token to the list
        tokens.add(text.substring(tokenStartIndex));

        //Remove quotation marks to quote-escape token
        for(int i = 0; i < tokens.size(); i++) {
            //Get the token
            String token = tokens.get(i);
            //Check if it starts with quotation marks
            if(token.startsWith("\"") && token.endsWith("\"")) {
                //Remove the quotation marks
                tokens.set(i, token.substring(1, token.length() - 1));
            }
        }

        //Set the command value
        command = tokens.get(0);
        //Initialize the array of arguments
        arguments = new String[tokens.size() - 1];
        //Copy the list of arguments from the token list
        for(int i = 1; i < tokens.size(); i++) {
            arguments[i - 1] = tokens.get(i);
        }
    }

    public String getCommand() {
        return command;
    }

    public String getArgument(int index) {
        return arguments[index];
    }

    public int getArgumentAmount() {
        return arguments.length;
    }
}
