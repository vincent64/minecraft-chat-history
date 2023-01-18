package com.vincent64.mcchathistory.util;

import javafx.scene.paint.Color;

public class ColorFormat {
    public static Color getColor(String format) {
        switch(format) {
            case "§0":
                return Color.rgb(0, 0, 0);
            case "§1":
                return Color.rgb(0, 0, 170);
            case "§2":
                return Color.rgb(0, 170, 0);
            case "§3":
                return Color.rgb(0, 170, 170);
            case "§4":
                return Color.rgb(170, 0, 0);
            case "§5":
                return Color.rgb(170, 0, 170);
            case "§6":
                return Color.rgb(255, 170, 0);
            case "§7":
                return Color.rgb(170, 170, 170);
            case "§8":
                return Color.rgb(85, 85, 85);
            case "§9":
                return Color.rgb(85, 85, 255);
            case "§a":
                return Color.rgb(85, 255, 85);
            case "§b":
                return Color.rgb(85, 255, 255);
            case "§c":
                return Color.rgb(255, 85, 85);
            case "§d":
                return Color.rgb(255, 85, 255);
            case "§e":
                return Color.rgb(255, 255, 85);
            case "§f":
                return Color.rgb(255, 255, 255);
            default:
                return Color.WHITE;
        }
    }
}
