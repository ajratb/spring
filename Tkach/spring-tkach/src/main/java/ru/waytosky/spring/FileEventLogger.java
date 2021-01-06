/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.spring;

import java.io.File;
import java.io.IOException;
//import java.nio.charset.Charset;
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
	private String type;

//    public FileEventLogger() {
//
//    }

	FileEventLogger(String filename) {// no matter private or public
		this(filename, "usual");//u can set it in xml and get rid of this constructor
	}

	public FileEventLogger(String filename, String type) {
		System.out.format("CONSTRUCTOR FileLogger of type:%s\n", type);
		this.filename = filename;
		this.type = type;
	}

	@Override
	public void logEvent(Event event) {
		try {
			System.out.println("im trying to log into the file");
			FileUtils.writeStringToFile(file, "blablabla", // event.toString(),
					"UTF-16", true);
		} catch (IOException ex) {
			System.out.println("failed");
			Logger.getLogger(FileEventLogger.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void init() throws IOException {
		System.out.println("INIT FileLogger of type: " + type);
		file = new File(filename);
//        System.out.format("ABS FILE NAME: %s\n", file.getAbsolutePath());
		if (file.exists() && !file.canWrite()) {
			throw new IllegalArgumentException("Can't write to file " + filename);
		} else if (!file.exists()) {
			file.createNewFile();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("FileEventLogger for filename: ");
		sb.append(filename).append(", and retrieved file: ").append(file.getAbsolutePath());
		return sb.toString();
	}

}
