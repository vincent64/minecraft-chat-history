using System;
using System.Windows;
using System.Net;
using System.IO;
using System.ComponentModel;
using System.Collections.Generic;

namespace MCCHInstaller {
    public partial class MainWindow : Window {
        private List<DownloadElement> queue;

        public MainWindow() {
            InitializeComponent();
        }
        private void install(object sender, RoutedEventArgs e) {
            tab1.Visibility = Visibility.Hidden;
            tab2.Visibility = Visibility.Visible;

            //Start the installation
            startInstallation();
        }

        private void startInstallation() {
            //Create directories
            string appDataPath = Environment.GetFolderPath(Environment.SpecialFolder.ApplicationData);
            string appPath = "MinecraftChatHistory";
            Directory.CreateDirectory(Path.Combine(appDataPath, appPath));
            Directory.CreateDirectory(Path.Combine(appDataPath, appPath, "MinecraftChatHistory"));
            Directory.CreateDirectory(Path.Combine(appDataPath, appPath, "MinecraftChatSearch"));
            Directory.CreateDirectory(Path.Combine(appDataPath, appPath, "resources"));

            //Get URL to GitHub latest release of MCCH and MCCS
            string mcchUrl = "https://github.com/vincent64/minecraft-chat-history/releases/latest/download/MinecraftChatHistory.jar";
            string mccsUrl = "https://github.com/vincent64/minecraft-chat-history/releases/latest/download/MinecraftChatSearch.jar";
            string mcchIcon = "https://raw.githubusercontent.com/vincent64/minecraft-chat-history/main/icons/icon_mcch_windows.ico";
            string mccsIcon = "https://raw.githubusercontent.com/vincent64/minecraft-chat-history/main/icons/icon_mccs_windows.ico";
            string readme = "https://raw.githubusercontent.com/vincent64/minecraft-chat-history/main/README.md";
            string license = "https://raw.githubusercontent.com/vincent64/minecraft-chat-history/main/LICENSE.md";

            //Create download elements
            queue = new List<DownloadElement>();
            queue.Add(new DownloadElement(new Uri(mcchUrl), Path.Combine(appDataPath, appPath, "MinecraftChatHistory", "MCCH.jar")));
            queue.Add(new DownloadElement(new Uri(mccsUrl), Path.Combine(appDataPath, appPath, "MinecraftChatSearch", "MCCS.jar")));
            queue.Add(new DownloadElement(new Uri(mcchIcon), Path.Combine(appDataPath, appPath, "resources", "icon_mcch_windows.ico")));
            queue.Add(new DownloadElement(new Uri(mccsIcon), Path.Combine(appDataPath, appPath, "resources", "icon_mccs_windows.ico")));
            queue.Add(new DownloadElement(new Uri(readme), Path.Combine(appDataPath, appPath, "README.md")));
            queue.Add(new DownloadElement(new Uri(license), Path.Combine(appDataPath, appPath, "LICENSE.md")));

            //Download JAR and resources
            download();
        }

        private void download() {
            if(queue.Count != 0) {
                //Download content
                using (var client = new WebClient()) {
                    client.DownloadProgressChanged += updateProgressbar;
                    client.DownloadFileCompleted += new AsyncCompletedEventHandler(downloadComplete);
                    //Download content
                    DownloadElement element = queue[0];
                    client.DownloadFileAsync(element.url, element.path);
                }
            } else {
                finishInstallation();
            }
        }

        private void downloadComplete(object sender, AsyncCompletedEventArgs e) {
            //Remove first element which has downloaded
            queue.RemoveAt(0);
            //Download other files
            download();
        }

        private void updateProgressbar(object sender, DownloadProgressChangedEventArgs e) {
            progressbar.Value = e.ProgressPercentage;
        }

        private void finishInstallation() {
            //Create shortcuts
            createShortcuts();

            tab2.Visibility = Visibility.Hidden;
            tab3.Visibility = Visibility.Visible;
        }

        private void createShortcuts() {
            string startMenuPath = Environment.GetFolderPath(Environment.SpecialFolder.CommonPrograms);
            string appDataPath = Environment.GetFolderPath(Environment.SpecialFolder.ApplicationData);
            string desktopPath = Environment.GetFolderPath(Environment.SpecialFolder.Desktop);

            //Create shortcut folder
            Directory.CreateDirectory(Path.Combine(startMenuPath, "MinecraftChatHistory"));

            //Get newly created directories' path
            string mcchPath = Path.Combine(appDataPath, "MinecraftChatHistory", "MinecraftChatHistory", "MCCH.jar");
            string mccsPath = Path.Combine(appDataPath, "MinecraftChatHistory", "MinecraftChatSearch", "MCCSLauncher.bat");
            string mcchIconPath = Path.Combine(appDataPath, "MinecraftChatHistory", "resources", "icon_mcch_windows.ico");
            string mccsIconPath = Path.Combine(appDataPath, "MinecraftChatHistory", "resources", "icon_mccs_windows.ico");

            //Create bat file for MCCS
            string mccsLauncher = "java -jar " + Path.Combine(appDataPath, "MinecraftChatHistory", "MinecraftChatSearch", "MCCS.jar").ToString();
            File.WriteAllText(Path.Combine(appDataPath, "MinecraftChatHistory", "MinecraftChatSearch", "MCCSLauncher.bat"), mccsLauncher);

            //Create start menu shortcuts
            Shortcut.createShortcut("Minecraft Chat History", "Shortcut for MCCH.", mcchPath, Path.Combine(startMenuPath, "MinecraftChatHistory"), mcchIconPath);
            Shortcut.createShortcut("Minecraft Chat Search", "Shortcut for MCCS.", mccsPath, Path.Combine(startMenuPath, "MinecraftChatHistory"), mccsIconPath);
            //Create desktop shortcuts
            Shortcut.createShortcut("Minecraft Chat History", "Shortcut for MCCH.", mcchPath, desktopPath, mcchIconPath);
            Shortcut.createShortcut("Minecraft Chat Search", "Shortcut for MCCS.", mccsPath, desktopPath, mccsIconPath);
        }

        private void close(object sender, RoutedEventArgs e) {
            Environment.Exit(0);
        }
    }
}
