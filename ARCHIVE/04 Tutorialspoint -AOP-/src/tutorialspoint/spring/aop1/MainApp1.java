package tutorialspoint.spring.aop1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ayrat
 */
public class MainApp1 {
     public static void main(String[] args) {
      ApplicationContext context = 
             new ClassPathXmlApplicationContext("Beans1.xml");

      Student student = (Student) context.getBean("student");

      student.getName();
      student.getAge();
      
      student.printThrowException();
   }
}
