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
public class FileEventLogger implements EventLogger{
    String filename;
    private File file;
    
    public FileEventLogger(){
        
    }

    public FileEventLogger(String filename) {
        this.filename = filename;
    }
    
    

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(),
                    "UTF-16", true);
        } catch (IOException ex) {
            Logger.getLogger(FileEventLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void init() throws IOException{
        this.file = new File(filename);
    }
}
