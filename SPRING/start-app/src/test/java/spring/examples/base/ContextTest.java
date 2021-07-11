package spring.examples.base;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
//import org.assertj.core.api.Ass

import spring.examples.base.beans.MyService;

@RunWith(SpringRunner.class) // a nice alias for SpringJUnit4ClassRunner
@ContextConfiguration(classes = AppConfig.class) // you can create TestAppConfig for using here
public class ContextTest {

	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	MyService service;

	@Test
	public void givenAMethodName_whenInjecting_thenApplicationContextInjectedIntoMethod() {
		assertThat(applicationContext).isNotNull()
				.withFailMessage("ApplicationContext should have been injected by Spring");
		assertThat(service).isNotNull();
		assertThat(service).isEqualTo(applicationContext.getBean("myService", MyService.class));
//		assertNotNull(applicationContext, "ApplicationContext should have been injected by Spring");
	}

}
