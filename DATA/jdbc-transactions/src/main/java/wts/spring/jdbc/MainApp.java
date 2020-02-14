package wts.spring.jdbc;

import java.util.Iterator;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import wts.spring.jdbc.model.Person;

/**
 * 
 * @author ayrat
 */
//@Service
public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);

        PersonDao personDAO = context.getBean(PersonDao.class);

        System.out.println("List of person is:");

        List<Person> persons = personDAO.getAllPersons();
        
        for (Person p : persons) {
            System.out.println(p);
        }
        
        Iterator<Person> itr = persons.iterator();
        while(itr.hasNext()){
            personDAO.deletePerson(itr.next());
        }
        
        persons.forEach(p -> personDAO.createPerson(p));
        
        persons = personDAO.getAllPersons();
        System.out.println("----------after recreating---------");
         for (Person p : persons) {
            System.out.println(p);
        }

        System.out.println("\nGet person with ID: " + persons.get(1).getId());

        Person personById = personDAO.getPersonById(persons.get(1).getId());
        System.out.println(personById);

        System.out.println("\nCreating person: ");
        long newId = persons.stream().map(Person::getId).max(Long::compare).get();
        Person person = new Person(newId + 1, 36, "Sergey", "Emets");
        System.out.println(person);
        personDAO.createPerson(person);
        System.out.println("\nList of person is:");

        for (Person p : personDAO.getAllPersons()) {
            System.out.println(p);
        }

//        System.out.println("\nDeleting person with ID 2");
//        personDAO.deletePerson(personById);
//
//        System.out.println("\nUpdate person with ID 4");
//
//        Person pperson = personDAO.getPersonById(4L);
//        pperson.setLastName("CHANGED");
//        personDAO.updatePerson(pperson);
//
//        System.out.println("\nList of person is:");
//        for (Person p : personDAO.getAllPersons()) {
//            System.out.println(p);
//        }

        context.close();
    }
}