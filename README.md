# Minecraft Chat History
View your Minecraft chat history with color and font formatting.

![GitHub release (latest by date)](https://img.shields.io/github/downloads/vincent64/minecraft-chat-history/v1.0/total)
![GitHub](https://img.shields.io/github/license/vincent64/minecraft-chat-history)
![Lines of code](https://img.shields.io/tokei/lines/github/vincent64/minecraft-chat-history)
![GitHub issues](https://img.shields.io/github/issues-raw/vincent64/minecraft-chat-history)

![Screenshot](src/resources/screenshot.png)

### What is Minecraft Chat History ?
Minecraft Chat History (MCCH) is an application that lets you view all your Minecraft chat history. The app includes a nice formatting with the default color and font of Minecraft.

### How does this work ?
The Minecraft chat is continuously saved in log files while you are playing. These log files are compressed in *.gz* files, and can be found in the *logs* folder inside your *.minecraft* folder.
MCCH decompresses these files, read them and format them according to the Minecraft style, so the chat logs look nice and can be readable for you.
The logs are displayed by date, to make it more readable.

*Note: If you wish to read the raw logs as they are saved in the files, selected **Raw logs** in the dropdown menu within the app.*


## Download and usage
The app works on Windows and MacOS. To install the app, read the steps below :
1. Download Java 19 from [this link](https://www.oracle.com/java/technologies/downloads/#java19).
2. Execute the downloaded file and follow the installation process.
3. Once Java is downloaded, come back on this page.
4. Go to **Release** on the right side of the page.
5. Select the most recent version.
6. Download the *.jar* file in **Assets**.
7. Execute the *.jar* file to launch the app. Done!


## FAQ

### Some chat logs are colored while some aren't. Why?
Some chat logs on Minecraft servers don't use the traditional way of coloring chat text. Unfortunately, those color formats aren't saved with the logs, and thus can't be read and formatted by MCCH. Only the default color formats starting with *§* (ex: *§1*, *§a*) can be formatted by MCCH.

### The app keeps saying "No logs found for this date". Why?
There are four causes that can produce this :
1. The path to the log files is incorrect. Make sure the path leads to the minecraft logs folder. You can change it in the **Settings**.
2. There are no logs. If your logs folder is empty, the app won't be able to show anything!
3. The date is wrong. Make sure you didn't select the wrong year!
4. There are only internal logs, and no chat logs. Select **All logs** in the dropdown menu to see the internal logs.

### Where is the chat history located ?
Your chat history is saved in log files, which you can find in the *logs* folder inside your *.minecraft* folder. The logs are compressed in *.gz* files, which you have to decompress in order to view the logs. MCCH decompresses these files for you, and show the logs with a nice formatting!

### The app froze. Why?
If the app froze after you selected a date or a different log type, it can be due to the size of the files. If the log files for this date are huge, the app will spend a lot of time loading them, and sometimes, it fails due to memory usage being too high. So be patient, but if it takes more than a minute, close the app and restart it!

### The app won't launch. Why?
If you get an error saying "*A JNI error has occured.*" upon lauching the app, it's because you have an older version of Java installed on your computer. MCCH requires Java 19. Check the *Download and usage* section above.


## Disclaimer
MCCH relies on the log files' name to display the chat logs by date. Changing the log files' name could not only cause issue with the app, but also with Minecraft itself. MCCH **only reads** the log files and do not alter them in any way.


## Feedback
Do you want a feature to be added ? Did you find a bug in the app ? Head out to the **Issue** tab above, and suggest your feature/bug there! :)