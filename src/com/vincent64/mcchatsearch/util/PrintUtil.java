package com.vincent64.mcchatsearch.util;

import com.vincent64.mcchatsearch.log.Result;
import com.vincent64.mcchatsearch.prompt.Messages;

import java.util.ArrayList;

public class PrintUtil {
    public static String getFormattedResults(ArrayList<Result> searchResults) {
        String resultAmount = searchResults.size() + "";
        resultAmount += getSpacing(72 - resultAmount.length());
        String formattedResult = String.format(Messages.resultsTop, resultAmount);

        for(int i = 0; i < Math.min(searchResults.size(), 32); i++) {
            String resultString = "│   - " + searchResults.get(i).getFileName() +
                    " (at line " + searchResults.get(i).getLine() +
                    ", character " + searchResults.get(i).getCharacter() + ")";
            formattedResult += resultString;
            formattedResult += getSpacing(90 - resultString.length());
            formattedResult += "│\n";
        }

        if(searchResults.size() > 32) {
            String moreResult = "│  ...and " + (searchResults.size() - 32) + " more results.";
            formattedResult += moreResult;
            formattedResult += getSpacing(90 - moreResult.length());
            formattedResult += "│\n";

            moreResult = "│  (use the save command to view all the results in a TXT file)";
            formattedResult += moreResult;
            formattedResult += getSpacing(90 - moreResult.length());
            formattedResult += "│\n";
        }

        formattedResult += Messages.resultsTip;

        formattedResult += Messages.boxBottom;
        return formattedResult;
    }

    public static String getSpacing(int spaces) {
        String spacing = "";
        for(int i = 0; i < spaces; i++) spacing += " ";
        return spacing;
    }
}
