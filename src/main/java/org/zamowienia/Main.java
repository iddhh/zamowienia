package org.zamowienia;

import java.util.Map;

public class Main {
    static void main(String[] args) {
        LoadSettings settings = new LoadSettings();
        String directory = settings.Directory();

        LoadFiles loadFiles = new LoadFiles(directory);
        Map<String,String> jsonlist = loadFiles.loadingfiles();

        Deserializer jsondeser = new Deserializer();
        jsondeser.deserializing(jsonlist);
    }
}
