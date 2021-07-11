package spring.examples.base;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import spring.examples.base.AppConfig;
import spring.examples.base.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(AppConfig.class)
@DisplayName("@SpringJUnit5Config Tests")
class Spring5JUnit5ComposedAnnotationIntegrationTest {

    @Autowired
    private Student student;

    @Autowired
    private List<Student> students;

    @Test
    @DisplayName("ApplicationContext injected into method")
    void givenAMethodName_whenInjecting_thenApplicationContextInjectedIntoMethod(ApplicationContext applicationContext) {
        assertNotNull(applicationContext, "ApplicationContext should have been injected into method by Spring");
        assertEquals(this.student, applicationContext.getBean("firstStudent", Student.class));
    }

    @Test
    @DisplayName("Spring @Beans injected into fields")
    void givenAnObject_whenInjecting_thenSpringBeansInjected() {
        assertNotNull(student, "Task should have been @Autowired by Spring");
        assertEquals("Petya", student.getName(), "Student's name");
        //there are 3 students in context: 2 from AppConfig and 1 from JUpiterTestEx..Config
        assertEquals(3, students.size(), "Number of Students in context");
    }
}