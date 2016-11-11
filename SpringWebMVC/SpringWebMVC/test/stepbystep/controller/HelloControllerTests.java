package stepbystep.controller;


import org.springframework.web.servlet.ModelAndView; 
import stepbystep.controller.HelloController;
import junit.framework.TestCase;  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ayrat
 */
public class HelloControllerTests extends TestCase {
    public void testHandleRequestView() throws Exception{       

        HelloController controller = new HelloController();

        ModelAndView modelAndView = controller.handleRequest(null, null);         

        assertEquals("hello.jsp", modelAndView.getViewName());

    }
}
