package spring.examples.props;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(AppWithPropsConf.class)
@DisplayName("Context Tests")
// if comment @TestPropertySource then @PropertySource will be used (from AppWithPropsConf)
@TestPropertySource("classpath:app-test.properties") //'classpath:' is needed here !
public class ContextTest {

	@Autowired
	Message msg;

	@Value("${msg}") String val;

	@Test
	public void givenAMethodName_whenInjecting_thenApplicationContextInjectedIntoMethod(
			ApplicationContext applicationContext) {

		assertNotNull(applicationContext, "ApplicationContext should have been injected by Spring");
		assertEquals(this.msg, applicationContext.getBean("message", Message.class));
	}

	@Test
	public void testPropertyFile(){
		assertEquals("123", val);
	}

}
