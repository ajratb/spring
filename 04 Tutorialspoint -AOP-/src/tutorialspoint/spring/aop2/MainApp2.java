/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorialspoint.spring.aop2;

import tutorialspoint.spring.aop1.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ayrat
 */
public class MainApp2 {
     public static void main(String[] args) {
      ApplicationContext context = 
             new ClassPathXmlApplicationContext("Beans2.xml");

      Student student = (Student) context.getBean("student");

      student.getName();
      student.getAge();
      
      student.printThrowException();
   }
}
