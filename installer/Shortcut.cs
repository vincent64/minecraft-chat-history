using System;
using IWshRuntimeLibrary;
using System.IO;

public static class Shortcut {
    public static void createShortcut(string name, string description,
                    string path, string shortcutPath, string iconPath) {
        //Create shortcut
        WshShell shell = new WshShell();
        IWshShortcut shortcut = shell.CreateShortcut(Path.Combine(shortcutPath, name + ".lnk")) as IWshShortcut;

        //Add shortcut informations
        shortcut.TargetPath = path;
        shortcut.Description = description;
        shortcut.IconLocation = iconPath;

        //Save the shortcut
        shortcut.Save();
    }
}