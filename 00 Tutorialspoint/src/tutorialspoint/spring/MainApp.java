/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorialspoint.spring;

/**
 *
 * @author ayrat
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AbstractApplicationContext context
                = new ClassPathXmlApplicationContext("Beans.xml");

        HelloWorld objA = (HelloWorld) context.getBean("helloWorld");

        objA.getMessage();
        objA.setMessage("I'm object A");
        
        HelloWorld objB = (HelloWorld) context.getBean("helloWorld");
        objB.setMessage("I'm object B");
        objB.getMessage();
        objA.getMessage();
        context.registerShutdownHook();
    }
}
