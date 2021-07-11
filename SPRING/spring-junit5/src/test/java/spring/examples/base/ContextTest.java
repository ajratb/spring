package spring.examples.base;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * From baeldung spring-5 ../jupiter/Spring5JUnit5IntegrationTest.java
 * @author ayrat
 *
 */
@SpringJUnitConfig(AppConfig.class)
public class ContextTest {

	@Autowired
	Student student;

	@Test
	public void givenAMethodName_whenInjecting_thenApplicationContextInjectedIntoMethod(
			ApplicationContext applicationContext) {

		assertNotNull(applicationContext, "ApplicationContext should have been injected by Spring");
		assertEquals(this.student, applicationContext.getBean("firstStudent", Student.class));
	}

}
