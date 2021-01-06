/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.spring;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ayrat
 */
public class CacheFileEventLogger extends FileEventLogger{

    List<Event> cache;
    int cacheSize;
    
//    public CacheFileEventLogger(){
//        super();
//    }
//
//    public CacheFileEventLogger(String filename) {
//        super(filename, "cache");
//    }
    
    public CacheFileEventLogger(String filename, int cacheSize) {
        
        super(filename, "cache");
        System.out.format("- cache size: %d\n", cacheSize);
        System.out.format("- filename: %s\n", filename);
        this.cacheSize=cacheSize;
        cache=new ArrayList<>();
    }
    
    
    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if(cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }
    
    public void destroy(){
        if(!cache.isEmpty())
            writeEventsFromCache();
    }

    private void writeEventsFromCache() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
