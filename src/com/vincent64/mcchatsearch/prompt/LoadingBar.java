package com.vincent64.mcchatsearch.prompt;

public class LoadingBar {
    public static String getLoadingBar(float progress) {
        //Calculate the amount of full blocks to display
        int progressBlocks = (int) (progress * 100);

        //Create loading bar string
        String loadingBar = "\rSearching:   ";
        //Choose between full and empty blocks to add according to the progress
        for(int i = 0; i < 50; i++) {
            if(i * 2 <= progressBlocks) {
                loadingBar += Messages.loadingBlockFull;
            } else {
                loadingBar += Messages.loadingBlockEmpty;
            }
        }

        //Calculate progress percentage
        int progressPercentage = (int) (progress * 100);
        //Add progress percentage at the end of the bar
        loadingBar += "  " + progressPercentage + "%";

        return loadingBar;
    }
}
