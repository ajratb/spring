/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.spring;

/**
 *
 * @author Ayrat
 */
public class ConsoleEventLogger implements EventLogger{
	
	

    public ConsoleEventLogger() {
		System.out.println("CONSTRUCTOR ConsoleEventLogger");
	}

	/**
     *
     * @param message
     */
    @Override
    public void logEvent(Event event){
        System.out.println(event.toString());
    }
    
    public void destroy() {
		System.out.println("DESTROY ConsoleEventLogger");
	}
}
