package spring.examples.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import spring.examples.base.beans.MyService;

@RunWith(SpringRunner.class)//a nice alias for SpringJUnit4ClassRunner
@ContextConfiguration(classes = AppConfig.class) // you can create TestAppConfig for using here
// also you can use xml configurations
public class MainAppTest {

	@Autowired
	MyService service;
	
	@Autowired
	ApplicationContext context;

	@Test
	public void testAppContext() {
//		fail("Not yet implemented");
//		service.printMsg();
		System.out.println("testAppContext");
		assertEquals(service.getMsg(), "hello");
		assertNotNull(service);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before class");
	}

//
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tear down after class");
	}

//
	@Before
	public void setUp() throws Exception {
		System.out.println("set up");
	}

//
	@After
	public void tearDown() throws Exception {
		service.printMsg();
		System.out.println("tear down");
	}

	//from gradle initiated project
	@Test
	public void testAppHasAGreeting() {
		MainApp classUnderTest = new MainApp();
		assertNotNull("app should have a greeting", classUnderTest.getGreeting());
	}

}
