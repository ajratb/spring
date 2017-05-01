/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.spring;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Ayrat
 */
public class FileEventLogger implements EventLogger {

    String filename;
    private File file;

    public FileEventLogger() {

    }

    public FileEventLogger(String filename) {
        System.out.println("FileLogger Constructor is acting");
        this.filename = filename;
    }

    @Override
    public void logEvent(Event event) {
        try {
            System.out.println("yes, im trying");
            FileUtils.writeStringToFile(file, "blablabla",//event.toString(),
                    "UTF-16", false);
        } catch (IOException ex) {
            System.out.println("failed");
            Logger.getLogger(FileEventLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void init() throws IOException {
        file = new File(filename);
        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException("Can't write to file " + filename);
        } else if (!file.exists()) {
            file.createNewFile();
        }
    }
}
