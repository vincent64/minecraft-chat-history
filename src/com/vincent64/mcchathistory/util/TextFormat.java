package com.vincent64.mcchathistory.util;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class TextFormat {
    public static ArrayList<Text> getText(String formattedText) {
        //Create array of texts
        ArrayList<Text> texts = new ArrayList<>();

        //Replace "\n" with actual line breaks in the text
        formattedText = formattedText.replaceAll("\\\\n", System.lineSeparator());

        //Create starting formatted text
        int currentStartPosition = 0;
        Text currentText = new Text();
        currentText.setFont(Font.font("Minecraft Regular", 16));
        currentText.setFill(Color.WHITE);

        for(int i = 0; i < formattedText.length(); i++) {
            //Check if color code is valid
            if(formattedText.charAt(i) == '§' && formattedText.length() > i + 1) {
                //Set current text with current content
                currentText.setText(formattedText.substring(currentStartPosition, i));
                //Get color format
                Color color = ColorFormat.getColor(formattedText.substring(i, i+2));
                //Increment by two to jump over the color code
                i += 2;
                currentStartPosition = i;
                //Add current text to the array
                texts.add(currentText);
                //Create new text
                currentText = new Text();
                currentText.setFont(Font.font("Minecraft Regular", 16));
                currentText.setFill(color);
            } else if(i + 1 == formattedText.length()) {
                //Set final text with current content
                currentText.setText(formattedText.substring(currentStartPosition, i + 1));
                texts.add(currentText);
            }
        }

        return texts;
    }
}
