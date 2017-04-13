/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Ayrat
 */
public class App {

    Client client;
    ConsoleEventLogger eventLogger;

    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {

        try (ConfigurableApplicationContext ctx = 
                new ClassPathXmlApplicationContext("spring.xml")) {
            App app = (App) ctx.getBean("app");
//        app.client=new Client("1","John Smith");
//        app.eventLogger=new ConsoleEventLogger();
            app.logEvent((Event) ctx.getBean("event"));
//                "Some event for user 1");
        }
//        app.client=new Client("1","John Smith");
//        app.eventLogger=new ConsoleEventLogger();
    }

    private void logEvent(Event event) {
//        String message=msg.replaceAll(client.getId(), client.getFullName()); 
        eventLogger.logEvent(event);

    }
}
