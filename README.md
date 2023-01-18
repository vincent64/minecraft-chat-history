# Minecraft Chat History
View your Minecraft chat history with color and font formatting.

![Lines of code](https://img.shields.io/tokei/lines/github/vincent64/minecraft-chat-history)

![Screenshot](src/resources/screenshot.png)

### What is Minecraft Chat History ?
Minecraft Chat History (MCCH) is an application that lets you view all your Minecraft chat history. The app includes a nice formatting with the default color of Minecraft.

### How does this work ?
The Minecraft chat is continuously saved in logs files while you are playing. These logs files are compressed in *.gz* files, and can be found in the *logs* folder inside the *.minecraft* folder.
MCCH uncompress these files, read them and format them according to the Minecraft style, so the chat logs look nice and can be readable for you.
The chat history is displayed by date, to make it more understandable.

*Note: If you wish to read the raw logs as they are saved in the files, selected **Raw logs** in the dropdown menu within the app.*


## Where can I download it ?
The app works on Windows, MacOS and Linux. To install the app, check the releases on the right of the page.

*Note: You need Java installed to execute the application. Java should already be installed on your computer if you play Minecraft. The application requires Java 19.*


## FAQ

### Some chat logs are colored while some aren't. Why?
Some chat logs on Minecraft servers don't use the traditional way of coloring chat text. Unfortunately, those color formats aren't saved with the logs, and thus can't be read and formatted by MCCH. Only the default color formats starting with *§* (ex: *§1*, *§a*) can be formatted by MCCH.

### The app keeps saying "No logs found for this date". Why?
There are four causes that can produce this :
1. The path to the logs is incorrect. Make sure the path leads to the minecraft logs folder. You can change it in the **Settings**.
2. There are no logs. If your logs folder is empty, the app won't be able to show anything!
3. The date is wrong. Make sure you didn't select the wrong year!
4. There are only internal logs, and no chat logs. Select **All logs** in the dropdown menu to see the internal logs.

### Where is the chat history located ?
Your chat history is saved in log files, which you can find in the *logs* folder inside your *.minecraft* folder. The logs are compressed in *.gz* files, which you have to uncompress in order to view the logs. MCCH uncompress these files for you, and show the logs with a nice formatting!

### The app froze. Why?
If the app froze after you selected a date or a different log type, it can be due to the size of the files. If the log files for this date are huge, the app will spend a lot of time loading them, and sometimes, it fails due to memory usage being too high.

### The app won't launch. Why?
If you get an error saying "*A JNI error has occured.*" upon lauching the app, it's because you have an older version of Java installed on your computer. MCCH requires Java 19. Check [this article](https://www.partitionwizard.com/partitionmanager/a-jni-error-has-occurred.html#:~:text=in%20no%20time.-,Solution%201,-%3A%20Install%20the%20Latest) on how to update Java.


## Disclaimer
MCCH relies on the logs files name to display the chat logs by date. Changing the logs files name could cause issue with the app, and also cause issue with Minecraft itself. MCCH **only reads** the logs files and do not alter them in any way.


## Feedback
Do you want a feature to be added ? Did you find a bug in the app ? Head out to the **Issue** tab above, and suggest your feature/bug there! :)