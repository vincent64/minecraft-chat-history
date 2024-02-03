using System;

public class DownloadElement {
    public Uri url { get; }
    public string path { get; }

    public DownloadElement(Uri url, string path) {
        this.url = url;
        this.path = path;
    }
}
